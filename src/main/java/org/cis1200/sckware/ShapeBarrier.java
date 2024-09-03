package org.cis1200.sckware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

public abstract class ShapeBarrier extends JPanel {

    private int size;
    private double rotation;
    private double rotationSpeed;
    private int centerX;
    private int centerY;
    private boolean passed = false;
    private Timer moveTimer;
    private int missingEdges;
    private int shrinkSpeed;

    public ShapeBarrier(int spawnSize, int edges) {
        this.size = spawnSize;
        this.missingEdges = new Random().nextInt(2) + 1; // Randomly set 1 to 3 missing edges
        this.rotation = Math.toRadians(new Random().nextInt(edges) * (360.0 / edges));
        this.rotationSpeed = 0.05;
        this.centerX = 640;
        this.centerY = 360;
        this.passed = false;
        this.shrinkSpeed = 20;

        this.moveTimer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (size > 0) {
                    // Shrink rate
                    size -= shrinkSpeed;
                    rotation += rotationSpeed; // Rotate by 1 degree per iteration
                } else {
                    removePolygon();
                }
            }
        });
        moveTimer.start();
    }

    public abstract void drawBarrier(Graphics2D g2d);

    protected void removePolygon() {
        this.size = 0;
        this.rotation = 0;
        this.rotationSpeed = 0;
        this.passed = true;
        this.moveTimer.stop();
    }

    public int getShapeSize() {
        return size;
    }

    public double getRotation() {
        return rotation;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setShrinkSpeed(int s) {
        this.shrinkSpeed = s;
    }

    public void setRotationSpeed(double r) {
        this.rotationSpeed = r;
    }

    public void setRotation(double rs) {
        this.rotation = Math.toRadians(rs);
    }

    public int getMissingEdges() {
        return this.missingEdges;
    }

    public abstract int getEdges();

    public abstract void setColor(Color c);

}

