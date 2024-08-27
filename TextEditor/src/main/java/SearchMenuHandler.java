// Importing necessary packages
import javax.swing.JMenu; // to create a menu
import javax.swing.JMenuItem; // to create a menu item

/**
 * The `SearchMenuHandler` class is responsible for adding search-related menu items to the search menu in the text editor application.
 * It interacts with the `SearchHandler` class to perform search operations such as partial word match, exact word match, and clearing highlights.

 * \@author Esdras Luna
 * \@version 1.0
 * \@since 2024-08-23
 */

public class SearchMenuHandler {

    // === ATTRIBUTES ===
    private final SearchHandler searchHandler;

    // === CONSTRUCTOR ===

    // A parameterized constructor that initializes the `searchHandler` attribute.
    public SearchMenuHandler(SearchHandler searchHandler) {
        this.searchHandler = searchHandler;
    }

    // === METHODS ===

    // A method that adds search-related menu items to the search menu.
    public void addSearchMenuItems(JMenu searchMenu) {
        // Create a menu item for  'Partial Word Match'
        JMenuItem findMenuItem = new JMenuItem("Partial Word Match");
        // Add an action listener to the menu item that shows the search dialog when clicked
        findMenuItem.addActionListener(e -> searchHandler.showSearchDialog(false));
        // Add the menu item to the search menu
        searchMenu.add(findMenuItem);

        // Create a menu item for 'Exact Word Match'
        JMenuItem searchFullWordMenuItem = new JMenuItem("Exact Word Match");
        // Add an action listener to the menu item that shows the search dialog when clicked
        searchFullWordMenuItem.addActionListener(e -> searchHandler.showSearchDialog(true));
        // Add the menu item to the search menu
        searchMenu.add(searchFullWordMenuItem);

        // Create a menu item for 'Clear Highlights'
        JMenuItem clearHighlightsMenuItem = new JMenuItem("Clear Highlights");
        // Add an action listener to the menu item that clears the highlights when clicked
        clearHighlightsMenuItem.addActionListener(e -> searchHandler.clearHighlights());
        // Add the menu item to the search menu
        searchMenu.add(clearHighlightsMenuItem);
    }
}
