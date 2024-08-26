// Import necessary packages/classes
import java.time.LocalDateTime; // to work with date and time
import java.time.format.DateTimeFormatter; // to format date and time
import javax.swing.JTextArea; // to work with JTextArea components
import javax.swing.SwingUtilities;

/**
 * The TimeDateHandler class is responsible for handling the insertion of the current time and date into a JTextArea.
 * This class provides methods to insert the formatted current time and date at the current caret position in the text area.
 *
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-22
 */

// A class to encapsulate the attributes and methods of the time and date handler
public class TimeDateHandler {

    // === CLASS ATTRIBUTES ===
    private final JTextArea textArea; // a variable to store the text area

    // === CLASS METHODS ===

    // A parameterized constructor to initialize a new time and date handler object
    public TimeDateHandler(JTextArea textArea) {
        this.textArea = textArea;
    } // end of constructor


    // A method to insert the current time and date at the beginning of the text area
    public void insertCurrentTimeDate() {
        // Use the SwingUtilities.invokeLater method to update the Swing components in a thread-safe manner
        SwingUtilities.invokeLater(() -> {
            // Get the current date and time and store it
            LocalDateTime current_time = LocalDateTime.now();
            // Create a formatter to format the date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Format the current date and time using the formatter
            String formatted_time = current_time.format(formatter);
            // Insert the formatted time at the beginning (e.g. index 0) of the text area
            textArea.insert(formatted_time + "\n", 0); // Insert the formatted time at the beginning of the text area
        });
    }
}