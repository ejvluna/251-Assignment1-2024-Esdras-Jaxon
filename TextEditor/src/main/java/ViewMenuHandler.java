// Import necessary libraries
import javax.swing.JMenu; // to create a menu
import javax.swing.JMenuItem; // to create a menu item

/**
 * The ViewMenuHandler class is responsible for handling the view menu items of the text editor application.
 * This class provides functionality to add view menu items to the view menu.

 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-23
 */

public class ViewMenuHandler {
    // === ATTRIBUTES ===
    private final TimeDateHandler timeDateHandler;

    // === CONSTRUCTOR ===
    public ViewMenuHandler(TimeDateHandler timeDateHandler) {
        this.timeDateHandler = timeDateHandler;
    }

    // === METHODS ===

    // A method to add view menu items to the view menu
    public void addViewMenuItems(JMenu viewMenu) {
        // Create a menu item to insert time/date
        JMenuItem timeDateMenuItem = new JMenuItem("Insert Time/Date");
        // Add an action listener to the menu item
        timeDateMenuItem.addActionListener(e -> timeDateHandler.insertCurrentTimeDate());
        // Add the menu item to the view menu
        viewMenu.add(timeDateMenuItem);
    }
}