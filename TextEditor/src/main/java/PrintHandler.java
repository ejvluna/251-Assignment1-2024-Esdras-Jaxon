// This class is responsible for handling print operations for the text editor application.

// Import necessary packages/classes
import javax.swing.*; // to work with Swing components
import java.awt.print.PrinterException; // to handle printer exceptions

// A class to encapsulate the attributes and methods of a print handler
public class PrintHandler {
    // Set up the main components of the print handler
    private final JTextArea textArea; // to work with the text area
    // A parameterized constructor to initialize a new print handler object
    public PrintHandler(JTextArea textArea) {
        this.textArea = textArea; // Initialize a new text area
    } // end of constructor

    // A method to handle the print operation
    public void print() {
        // Use a try-catch block to handle the print operation
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