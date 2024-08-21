// This class is responsible for handling file operations such as reading, writing, and exporting files.

// Import ODF TOOLKIT classes for ODT file handling
import org.odftoolkit.odfdom.doc.OdfTextDocument; // to work with ODT files
import org.w3c.dom.NodeList; // to use NodeList class for working with the content of the document
import org.w3c.dom.Node; // to use Node class to represent a node in the document

//  Import other necessary packages/classes
import javax.swing.*; // to work with Swing components
import java.io.*; // to work with input/output files

// A class to encapsulate the attributes and methods of the file handler
public class FileHandler {
    // Set up the main components of the file handler
    private final JTextArea textArea; // to work with the text area
    private final JFileChooser fileChooser; // to work with the file chooser
    // A parameterized constructor to initialize a new file handler object
    public FileHandler(JTextArea textArea, JFileChooser fileChooser) {
        this.textArea = textArea; // Initialize a new text area
        this.fileChooser = fileChooser; // Initialize a new file chooser
    }

    // A method to handle the 'New' action
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

    // A method to handle the 'Open' action
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
