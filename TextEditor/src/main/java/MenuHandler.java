// Import necessary packages
import javax.swing.JMenu; // to create a menu
import javax.swing.JMenuBar; // to create a menu bar

/**
 * The `MenuHandler` class is responsible for creating and managing the menu bar in the text editor application.
 * It initializes various menu handlers and provides a method to create the menu bar with all the necessary menus and menu items.
 * This class interacts with the `TextEditor` and `FileHandler` classes to perform menu-related operations.

 * \@author Esdras Luna
 * \@version 1.0
 * \@since 2024-08-23
 */

public class MenuHandler {
    // Create instances of the menu handlers
    private final FileMenuHandler fileMenuHandler;
    private final EditMenuHandler editMenuHandler;
    private final SearchMenuHandler searchMenuHandler;
    private final ViewMenuHandler viewMenuHandler;
    private final HelpMenuHandler helpMenuHandler;

    // A class constructor to initialize the menu handlers of a new menu handler instance
    public MenuHandler(TextEditor textEditor, FileHandler fileHandler) {
        this.fileMenuHandler = new FileMenuHandler(textEditor, fileHandler);
        this.editMenuHandler = new EditMenuHandler(new EditHandler(textEditor.getTextArea()));
        this.searchMenuHandler = new SearchMenuHandler(new SearchHandler(textEditor.getTextArea()));
        this.viewMenuHandler = new ViewMenuHandler(new TimeDateHandler(textEditor.getTextArea()));
        this.helpMenuHandler = new HelpMenuHandler(new AboutHandler());
    }

    // A method to create the menu bar for the text editor application
    public JMenuBar createMenuBar() {
        // Create a new menu bar
        JMenuBar menuBar = new JMenuBar();
        // Create a new 'File' menu, add it to the menu bar, and add menu items to the menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenuHandler.addFileMenuItems(fileMenu);
        // Create a new 'Edit' menu, add it to the menu bar, and add menu items to the menu
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        editMenuHandler.addEditMenuItems(editMenu);
        // Create a new 'Search' menu, add it to the menu bar, and add menu items to the menu
        JMenu searchMenu = new JMenu("Search");
        menuBar.add(searchMenu);
        searchMenuHandler.addSearchMenuItems(searchMenu);
        // Create a new 'View' menu, add it to the menu bar, and add menu items to the menu
        JMenu viewMenu = new JMenu("View");
        menuBar.add(viewMenu);
        viewMenuHandler.addViewMenuItems(viewMenu);
        // Create a new 'Help' menu, add it to the menu bar, and add menu items to the menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        helpMenuHandler.addHelpMenuItems(helpMenu);
        // Return the menu bar with all the menus and menu items
        return menuBar;
    }
}