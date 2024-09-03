package org.cis1200.sckware;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;

public class Sprite extends SpriteForm {

    private int rotationSpeed = 5;
    private int angle = 0;

    public Sprite() {
        super();
    }

    @Override
    public int getOrbitRadius() {
        return ORBIT_RADIUS;
    }

    @Override
    public int getRotationSpeed() {
        return this.rotationSpeed;
    }

    public boolean hits(ShapeBarrier b) {
        for (int i = 0; i < b.getEdges(); i++) {
            if ((i >= b.getEdges() - b.getMissingEdges()) || (i < b.getMissingEdges())) {
                continue; // Skip checking missing edges
            }
            double angle1 = b.getRotation() +
                    (2.0 * Math.PI / b.getEdges() * i);
            double angle2 = b.getRotation() +
                    (2.0 * Math.PI / b.getEdges() * ((i + 1) % b.getEdges()));

            double x1 = (int) (b.getCenterX() + b.getShapeSize() * Math.cos(angle1));
            double y1 = (int) (b.getCenterY() + b.getShapeSize() * Math.sin(angle1));
            double x2 = (b.getCenterX() + b.getShapeSize() * Math.cos(angle2));
            double y2 = (b.getCenterY() + b.getShapeSize() * Math.sin(angle2));

            Line2D line = new Line2D.Double(x1, y1, x2, y2);
            //coordinates of all lines
            //System.out.println("Line Coordinates" + x1 + " " + x2
            // + " " + y1 + " " + y2);
            if (line.intersects(getSpriteX() - (float) SQUARE_SIZE / 1.5,
                    getSpriteY() - (float) SQUARE_SIZE / 1.5,
                    SQUARE_SIZE, SQUARE_SIZE)) {
                this.setSpriteColor(Color.DARK_GRAY);
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        int a = getAngle();
        if (getRotationCW()) {
            // Rotate clockwise
            angle += rotationSpeed;
            //setAngle((a += 5));
            repaint();
        } else if (getRotationCCW()) {
            // Rotate counter-clockwise
            angle -= rotationSpeed;
            // setAngle((a -= 5));
            repaint();
        }
    }

    public void getSpriteLocation() {
        //        System.out.println("Other X: " + squareX + "Other Y: " + squareY);
    }
}
