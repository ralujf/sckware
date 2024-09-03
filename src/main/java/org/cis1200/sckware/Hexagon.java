package org.cis1200.sckware;

import java.awt.*;

public class Hexagon extends ShapeBarrier {
    private static final int EDGES = 6;

    private Color color = Color.GRAY;

    public Hexagon(int spawnSize) {
        super(spawnSize, EDGES);
    }

    public void drawBarrier(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(13));

        for (int i = 0; i < EDGES; i++) {
            if ((i >= EDGES - getMissingEdges()) || (i < getMissingEdges())) {
                continue; // Skip drawing the missing edges
            }
            double angle1 = 2.0 * Math.PI / EDGES *
                    i + getRotation();
            double angle2 = 2.0 * Math.PI / EDGES *
                    ((i + 1) % EDGES) + getRotation();

            int x1 = (int) (getCenterX() + getShapeSize() * Math.cos(angle1));
            int y1 = (int) (getCenterY() + getShapeSize() * Math.sin(angle1));
            int x2 = (int) (getCenterX() + getShapeSize() * Math.cos(angle2));
            int y2 = (int) (getCenterY() + getShapeSize() * Math.sin(angle2));

            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    public int getEdges() {
        return EDGES;
    }

    public void setColor(Color c) {
        this.color = c;
    }
}