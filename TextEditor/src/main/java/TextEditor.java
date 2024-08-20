// Project Requirements:
// 1. Full GUI access to the application
// 2. A menu of options at the top of the editor with the following sub-menus: File, Search, View, and Help
// 3. Implement functionalities specified in assignment requirements via sub-menu options.

// === Section 0: Import the necessary libraries/packages for the program ===

import javax.swing.*; // to use Swing components for the GUI
import java.awt.*; // to use AWT components for the GUI
import java.io.*; // to use file input/output for reading files
import javax.swing.filechooser.FileNameExtensionFilter; // to filter file types
import org.odftoolkit.odfdom.doc.OdfTextDocument; // to work with ODT files
import org.w3c.dom.NodeList; // to use NodeList class for working with the content of the document
import org.w3c.dom.Node; // to use Node class to represent a node in the document


// === Section 1: Create a TextEditor class to implement the text editor application ===

public class TextEditor extends JFrame {
    // Declare the main components of the text editor:
    // a text area for editing text,
    private final JTextArea textArea;
    // a file chooser for opening/saving files
    private final JFileChooser fileChooser;

    // A class constructor to initialize a new text editor window
    public TextEditor() {
        // Set the initial properties of the text editor window: Title, Size, Close Operation
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // Set up an area for editing text
        textArea = new JTextArea(); // Create a new instance of JTextArea class
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Set the font for the text area
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Add a scroll pane to the text area and set its position

        // Set up a menu bar for the text editor
        JMenuBar menuBar = new JMenuBar(); // Create a new instance of JMenuBar class
        setJMenuBar(menuBar); // Set the new menu bar as the menu bar for the text editor

        // Set up the required main menus for the menu bar (using JMenu class): File, Search, View, Help
        JMenu fileMenu = new JMenu("File");
        JMenu searchMenu = new JMenu("Search");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");

        // Add the main menus to the menu bar (using the add() method of the JMenuBar class)
        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        // Initialize a file chooser for selecting menu items (directory, file)
        fileChooser = new JFileChooser();

        // *** further implementation of the text editor functionalities will be added here

        // Set up a "New" menu item under the "File" menu
        JMenuItem newMenuItem = new JMenuItem("New"); // create a new instance of JMenuItem class
        fileMenu.add(newMenuItem); // add the "New" menu item to the "File" menu
        newMenuItem.addActionListener(e -> new TextEditor()); // add an action listener to the "New" menu item

        // Set up an "Open" menu item under the "File" menu
        JMenuItem openMenuItem = new JMenuItem("Open"); // create a new instance of JMenuItem class
        fileMenu.add(openMenuItem); // add the "Open" menu item to the "File" menu
        // Add an action listener to the "Open" menu item
        openMenuItem.addActionListener(e -> {
            // Display a file chooser dialog and get the selected file
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                // Check the file extension and call the appropriate method to read the file
                if (filePath.endsWith(".txt")) {
                    readTxtFile(selectedFile);
                }
                else if (filePath.endsWith(".odt")) {
                    readOdtFile(selectedFile);
                }
                // If the file type is not supported, display an error message
                else {
                    JOptionPane.showMessageDialog(this, "Unsupported file type", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Make the new text editor window visible
        setVisible(true);
    } // end of class constructor


    // === Section 2: helper methods to implement the functionalities of the text editor ===

    // A method to read and display the content of a .txt file
    private void readTxtFile(File file) {
        // Use a try-catch block to try to read the content of the file line by line using a BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            // If the file is successfully read, display the content in the text area
            textArea.setText(content.toString());
            // If an error occurs while reading the file, display an error message
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // A method to read and display the content of an .odt file
    private void readOdtFile(File file) {
        // Use a try-catch block to try to load the content of the file using OdfTextDocument
        try {
            OdfTextDocument document = OdfTextDocument.loadDocument(file);
            StringBuilder content = new StringBuilder();
            // Get the content root of the document and extract the paragraphs
            NodeList paragraphs = document.getContentRoot().getElementsByTagName("text:p");
            // Iterate through the paragraphs and append the text content to the StringBuilder
            for (int i = 0; i < paragraphs.getLength(); i++) {
                Node paragraph = paragraphs.item(i);
                content.append(paragraph.getTextContent()).append("\n");
            }
            // Display the content in the text area
            textArea.setText(content.toString());
            // If an error occurs while reading the file, display an appropriate error message
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // === Section 3: the 'main' method to run the text editor application ===
    public static void main (String[] args) {
        // Create a new instance of the TextEditor class to run the text editor application
        new TextEditor();
    }
}