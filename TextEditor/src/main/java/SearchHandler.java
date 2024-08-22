// Import necessary packages/classes
import javax.swing.*; // to use Swing components
import javax.swing.text.*; // to use text-related classes
import java.awt.*; // to use AWT components
import java.util.regex.Matcher; // to use regular expression matching
import java.util.regex.Pattern; // to use regular expression patterns

/**
 * The SearchHandler class provides functionality to search for text within a JTextArea.
 * It includes methods to find and highlight occurrences of a search term.
 * The search can be performed with or without case sensitivity and with or without full word matching.
 *
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-22
 */

// A class to encapsulate the attributes and methods of the search handler
public class SearchHandler {

    // === CLASS ATTRIBUTES ===
    private final JTextArea textArea; // a text area for editing text
    private final Highlighter highlighter; // a highlighter object to store and manage highlights
    private final Highlighter.HighlightPainter painter; // a highlight painter object to specify the color of highlights

    // === CLASS METHODS ===

    // A parameterized constructor to initialize a new search handler object
    public SearchHandler(JTextArea textArea) {
        this.textArea = textArea; // Assign the text area passed as a parameter to the class attribute
        this.highlighter = textArea.getHighlighter(); // Get the highlighter object from the text area
        this.painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN); // Create a new highlight painter with a specific color
    }

    // A method to show a search dialog and display the number of occurrences found
    public void showSearchDialog(boolean fullWord) {
        // Use a JOptionPane to prompt the user for a search term
        String searchTerm = JOptionPane.showInputDialog(textArea, "Enter search term:");
        // If the search term is not null or empty, search for the term and store the number of occurrences
        if (searchTerm != null && !searchTerm.isEmpty()) {
            // Call the appropriate search method based on whether full word search is enabled
            int occurrences = fullWord ? searchFullWord(searchTerm) : search(searchTerm);
            // Display a message dialog with the number of occurrences found
            JOptionPane.showMessageDialog(textArea,
                    "Found " + occurrences + " occurrence(s) of '" + searchTerm + "'");
        }
    }

    // A method to search for text within the text area
    public int search(String searchText) {
        highlighter.removeAllHighlights(); // Clear any highlights from previous searches
        String textContent = textArea.getText().toLowerCase(); // Convert the text from the content area to lowercase
        String searchLower = searchText.toLowerCase(); // Convert text from search term to lowercase
        int index = 0; // to keep track of the index of the search term
        int count = 0; // to keep track of the number of occurrences found
        // Find all occurrences of the search term using a while loop that runs until the search term is not found (e.g. indexOf returns -1)
        while ((index = textContent.indexOf(searchLower, index)) != -1) {
            // Use a try-catch block to try to highlight any occurrences of the search term and catch any exceptions (e.g. BadLocationException)
            try {
                // Highlight the text from the current index to the end of the search term
                highlighter.addHighlight(index, index + searchText.length(), painter);
                index += searchText.length();// Move the index to the end of the search term
                count++; // Increment the count of occurrences found
                // If an exception occurs, print the stack trace
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        return count; // Return the number of occurrences found
    }

    // A method to search for a full word within the text area
    public int searchFullWord(String searchText) {
        highlighter.removeAllHighlights(); // Clear previous highlights
        String textContent = textArea.getText().toLowerCase(); // Convert text content to lowercase
        String searchLower = searchText.toLowerCase(); // Convert search text to lowercase
        int count = 0; // to keep track of the number of occurrences found
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(searchLower) + "\\b"); // Create a pattern for full word matches
        Matcher matcher = pattern.matcher(textContent); // Find matches in the text content and store them in a matcher object
        // Use a while loop to find all occurrences of a match
        while (matcher.find()) {
            // Use a try-catch block to try to highlight any occurrences of the search term and catch any exceptions (e.g. BadLocationException)
            try {
                highlighter.addHighlight(matcher.start(), matcher.end(), painter); // Highlight the matched text
                count++; // Increment the count of occurrences found
                // If an exception occurs, print the stack trace
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        // Return the number of occurrences found
        return count;
    }

    // A method to clear all highlights from the text area
    public void clearHighlights() {
        highlighter.removeAllHighlights();
    }

}