// This class is responsible for creating the GUI used to interact with the text editor application.

// Import necessary packages/classes
import javax.swing.*; // to use Swing components for the GUI
import java.awt.*; // to use AWT components for the GUI
import java.io.File;

// A class to encapsulate the attributes and methods of the text editor application
public class TextEditor extends JFrame {
    // Set up the main components of the text editor GUI:
    private final JTextArea textArea; // a text area for editing text
    private final JFileChooser fileChooser; // a file chooser for opening/saving files
    private final FileHandler fileHandler; // a file handler to handle file operations
    private final MenuHandler menuHandler; // a menu handler to create the menu bar

    // A non-parameterized constructor to initialize a new text editor window object
    public TextEditor() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set up a text area for editing text with a monospaced font
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Initialize the file chooser and file handler and menu handler
        fileChooser = new JFileChooser();
        fileHandler = new FileHandler(textArea, fileChooser);
        menuHandler = new MenuHandler(this, fileHandler);

        // Set the menu bar created by the menu handler and make it visible
        setJMenuBar(menuHandler.createMenuBar());
        setVisible(true);
    }
    // A method to get the file chooser instance
    public JFileChooser getFileChooser() {
        return fileChooser;
    }
    // A main method to run the text editor application
    public static void main(String[] args) {
        new TextEditor();
    }
}

/*

import javax.swing.*; // to use Swing components for the GUI
import java.awt.*; // to use AWT components for the GUI
import java.io.*; // to use file input/output for reading files
import java.io.IOException; // to handle IO exceptions
// Libraries for working with ODT files
import org.odftoolkit.odfdom.doc.OdfTextDocument; // to work with ODT files
import org.w3c.dom.NodeList; // to use NodeList class for working with the content of the document
import org.w3c.dom.Node; // to use Node class to represent a node in the document
// Libraries for working with PDF files
import org.apache.pdfbox.pdmodel.PDDocument; // to work with PDF files using PDFBox
import org.apache.pdfbox.pdmodel.PDPage; // to create a new page in the PDF document
import org.apache.pdfbox.pdmodel.PDPageContentStream; // to write content to the PDF page
import org.apache.pdfbox.pdmodel.font.PDType1Font; // to set the font for the PDF content
import org.apache.pdfbox.pdmodel.font.Standard14Fonts; // to use the new constructor for PDType1Font per https://pdfbox.apache.org/3.0/migration.html

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

        // 1.1. 'File' menu item Implementations here:

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
                // Check the file extension and call the appropriate helper method to read the file
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

        // Set up a "Save" menu item under the "File" menu
        JMenuItem saveMenuItem = new JMenuItem("Save"); // create a new instance of JMenuItem class
        fileMenu.add(saveMenuItem); // add the "Save" menu item to the "File" menu
        // Add an action listener to the "Save" menu item
        saveMenuItem.addActionListener(e -> {
            // Display a file chooser dialog
            int returnValue = fileChooser.showSaveDialog(this);
            // If the user selects a file location and name, save the content of the current text area to the file location
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                // Check if the file name ends with .txt, if not, append .txt (e.g. to ensure the file is saved as a .txt file)
                if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
                }
                // Call a helper method to save the content to the selected file
                saveTxtFile(selectedFile);
            }
        });

        // Set up a "Quit" menu item under the "File" menu
        JMenuItem quitMenuItem = new JMenuItem("Quit"); // create a new instance of JMenuItem class
        fileMenu.add(quitMenuItem); // add the "Quit" menu item to the "File" menu
        // Add an action listener to the "Quit" menu item
        quitMenuItem.addActionListener(e -> {
            // Quit the program and close all windows
            System.exit(0);
        });


        // Set up an "Export as PDF" menu item under the "File" menu
        JMenuItem exportPdfMenuItem = new JMenuItem("Export as PDF"); // create a new instance of JMenuItem class
        fileMenu.add(exportPdfMenuItem); // add the "Export as PDF" menu item to the "File" menu
        // Add an action listener to the "Export as PDF" menu item. When clicked:
        exportPdfMenuItem.addActionListener(e -> {
            // Display a file chooser dialog
            int returnValue = fileChooser.showSaveDialog(this);
            // If the user selects a file location and name, save the content of the current text area to the file location
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                // Check if the file name ends with .pdf, if not, append .pdf (e.g. to ensure the file is saved as a .pdf file)
                if (!selectedFile.getName().toLowerCase().endsWith(".pdf")) {
                    selectedFile = new File(selectedFile.getAbsolutePath() + ".pdf");
                }
                // Call a helper method to save the content to the selected location as a PDF file
                exportToPdf(selectedFile);
            }
        });





        // 1.2 'Search' menu item Implementations here:

        // 1.3 'View' menu item Implementations here:

        // 1.4 'Help' menu item Implementations here:


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
        // Use a try-with-resources block to try to load the ODT file using the OdfTextDocument class
        try (OdfTextDocument document = OdfTextDocument.loadDocument(file)) {
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

    // A method to save the content of the text area to a .txt file
    private void saveTxtFile(File file) {
        // Use a try-catch block to try to write the content of the text area to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textArea.getText());
            // If the file is successfully written, display a success message
            JOptionPane.showMessageDialog(this, "File saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            // If an error occurs while writing the file, display an error message
            JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A method to export the content of the text area to a PDF file using Apache PDFBox
    private void exportToPdf(File file) {
        // Use a try-with-resources block to try to create a new PDF document  and write the content of the text area to the PDF
        try (PDDocument document = new PDDocument()) {
            // Create a new page in the PDF document and add it to the document
            PDPage page = new PDPage();
            document.addPage(page);
            // Create a new content stream for the page and write the content of the text area to the PDF page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            // Create a font object to specify the font for the text in the PDF per https://pdfbox.apache.org/3.0/migration.html
            PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
            // Begin the text block (e.g. to write text to the PDF)
            contentStream.beginText();
            // Use the font object to set the font for the content stream and specify the font size
            contentStream.setFont(font, 12);
            // Set the position for the text in the PDF (e.g. offset from the top-left corner)
            contentStream.newLineAtOffset(25, 750);
            // Split the text area content into lines and write each line to the PDF. Make the delimiter "\n" to split the text area content into lines
            String[] lines = textArea.getText().split("\n");
            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -15);
            }
            contentStream.endText();
            contentStream.close();
            document.save(file);
            // If the file is successfully exported, display a success message
            JOptionPane.showMessageDialog(this, "File exported successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        // If an error occurs while exporting the file, display an error message
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error exporting file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

        // === Section 3: the 'main' method to run the text editor application ===
    public static void main (String[] args) {
        // Create a new instance of the TextEditor class to run the text editor application
        new TextEditor();
    }
}
*/






// Reference:
// A try-with-resources block is a try statement that declares one or more resources (objects) that must be closed after the program is finished with it, which is a more robust way of handling resources.
// To achieve this the 'object' is declared within the parentheses of the try statement, ensuring that it will be automatically closed at the end of the block.