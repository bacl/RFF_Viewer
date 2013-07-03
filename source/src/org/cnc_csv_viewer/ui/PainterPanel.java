/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnc_csv_viewer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;
import org.cnc_csv_viewer.CSVViewer;
import org.cnc_csv_viewer.data.Beam;

/**
 *
 * @author Bruno
 */
public class PainterPanel extends javax.swing.JPanel {

    public static int barraXoffset = 120;
    public static int barraXXoffset = 50;
    public static int barraYoffset = 0;
    public static int barraYYoffset = 150;
    private BufferedImage image;
    private Point p1;
    private Point p2;
    private ArrayList<Beam> list;
    private BeamPainter bpainter = new BeamPainter();
    private JLabel txt_label;
    private int bt = 0; 

    /**
     * Creates new form PainterPanel
     */
    public PainterPanel() {
        image = null; 
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font font = getFont();
        g2.setColor(BeamPainter.painterBackground);
        g2.setFont(font.deriveFont(12));
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        /////////////////
        if (getImage() != null) {
            g2.drawImage(getImage(), null, 0, 0);
        }  

        //////////////////////
        g2.setColor(Color.red);
        if (getP1() != null) {
            g2.drawOval(getP1().x - 1, getP1().y - 1, 2, 2);
        }
        if (getP2() != null) {
            g2.drawOval(getP2().x - 1, getP2().y - 1, 2, 2);
        }

        if (getP1() != null && getP2() != null) {
            g2.drawLine(getP1().x, getP1().y, getP2().x, getP2().y);
            g2.setColor(Color.YELLOW);
            int height = Math.abs(getP1().y - getP2().y);
            int width = Math.abs(getP1().x - getP2().x);
            double length = Math.sqrt(width * width + height * height);
            String str = "" + (int) length + "mm";
            getTxt_label().setText(str);

            g2.drawString(str, getP2().x - 40, getP2().y - 4);
        }


    }

    public void drawBeams() {
        Dimension preferredSize = this.getPreferredSize();

        image = new BufferedImage((int) preferredSize.getWidth(), (int) preferredSize.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        g2.setColor(BeamPainter.painterBackground);
        g2.setFont(getFont().deriveFont(12));
        g2.fillRect(0, 0, (int) preferredSize.getWidth(), (int) preferredSize.getHeight());


        if (getList() != null) {
            int i = 0;
            int y = 0;

            for (Beam barra : getList()) {
                i++;
                y = i * 50;
                bpainter.drawBarra(g2, barra, barraXoffset, y);

                ///////////////////////////////////////////////////////////
                //  beam label
                g2.setColor(Color.red);
                int x = 90 - getFontMetrics(getFont()).stringWidth(barra.getLabel());
                y = y - 6;
                g2.drawString(barra.getLabel(), x, y);
                String txt = "" + barra.getLength() + " mm";
                x = 90 - getFontMetrics(getFont()).stringWidth(txt);
                g2.drawString(txt, x, y + 15);
            }


            {
                int ss = (int) ((int) preferredSize.getHeight()) - 16;
                g2.setColor(Color.WHITE);
                g2.drawString("Label:", 10, ss - 14);

                g2.setColor(Color.BLUE);
                g2.drawString("WEB", 350, ss);

                g2.setColor(Color.RED);
                g2.drawString("SERVICE_HOLES", 250, ss);

                g2.setColor(Color.ORANGE);
                g2.drawString("END_TRUSS", 180, ss);

                g2.setColor(Color.MAGENTA);
                g2.drawString("LIP_CUT", 120, ss);

                g2.setColor(Color.CYAN);
                g2.drawString("NOTCH", 70, ss);

                g2.setColor(Color.green);
                g2.drawString("SWAGE", 10, ss);
            }
        }


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed


        if (evt.getButton() == MouseEvent.BUTTON1) {
            setP1(evt.getPoint());
            setBt(MouseEvent.BUTTON1);
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            setP2(evt.getPoint());
            setBt(MouseEvent.BUTTON3);
        } else {
            setP1(null);
            setP2(null);
        }
        updateUI();

    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (getBt() == MouseEvent.BUTTON1) {
            if (getP2() != null && Math.abs(getP2().y - evt.getPoint().y) < 11) {
                setP1(new Point(evt.getPoint().x, getP2().y));
            } else {
                setP1(evt.getPoint());
            }

        } else if (getBt() == MouseEvent.BUTTON3) {
            if (getP1() != null && Math.abs(getP1().y - evt.getPoint().y) < 11) {
                setP2(new Point(evt.getPoint().x, getP1().y));
            } else {
                setP2(evt.getPoint());
            }
        }
        updateUI();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
    }//GEN-LAST:event_formMouseMoved

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        setBt(-1);
    }//GEN-LAST:event_formMouseReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * @return the p1
     */
    public Point getP1() {
        return p1;
    }

    /**
     * @param p1 the p1 to set
     */
    public void setP1(Point p1) {
        this.p1 = p1;
    }

    /**
     * @return the p2
     */
    public Point getP2() {
        return p2;
    }

    /**
     * @param p2 the p2 to set
     */
    public void setP2(Point p2) {
        this.p2 = p2;
    }

    /**
     * @return the list
     */
    public ArrayList<Beam> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList<Beam> list) {
        this.list = list;
    }

    /**
     * @return the bpainter
     */
    public BeamPainter getBpainter() {
        return bpainter;
    }

    /**
     * @param bpainter the bpainter to set
     */
    public void setBpainter(BeamPainter bpainter) {
        this.bpainter = bpainter;
    }

    /**
     * @return the txt_label
     */
    public JLabel getTxt_label() {
        return txt_label;
    }

    /**
     * @param txt_label the txt_label to set
     */
    public void setTxt_label(JLabel txt_label) {
        this.txt_label = txt_label;
    }

    /**
     * @return the bt
     */
    public int getBt() {
        return bt;
    }

    /**
     * @param bt the bt to set
     */
    public void setBt(int bt) {
        this.bt = bt;
    }
}