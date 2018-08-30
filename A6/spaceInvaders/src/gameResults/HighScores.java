package gameResults;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Maintains a list of the GameResults with highest scores. The list is initially read from disk by
 * the constructor, and saved to disk whenever the list is updated.
 */
public class HighScores implements Serializable {
    /**
     * The maximum number of high scores to be saved.
     */
    public static final int MAX_NUMBER_SCORES_SAVED = 10;

    /**
     * The name of the file that stores the high scores for the game.
     */
    public static final String HIGH_SCORES_FILE_NAME = "HighScores.ser";

    public static final long serialVersionUID = 1l;

    /**
     * The sole instance of this class.
     */
    private static HighScores instance = null;

    /**
     * @return the sole instance of this class
     */
    public static HighScores getInstance() {
        if (instance == null) {
            instance = new HighScores();
        }

        return instance;
    }

    /**
     * The list that stores the highest scores of the game.
     */
    public LinkedList<GameResult> highScores;

    /**
     * Read in the high scores for the game from disk and store them in the listed list highScores.
     */
    @SuppressWarnings("unchecked")
    private HighScores() {
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;

        try {
            fileIn = new FileInputStream(HIGH_SCORES_FILE_NAME);
            objectIn = new ObjectInputStream(fileIn);
            highScores = (LinkedList<GameResult>) objectIn.readObject();
            objectIn.close();
        } catch (FileNotFoundException fnfe) {
            /*
             * The file isn't found so this is the first play of the game, and no previous results
             * exist.
             */
            highScores = new LinkedList<GameResult>();
        } catch (IOException ioe) {
            /*
             * This should not happen unless the file name is invalid, but continue with no previous
             * results.
             */
            ioe.printStackTrace();
            highScores = new LinkedList<GameResult>();
        } catch (ClassNotFoundException cnfe) {
            /*
             * This should not happen unless file name is invalid, but continue with no previous
             * results.
             */
            cnfe.printStackTrace();
            highScores = new LinkedList<GameResult>();
        }
    }

    /**
     * @return a linked list of the past high scores
     */
    public LinkedList<GameResult> getHighScores() {
        return highScores;
    }

    /**
     * @return is score high enough to merit being stored with the high scores
     */
    public boolean isHighScore(int score) {
        if (score <= 0)
            return false;
        else if (highScores.size() < MAX_NUMBER_SCORES_SAVED)
            return true;
        else if (score > highScores.getLast().getScore())
            return true;
        else
            return false;
    }

    /**
     * Add result to the linked list of high scores, and save the scores to disk.
     * 
     * @param newResult the result of a play of the game to be saved
     * @precond isHighScore(result.getScore())
     */
    public void saveGameResult(GameResult newResult) {
        ListIterator<GameResult> iterator = highScores.listIterator();
        boolean found = false; // found the first score smaller than the new one
        while (!found && iterator.hasNext())
            if (newResult.getScore() > iterator.next().getScore())
                found = true;
        if (found) {
            // move back before the smaller score
            iterator.previous();
            iterator.add(newResult);
        } else
            // add at the end of the list
            iterator.add(newResult);
        if (highScores.size() > MAX_NUMBER_SCORES_SAVED)
            highScores.removeLast();
        save();
    }

    /**
     * Save the linked list of high scores to disk.
     */
    private void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(HIGH_SCORES_FILE_NAME);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(highScores);
            objectOut.close();
        } catch (FileNotFoundException fnfe) {
            /* This should not happen unless file name is invalid. */
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            /* This should not happen unless file name is invalid. */
            ioe.printStackTrace();
        }
    }
}
