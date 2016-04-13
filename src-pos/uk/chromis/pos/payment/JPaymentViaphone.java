package uk.chromis.pos.payment;

import com.viaphone.plugin.ViaphoneApi;
import com.viaphone.plugin.model.CreateResp;
import com.viaphone.plugin.model.Product;
import com.viaphone.plugin.utils.Utils;
import uk.chromis.pos.customers.CustomerInfoExt;
import uk.chromis.pos.ticket.TicketInfo;
import uk.chromis.pos.ticket.TicketLineInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class JPaymentViaphone extends JPanel implements JPaymentInterface {

    private TicketInfo ticketInfo;
    private double m_dTotal;
    private JPaymentNotifier m_notifier;

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
                ViaphoneApi api = new ViaphoneApi(clientId, clientSecret);
                java.util.List<Product> items = new ArrayList<>();

                for (TicketLineInfo item : ticketInfo.getLines()) {
                    items.add(new Product(item.getProductName(), item.getProductCategoryID(),
                            "lg", (int) item.getMultiply(), item.getPrice()));
                }

                CreateResp resp = api.createPayment(items);
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
        qr.setFont(new Font("Arial", 1, 36)); // NOI18N
        qr.setHorizontalAlignment(SwingConstants.CENTER);
        qr.setText("QR here"); // NOI18N
        add(qr);


    }

    private JLabel jLabel1;
    private JLabel qr;
}
