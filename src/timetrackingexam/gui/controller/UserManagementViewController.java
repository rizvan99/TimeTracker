
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.AlertFactory;
import timetrackingexam.gui.util.NodeCustomizer;
import timetrackingexam.gui.util.TooltipFactory;
import timetrackingexam.gui.util.ViewGuide;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class UserManagementViewController implements Initializable {

    private AppModel am;
    private User currentUser;
    private ObservableList<User> allUsers;
    private static final String ADD_EDIT_USER_VIEW_PATH = "/timetrackingexam/gui/view/promts/AddEditUserView.fxml"; 
    
    @FXML
    private TableView<User> tblEmployeeTable;   
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuUser;
    @FXML
    private JFXButton btnNewUser;
    @FXML
    private JFXButton btnEditUser; 
    @FXML
    private JFXButton btnDeleteUser;
    @FXML
    private TableColumn<User, String> columnEmail;
    @FXML
    private TableColumn<User, String> columnUserRole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       am = AppModel.getInstance();
       currentUser = am.getCurrentUser();
       menuUser.setText(currentUser.getEmail());           
       allUsers = am.getAllUsers();        
       tblEmployeeTable.setItems(allUsers);       
              
       initColumns();
       initTooltips();
       initEffects();
    }    
    
    /**
     * Customizes the way columns in tableview display information about users
     */
    private void initColumns() {
        columnName.setCellValueFactory(data -> {
            String name = data.getValue().getLastName() + ", " + data.getValue().getFirstName();
            return new SimpleStringProperty(name);
        });
        
        columnEmail.setCellValueFactory(data -> {
            String email = data.getValue().getEmail();
            return new SimpleStringProperty(email);
        });
        
        columnUserRole.setCellValueFactory(data -> {
            String role = data.getValue().getRole().toString();
            return new SimpleStringProperty(role);
        });
    }
    
    /**
     * Sets tooltips for nodes
     */
    private void initTooltips() {
        btnNewUser.setTooltip(TooltipFactory.create("Click here to create a new employee profile"));        
        btnEditUser.setTooltip(TooltipFactory.create("Click here to edit an existing employee profile.\nSelect a user first"));
        btnDeleteUser.setTooltip(TooltipFactory.create("Click here to delete an existing employee profile.\nSelect a user first"));
    }
    
    /**
     * Sets effects for nodes
     */
    private void initEffects() {
        NodeCustomizer.nodeEffect(btnNewUser);
        NodeCustomizer.nodeEffect(btnEditUser);
        NodeCustomizer.nodeEffect(btnDeleteUser);
    }
    
    /**
     * Closes view and program
     * @param event 
     */
    @FXML
    private void closeProgram(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        primStage.close();
    }

    /**
     * Opens login view and closes currently displaying view
     * @param event 
     */
    @FXML
    private void logoutToLoginView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.logout(primStage);
    }

    /**
     * Opens change password view on top of currently displaying view
     * @param event 
     */
    @FXML
    private void openPasswordView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.changePasswordView(primStage);
    }
    
    /**
     * Opens add/edit user view on top of currently displaying, with intent to create a new user
     * @param event 
     */
    @FXML
    private void newUser(ActionEvent event) {
        am.setSelectedUser(null); 
        Stage primStage = (Stage) btnEditUser.getScene().getWindow();
        ViewGuide.openView(ADD_EDIT_USER_VIEW_PATH, "New user", primStage, false, true);
        am.setSelectedUser(tblEmployeeTable.getSelectionModel().getSelectedItem());
    }

    /**
     * Opens add/edit user view on top of currently displaying, with intent to update selected, existing user
     * Will notify (program) user if a user is not selected with appropriate alert box
     * @param event 
     */
    @FXML
    private void editUser(ActionEvent event) {
        am.setSelectedUser(tblEmployeeTable.getSelectionModel().getSelectedItem());
        Stage primStage = (Stage) btnEditUser.getScene().getWindow();
        if (am.getSelectedUser()!=null) {
            ViewGuide.openView(ADD_EDIT_USER_VIEW_PATH, "Edit user", primStage, false, true);
        }
        else {
            AlertFactory.showErrorAlert("Select a user to edit");
        }
    }     
    
    /**
     * Forwards request to delete selected user
     * Will notify (program) user if a user is not selected with appropriate alert box
     * @param event 
     */
    @FXML
    private void deleteUser(ActionEvent event) {
        am.setSelectedUser(tblEmployeeTable.getSelectionModel().getSelectedItem());
        if (am.getSelectedUser()==null) {
            AlertFactory.showErrorAlert("Select a user to delete");
            return;
        }
        if (am.getSelectedUser().equals(am.getCurrentUser())) {
            AlertFactory.showErrorAlert("You can not delete logged in user!");
            return;
        }
        Alert alert = AlertFactory.createConfirmationAlert(String.format("%s%n%s", "Are you sure you want to delete this", am.getSelectedUser()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            am.deleteUser(am.getSelectedUser());
            am.fetch();
            am.setSelectedUser(null);
            alert.close();
        }
        else {
            alert.close();
        }
    }

    /**
     * Opens project management view and closes currently displaying view
     * @param event 
     */
    @FXML
    private void goToProjectManagement(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.projectManagementView(primStage);
    }

    /**
     * Opens project overview and closes currently displaying view
     * @param event 
     */
    @FXML
    private void goToTasks(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.projectsOverview(primStage);
    }

    
    
    
}
