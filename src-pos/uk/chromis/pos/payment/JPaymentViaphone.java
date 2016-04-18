package uk.chromis.pos.payment;

import com.viaphone.plugin.ResultListener;
import com.viaphone.plugin.ViaphoneApi;
import com.viaphone.plugin.model.CreateResp;
import com.viaphone.plugin.model.Product;
import com.viaphone.plugin.model.PurchaseStatus;
import com.viaphone.plugin.model.PurchaseStatusResp;
import com.viaphone.plugin.utils.Utils;
import uk.chromis.pos.customers.CustomerInfoExt;
import uk.chromis.pos.ticket.TicketInfo;
import uk.chromis.pos.ticket.TicketLineInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class JPaymentViaphone extends JPanel implements JPaymentInterface, ResultListener {

    private TicketInfo ticketInfo;
    private double m_dTotal;
    private JPaymentNotifier m_notifier;
    public ViaphoneApi viaphoneApi;

    public JPaymentViaphone(JPaymentNotifier notifier) {
        m_notifier = notifier;
        initComponents();
    }

    @Override
    public void activate(CustomerInfoExt customerext, double dTotal, String transID) {

        m_dTotal = dTotal;
        m_notifier.setStatus(true, true);
    }

    @Override
    public void activate(TicketInfo ticketInfo) {
        this.ticketInfo = ticketInfo;

    }

    @Override
    public PaymentInfo executePayment() {
        if (ticketInfo != null) {
            String clientId = "ae99f6c6-52f2-4433-85b5-e81c31f5805f";
            String clientSecret = "3cfe5805-da51-4359-9db1-fc1754ee449f";

            try {
                viaphoneApi = new ViaphoneApi(clientId, clientSecret, this);
                java.util.List<Product> items = new ArrayList<>();

                for (TicketLineInfo item : ticketInfo.getLines()) {
                    items.add(new Product(item.getProductName(), item.getProductCategoryID(),
                            "lg", (int) item.getMultiply(), item.getPrice()));
                }

                CreateResp resp = viaphoneApi.createPurchase(items);
                BufferedImage img = ImageIO.read(Utils.generateQr(resp.getToken()));
                ImageIcon icon = new ImageIcon(img);
                qr.setIcon(icon);
                System.out.println(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new PaymentInfoFree(m_dTotal);
    }

    @Override
    public Component getComponent() {
        return this;
    }


    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel1.setFont(new Font("Arial", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Pay using viaphone"); // NOI18N
        add(jLabel1);

        qr = new JLabel();
        qr.setFont(new Font("Arial", 1, 12)); // NOI18N
        qr.setHorizontalAlignment(SwingConstants.CENTER);
        qr.setText("QR here"); // NOI18N
        add(qr);


    }

    private JLabel jLabel1;
    private JLabel qr;

    @Override
    public void onAuthorized(double v) {
        qr.setText("Purchase authorized with discount amount: " + v);
        qr.setIcon(null);
    }

    @Override
    public void onConfirmed(PurchaseStatus purchaseStatus) {
        qr.setText("Purchase confirmed with status: " + purchaseStatus.name());
    }

    @Override
    public void onCancel(PurchaseStatus purchaseStatus) {
        qr.setText("Purchase canceled with status: " + purchaseStatus.name());
    }

    @Override
    public void onError(PurchaseStatusResp.Status status) {
        qr.setText("Ooops error: " + status.name());
    }
}
