package org.cis1200.sckware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * GameCourt
 * <p>
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */

public class GameCourt extends JPanel {
    private boolean playing = false; // whether the game is running
    private final JLabel status; // Current status text, i.e. "Running..."
    public static final int COURT_WIDTH = 1280;
    public static final int COURT_HEIGHT = 720;
    public static final int INTERVAL = 10;
    private int level;
    private float points;
    private SpriteForm sprite;
    private ArrayList<ShapeBarrier> barriers;
    private Timer barrierTimer;
    private Timer deletionTimer;
    private Timer gameTimer;
    private Levels levelGrid;
    private EndScreen gameOver;

    private int factor = 7;

    private boolean insaneMode = false;

    public GameCourt(JLabel status) {
        //redraw everything in the JFrame this often
        gameTimer = new Timer(INTERVAL, e -> tick());
        gameTimer.start();
        setFocusable(true);

        //collections requirement
        barriers = new ArrayList<>();

        //set initial level
        //use of 2D array to spawn in barriers
        levelGrid = new Levels();
        levelGrid.setLevel(level);

        //draw barriers
        this.barrierTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (insaneMode) {
                    //refactor

                    ShapeBarrier shapeInsane = new Hexagon(900);
                    shapeInsane.setRotationSpeed(0.16);
                    barriers.add(shapeInsane);
                    shapeInsane.setColor(Color.RED);
                } else {

                    if (getLevel() == 1) {
                        ShapeBarrier shape1 = new Hexagon(900);
                        //the 2D is used here to create a variable rotation speed
                        int matrixSize = 4;

                        for (int i = 0; i < matrixSize; i++) {
                            for (int j = 0; j < matrixSize; j++) {
                                //pull an adjacent pair randomly and count this
                                //as the value to be used
                                shape1.setRotationSpeed(0.08);
                            }
                        }
                        barriers.add(shape1);
                    } else if (getLevel() == 2) {
                        ShapeBarrier shape2 = new Square(900);
                        shape2.setRotationSpeed(0.09);
                        barriers.add(shape2);
                    } else if (getLevel() == 3) {
                        ShapeBarrier shape3 = new Septagon(900);
                        shape3.setRotationSpeed(0.10);
                        barriers.add(shape3);
                    }
                }
            }
        });
        this.barrierTimer.start();

        addKeyListener(new KeyAdapter() {
            //update status to match
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    try {
                        sprite.rotateCounterClockwise = true;
                        // Start rotating counter-clockwise
                    } catch (NullPointerException ex) {
                        System.out.println("No sprite");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    try {
                        sprite.rotateClockwise = true;
                        // Start rotating clockwise
                    } catch (NullPointerException ex) {
                        System.out.println("No sprite");
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    try {
                        sprite.rotateCounterClockwise = false;
                        // Stop rotating counter-clockwise
                    } catch (NullPointerException ex) {
                        System.out.println("No sprite");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    try {
                        sprite.rotateClockwise = false;
                        // Stop rotating clockwise
                    } catch (NullPointerException ex) {
                        System.out.println("No sprite");
                    }
                }
            }
        });
        //put in scope of constructor
        this.status = status;
    }

    void tick() {
        //update the movement of sprite
        if (playing) {
            sprite.move();
            //constantly check for collision
            for (ShapeBarrier barrier : barriers) {
                if (sprite.hits(barrier)) {
                    end();
                    break;
                }
                if (barrier.getShapeSize() <= 5) {
                    points += 0.5;
                }
            }

            if (points < 1000 && points > 500) {
                sprite.setSpriteColor(Color.BLUE);
                status.setText("Level 2");
                setLevel(2);
                levelGrid.setLevel(2);
            } else if (points < 5000 && points > 3000) {
                status.setText("Level 3");
                setLevel(3);
                levelGrid.setLevel(3);
            } else if (points > 5000) {
                sprite.setSpriteColor(Color.RED);
                status.setText("Insane");
                insaneMode = true;
            }
            repaint();
        }

    }

    public void reset() {
        if (gameOver != null) {
            remove(gameOver);
        }

        points = 0;
        playing = true;
        sprite = new Sprite();
        barriers = new ArrayList<>();
        barrierTimer.restart();
        gameTimer.restart();
        status.setText("Level 0");
        setLevel(1);
        requestFocusInWindow();
    }

    //end screen function
    public void end() {
        //make sure this is appropriate clearly everything
        insaneMode = false;
        playing = false;
        sprite = null;
        barriers = null;
        barrierTimer.stop();
        gameTimer.stop();
        status.setText("Level: ");
        //output the end screen tab
        gameOver = new EndScreen(points);
        add(gameOver);
        requestFocusInWindow();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playing) {
            sprite.draw((Graphics2D) g);

            if (sprite == null) {
                throw new NullPointerException("No Sprite buddy");
            }

            if (barriers != null) {
                for (ShapeBarrier barrier : barriers) {
                    barrier.drawBarrier((Graphics2D) g);
                }
            }
        }

    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int l) {
        this.level = l;
    }

    public void setPlaying(boolean p) {
        this.playing = p;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
