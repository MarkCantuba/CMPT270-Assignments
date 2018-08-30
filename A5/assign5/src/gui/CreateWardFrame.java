package gui;

import javax.swing.JFrame;

/**
 * The frame to obtain input to initialize the ward, and then it will start the main system.
 */
public class CreateWardFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create the frame for the entry of the ward information.
     */
    public CreateWardFrame() {
        setTitle("Ward Creation");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        CreateWardPanel panel = new CreateWardPanel();
        add(panel);
    }

    public static final long serialVersionUID = 1;
}
