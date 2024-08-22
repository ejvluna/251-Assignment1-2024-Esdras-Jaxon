// Import required libraries/classes
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea; // to create a text area with syntax highlighting

/**
 * The ViewHandler class is responsible for handling the view components of the text editor application.
 * This class provides functionality to select, copy, paste, and cut text in the text area.
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-23
 */

// A class to encapsulate the attributes and methods of the view handler
public class ViewHandler {

    // === CLASS ATTRIBUTES ===

    private final RSyntaxTextArea textArea; // to reference the text area of the text editor

    // === CLASS METHODS ===

    // A parameterized constructor to initialize a new view handler object
    public ViewHandler(RSyntaxTextArea textArea) {
        this.textArea = textArea;
    }
    // A method to select all text in the text area
    public void selectAll() {
        textArea.selectAll();
    }
    // A method to copy selected text in the text area
    public void copy() {
        textArea.copy();
    }
    // A method to paste text in the text area
    public void paste() {
        textArea.paste();
    }
    // A method to cut selected text in the text area
    public void cut() {
        textArea.cut();
    }
}
