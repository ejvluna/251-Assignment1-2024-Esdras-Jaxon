// Import Apache PDFBox classes for PDF handling
import org.apache.pdfbox.pdmodel.PDDocument; // to work with PDF documents
import org.apache.pdfbox.pdmodel.PDPage; // to work with PDF pages
import org.apache.pdfbox.pdmodel.PDPageContentStream; // to work with PDF page content streams
import org.apache.pdfbox.pdmodel.font.PDType1Font; // to work with PDF fonts
import org.apache.pdfbox.pdmodel.font.Standard14Fonts; // to use the new constructor for PDType1Font per https://pdfbox.apache.org/3.0/migration.html
// Import other necessary packages/classes
import javax.swing.*; // to work with Swing components
import java.io.File; // to work with input/output files
import java.io.IOException; // to handle input/output exceptions

/**
 * The PDFExporter class is responsible for exporting the text content to a PDF file.
 * This class uses Apache PDFBox to create and write content to PDF documents.
 * It ensures the file is saved with a .pdf extension and handles any exceptions that occur during the export process.
 * It also displays appropriate messages for success or error conditions.

 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-21
 */

// A class to encapsulate the attributes and methods of the PDF exporter object
public class PDFExporter {

    // === METHODS ===
    // A static method (i.e. called without creating an instance of the class) to export the text content to a PDF file
    public static void exportToPdf(File file, String text) {
        // Check if the file name ends with .pdf, if not, append .pdf to ensure it is recognized as a PDF file
        if (!file.getName().toLowerCase().endsWith(".pdf")) {
            file = new File(file.getAbsolutePath() + ".pdf");
        }
        // Use a try-with-resources block to create a new PDF document and write the text content to it
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(); // Create a new PDF page
            document.addPage(page); // Add the page to the document
            PDPageContentStream contentStream = createContentStream(document, page, text); // Create and set up the content stream
            contentStream.close();
            // Call the save method on the document to save the PDF file
            document.save(file);
            // Show a success message if the file is exported successfully
            JOptionPane.showMessageDialog(null, "File exported successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            // If an exception occurs during the export process, show an error message
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error exporting file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A method to create and set up the content stream for writing text to the PDF file
    private static PDPageContentStream createContentStream(PDDocument document, PDPage page, String text) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page); // Create a new content stream
        PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA); // Create a new font
        // Set up the content stream to write the content from the text area to the PDF file
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.newLineAtOffset(25, 750);
        // Split the text content into lines based on newline character delimiter
        String[] lines = text.split("\n");
        // Iterate over the lines and write each line to the PDF file
        for (String line : lines) {
            contentStream.showText(line);
            contentStream.newLineAtOffset(0, -15);
        }
        // Once all lines are written, end the text
        contentStream.endText();
        // Return the content stream
        return contentStream;
    }
}

