// Import the necessary packages/classes
import java.awt.Color; // to work with colors
import java.awt.Font; // to work with fonts
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea; // to work with the RSyntaxTextArea component

/**
 * The ConfigApplier class is responsible for applying the configuration settings to the text editor.
 * This class takes a ConfigLoader object as input and applies the settings to the text area.
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-23
 */

// A class to encapsulate the attributes and methods of the configuration applier
public class ConfigApplier {

    // === ATTRIBUTES ===
    private final ConfigLoader config;

    // === CONSTRUCTOR ===
    // A parameterized constructor to initialize a new configuration applier object
    public ConfigApplier(ConfigLoader config) {
        this.config = config;
    }

    // === METHODS ===
    // A method to apply the configuration settings to the text area
    public void applyConfig(RSyntaxTextArea textArea) {
        // Get the font format, size, and color from the configuration file
        String textFormat = config.getTextFormat(); // Get the text format (i.e., font type)
        int fontSize = config.getFontSize(); // Get the font size
        String fontColor = config.getFontColor(); // Get the font color

        // Get the font type and size from the configuration file and apply it to the text area
        Font font = new Font(textFormat, Font.PLAIN, fontSize); // Store the font type and size
        textArea.setFont(font); // Set the text area font to the specified font

        // Get the font color from the configuration file and convert it to a Color object, then apply it to the text area
        Color color = getColorFromString(fontColor); // Get the font color as a Color object
        textArea.setForeground(color); // Set the text area color to the font color
    }

    // A private method to convert a color string to a Color object
    private Color getColorFromString(String colorString) {
        //  Create a local variable to store the color object
        Color color;
        // Try to get the color by name (e.g., "red", "blue", etc.)
        try {
            color = (Color) Color.class.getField(colorString.toUpperCase()).get(null);
        } catch (Exception e) {
            // If the color name is not found, try to decode the color from a hex string
            try {
                color = Color.decode(colorString);
                // If both methods fail, print an error message and return the default color (gray)
            } catch (NumberFormatException ex) {
                System.err.println("Invalid color format: " + colorString + ". Defaulting to default color.");
                color = Color.black; // Specify the default color
            }
        }
    return color; // Return the color object
    }
}