// Import required Yaml and Map classes for configuration file parsing
import org.yaml.snakeyaml.Yaml; // to parse YAML configuration files
import java.io.InputStream; // to read from an input stream
import java.util.Map; // to store key-value pairs from the configuration file

/**
 * The ConfigLoader class is responsible for loading the configuration settings from a YAML file.
 * This class reads the configuration file and provides methods to access the settings.
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-23
 */

public class ConfigLoader {

    // ===  ATTRIBUTES ===
    private Map<String, Object> config; // a map to store key-value pairs from the configuration file

    // === CONSTRUCTORS ===
    // A non-parameterized constructor to initialize a new configuration loader object
    public ConfigLoader(String configFileName) {
        loadConfig(configFileName); // Load the configuration file from the specified file path
    }

    // === METHODS ===
    // A private method to load the configuration file and store its contents in the map
    private void loadConfig(String filePath) {
        // Use a try-with-resources block to try reading the configuration file
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            // If the input stream is null (e.g. file not found), throw an exception
            if (inputStream == null) {
                throw new IllegalArgumentException("Config file not found: " + filePath);
            }
            // Otherwise, load the configuration file using the YAML parser and store it in the 'config' map (e.g. key-value pairs)
            Yaml yaml = new Yaml();
            config = yaml.load(inputStream);
            // Print a success message if the configuration is loaded successfully
            System.out.println("Configuration loaded successfully from " + filePath);
            // Catch any exceptions that occur during the loading process and print an error message
        } catch (Exception e) {
            System.err.println("Error loading config from " + filePath + ": " + e.getMessage());
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    // A getter method to retrieve the text format from the configuration file
    public String getTextFormat() {
        return getValue("textFormat", String.class);
    }

    // A getter method to retrieve the font size from the configuration file
    public int getFontSize() {
        return getValue("fontSize", Integer.class);
    }

    // A getter method to retrieve the font color from the configuration file
    public String getFontColor() {
        return getValue("fontColor", String.class);
    }

    // A generic method to retrieve a value from the configuration map
    private <T> T getValue(String key, Class<T> type) {
        // Get the value associated with the specified key from the configuration map
        Object value = config.get(key);
        // If the key is missing or the value is not of the expected type, throw an exception
        if (!type.isInstance(value)) {
            throw new IllegalStateException("Invalid or missing configuration for key: " + key);
        }
        // Otherwise, cast the value to the specified type and return it
        return type.cast(value);
    }
}

