// Import necessary packages
import javax.swing.JMenu; // to create a menu
import javax.swing.JMenuItem; // to create a menu item

/**
 * The \`EditMenuHandler\` class is responsible for adding menu items to the Edit menu in the application's menu bar.
 * It provides methods to add common edit actions such as Select All, Copy, Paste, and Cut.
 * These actions are linked to the corresponding methods in the \`ViewHandler\` class.
 * \@author Esdras Luna
 * \@version 1.0
 * \@since 2024-08-23
 */

public class EditMenuHandler {
    // === ATTRIBUTES ===
    private final EditHandler editHandler;
    // === CONSTRUCTOR ===
    // A parameterized constructor
    public EditMenuHandler(EditHandler editHandler) {
        // Initialize the viewHandler attribute with the parameter value
        this.editHandler = editHandler;
    }
    // === METHODS ===
    public void addEditMenuItems(JMenu editMenu) {
        // Create a new menu item for 'Select All' and add an action listener to it, then add it to the Edit menu
        JMenuItem selectMenuItem = new JMenuItem("Select All");
        selectMenuItem.addActionListener(e -> editHandler.selectAll());
        editMenu.add(selectMenuItem);
        // Create a new menu item for 'Copy' and add an action listener to it, then add it to the Edit menu
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.addActionListener(e -> editHandler.copy());
        editMenu.add(copyMenuItem);
        // Create a new menu item for 'Paste' and add an action listener to it, then add it to the Edit menu
        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.addActionListener(e -> editHandler.paste());
        editMenu.add(pasteMenuItem);
        // Create a new menu item for 'Cut' and add an action listener to it, then add it to the Edit menu
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.addActionListener(e -> editHandler.cut());
        editMenu.add(cutMenuItem);
    }
}