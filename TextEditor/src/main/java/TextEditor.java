// Import Java Swing classes to create the GUI
import javax.swing.JFrame; // to create a window
import javax.swing.JFileChooser; // to create a file chooser
import javax.swing.SwingUtilities; // to work with Swing components
import java.awt.Color;
import java.awt.Font;
import java.awt.*;

// Import RSyntaxTextArea classes for syntax highlighting
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea; // to create a text area with syntax highlighting
import org.fife.ui.rtextarea.RTextScrollPane; // to create a scrollable text area
import org.fife.ui.rsyntaxtextarea.SyntaxConstants; // to set syntax highlighting style

/**
 * The TextEditor class is responsible for creating the GUI used to interact with the text editor application.
 * This class sets up the main components of the text editor, including the text area and file chooser, and initializes the menu bar.
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-20
 */

// A class to encapsulate the attributes and methods of the text editor application
public class TextEditor extends JFrame {

    // === CLASS ATTRIBUTES ===

    // Attributes of the text editor class
    private final JFileChooser fileChooser; // a file chooser for opening/saving files
    private final RSyntaxTextArea textArea = new RSyntaxTextArea(); // a text area for editing text with syntax highlighting
    //private static ConfigLoader config; // a configuration loader to load settings from a configuration file (e.g. yaml file

    // === CLASS METHODS ===

    // A non-parameterized constructor to initialize a new text editor window object
    public TextEditor() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Specify the default layout, font, and other properties of the text area of the text editor
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Set the font type and size
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        textArea.setEditable(true); // Enable editing
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE); // Default to no syntax highlighting
        add(new RTextScrollPane(textArea), BorderLayout.CENTER); // Add the text area to the center of the frame

        // Initialize a new file chooser and file handler for file operations
        fileChooser = new JFileChooser();
        FileHandler fileHandler = new FileHandler(textArea, fileChooser);
        MenuHandler menuHandler = new MenuHandler(this, fileHandler);
        //config = new ConfigLoader("config.yaml");  // Load the configuration file from the resources directory

        // Set the menu bar created by the menu handler and make it visible
        setJMenuBar(menuHandler.createMenuBar());
        setVisible(true);

    } // end of constructor

    // A public method to access the file chooser object from other classes (e.g. MenuHandler)
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    // A public method to access the text area object from other classes (e.g. MenuHandler)
    public RSyntaxTextArea getTextArea() {
        return textArea;
    }

}


// Reference/Notes:
// A try-with-resources block is a try statement that declares one or more resources (objects) that must be closed after the program is finished with it, which is a more robust way of handling resources.
// To achieve this the 'object' is declared within the parentheses of the try statement, ensuring that it will be automatically closed at the end of the block.
// Encountered bothersome issue with 'variable shadowing' when including a new  'textArea' variable in the 'TextEditor' class constructor due to class vs constructor level declaration
// As a result the TimeDateHandler class was not able to insert the current time and date into the desired text area when using the getTextArea method
// Resolved by removing the constructor-level 'textArea' declaration and using the class-level 'textArea' variable directly in the constructor
