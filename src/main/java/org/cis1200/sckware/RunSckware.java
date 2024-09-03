package org.cis1200.sckware;

// imports necessary libraries for Java swing

import javax.swing.*;
import java.awt.*;
import java.net.URL;


/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunSckware implements Runnable {
    private int windowH = 720;
    private int windowW = 1280;

    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for
        // local variables.

        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        JFrame frame = new JFrame("Sckware");
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setSize(windowW, windowH);
        frame.setLocationRelativeTo(null);

        URL imageURL = RunSckware.class.getClassLoader().getResource("SckwareTitle.png");
        if (imageURL == null) {
            throw new NullPointerException("The image did not load correctly");
        }

        ImageIcon icon = new ImageIcon(imageURL);
        JLabel title = new JLabel(icon);
        title.setPreferredSize(new Dimension(1280, 720));

        // status panel
        final JPanel status_panel = new JPanel();
        status_panel.setBackground(Color.DARK_GRAY);
        frame.add(status_panel, BorderLayout.SOUTH);

        final JLabel status = new JLabel("Level: ");
        status.setFont(new Font("Arial", Font.BOLD, 40));
        status_panel.add(status);

        // main playing area
        final GameCourt court = new GameCourt(status);
        court.setBackground(Color.DARK_GRAY);
        frame.add(court, BorderLayout.CENTER);

        // reset/Start button
        final JPanel control_panel = new JPanel();
        control_panel.setBackground(Color.DARK_GRAY);

        //overlay title screen
        frame.add(title, BorderLayout.CENTER);

        //build navigation bar at top of screen
        JPanel navigationBar = new JPanel();

        //scoreboard pop up
        JPanel scores = new JPanel();
        scores.setLayout(new BorderLayout());
        Leaderboard leaderboardUI = new Leaderboard();
        scores.add(leaderboardUI, BorderLayout.CENTER);
        scores.setVisible(false);


        JButton closeScores = new JButton("Close");
        closeScores.addActionListener(e -> {
            scores.setVisible(false);
            frame.remove(scores);
            frame.revalidate();
            frame.repaint();
        });

        scores.add(closeScores, BorderLayout.NORTH);
        frame.add(scores, BorderLayout.WEST);

        JButton leaders = new JButton("Leaderboard");
        leaders.addActionListener(e -> {
            // new data
            leaderboardUI.updateUILeaderboard();
            frame.add(scores, BorderLayout.WEST);
            frame.revalidate();
            frame.repaint();
            scores.setVisible(true);
        });

        //---------------------------------------------

        JButton infoButton = new JButton("Instructions");
        infoButton.addActionListener(e -> {
            JDialog instructions = new JDialog(frame, "Instructions", true);

            JLabel infoHeader = new JLabel("How to Play");
            JPanel headerPanel = new JPanel(new FlowLayout());
            headerPanel.add(infoHeader);
            JLabel submissionInfo = new JLabel(
                    ("This game was implemented by " +
                    "first researching ways " +
                    "in which I could draw advanced shapes. " +
                    "That brought me to the idea of using " +
                    "Graphics2D so I could change the properties easily."));
            submissionInfo.setBounds(0,0, 400, 100);

            JLabel rules = new JLabel("Make sure you are in full screen!");
            JLabel info = new JLabel("Avoid the barriers, Or Phase through them! use the " +
                    "<Left> and <Right> arrow keys");
            JPanel centerPanel = new JPanel(new FlowLayout());

            centerPanel.add(submissionInfo);
            centerPanel.add(info);

            instructions.setLayout(new BorderLayout());
            instructions.add(rules, BorderLayout.SOUTH);
            instructions.add(centerPanel, BorderLayout.CENTER);
            instructions.add(headerPanel, BorderLayout.NORTH);
            instructions.setPreferredSize(new Dimension(1100, 200));
            instructions.setLocationRelativeTo(null);
            instructions.pack();
            instructions.setVisible(true);
            instructions.setModal(false);
        });

        navigationBar.setBackground(Color.DARK_GRAY);
        navigationBar.setLayout(new FlowLayout());
        navigationBar.add(control_panel);
        navigationBar.add(leaders);
        navigationBar.add(infoButton);

        frame.add(navigationBar, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.

        final JButton reset = new JButton("Start Game");
        reset.addActionListener(e -> {
            court.reset();
            frame.getContentPane().remove(title);
            frame.add(court, BorderLayout.CENTER);
        });
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}