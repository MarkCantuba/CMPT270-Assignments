package view;

import gameResults.GameResult;
import gameResults.HighScores;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel to display the high scores from past plays of the game.
 */
public class HighScoresPanel extends ViewPanel {
    static final int FONT_SIZE = 72;

    public static final long serialVersionUID = 1;

    /**
     * Create a high scores panel, with the high scores, and a button to return to the welcome
     * panel.
     * 
     * @param width the width of the panel
     * @param height the height of the panel
     * @param listener the class listening for the event that signals the button was pressed
     */
    public HighScoresPanel(int width, int height, ActionListener listener) {
        setSize(width, height);
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(Box.createRigidArea(new Dimension(0, height / 10)));

        JLabel label = new JLabel("High Scores");
        label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        label.setForeground(Color.GREEN);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        add(Box.createRigidArea(new Dimension(0, height / 10)));

        JPanel scoresPanel = new JPanel();
        scoresPanel.setBackground(Color.BLACK);
        scoresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scoresPanel);

        addScores(scoresPanel);

        add(Box.createRigidArea(new Dimension(0, height / 20)));

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.GREEN);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(backButton);
        backButton.setActionCommand("welcome");
        backButton.addActionListener(listener);
    }

    /**
     * Add the headers and the scores to the specified panel.
     * 
     * @param panel the panel into which the items are to be added
     */
    private void addScores(JPanel panel) {
        List<GameResult> scores = HighScores.getInstance().getHighScores();
        panel.setMaximumSize(new Dimension(175, 30 * (1 + scores.size())));
        panel.setLayout(new GridLayout(0, 3));

        Font font = new Font("Arial", Font.BOLD, 16);
        Color color = Color.GREEN;

        JLabel id = new JLabel("Id");
        id.setFont(font);
        id.setForeground(color);
        panel.add(id);

        JLabel level = new JLabel("Level");
        level.setFont(font);
        level.setForeground(color);
        panel.add(level);

        JLabel score = new JLabel("Score   ");
        score.setFont(font);
        score.setForeground(color);
        panel.add(score);

        for (GameResult entry : scores) {
            id = new JLabel(entry.getId());
            id.setFont(font);
            id.setForeground(color);
            panel.add(id);


            level = new JLabel(entry.getLevel() + "");
            level.setFont(font);
            level.setForeground(color);
            panel.add(level);

            score = new JLabel(entry.getScore() + "");
            score.setFont(font);
            score.setForeground(color);
            panel.add(score);
        }
    }

}
