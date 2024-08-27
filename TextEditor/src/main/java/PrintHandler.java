// Import necessary packages/classes
import javax.swing.*; // to work with Swing components
import java.awt.print.PrinterException; // to handle printer exceptions

/**
 * The PrintHandler class is responsible for handling print operations for the text editor application.
 * This class provides functionality to print the content of a text area and displays appropriate messages based on the print status.
 * It handles any exceptions that occur during the print process.

 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-21
 */

public class PrintHandler {

    // === ATTRIBUTES ===
    // Attributes of the print handler class
    private final JTextArea textArea; // to work with the text area

    // === CONSTRUCTOR ===
    // A parameterized constructor to initialize a new print handler object
    public PrintHandler(JTextArea textArea) {
        this.textArea = textArea; // Initialize a new text area
    } //

    // === METHODS ===
    // A method to handle the print operation
    public void print() {
        // Use a try-catch block to handle the print operation and any exceptions that may occur
        try {
            boolean complete = textArea.print(); // To verify if the printing successfully completed
            // Show a message dialog based on the printing status outcome
            if (complete) {
                JOptionPane.showMessageDialog(null, "Printing Complete", "Print", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Printing Cancelled", "Print", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Printing Failed: " + e.getMessage(), "Print", JOptionPane.ERROR_MESSAGE);
        }
    }
}