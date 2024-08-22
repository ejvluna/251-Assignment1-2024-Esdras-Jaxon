// Import necessary packages/classes
import javax.swing.*; // to work with Swing components
import java.io.File; // to work with input/output files

/**
 * The MenuHandler class is responsible for creating the menu bar and handling the menu items actions in a simple text editor application.
 * This class provides functionality to manage file operations, print operations, and display the 'About' dialog.
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-21
 */

// A class to encapsulate the attributes and methods of the menu handler
public class MenuHandler {

    // === CLASS ATTRIBUTES ===

    // Attributes of the menu handler class
    private final TextEditor textEditor; // to work with the text editor
    private final FileHandler fileHandler; // to work with file operations
    private final PrintHandler printHandler; // to work with print operations
    private final AboutHandler aboutHandler; // to work with the 'About' dialog
    private final TimeDateHandler timeDateHandler; // to work with time and date operations
    private final SearchHandler searchHandler; // to work with search operations

    // === CLASS METHODS ===

    // A parameterized constructor to initialize a new menu handler object with associated objects
    public MenuHandler(TextEditor textEditor, FileHandler fileHandler) {
        // Initialize associated objects
        this.textEditor = textEditor;
        this.fileHandler = fileHandler;
        this.printHandler = new PrintHandler(textEditor.getTextArea());
        this.aboutHandler = new AboutHandler();
        this.timeDateHandler = new TimeDateHandler(textEditor.getTextArea());
        this.searchHandler = new SearchHandler(textEditor.getTextArea());
    } // end of constructor

    // A method to create a menu bar and add menu items
    public JMenuBar createMenuBar() {

        // Create a new menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create Main Menus and add them to the Menu bar
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu searchMenu = new JMenu("Search");
        menuBar.add(searchMenu);
        JMenu viewMenu = new JMenu("View");
        menuBar.add(viewMenu);
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // Call class methods to add the respective menu items to each menu
        addFileMenuItems(fileMenu);
        addHelpMenuItems(helpMenu);
        addViewMenuItems(viewMenu);
        addSearchMenuItems(searchMenu);

        // Return the menu bar with the added menus
        return menuBar;
    }

    // A method to add menu items to the File menu
    private void addFileMenuItems(JMenu fileMenu) {

        // Create a 'New' menu item with an action listener and add it to the File menu
        JMenuItem newMenuItem = new JMenuItem("New");
        fileMenu.add(newMenuItem);
        newMenuItem.addActionListener(e -> handleNewAction());

        // Create an 'Open' menu item with an action listener and add it to the File menu
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        openMenuItem.addActionListener(e -> handleOpenAction());

        // Create a 'Save' menu item with an action listener and add it to the File menu
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);
        saveMenuItem.addActionListener(e -> handleSaveAction());

        // Create an 'Export as PDF' menu item with an action listener and add it to the File menu
        JMenuItem exportPdfMenuItem = new JMenuItem("Export as PDF");
        fileMenu.add(exportPdfMenuItem);
        exportPdfMenuItem.addActionListener(e -> handleExportPdfAction());

        // Create a 'Print' menu item with an action listener and add it to the File menu
        JMenuItem printMenuItem = new JMenuItem("Print");
        fileMenu.add(printMenuItem);
        printMenuItem.addActionListener(e -> handlePrintAction());

        // Create a 'Quit' menu item with an action listener and add it to the File menu
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        fileMenu.add(quitMenuItem);
        quitMenuItem.addActionListener(e -> handleQuitAction());
    }

    // A method to add menu items to the Help menu
    private void addHelpMenuItems(JMenu helpMenu) {

        // Create a menu item for 'About' with an action listener and add it to the Help menu
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(e -> handleAboutAction());
    }

    // A method to add menu items to the 'View' menu
    private void addViewMenuItems(JMenu viewMenu) {

        // Create a menu item for 'Insert Time/Date' with an action listener and add it to the View menu
        JMenuItem timeDateMenuItem = new JMenuItem("Insert Time/Date");
        viewMenu.add(timeDateMenuItem);
        timeDateMenuItem.addActionListener(e -> handleTimeDateAction());
    }

    // A method to add menu items to the 'Search' menu
    private void addSearchMenuItems(JMenu searchMenu) {
        // A menu item for 'Partial Word Match' with an action listener and add it to the Search menu
        JMenuItem findMenuItem = new JMenuItem("Partial Word Match");
        searchMenu.add(findMenuItem);
        findMenuItem.addActionListener(e -> handleFindAction());

        // A menu item for 'Exact Word Match' with an action listener and add it to the Search menu
        JMenuItem searchFullWordMenuItem = new JMenuItem("Exact Word Match");
        searchMenu.add(searchFullWordMenuItem);
        searchFullWordMenuItem.addActionListener(e -> handleFindFullWordAction());

        // A menu item for 'Clear Highlights' with an action listener and add it to the Search menu
        JMenuItem clearHighlightsMenuItem = new JMenuItem("Clear Highlights");
        searchMenu.add(clearHighlightsMenuItem);
        clearHighlightsMenuItem.addActionListener(e -> handleClearHighlightsAction());
    }

    // --- 'File' Menu helper methods

    // A helper method to handle the 'New' menu item action
    private void handleNewAction() {
        // Create a new text editor window
        new TextEditor();
    }

    // A helper method to handle the 'Open' menu item action
    private void handleOpenAction() {
        try {
        // Show the file chooser dialog and read the selected file
        int returnValue = textEditor.getFileChooser().showOpenDialog(textEditor);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.getFileChooser().getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            // Determine the file type based on the file extension and call the appropriate file handler method
            switch (getFileExtension(filePath)) {
                case "txt": // If the file type is 'txt', call the readTxtFile method
                    fileHandler.readTxtFile(selectedFile);
                    break;
                case "odt": // If the file type is 'odt', call the readOdtFile method
                    fileHandler.readOdtFile(selectedFile);
                    break;
                case "rtf": // If the file type is 'rtf', call the readRtfFile method
                    fileHandler.readRtfFile(selectedFile);
                    break;
                    // If the file type is not supported, show an error message
                default:
                    JOptionPane.showMessageDialog(textEditor, "Unsupported file type", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        } catch (Exception ex) {
            System.err.println("Unexpected error in handleOpenAction: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(textEditor, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A helper method to handle the 'Save' menu item action
    private void handleSaveAction() {
        // Show the file chooser dialog
        int returnValue = textEditor.getFileChooser().showSaveDialog(textEditor);
        // If the user selects a directory, get the file path
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.getFileChooser().getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            // If the file path does not end with '.txt', add it to the file name to ensure it is saved as a .txt file by default
            if (!filePath.toLowerCase().endsWith(".txt")) {
                selectedFile = new File(filePath + ".txt");
            }
            // Call a file handler class method to save the text content to the selected file path
            fileHandler.saveTxtFile(selectedFile);
        }
    }

    // A helper method to handle the 'Export as PDF' menu item action
    private void handleExportPdfAction() {
        // Show the file chooser dialog
        int returnValue = textEditor.getFileChooser().showSaveDialog(textEditor);
        // If the user selects a file, export the text content as a PDF file
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.getFileChooser().getSelectedFile(); // Get the selected file
            fileHandler.exportToPdf(selectedFile); // Call a file handler class method to export the text content as a PDF file
        }
    }

    // A helper method to handle the 'Print' menu item action
    private void handlePrintAction() {
        // Call the print handler class method to print the text content
        printHandler.print();
    }

    // A helper method to handle the 'Quit' menu item action
    private void handleQuitAction() {
        // Exit the application
        System.exit(0);
    }

    // --- 'Help' Menu helper methods

    // A helper method to handle the 'About' menu item action
    private void handleAboutAction() {
        // Call an 'About' handler class method to show the 'About' dialog
        aboutHandler.showAboutDialog();
    }

    // --- 'View' Menu helper methods

    // A helper method to handle the 'Insert Time/Date' menu item action
    private void handleTimeDateAction() {
        System.out.println("DEBUG: handleTimeDateAction called"); // DEBUG: Print a message to the console
        // Call the time and date handler class method to insert the current time and date
        timeDateHandler.insertCurrentTimeDate();
    }

    // --- 'Search' Menu helper methods

    // A helper method to handle the 'Find' menu item action
    private void handleFindAction() {
        // Call the search handler class method to display the search dialog
        searchHandler.showSearchDialog(false); // false to indicate that it is not a full word search
    }

    // A helper method to handle the 'Search Full Word' menu item action
    private void handleFindFullWordAction() {
        // Call the search handler class method to display the search dialog
        searchHandler.showSearchDialog(true ); // true to indicate that it is a full word search
    }

    // A helper method to handle the 'Clear Highlights' menu item action
    private void handleClearHighlightsAction() {
        searchHandler.clearHighlights();
    }


    // -- Other helper methods
    // A helper method to get the file extension from the file path
    private String getFileExtension(String filePath) {
        // Find the file type extension by locating the last '.' in the file path and returning the substring after it
        int dotIndex = filePath.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filePath.substring(dotIndex + 1).toLowerCase();
    }
}