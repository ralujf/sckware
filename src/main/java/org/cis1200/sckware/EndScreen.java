package org.cis1200.sckware;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class EndScreen extends JLayeredPane {

    public EndScreen(float points) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 200));

        JTextField username = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String userInput = username.getText();
            //submit the credentials to the file
            System.out.println(userInput + String.valueOf(points));
            addPlayer(userInput, (int) points);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        JLabel title = new JLabel("Game Over");
        JLabel score = new JLabel("Score: " + points);
        score.setFont(new Font("Arial", Font.BOLD, 30));
        JLabel user = new JLabel("Username: ");
        user.setFont(new Font("Arial", Font.BOLD, 30));

        panel.add(user);
        panel.add(username);

        titlePanel.add(title);

        add(titlePanel, BorderLayout.NORTH);
        panel.add(score, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }

    public void addPlayer(String playerName, int score) {
        System.out.println("add player:");
        Leaderboard leaderScores = new Leaderboard();

        HashMap<String, Integer> scoreboard = leaderScores.getLeaderScores();
        scoreboard.put(playerName, score);

        leaderScores.setLeaderScores(scoreboard);
    }

}
