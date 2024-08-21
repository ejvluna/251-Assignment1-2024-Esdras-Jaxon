// This class is responsible for creating the  menu bar and handling the menu items actions.

// Import necessary packages/classes
import javax.swing.*; // to work with Swing components
import java.io.File; // to work with input/output files

// A class to encapsulate the attributes and methods of the menu handler
public class MenuHandler {
    // Set up the main components of the menu handler
    private final TextEditor textEditor; // to work with the text editor
    private final FileHandler fileHandler; // to work with files

    // A parameterized constructor to initialize a new menu handler object
    public MenuHandler(TextEditor textEditor, FileHandler fileHandler) {
        this.textEditor = textEditor; // Initialize a new text editor
        this.fileHandler = fileHandler; // Initialize a new file handler
    }

    // A method to create the menu bar and add menu items
    public JMenuBar createMenuBar() {
        // Set up a new menu bar
        JMenuBar menuBar = new JMenuBar();
        // Set up the required menus: File, Search, View, and Help
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu searchMenu = new JMenu("Search");
        menuBar.add(searchMenu);
        JMenu viewMenu = new JMenu("View");
        menuBar.add(viewMenu);
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        // Add menu items to the File menu
        addFileMenuItems(fileMenu);
        // Return the created menu bar
        return menuBar;
    }

    // A method to add menu items to the File menu
    private void addFileMenuItems(JMenu fileMenu) {
        // Set up a 'New' menu item with an action listener and add it to the File menu
        JMenuItem newMenuItem = new JMenuItem("New");
        fileMenu.add(newMenuItem);
        newMenuItem.addActionListener(e -> handleNewAction());
        // Set up an 'Open' menu item with an action listener and add it to the File menu
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        openMenuItem.addActionListener(e -> handleOpenAction());
        // Set up a 'Save' menu item with an action listener and add it to the File menu
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);
        saveMenuItem.addActionListener(e -> handleSaveAction());
        // Set up an 'Export as PDF' menu item with an action listener and add it to the File menu
        JMenuItem exportPdfMenuItem = new JMenuItem("Export as PDF");
        fileMenu.add(exportPdfMenuItem);
        exportPdfMenuItem.addActionListener(e -> handleExportPdfAction());
        // Set up a 'Quit' menu item with an action listener and add it to the File menu
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        fileMenu.add(quitMenuItem);
        quitMenuItem.addActionListener(e -> handleQuitAction());
    }

    // A helper method to handle the 'New' menu item action
    private void handleNewAction() {
        // Create a new text editor window
        new TextEditor();
    }

    // A helper method to handle the 'Open' menu item action
    private void handleOpenAction() {
        // Show the file chooser dialog and read the selected file
        int returnValue = textEditor.getFileChooser().showOpenDialog(textEditor);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = textEditor.getFileChooser().getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            // Find out the file type and then call the appropriate file handler method to read the file
            switch (getFileExtension(filePath)) {
                case "txt":
                    fileHandler.readTxtFile(selectedFile);
                    break;
                case "odt":
                    fileHandler.readOdtFile(selectedFile);
                    break;
                    // If the file type is not supported, show an error message
                default:
                    JOptionPane.showMessageDialog(textEditor, "Unsupported file type", "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    // A helper method to handle the 'Quit' menu item action
    private void handleQuitAction() {
        // Exit the application
        System.exit(0);
    }

    // A helper method to get the file extension from the file path
    private String getFileExtension(String filePath) {
        // Find the file type extension by locating the last '.' in the file path and returning the substring after it
        int dotIndex = filePath.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filePath.substring(dotIndex + 1).toLowerCase();
    }
}
        /*
        // Add menu items to the File menu
        JMenuItem newMenuItem = new JMenuItem("New");
        fileMenu.add(newMenuItem);
        newMenuItem.addActionListener(e -> new TextEditor());

        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        openMenuItem.addActionListener(e -> {
            int returnValue = textEditor.getFileChooser().showOpenDialog(textEditor);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = textEditor.getFileChooser().getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                switch (getFileExtension(filePath)) {
                    case "txt":
                        fileHandler.readTxtFile(selectedFile);
                        break;
                    case "odt":
                        fileHandler.readOdtFile(selectedFile);
                        break;
                    default:
                        JOptionPane.showMessageDialog(textEditor, "Unsupported file type", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);
        saveMenuItem.addActionListener(e -> {
            int returnValue = textEditor.getFileChooser().showSaveDialog(textEditor);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = textEditor.getFileChooser().getSelectedFile();
                fileHandler.saveTxtFile(selectedFile);
            }
        });

        JMenuItem exportPdfMenuItem = new JMenuItem("Export as PDF");
        fileMenu.add(exportPdfMenuItem);
        exportPdfMenuItem.addActionListener(e -> {
            int returnValue = textEditor.getFileChooser().showSaveDialog(textEditor);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = textEditor.getFileChooser().getSelectedFile();
                fileHandler.exportToPdf(selectedFile);
            }
        });

        JMenuItem quitMenuItem = new JMenuItem("Quit");
        fileMenu.add(quitMenuItem);
        quitMenuItem.addActionListener(e -> System.exit(0));

        return menuBar;
    }
    private String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filePath.substring(dotIndex + 1).toLowerCase();
    }
}
*/
