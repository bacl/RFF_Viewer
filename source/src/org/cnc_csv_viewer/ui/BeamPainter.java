/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnc_csv_viewer.ui;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import org.cnc_csv_viewer.data.Beam;

/**
 *
 * @author Bruno
 */
public class BeamPainter implements Serializable {

    public static Color painterBackground = Color.black;
    public static Color painterForeground = Color.white;
    ///////////////
    public static int beamHeight = 40;
    public static int screwSize = 6;
    ///////////////
    private int cutWidth = 48;
    private int cutHeight = 40;
    private int serviceHolesSize = 35;

    public void drawBarra(Graphics2D g2d, Beam b, int x, int y) {

        g2d.setColor(painterForeground);

        //////////////////////////
        // beam
        g2d.draw(new Rectangle2D.Double(x, y - beamHeight / 2, b.getLength(), beamHeight));

        //////////////////////////
        // buraco do parafuso      
        for (Integer c : b.getDIMPLE()) {
            drawBuracoPrafuso(g2d, x + c, y, screwSize);
        }


        g2d.setColor(Color.green);
        for (Integer in : b.getSWAGE()) {
            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(makeComposite(0.3f));
            g2d.fillRect(x - (cutWidth / 2) + in, y - (cutHeight / 2), cutWidth, cutHeight);
            g2d.setComposite(originalComposite);
        }

        {
            g2d.setColor(Color.MAGENTA);
            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(makeComposite(0.3f));
            for (Integer in : b.getLIP_CUT()) {
                g2d.fillRect(x - (cutWidth / 2) + in, y - (cutHeight / 2), cutWidth, cutHeight);
            }
            g2d.setComposite(originalComposite);
        }
        {
            g2d.setColor(Color.CYAN);
            g2d.setStroke(new BasicStroke(2));
            for (Integer in : b.getNOTCH()) {
                g2d.drawRect(x - (cutWidth / 2) + in, y - (cutHeight / 2), cutWidth, cutHeight);
            }
            g2d.setStroke(new BasicStroke(1));
        }
        {
            g2d.setColor(Color.ORANGE);
            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(makeComposite(0.8f));

            Polygon polyEND_TRUSS = new Polygon();
            for (Integer in : b.getEND_TRUSS()) {
                int xx = x - 10 + in;
                int yy = y - 20;
                polyEND_TRUSS.reset();
                polyEND_TRUSS.addPoint(xx + 0, yy + 0);
                polyEND_TRUSS.addPoint(xx + 20, yy + 0);
                polyEND_TRUSS.addPoint(xx + 10, yy + 10);
                polyEND_TRUSS.addPoint(xx + 10, yy + 30);
                polyEND_TRUSS.addPoint(xx + 20, yy + 40);
                polyEND_TRUSS.addPoint(xx + 0, yy + 40);
                polyEND_TRUSS.addPoint(xx + 10, yy + 30);
                polyEND_TRUSS.addPoint(xx + 10, yy + 10);
                g2d.fillPolygon(polyEND_TRUSS);
            }
            g2d.setComposite(originalComposite);
        }
        
        g2d.setColor(Color.RED);
        Composite originalComposite = g2d.getComposite();
        g2d.setComposite(makeComposite(0.4f));
        if (b.getServiceHoles() != null) {
            for (Integer in : b.getServiceHoles()) {
                g2d.fillOval(x - serviceHolesSize / 2 + in, y - serviceHolesSize / 2, serviceHolesSize, serviceHolesSize);
            }
        }
        g2d.setComposite(originalComposite);

        g2d.setColor(Color.BLUE);
        originalComposite = g2d.getComposite();
        g2d.setComposite(makeComposite(0.6f));
        if (b.getWebHoles() != null) {
            for (Integer in : b.getWebHoles()) {
                g2d.fillOval(x - serviceHolesSize / 2 + in, y - serviceHolesSize / 2, serviceHolesSize, serviceHolesSize);
            }
        }
        g2d.setComposite(originalComposite);

    }

    public void drawBuracoPrafuso(Graphics2D g2d, int pAx, int pAy, int largura) { 
        int yx = largura / 2;
        g2d.drawOval(pAx - yx, pAy - yx, largura, largura);
    }

    private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }
}
