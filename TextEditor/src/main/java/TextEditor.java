// This class is responsible for creating the GUI used to interact with the text editor application.

// Import necessary packages/classes
import javax.swing.*; // to use Swing components for the GUI
import java.awt.*; // to use AWT components for the GUI

// A class to encapsulate the attributes and methods of the text editor application
public class TextEditor extends JFrame {
    // Set up the main components of the text editor GUI:t
    private final JFileChooser fileChooser; // a file chooser for opening/saving files
    private final JTextArea textArea = new JTextArea(); // a text area for editing text

    // A non-parameterized constructor to initialize a new text editor window object
    public TextEditor() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set up a text area for editing text with a monospaced font
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Initialize a new file chooser and file handler for file operations
        fileChooser = new JFileChooser();
        FileHandler fileHandler = new FileHandler(textArea, fileChooser);
        MenuHandler menuHandler = new MenuHandler(this, fileHandler);

        // Set the menu bar created by the menu handler and make it visible
        setJMenuBar(menuHandler.createMenuBar());
        setVisible(true);
    } // end of constructor

    // A public method to access the file chooser object from other classes (e.g. MenuHandler)
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    // A public method to access the text area object from other classes (e.g. MenuHandler)
    public JTextArea getTextArea() {
        return textArea;
    }


    // A main method to run the text editor application
    public static void main(String[] args) {
        // Create a new text editor window
        SwingUtilities.invokeLater(TextEditor::new); // Use the invokeLater method to ensure the GUI is created on the Event Dispatch Thread
    }
}

// Reference:
// A try-with-resources block is a try statement that declares one or more resources (objects) that must be closed after the program is finished with it, which is a more robust way of handling resources.
// To achieve this the 'object' is declared within the parentheses of the try statement, ensuring that it will be automatically closed at the end of the block.