package org.cis1200.sckware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class SpriteForm extends JPanel implements ActionListener {
    protected static final int SQUARE_SIZE = 10;
    protected static final int ORBIT_RADIUS = 60;
    private int spriteX;
    private int spriteY;
    private int centerX = 640;
    private int centerY = 360;
    public boolean rotateClockwise = false;
    public boolean rotateCounterClockwise = false;
    private int angle = 0;
    private Color color = Color.WHITE;
    private boolean active = false;

    public SpriteForm() {
        Timer timer = new Timer(16, this);
        timer.start();
    }

    public void move() {
        if (rotateClockwise) {
            angle += getRotationSpeed();
        } else if (rotateCounterClockwise) {
            angle -= getRotationSpeed();
        }
    }

    public void draw(Graphics2D g2d) {
        spriteX = (int) (centerX + getOrbitRadius() * Math.cos(Math.toRadians(angle)));
        spriteY = (int) (centerY + getOrbitRadius() * Math.sin(Math.toRadians(angle)));
        g2d.setColor(color);
        g2d.fillRect(spriteX - SQUARE_SIZE / 2,
                spriteY - SQUARE_SIZE / 2,
                SQUARE_SIZE, SQUARE_SIZE);
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean b) {
        this.active = b;
    }

    public abstract int getOrbitRadius();

    public abstract int getRotationSpeed();

    public boolean getRotationCCW() {
        return this.rotateCounterClockwise;
    }

    ;

    public boolean getRotationCW() {
        return this.rotateCounterClockwise;
    }

    ;

    public void setRotationCCW(boolean b) {
        this.rotateCounterClockwise = b;
    }

    ;

    public void setRotationCW(boolean b) {
        this.rotateCounterClockwise = b;
    }

    ;

    public abstract boolean hits(ShapeBarrier barrier);

    public abstract void actionPerformed(ActionEvent e);

    public void setSpriteColor(Color c) {
        this.color = c;
    }


    public void setSpriteX(int x) {
        this.spriteX = x;
    }


    public void setSpriteY(int y) {
        this.spriteY = y;
    }


    public int getSpriteX() {
        return this.spriteX;
    }


    public int getSpriteY() {
        return this.spriteY;
    }


    public int getCenterX() {
        return this.centerX;
    }

    public int getCenterY() {
        return this.centerY;
    }

    public int getAngle() {
        return this.angle;
    }

    public void setAngle(int a) {
        this.angle = a;
    }

    public void getSpriteLocation() {
        //        System.out.println("Other X: " + squareX + "Other Y: " + squareY);
    }
}

