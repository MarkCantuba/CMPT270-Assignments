package view;

import gameResults.HighScores;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

/**
 * A panel to display the user's score, and a text field for the user to enter their id so that
 * their score can be saved as a high score.
 */
public class SaveScorePanel extends ViewPanel {
    public static final long serialVersionUID = 1;

    /**
     * Create a panel to display the user's score, a text field for the user to enter their id.
     * 
     * @param width the width of the panel
     * @param height the height of the panel
     * @param score the user's score
     * @param level the user's level
     * @param listener the class listening for the event that signals the Enter key was pressed
     */
    public SaveScorePanel(int width, int height, int score, int level, ActionListener listener) {
        setSize(width, height);
        setBackground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 16);
        Color charColor = Color.GREEN;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(Box.createRigidArea(new Dimension(0, height / 5)));

        /* Place a header with the welcome message. */
        JLabel header =
                new JLabel("You are in the top " + HighScores.MAX_NUMBER_SCORES_SAVED + "!");
        header.setFont(new Font("Arial", Font.BOLD, 48));
        header.setForeground(charColor);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(header);

        add(Box.createRigidArea(new Dimension(0, height / 5)));

        /* Use a fixed-size grid to organize the fields for the user. */
        JPanel promptPanel = new JPanel();
        promptPanel.setBackground(Color.BLACK);
        promptPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        promptPanel.setMaximumSize(new Dimension(250, 200));
        add(promptPanel);
        promptPanel.setLayout(new GridLayout(5, 2));

        /* The labels for the level and score. */
        JLabel levelLabel = new JLabel("Level");
        levelLabel.setFont(font);
        levelLabel.setForeground(charColor);
        promptPanel.add(levelLabel);

        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(font);
        scoreLabel.setForeground(charColor);
        promptPanel.add(scoreLabel);

        /* The user's level and score. */
        levelLabel = new JLabel(level + "");
        levelLabel.setFont(font);
        levelLabel.setForeground(charColor);
        promptPanel.add(levelLabel);

        scoreLabel = new JLabel(score + "");
        scoreLabel.setFont(font);
        scoreLabel.setForeground(charColor);
        promptPanel.add(scoreLabel);

        /* A blank row to provide space between the data and the text field. */
        promptPanel.add(new JLabel(""));
        promptPanel.add(new JLabel(""));

        /* The prompt and text field for use's id. */
        JLabel idLabel = new JLabel("Enter 5 char id ");
        idLabel.setFont(font);
        idLabel.setForeground(charColor);
        promptPanel.add(idLabel);

        JTextField idField = new JTextField(5);
        focusComponent = idField;
        idField.setActionCommand("savescore");
        idField.addActionListener(listener);
        JPanel idPanel = new JPanel();
        idPanel.setBackground(Color.BLACK);
        idPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(idField);
        promptPanel.add(idPanel);

        /* Instruction for acceptance of the id. */
        JLabel enterLabel = new JLabel("Press Enter");
        enterLabel.setFont(font);
        enterLabel.setForeground(charColor);
        promptPanel.add(enterLabel);
    }
}
