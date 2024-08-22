// Import ODF TOOLKIT classes for ODT file handling
import org.odftoolkit.odfdom.doc.OdfTextDocument; // to work with ODT files
import org.w3c.dom.NodeList; // to use NodeList class for working with the content of the document
import org.w3c.dom.Node; // to use Node class to represent a node in the document


//  Import other necessary packages/classes
import javax.swing.*; // to work with Swing components
import java.io.*; // to work with input/output files
import javax.swing.text.BadLocationException;
import javax.swing.text.rtf.RTFEditorKit; // to handle RTF files


/**
 * The FileHandler class is responsible for handling file operations in a simple text editor application.
 * This class provides functionality to open, read, save, and export text files.
 * It also handles displaying appropriate messages for success or error conditions.
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-21
 */

// A class to encapsulate the attributes and methods of the file handler
public class FileHandler {

    // === CLASS ATTRIBUTES ===

    // Attributes of the file handler class
    private final JTextArea textArea; // to work with the text area
    private final JFileChooser fileChooser; // to work with the file chooser

    // === CLASS METHODS ===

    // A parameterized constructor to initialize a new file handler object
    public FileHandler(JTextArea textArea, JFileChooser fileChooser) {
        this.textArea = textArea; // Initialize a new text area
        this.fileChooser = fileChooser; // Initialize a new file chooser
    }

    // A method to handle the 'Open' action for .txt files
    public void readTxtFile(File file) {
        // Use a try-with-resources block to read the file using a buffered reader object
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder(); // Create a string builder to store the content
            String line; // Create a string to store each line of the file
            // Read the file line by line and append the content to the string builder to store it
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            // Set the content from the string builder in the text area of the GUI to display it
            textArea.setText(content.toString());
        }
        // If an exception occurs, show an error message
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A method to handle the 'Open' action for .ODT files
    public void readOdtFile(File file) {
        // Use a try-with-resources block to load the ODT file using the OdfTextDocument class
        try (OdfTextDocument document = OdfTextDocument.loadDocument(file)) {
            StringBuilder content = new StringBuilder();
            // Get the content root of the document and retrieve the paragraphs using the tag name
            NodeList paragraphs = document.getContentRoot().getElementsByTagName("text:p");
            // Iterate over the paragraphs and append the text content to the string builder to store it
            for (int i = 0; i < paragraphs.getLength(); i++) {
                Node paragraph = paragraphs.item(i);
                content.append(paragraph.getTextContent()).append("\n");
            }
            // Set the content from the string builder in the text area of the GUI to display it
            textArea.setText(content.toString());
            // If an exception occurs, show an appropriate error message
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A method to handle the 'Open' action for .RTF files
    public void readRtfFile(File file) {
        // Check if the file exists and is readable
        if (!file.exists() || !file.canRead()) {
            JOptionPane.showMessageDialog(null, "File does not exist or is not readable", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the required components for reading the RTF file
        JTextPane textPane = new JTextPane(); // Create a JTextPane to
        RTFEditorKit rtfKit = new RTFEditorKit(); // Create an RTF editor kit to handle RTF files
        textPane.setEditorKit(rtfKit); // 'Set' the RTF editor kit for the text pane in order to read the RTF file

        // Use a try-with-resources block to read the RTF file using a file input stream
        try (FileInputStream inputStream = new FileInputStream(file)) {
            // Use the RTF editor kit to read the content of the RTF file (via the input stream) into the JTextPane
            rtfKit.read(inputStream, textPane.getDocument(), 0);
            // Extract the text from the JTextPane and store it in a string
            String extractedText = textPane.getDocument().getText(0, textPane.getDocument().getLength());
            // Check if the extracted text is null or empty
            if (extractedText != null && !extractedText.isEmpty()) {
                // If the extracted text is not null or empty, create a StringBuilder to store the content
                StringBuilder content = new StringBuilder(extractedText);
            // Set the content from the StringBuilder to the text area of the GUI to display it
            textArea.setText(content.toString());
            // If an exception occurs, show an error message
        } else {
            JOptionPane.showMessageDialog(null, "No content found in the RTF file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Catch any exceptions that may occur during the reading of the RTF file and display an appropriate error message
        } catch (IOException ex) {
            System.err.println("IOException while reading RTF file: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading RTF file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (BadLocationException ex) {
            System.err.println("BadLocationException while reading RTF file: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error processing RTF content: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A method to handle the 'Save' action
    public void saveTxtFile(File file) {
        // Use a try-with-resources block to write the content of the text area to the file using a buffered writer object
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write the text content from the text area to the writer object
            writer.write(textArea.getText());
            // Show a success message if the file is saved successfully
            JOptionPane.showMessageDialog(null, "File saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            // If an exception occurs, show an error message
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A method to export the text content as a PDF file
    public void exportToPdf(File file) {
        // Call the PDF exporter class method to export the text content as a PDF file
        PDFExporter.exportToPdf(file, textArea.getText());
    }
}
