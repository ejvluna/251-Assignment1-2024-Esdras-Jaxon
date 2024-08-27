// Import the required classes
import javax.swing.JMenu; // to create a menu
import javax.swing.JMenuItem; // to create a menu item

/**
 * The `HelpMenuHandler` class is responsible for managing the Help menu in the application's menu bar.
 * It provides methods to add menu items and handle actions such as displaying the About dialog.
 * This class interacts with the `AboutHandler` class to perform help-related operations.

 * \@author Esdras Luna
 * \@version 1.0
 * \@since 2024-08-23
 */

public class HelpMenuHandler {

    // === ATTRIBUTES ===
    private final AboutHandler aboutHandler;

    // === CONSTRUCTOR ===

    // A parameterized constructor that initializes the `aboutHandler` attribute.
    public HelpMenuHandler(AboutHandler aboutHandler) {
        this.aboutHandler = aboutHandler;
    }

    // === METHODS ===

    // A method that adds the Help menu items to the Help menu.
    public void addHelpMenuItems(JMenu helpMenu) {
        // Create a menu item for the About option
        JMenuItem aboutMenuItem = new JMenuItem("About");
        // Add an action listener to the menu item
        aboutMenuItem.addActionListener(e -> aboutHandler.showAboutDialog());
        // Add the menu item to the Help menu
        helpMenu.add(aboutMenuItem);
    }
}
