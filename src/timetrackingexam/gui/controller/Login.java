
package timetrackingexam.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import timetrackingexam.be.User;
import timetrackingexam.bll.security.LoginTools;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertFactory;
import timetrackingexam.gui.util.ViewGuide;

/**
 *
 * @author Rizvan
 */
public class Login implements Initializable
{

    private AppModel appModel;
    
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       appModel = AppModel.getInstance();
       appModel.setCurrentUser(null);
       appModel.setCurrentProject(null);
       appModel.setCurrentTask(null);
       appModel.setSelectedUser(null);
       initKeys();

    }    
    
    /**
     * Initializes actions on enter keystroke for nodes
     */
    private void initKeys() {
        actionOnEnterKey(txtPassword);
        actionOnEnterKey(txtName);
    }
    
    /**
     * Sets action on using Enter key for node
     * Currently, when pressing enter, an attempt is made to login
     * @param node to apply action on enter keystroke for
     */
    private void actionOnEnterKey(Node node) {
        node.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                login();
            }
        });
    }

    /**
     * Attempts to login
     * @param event 
     */
    @FXML
    private void handleLogin(ActionEvent event)
    {
        login();
    }    
        
    /**
     * Checks if email and password entered in text fields match those of a user in list of all users
     * If such a user is found, sets currentUser variable to this user and opens new view depending on whether or not user has admin privileges
     * If text fields are not filled out, or if no user is found, will notify with appropriate alert box
     */
    public void login() {
        String email = txtName.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            AlertFactory.showErrorAlert("The input fields must be filled out");
        } else {
            User user = LoginTools.getVerifiedUser(email, password, appModel.getAllUsers());

            if (user != null) {
                appModel.setCurrentUser(user);
                Stage primStage = (Stage) btnLogin.getScene().getWindow();

                switch (appModel.getCurrentUser().getRole()) {
                    case Default:
                        ViewGuide.projectsOverview(primStage);
                        break;
                    case Admin:
                        ViewGuide.projectManagementView(primStage);
                        break;
                    default:
                        AlertFactory.showErrorAlert("No view defined for this role");
                }
            } else {
                AlertFactory.showErrorAlert("Email or password incorrect");
            }
        }
        txtPassword.clear();
    }
    
}
