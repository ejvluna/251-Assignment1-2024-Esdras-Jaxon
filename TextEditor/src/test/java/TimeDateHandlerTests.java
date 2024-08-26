// Importing necessary libraries
import org.junit.jupiter.api.BeforeEach; // for using the @BeforeEach annotation
import org.junit.jupiter.api.Test; // for using the @Test annotation
import static org.junit.jupiter.api.Assertions.*; // for using assertions such as assertEquals, assertTrue, assertFalse, and assertNull
import javax.swing.JTextArea; // for using JTextArea components
import javax.swing.SwingUtilities; // for using SwingUtilities

/**
 * A class to test the functionality of the TimeDateHandler class.
 */
public class TimeDateHandlerTests {

    // === ATTRIBUTES ===
    // Create a TimeDateHandler object and a JTextArea object for testing
    private TimeDateHandler timeDateHandler;
    private JTextArea textArea;

    // === TEST METHODS ===

    // A method to set up the test environment before each test method
    @BeforeEach
    public void setUp() {
        textArea = new JTextArea();
        timeDateHandler = new TimeDateHandler(textArea);
    }

    // A test method to test the insertion of the current time and date
    @Test
    public void testInsertCurrentTimeDate() {
        try {
            // Act: simulate the insertion of the current time and date
            timeDateHandler.insertCurrentTimeDate();
            // Wait for the SwingUtilities.invokeLater to complete
            SwingUtilities.invokeAndWait(() -> {});
            // Assert: check if the text area is not empty
            assertFalse(textArea.getText().isEmpty());
            // If an exception is thrown, fail the test and print the exception message
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
