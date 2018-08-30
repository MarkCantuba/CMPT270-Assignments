package view;

import java.awt.Component;
import javax.swing.JPanel;

/**
 * A class for the panel to be placed in the frame of the main view. It has a function to return the
 * Component within it to obtain the focus when the containing frame is realized.
 */
public class ViewPanel extends JPanel {
    /** The Component to be in focus when the containing frame is realized. */
    protected Component focusComponent;

    /**
     * @return the Component of this panel that should have the focus when the frame is realized.
     */
    public Component getFocusComponent() {
        return focusComponent;
    }

    /**
     * Set the Component of this panel that should have the focus when the frame is realized.
     */
    public void setFocusComponent(Component focusComponent) {
        this.focusComponent = focusComponent;
    }

    public static final long serialVersionUID = 1;
}
