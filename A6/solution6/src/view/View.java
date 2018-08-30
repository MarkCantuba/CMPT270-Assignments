package view;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

import model.GameInfoProvider;

/**
 * The view of the model-view controller architecture. This view is a frame used to display various
 * views of the game and its results.
 */
public class View extends JFrame {
    public static final int TITLE_BAR_HEIGHT = 32;
    public static final int BORDER_WIDTH = 6;

    /*
     * Dependent upon the status of the game, different views are displayed in the frame to show
     * what is going on in the game. When the game is being shown, the game and score panels observe
     * changes in the game, and use a game provider to access information from the game.
     */

    /**
     * Initialize the frame for the various views to be inserted into it, where width and height are
     * the dimensions for the game.
     */
    public View(int width, int height) {
        setTitle("Space Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Width and height passed in are those for the game panel, so add enough height for the
         * title bar and the information panel, and enough width for the border.
         */
        setSize(width + BORDER_WIDTH, height + GameInfoPanel.HEIGHT + TITLE_BAR_HEIGHT);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
    }

    /**
     * Set the frame visible showing the panel passed in, and set the focus in the Component
     * obtained from the panel.
     * 
     * @param panel the panel to be shown in the view
     */
    public void displayPanel(ViewPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().validate();
        Component focusComponent = panel.getFocusComponent();
        if (focusComponent != null)
            focusComponent.requestFocusInWindow();
        setVisible(true);
    }

    /**
     * Construct the welcome view, and make it visible.
     * 
     * @param actionListener the listener for a button press
     */
    public void showWelcomeView(ActionListener actionListener) {
        ViewPanel panel = new WelcomePanel(getWidth(), getHeight(), actionListener);
        displayPanel(panel);
    }

    /**
     * Construct a view showing high scores from past plays of the game, and make the view visible.
     * 
     * @param actionListener the listener for a button press
     */
    public void showHighScoresView(ActionListener actionListener) {
        ViewPanel panel = new HighScoresPanel(getWidth(), getHeight(), actionListener);
        displayPanel(panel);
    }

    /**
     * Construct the view to allow the user to save his/her score, and make the view visible.
     * 
     * @param gameInfoProvider the access to the level and score in the game
     * @param actionListener the listener for the key stroke Enter in the text field
     */
    public void showSaveScoreView(GameInfoProvider gameInfoProvider, ActionListener actionListener) {
        ViewPanel panel =
                new SaveScorePanel(getWidth(), getHeight(), gameInfoProvider.getPlayerScore(),
                        gameInfoProvider.getLevel(), actionListener);
        displayPanel(panel);
    }

    /**
     * Construct and make visible the view of the game where the panel with the game status is at
     * the top, and a panel to the display of the game is in the middle. The two panels get the
     * needed game information via the GameInfoProvider, and the game panel passes key events to the
     * keyListener.
     * 
     * @param gameInfoProvider the access to the information about the game
     * @param keyListener the listener for key strokes in a panel
     */
    public void showNewGameView(GameInfoProvider gameInfoProvider, KeyListener keyListener) {
        ViewPanel overallPanel = new ViewPanel();
        /* A ViewPanel is used so that the component to have the focus can be set. */
        overallPanel.setLayout(new BorderLayout());

        GameInfoPanel infoPanel = new GameInfoPanel(gameInfoProvider);
        infoPanel.setPreferredSize(new Dimension(getWidth(), GameInfoPanel.HEIGHT));
        overallPanel.add(infoPanel, BorderLayout.PAGE_START);
        // the info panel is to be informed when the game changes so that it can be updated
        gameInfoProvider.addObserver(infoPanel);

        GamePanel gamePanel = new GamePanel(keyListener, gameInfoProvider);
        overallPanel.add(gamePanel, BorderLayout.CENTER);
        // the game panel is to be informed when the game changes
        // so that it can show the new situation
        gameInfoProvider.addObserver(gamePanel);
        overallPanel.setFocusComponent(gamePanel);

        displayPanel(overallPanel);
    }

    public static final long serialVersionUID = 1;
}
