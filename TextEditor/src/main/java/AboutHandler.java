/**
 * The AboutHandler class encapsulates the attributes and methods for handling the "About" dialog in a simple text editor application.
 * This class provides functionality to display information about the team members and the application itself.
 * @author Esdras Luna
 * @version 1.0
 * @since 2024-08-22
 */

// Import necessary packages/classes
import javax.swing.*; // to work with Swing components

// A class to encapsulate the attributes and methods of the 'About' handler object
public class AboutHandler {

    // === CLASS ATTRIBUTES ===

    // Attributes of the 'About' handler class
    private  String[] teamMembers; // a string array to store the team member names
    private  String message; // a string to store an additional message about the application

    // === CLASS METHODS ===

    // A non-parameterized constructor to initialize the attributes of a new 'About' handler object
    public AboutHandler() {
        // Set up the team members and message with a default value
        this.teamMembers = new String[]{"Esdras Luna", "Jaxon AG"};
        this.message = "This is a simple text editor application we built in Java in Aug 2024 for a Massey University Assignment.";
    } // end of constructor (Note: optional to create a parameterized constructor for custom initialization)

    // A method to show the 'About' dialog with information about the team members and the application
    public void showAboutDialog() {
        // Set up a string builder to create the message for the 'About' dialog
        StringBuilder aboutMessage = new StringBuilder("Team Members:\n");
        // Iterate over the team members and append them to the message
        for (String member : teamMembers) {
            aboutMessage.append(member).append("\n");
        }
        // Append the additional message to the 'About' dialog
        aboutMessage.append("\n").append(message);
        // Show the 'About' dialog with the created message
        JOptionPane.showMessageDialog(null, aboutMessage.toString(), "About", JOptionPane.INFORMATION_MESSAGE);
    }

    // A setter method to modify the team member names
    public void setTeamMembers(String[] teamMembers) {
        this.teamMembers = teamMembers;
    }

    // A getter method to retrieve the team member names
    public String[] getTeamMembers() {
        return teamMembers;
    }

    // A setter method to modify the additional message
    public void setMessage(String message) {
        this.message = message;
    }

    // A getter method to retrieve the additional message
    public String getMessage() {
        return message;
    }
}