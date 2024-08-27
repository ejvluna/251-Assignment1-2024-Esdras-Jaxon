// Import required libraries/classes
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea; // to create a text area with syntax highlighting

/**
 * The ViewHandler class is responsible for handling the view components of the text editor application.
 * This class provides functionality to select, copy, paste, and cut text in the text area.

 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-23
 */

public class EditHandler {
    // ===  ATTRIBUTES ===
    private final RSyntaxTextArea textArea; // to reference the text area of the text editor

    // === CONSTRUCTORS ===
    // A parameterized constructor to initialize a new view handler object
    public EditHandler(RSyntaxTextArea textArea) {
        this.textArea = textArea;
    }

    // === METHODS ===
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
