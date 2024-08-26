// Import necessary packages
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JTextArea;

/**
 * A class to test the functionality of the TimeDateHandler class.
 */
public class SearchHandlerTests {

    // === ATTRIBUTES ===
    private SearchHandler searchHandler;
    private JTextArea textArea;

    // === TEST METHODS ===

    // A method to set up the test environment before each test method
    @BeforeEach
    public void setUp() {
        textArea = new JTextArea();
        searchHandler = new SearchHandler(textArea);
    }

    // A test method to test the search functionality for occurrences of a term anywhere in the text
    @Test
    public void testSearch() {
        // Arrange: specify the test search term and content
        String searchText = "te";
        String content = "This is a test content for the TextEditor.";
        textArea.setText(content);
        // Act:  calculate the number of occurrences of the search term in the text area
        int occurrences = searchHandler.search(searchText);
        // Assert: specify the expected number of occurrences
        assertEquals(4, occurrences);
    }

    // A test method to test the search functionality for a specific search term with full word matching
    @Test
    public void testSearchFullWord() {
        // Arrange: specify the test search term and content
        String searchText = "te";
        String content = "This is a test content for the TextEditor. Another test.";
        textArea.setText(content);
        // Act: calculate the number of occurrences of the search term in the text area
        int occurrences = searchHandler.searchFullWord(searchText);
        // Assert: specify the expected number of occurrences
        assertEquals(0, occurrences);
    }
}
