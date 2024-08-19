// Project Requirements:
// 1. Full GUI access to the application
// 2. A menu of options at the top of the editor with the following sub-menus: File, Search, View, and Help
// 3. Implement functionalities specified in assignment requirements via sub-menu options.

// === Section 0: Import the necessary libraries/packages for the program ===

import javax.swing.*; // to use Swing components
import java.awt.*; // to use AWT components

// === Section 1: Create a TextEditor class to implement the text editor application ===

public class TextEditor extends JFrame {
    // Declare the main components of the text editor:
    // a text area for editing text,
    private JTextArea textArea;
    // a file chooser for opening/saving files
    private JFileChooser fileChooser;

    // A class constructor to initialize a new text editor window
    public TextEditor() {
        // Set the initial properties of the text editor window: Title, Size, Close Operation
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up a text area for editing text and
        textArea = new JTextArea(); // Create a new instance of JTextArea class
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Set up a menu bar for the text editor using JMenuBar class
        JMenuBar menuBar = new JMenuBar(); // Create a new instance of JMenuBar class
        setJMenuBar(menuBar); // Set the new menu bar as the menu bar for the text editor

        // Set up the required main menus for the menu bar using JMenu class: File, Search, View, Help
        JMenu fileMenu = new JMenu("File");
        JMenu searchMenu = new JMenu("Search");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");

        // Add the main menus to the menu bar using the add() method of the JMenuBar class
        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        // Initialize a file chooser for selecting menu items (directory, file)
        fileChooser = new JFileChooser();

        // Make the new text editor window visible
        setVisible(true);

    } // end of class constructor


    // === Section 2: helper methods to implement the functionalities of the text editor ===

    // under construction

    // === Section 3: a main method to run the text editor application ===
    public static void main(String[] args) {
        new TextEditor();
    }
}