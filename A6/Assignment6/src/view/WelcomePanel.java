package view;

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;

/**
 * A panel to welcome a user and give the choice between playing a game and viewing the high scores
 * from past plays of the game.
 */
public class WelcomePanel extends ViewPanel {
    public static final long serialVersionUID = 1;

    static final int FONT_SIZE = 72;
    static final int BUTTON_SPACER_SIZE = 30;
    static final int BUTTON_WIDTH = 140;
    static final int BUTTON_HEIGHT = 30;

    /**
     * Create the welcome panel, with the game name, a button to start the game, and a button to
     * request past high scores be shown.
     * 
     * @param width the width of the panel
     * @param height the height of the panel
     * @param listener the class listening for the event that signals a button was pressed
     */
    public WelcomePanel(int width, int height, ActionListener listener) {
        setSize(width, height);
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(Box.createRigidArea(new Dimension(0, height / 5)));

        JLabel label = new JLabel("Space Invaders");
        label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        label.setForeground(Color.GREEN);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        add(Box.createRigidArea(new Dimension(0, height / 5)));

        JButton newGameButton = new JButton("New Game");
        newGameButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.GREEN);
        newGameButton.setActionCommand("newgame");
        newGameButton.addActionListener(listener);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(newGameButton);

        add(Box.createRigidArea(new Dimension(0, BUTTON_SPACER_SIZE)));

        JButton highScoreButton = new JButton("High Scores");
        highScoreButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        highScoreButton.setBackground(Color.BLACK);
        highScoreButton.setForeground(Color.GREEN);
        highScoreButton.setActionCommand("highscores");
        highScoreButton.addActionListener(listener);
        highScoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(highScoreButton);

        add(Box.createRigidArea(new Dimension(0, BUTTON_SPACER_SIZE)));

        JButton quitButton = new JButton("Quit");
        quitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.GREEN);
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(listener);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(quitButton);
    }
}
