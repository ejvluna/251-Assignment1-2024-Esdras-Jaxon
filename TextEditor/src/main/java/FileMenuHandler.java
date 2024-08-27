// Import required packages
import javax.swing.JMenu; // to create a menu
import javax.swing.JMenuItem; // to create a menu item
import javax.swing.JOptionPane; // to show dialog boxes
import javax.swing.JFileChooser; // to create a file chooser
import java.io.File; // to work with files
import org.fife.ui.rsyntaxtextarea.SyntaxConstants; // to set syntax highlighting

/**
 * The \`FileMenuHandler\` class is responsible for managing the file menu in the application's menu bar.
 * It provides methods to add menu items and handle actions such as New, Open, Save, Export as PDF, Print, and Quit.
 * This class interacts with the \`TextEditor\` and \`FileHandler\` classes to perform file-related operations.
 * \@author Esdras Luna
 * \@version 1.0
 * \@since 2024-08-23
 */

public class FileMenuHandler {

    // === ATTRIBUTES ===
    private final TextEditor textEditor;
    private final FileHandler fileHandler;

    // === CONSTRUCTOR ===

    // A parameterized constructor that initializes the textEditor and fileHandler attributes
    public FileMenuHandler(TextEditor textEditor, FileHandler fileHandler) {
        this.textEditor = textEditor;
        this.fileHandler = fileHandler;
    }

    // === METHODS ===

    // A method to add file menu items to the file menu
    public void addFileMenuItems(JMenu fileMenu) {
        // Create a 'New' menu item, add it to the file menu, and add an action listener to it
        JMenuItem newMenuItem = new JMenuItem("New");
        fileMenu.add(newMenuItem);
        newMenuItem.addActionListener(e -> handleNewAction());

        // Create an 'Open' menu item, add it to the file menu, and add an action listener to it
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(openMenuItem);
        openMenuItem.addActionListener(e -> handleOpenAction());

        // Create a 'Save' menu item, add it to the file menu, and add an action listener to it
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);
        saveMenuItem.addActionListener(e -> handleSaveAction());

        // Create an 'Export as PDF' menu item, add it to the file menu, and add an action listener to it
        JMenuItem exportPdfMenuItem = new JMenuItem("Export as PDF");
        fileMenu.add(exportPdfMenuItem);
        exportPdfMenuItem.addActionListener(e -> handleExportPdfAction());

        // Create a 'Print' menu item, add it to the file menu, and add an action listener to it
        JMenuItem printMenuItem = new JMenuItem("Print");
        fileMenu.add(printMenuItem);
        printMenuItem.addActionListener(e -> handlePrintAction());

        // Create a 'Quit' menu item, add it to the file menu, and add an action listener to it
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        fileMenu.add(quitMenuItem);
        quitMenuItem.addActionListener(e -> handleQuitAction());
    }

    // A method to handle the 'New' action (e.g. open a new text editor window)
    private void handleNewAction() {
        new TextEditor();
    }

    // A method to handle the 'Open' action (e.g. open a file)
    private void handleOpenAction() {
        // Use a try-catch block to handle exceptions
        try {
            // Show a file chooser dialog and store the return value
            int returnValue = textEditor.getFileChooser().showOpenDialog(textEditor);
            // If the user selected a file
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File selectedFile = textEditor.getFileChooser().getSelectedFile();
                // If the file is valid
                if (isFileValid(selectedFile)) {
                    // Handle the file based on its extension
                    handleFile(selectedFile);
                } else {
                    // Show an error message if the file is not valid
                    fileHandler.handleMessage("File does not exist or is not readable", "Error", JOptionPane.ERROR_MESSAGE, null);
                }
            }
        } catch (Exception ex) {
            // Show an error message if an exception occurs
            fileHandler.handleMessage("An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, ex);
        }
    }
    // A method to check if the file is valid (e.g. exists and is readable)
    private boolean isFileValid(File file) {
        return file.exists() && file.canRead();
    }

    // A method to handle the file based on its extension
    private void handleFile(File file) {
        // Get the file path
        String filePath = file.getAbsolutePath();
        // Use a switch statement to handle the file based on its extension type
        switch (getFileExtension(filePath)) {
            case "txt":
                fileHandler.readTxtFile(file);
                break;
            case "odt":
                fileHandler.readOdtFile(file);
                break;
            case "rtf":
                fileHandler.readRtfFile(file);
                break;
            case "java":
                fileHandler.readSourceCodeFile(file, SyntaxConstants.SYNTAX_STYLE_JAVA);
                break;
            case "py":
                fileHandler.readSourceCodeFile(file, SyntaxConstants.SYNTAX_STYLE_PYTHON);
                break;
            case "cpp":
                fileHandler.readSourceCodeFile(file, SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                break;
            case "js":
                fileHandler.readSourceCodeFile(file, SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
                break;
            default:
                fileHandler.handleMessage("Unsupported file type", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    // A method to handle the 'Save' action (e.g. save a file)
    private void handleSaveAction() {
        // Show a file chooser dialog and store the return value
        int returnValue = textEditor.getFileChooser().showSaveDialog(textEditor);
        // If the user selected a file
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Get the selected file and its path
            File selectedFile = textEditor.getFileChooser().getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            // If the file path does not end with '.txt', add '.txt' to the file path
            if (!filePath.toLowerCase().endsWith(".txt")) {
                selectedFile = new File(filePath + ".txt");
            }
            // Save the text editor content to the selected file
            fileHandler.saveTxtFile(selectedFile);
        }
    }

    // A method to handle the 'Export as PDF' action (e.g. export a file as a PDF)
    private void handleExportPdfAction() {
        // Show a file chooser dialog to select the file to export as a PDF
        int returnValue = textEditor.getFileChooser().showSaveDialog(textEditor);
        // If the user selects a file
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Store the file path selected by the user
            File selectedFile = textEditor.getFileChooser().getSelectedFile();
            // Export the text editor content as a PDF file to the selected file path
            fileHandler.exportToPdf(selectedFile);
        }
    }

    // A method to handle the 'Print' action (e.g. print the text editor content)
    private void handlePrintAction() {
        new PrintHandler(textEditor.getTextArea()).print();
    }

    // A method to handle the 'Quit' action (e.g. exit the application)
    private void handleQuitAction() {
        System.exit(0);
    }

    // A method to get the file extension from a file path
    private String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filePath.substring(dotIndex + 1).toLowerCase();
    }
}