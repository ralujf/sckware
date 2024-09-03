package org.cis1200.sckware;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Leaderboard extends JPanel {
    private static HashMap<String, Integer> leaderScores;

    public Leaderboard() {
        leaderScores = new HashMap<>();
        this.setLayout(new GridLayout(10, 2));
    }

    public HashMap<String, Integer> getLeaderScores() {
        return leaderScores;
    }

    public void setLeaderScores(HashMap<String, Integer> map) {
        leaderScores = map;
    }

    public void updateUILeaderboard() {
        removeAll();

        Set<String> names = leaderScores.keySet();
        for (HashMap.Entry<String, Integer> entry : leaderScores.entrySet()) {
            JLabel nameLabel = new JLabel(entry.getKey());
            JLabel scoreLabel = new JLabel(Integer.toString(entry.getValue()));
            System.out.print(nameLabel + " " + scoreLabel);
            add(nameLabel);
            add(scoreLabel);
        }
        revalidate();
        repaint();
    }
}
