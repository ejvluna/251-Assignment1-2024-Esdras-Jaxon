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

// A class to encapsulate the attributes and methods of the configuration loader
public class ConfigLoader {

    // === CLASS ATTRIBUTES ===
    private Map<String, Object> config; // a map to store key-value pairs from the configuration file

    // === CLASS METHODS ===

    // A non-parameterized constructor to initialize a new configuration loader object
    public ConfigLoader(String configFileName) {
        loadConfig(configFileName); // Load the configuration file from the specified file path
    }

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
        return getNestedValue("default.textFormat", String.class);
    }

    // A getter method to retrieve the font size from the configuration file
    public int getFontSize() {
        return getNestedValue("default.fontSize", Integer.class);
    }

    // A getter method to retrieve the font color from the configuration file
    public String getFontColor() {
        return getNestedValue("default.fontColor", String.class);
    }

    // A generic method to retrieve a nested value from the configuration map
    @SuppressWarnings("unchecked")
    private <T> T getNestedValue(String key, Class<T> type) {
        String[] parts = key.split("\\."); // Split the key into parts using the dot separator
        Map<String, Object> current = config; // Start with the top-level map
        // Iterate over the parts of the key to traverse the nested structure
        for (int i = 0; i < parts.length - 1; i++) {
            // Get the next level of the nested map based on the current key part
            current = (Map<String, Object>) current.get(parts[i]);
            // If the current map is null, throw an exception indicating the key was not found
            if (current == null) {
                throw new IllegalStateException("Configuration key not found: " + key);
            }
        }
        // Get the value of the final key part and cast it to the specified type
        Object value = current.get(parts[parts.length - 1]);
        // If the value is null or not of the specified type, throw an exception
        if (value == null || !type.isInstance(value)) {
            throw new IllegalStateException("Invalid or missing configuration for key: " + key);
        }
        // Return the value cast to the specified type
        return type.cast(value);
    }
}

    // A private method to load the configuration file and store its contents in the map