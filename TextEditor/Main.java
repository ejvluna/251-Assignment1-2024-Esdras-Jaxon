// Import the SwingUtilities class to create a new thread for the Swing GUI
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;

// A class to encapsulate the main method of the text editor application
public class Main {
    // A main method to start the text editor application
    public static void main(String[] args) {
        // Create a new configuration loader object to load the config.yaml file with the settings
        ConfigLoader config = new ConfigLoader("config.yaml");
        // Set and Apply FlatLaf Theme
        FlatCyanLightIJTheme.setup();
        // Create a new text editor window to start the application: use invokeLater to run on the EDT to avoid threading issues
        SwingUtilities.invokeLater(() -> {
            // Create a new text editor instance
            TextEditor editor = new TextEditor();
            // Apply the configuration settings to the text editor from the configuration file (overrides any defaults specified in the editor constructor)
            ConfigApplier configApplier = new ConfigApplier(config);
            // Apply the configuration settings to the text area in the editor
            configApplier.applyConfig(editor.getTextArea());
        });
    }
}