package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.Timer;

import util.PropertiesDiskStorage;
import view.View;
import model.Game;
import model.GameInfoProvider;
import model.GameControl;
import model.GameObserver;
import gameResults.GameResult;
import gameResults.HighScores;

/**
 * The class to start the space invaders game. It is also the controller in the
 * model-view-controller architecture of the system, i.e., when an event arrives, it takes
 * appropriate action to update the game and the view. It also observes when the game is over to
 * respond to it.
 */
public class Controller implements KeyListener, ActionListener, GameObserver {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    /**
     * The frame to hold the panel with the current view of the game.
     */
    private View view;

    /**
     * Create a view object, and have the view display the initial welcome message.
     */
    public void start() {
        PropertiesDiskStorage.setFileName("SpaceInvaders.properties");
        view = new View(WIDTH, HEIGHT);
        view.showWelcomeView(this); // this is passed in as an ActionListener
    }

    /**
     * An interface to the game with the methods that the Controller can apply to the game.
     */
    private GameControl gameControl;

    /**
     * An interface to the game with the methods that can be used to access information from the
     * game.
     */
    private GameInfoProvider gameInfo;

    /**
     * When an action event is received, take the action specified by its command: start a new game,
     * save the score from the last game, show the welcome view, or show the high scores.
     * 
     * @param event the action event that records the type of action to take.
     */
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if (actionCommand.equals("newgame")) {
            Game game = new Game(WIDTH, HEIGHT);
            gameControl = game;
            gameInfo = game;
            gameInfo.addObserver(this); // this is passed in as a GameObserver
            view.showNewGameView(gameInfo, this); // this is passed in as a KeyListener
            gameControl.start();
        } else if (actionCommand.equals("savescore")) {
            /* Obtain the id entered by the user. */
            String id = ((JTextField) event.getSource()).getText();
            if (id != null && !id.equals("")) {
                GameResult result =
                        new GameResult(id, gameInfo.getPlayerScore(), gameInfo.getLevel());
                HighScores.getInstance().saveGameResult(result);
            }
            view.showWelcomeView(this); // this is passed in as an ActionListener
        } else if (actionCommand.equals("quit"))
            System.exit(0);
        else if (actionCommand.equals("welcome"))
            view.showWelcomeView(this); // this is passed in as an ActionListener
        else if (actionCommand.equals("highscores"))
            view.showHighScoresView(this); // this is passed in as an ActionListener
        else
            throw new IllegalStateException("The event has action command " + actionCommand
                    + " that is invalid.");
    }

    /**
     * When a key is released, take appropriate action.
     * 
     * @param e the key event that records which key was released
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_LEFT):
                gameControl.getPlayer().moveLeft();
                break;
            case (KeyEvent.VK_RIGHT):
                gameControl.getPlayer().moveRight();
                break;
            case (KeyEvent.VK_SPACE):
                gameControl.getPlayer().fire();
                break;
            case (KeyEvent.VK_ESCAPE):
                gameControl.togglePaused();
                break;
            default:
                // ignore other keys
                break;
        }
    }

    /**
     * No action for a key press.
     */
    public void keyPressed(KeyEvent e) {}

    /**
     * No action for a key typed - use key release.
     */
    public void keyTyped(KeyEvent e) {}

    /**
     * When notified of a game change, check for the end of the game. If it is the end of game, show
     * that final view for 2.5 seconds, and then either display the welcome view, or, if the score
     * is high enough to be saved, display the save score view.
     */
    public void gameChanged() {
        if (gameInfo.isOver()) {
            final Controller thisController = this;
            Timer t = new Timer(2500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int gameScore = gameInfo.getPlayerScore();
                    if (HighScores.getInstance().isHighScore(gameScore)) {
                        view.showSaveScoreView(gameInfo, thisController);
                        // this controller is passed in as an ActionListener
                    } else {
                        view.showWelcomeView(thisController);
                        // this controller is passed in as an ActionListener
                    }
                }
            });
            /*
             * Instead of just putting this thread to sleep, a Timer is needed as the current thread
             * (the event dispatch thread) needs to continue execution to update the view to show
             * the game over message.
             */
            t.setRepeats(false);
            t.start();
        }
    }
}
