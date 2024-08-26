// Import necessary packages/classes
import org.junit.jupiter.api.*; // to use JUnit 5 testing annotations
import static org.junit.jupiter.api.Assertions.*; // to use JUnit 5 assertions
import java.io.*; // to work with input/output streams (e.g. files)
import javax.swing.*; // to work with Swing components (e.g. JTextArea, JFileChooser)
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea; // to work with RSyntaxTextArea components
import org.fife.ui.rsyntaxtextarea.SyntaxConstants; // to work with syntax highlighting constants

/**
 * A class to test the functionality of the TimeDateHandler class.
 */
public class FileHandlerTests {

    // === ATTRIBUTES ===
    private FileHandler fileHandler;
    private RSyntaxTextArea textArea;
    private JFileChooser fileChooser;

    // File paths for the test files stored in the resources directory of the test folder
    private final String testTxtFilePath = "src/test/resources/testFile.txt";
    private final String testOdtFilePath = "src/test/resources/testFile.odt";
    private final String testRtfFilePath = "src/test/resources/testFile.rtf";
    private final String testJavaFilePath = "src/test/resources/testFile.java";
    private final String testPyFilePath = "src/test/resources/testFile.py";
    private final String testCppFilePath = "src/test/resources/testFile.cpp";
    private final String testJsFilePath = "src/test/resources/testFile.js";
    private final String testContent = "This is a test content for the TextEditor.";

    // === TEST METHODS ===

    // A method to set up the test environment before each test method
    @BeforeEach
    public void setUp() {
        // New instances of RSyntaxTextArea, JFileChooser, and FileHandler
        textArea = new RSyntaxTextArea();
        fileChooser = new JFileChooser();
        fileHandler = new FileHandler(textArea, fileChooser);
    }

    // === TEST CASES ===

    // A test case to test the readTxtFile method of the FileHandler class
    @Test
    public void testReadTxtFile() throws IOException {
        // Read the test file and compare the content with the expected content
        fileHandler.readTxtFile(new File(testTxtFilePath));
        assertEquals(testContent, fileHandler.getTextArea().getText().trim());
    }

    // A test case to test the readOdtFile method of the FileHandler class
    @Test
    public void testReadOdtFile() throws IOException {
        // Read the test file and compare the content with the expected content
        fileHandler.readOdtFile(new File(testOdtFilePath));
        assertEquals(testContent, fileHandler.getTextArea().getText().trim());
    }

    // A test case to test the readRtfFile method of the FileHandler class
    @Test
    public void testReadRtfFile() throws IOException {
        // Read the test file and compare the content with the expected content
        fileHandler.readRtfFile(new File(testRtfFilePath));
        assertEquals(testContent, fileHandler.getTextArea().getText().trim());
    }

    // A test case to test the readJavaFile method of the FileHandler class
    @Test
    public void testReadJavaFile() throws IOException {
        // Read the test file and compare the content with the expected content
        fileHandler.readSourceCodeFile(new File(testJavaFilePath), SyntaxConstants.SYNTAX_STYLE_JAVA);
        assertTrue(fileHandler.getTextArea().getText().contains(testContent));
    }

    // A test case to test the readPyFile method of the FileHandler class
    @Test
    public void testReadPyFile() throws IOException {
        // Read the test file and compare the content with the expected content
        fileHandler.readSourceCodeFile(new File(testPyFilePath), SyntaxConstants.SYNTAX_STYLE_PYTHON);
        assertTrue(fileHandler.getTextArea().getText().contains(testContent));
    }

    // A test case to test the readCppFile method of the FileHandler class
    @Test
    public void testReadCppFile() throws IOException {
        // Read the test file and compare the content with the expected content
        fileHandler.readSourceCodeFile(new File(testCppFilePath), SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        assertTrue(fileHandler.getTextArea().getText().contains(testContent));
    }

    // A test case to test the readJsFile method of the FileHandler class
    @Test
    public void testReadJsFile() throws IOException {
        // Read the test file and compare the content with the expected content
        fileHandler.readSourceCodeFile(new File(testJsFilePath), SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        assertTrue(fileHandler.getTextArea().getText().contains(testContent));
    }

    // A test case to test the saveTxtFile method of the FileHandler class
    @Test
    public void testSaveTxtFile() throws IOException {
        // Create a string with the test content and set it in the text area
        String testContent = "This is a test content for the TextEditor.";
        textArea.setText(testContent);
        // Create a temporary file to save the content
        File tempFile = File.createTempFile("testFile", ".txt");
        fileHandler.saveTxtFile(tempFile); // Save the content to the temporary file
        StringBuilder content = new StringBuilder(); // Create a string builder to store the content
        // Use a try-with-resources block to try reading the content from the temporary file line by line using a buffered reader
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        // Compare the content read from the file with the expected content (e.g. testContent)
        assertEquals(testContent, content.toString().trim());
        tempFile.delete(); // Delete the temporary file after the test
    }

    // A method to clean up the test environment after each test case
    @AfterEach
    public void tearDown() {
    }
}

