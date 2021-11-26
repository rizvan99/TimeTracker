
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import timetrackingexam.be.User;
import timetrackingexam.bll.security.LoginTools;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertFactory;
import timetrackingexam.gui.util.NodeCustomizer;
import timetrackingexam.gui.util.TooltipFactory;

/**
 * FXML Controller class
 *
 * @author fauxtistic
 */
public class ChangePasswordViewController implements Initializable {

    private AppModel am;
    private User user;
    
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private PasswordField txtOldPass;
    @FXML
    private PasswordField txtNewPass;
    @FXML
    private PasswordField txtNewPass2;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        user = am.getCurrentUser();
        initKeys();
        initTooltips();
        initButtonEffects();
    }    
    
    /**
     * Sets tooltips for nodes
     */
    private void initTooltips() {
        btnSave.setTooltip(TooltipFactory.create("Click to change password"));
        btnCancel.setTooltip(TooltipFactory.create("Click to exit window without changing password"));
    }
    
    /**
     * Sets effects for nodes
     */
    private void initButtonEffects() {
        NodeCustomizer.nodeEffect(btnSave);
        NodeCustomizer.nodeEffect(btnCancel);
    }    
    
    /**
     * Initializes actions on enter keystroke for nodes
     */
    private void initKeys() {
        actionOnEnterKey(txtOldPass);
        actionOnEnterKey(txtNewPass);
        actionOnEnterKey(txtNewPass2);
    }
    
    /**
     * Sets action on using Enter key for node
     * Currently, when pressing enter, an attempt is made to validate information in text fields
     * @param node to apply action on enter keystroke for
     */
    private void actionOnEnterKey(Node node) {
        node.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                changePassword();
            }
        });
    }

    /**
     * Starts process for saving changes by attempting to validate information in textfields
     * @param event 
     */
    @FXML
    private void savePassword(ActionEvent event) {
         changePassword();        
                        
    }
    
    /**
     * Attempts to validate information in text fields
     * Checks if text fields are filled out, if old password matches user's current password, and if the new password has been written exactly the same way twice
     */
    private void changePassword() {
        String oldPassword = txtOldPass.getText();
        String newPassword = txtNewPass.getText();
        String repeatedNewPassword = txtNewPass2.getText();
        
        if (oldPassword.isEmpty() || newPassword.isEmpty() || repeatedNewPassword.isEmpty()) {
            AlertFactory.showErrorAlert("All input fields must be filled out");
            return;
        }
        if (!newPassword.equals(repeatedNewPassword)) {
            AlertFactory.showErrorAlert("The text in the two fields where you have entered the new password do not match");
            txtNewPass.clear();
            txtNewPass2.clear();
            return;
        }
        String verifiedNewPassword = LoginTools.getVerifiedNewPassword(user, oldPassword, newPassword);
        if (verifiedNewPassword != null) {
            confirmPassword(user, verifiedNewPassword);
        }
        else {
            AlertFactory.showErrorAlert("The password you have entered in the first field is incorrect");
            txtOldPass.clear();            
        }      
    }

    /**
     * Forwards request to update user with new information (new password)
     * Confirmation alert box will be shown to ask (program) user if s/he is certain password should be changed
     * @param user whose password is to be changed
     * @param password the new password
     */
    private void confirmPassword(User user, String password) {
        Alert alert = AlertFactory.createConfirmationAlert("Are you sure you want to change your password?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            user.setPassword(password);
            am.updateUser(user);
            am.fetch();
            close();
        } 
        else {
            alert.close();
        }        
    }
    
    /**
     * Asks to close view
     * @param event 
     */
    @FXML
    private void cancel(ActionEvent event) {
        close();
    }
    
    /**
     * Closes view
     */
    private void close() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
}
