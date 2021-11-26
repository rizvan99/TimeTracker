
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
public class AddEditUserViewController implements Initializable {

    private AppModel am;
    private User selectedUser;
    
    @FXML
    private TextField txtEmail;    
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private ComboBox<User.Role> cbbRole;
    @FXML
    private JFXButton btnSaveUser;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        selectedUser = am.getSelectedUser();
        List<User.Role> roles = Arrays.asList(User.Role.values());
        cbbRole.setItems(FXCollections.observableArrayList(roles));   
        if (selectedUser != null) {
            setUserInformation(selectedUser);
        }
        initKeys();
        initTooltips();
        initButtonEffects();
        
    }    

    /**
     * Displays user information in text fields
     * @param user to display information from
     */
    private void setUserInformation(User user) {
        txtEmail.setText(user.getEmail());
        txtEmail.setDisable(true);
        txtEmail.setStyle("-fx-background-color: gray");
        txtPassword.setDisable(true);
        txtPassword.setStyle("-fx-background-color: gray");
        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        cbbRole.getSelectionModel().select(user.getRole());
    }
    
    /**
     * Initializes actions on enter keystroke for nodes
     */
    private void initKeys() {
        actionOnEnterKey(txtEmail);
        actionOnEnterKey(txtFirstName);
        actionOnEnterKey(txtLastName);
        actionOnEnterKey(txtPassword);
        actionOnEnterKey(cbbRole);
    }
    
    /**
     * Sets action on using Enter key for node
     * Currently, when pressing enter, an attempt is made to validate information in text fields
     * @param node to apply action on enter keystroke for
     */
    private void actionOnEnterKey(Node node) {
        node.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                validateInformation();
            }
        });
    }
    
    /**
     * Sets tooltips for nodes
     */
    private void initTooltips() {
        btnSaveUser.setTooltip(TooltipFactory.create("Click to save user"));
        btnCancel.setTooltip(TooltipFactory.create("Click to exit window without saving changes"));
    }
    
    /**
     * Sets effects for nodes
     */
    private void initButtonEffects() {
        NodeCustomizer.nodeEffect(btnSaveUser);
        NodeCustomizer.nodeEffect(btnCancel);
    }
    
    /**
     * Starts process for saving changes by attempting to validate information in textfields
     * @param event 
     */
    @FXML
    private void saveUser(ActionEvent event) {        
        validateInformation();
    }
    
    /**
     * Attempts to validate information in text fields
     * Checks if text fields alle filled out, if email is valid and if email is not used by another user
     */
    private void validateInformation() {
        String email = txtEmail.getText();
        String password = (selectedUser!=null ) ? selectedUser.getPassword() : LoginTools.hashPassword(txtPassword.getText());
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        User.Role role = cbbRole.getSelectionModel().getSelectedItem();
        
        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || role == null) {
            AlertFactory.showErrorAlert("You have not entered all the necessary information");
            return;
        }        
       
        if (selectedUser != null) {
            saveChanges(new User(firstName, lastName, email, password, role, selectedUser.getId()));
        } else if (LoginTools.validateEmail(email)) {
            if (!am.checkIfEmailIsUsed(email)) {
                saveChanges(new User(firstName, lastName, email, password, role, 0));
            } else {
                AlertFactory.showErrorAlert("Email is already used by another user");
            }
        } else {
            AlertFactory.showErrorAlert("You must enter a valid email address");
        }
    }
    
    /**
     * Saves changes made
     * Creates a new user or updates an existing one depending on which button was pushing when opening view
     * Also shows confirmation alert which will ask (program) user if user is certain the changes should be made 
     * @param user to create or update
     */
    private void saveChanges(User user) {

        Alert alert = AlertFactory.createConfirmationAlert(String.format("%s%n%s", "Are you sure you want to save this", user));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (selectedUser == null) {
                am.addUser(user);
                am.fetch();
                close();
            } else {
                am.updateUser(user);
                am.fetch();
                close();
            }
        } else {
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
