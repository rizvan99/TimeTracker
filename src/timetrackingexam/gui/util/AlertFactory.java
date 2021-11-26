
package timetrackingexam.gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

/**
 *
 * @author fauxtistic
 */
public class AlertFactory {   
            
    /**
     * Creates and shows error alert with message
     * @param message to be displayed in alert box
     */
    public static void showErrorAlert(String message) {
        Alert alert = createAlert("Error Dialog", "ERROR", message, Alert.AlertType.ERROR);        
        alert.showAndWait();
    }
    
    /**
     * Creates and returns confirmation alert with message
     * @param message to be displayed in alert box
     * @return confirmation alert with specified message
     */
    public static Alert createConfirmationAlert(String message) {
        return createAlert("Confirm action", null, message, Alert.AlertType.CONFIRMATION);
    } 
    
    /**
     * Creates a new alert, customizes it according to parameters, and returns it
     * Resizable is set to true
     * @param title of alert box
     * @param header of alert box
     * @param message to be displayed in alert box
     * @param type alert type
     * @return the new alert
     */
    public static Alert createAlert(String title, String header, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setResizable(true);
        alert.setContentText(String.format(message));
        return alert;
    }
    
}
