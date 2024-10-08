// Import the SwingUtilities class to create a new thread for the Swing GUI
import javax.swing.SwingUtilities; // to create a new thread for the Swing GUI
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme; // to set and apply FlatLaf Theme

/**
 * The `Main` class encapsulates the main method of the text editor application.
 * It initializes the application by loading configuration settings, setting up the theme,
 * and creating the main text editor window.

 * \@author Esdras Luna
 * \@version 1.0
 * \@since 2024-08-23
 */

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