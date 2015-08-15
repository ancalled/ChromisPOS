//    Chromis POS  - The New Face of Open Source POS
//    Copyright (c) 2015 
//    http://www.chromis.co.uk
//
//    This file is part of Chromis POS
//
//     Chromis POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Chromis POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>.

package uk.chromis.pos.catalog;

import uk.chromis.beans.JFlowPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author adrianromero
 */
public class JCatalogTab extends javax.swing.JPanel {
    
    private JFlowPanel flowpanel;
    
    /** Creates new form JCategoryProducts */
    public JCatalogTab() {
        initComponents();

        flowpanel = new JFlowPanel();
        JScrollPane scroll = new JScrollPane(flowpanel);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));
        
        add(scroll, BorderLayout.CENTER);
    }
    
    @Override
    public void setEnabled(boolean value) {
        flowpanel.setEnabled(value);
        super.setEnabled(value);
    }
// ADDED JDL 09.04.13 TEXTTIP FUNCTION

    /**
     *
     * @param ico
     * @param al
     * @param textTip
     */
    public void addButton(Icon ico, ActionListener al, String textTip, String col) {
        JButton btn = new JButton();
        btn.applyComponentOrientation(getComponentOrientation());
        btn.setIcon(ico);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        if (textTip != null){
        btn.setToolTipText(textTip);
        }
        btn.setRequestFocusEnabled(false);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setMargin(new Insets(0, 0, 0, 0));
        if (!"".equals(col)) {
            btn.setBorder(BorderFactory.createLineBorder(new Color((int) Integer.decode(col)), 3));
        }
        btn.addActionListener(al);
        flowpanel.add(btn);        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
