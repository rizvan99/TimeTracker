
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
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
public class ProjectManagementViewController implements Initializable {

    private final String ADD_EDIT_PROJECT_PATH = "/timetrackingexam/gui/view/promts/AddEditProjectView.fxml";
    private final String ADD_EDIT_CLIENT_PATH = "/timetrackingexam/gui/view/promts/AddEditClientView.fxml";
    
    private AppModel am;
    private User currentUser;
    private Project selectedProject;
    
    @FXML
    private JFXListView<Project> lstProjects;
    @FXML
    private Text txtSelectedProject;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menuItemPassword;
    @FXML
    private Menu menuUser;
    @FXML
    private JFXTextArea fldDescription;
    @FXML
    private MenuItem menuItemTasks;
    @FXML
    private MenuItem menuItemUser;
    @FXML
    private JFXButton btnNewProject;
    @FXML
    private JFXButton btnEditProject;
    @FXML
    private JFXComboBox<Client> cbbClients;
    @FXML
    private JFXButton btnAddClient;
    @FXML
    private JFXButton btnEditClient;
    @FXML
    private JFXButton btnDeleteClient;
    @FXML
    private JFXButton btnDeleteProject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       am = AppModel.getInstance();
       cbbClients.setItems(am.getAllClients()); 
       
       currentUser = am.getCurrentUser();
       menuUser.setText(currentUser.getEmail());
       am.setCurrentProject(null);
       am.setCurrentTask(null);
       am.setSelectedUser(null);
       txtSelectedProject.setText("(Select Project)");
       
       initListener();
       initTooltips();
       initEffects();
    }    

    private void initListener() {
        cbbClients.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
            lstProjects.getItems().clear();
            showClientProjects(newValue);            
            });
    }
    
    private void initTooltips() {
        btnNewProject.setTooltip(TooltipFactory.create("Click here to create a new project"));        
        btnEditProject.setTooltip(TooltipFactory.create("Click here to edit an existing project.\nSelect a project first"));
        btnDeleteProject.setTooltip(TooltipFactory.create("Click here to delete an existing project.\nSelect a project first"));
        btnAddClient.setTooltip(TooltipFactory.create("Click here to create a new client"));        
        btnEditClient.setTooltip(TooltipFactory.create("Click here to edit an existing client.\nSelect a client first"));
        btnDeleteClient.setTooltip(TooltipFactory.create("Click here to delete an existing client.\nSelect a client first"));
    }
    
    private void initEffects() {
        NodeCustomizer.nodeEffect(btnNewProject);
        NodeCustomizer.nodeEffect(btnEditProject);
        NodeCustomizer.nodeEffect(btnDeleteProject);
        NodeCustomizer.nodeEffect(btnAddClient);
        NodeCustomizer.nodeEffect(btnEditClient);
        NodeCustomizer.nodeEffect(btnDeleteClient);
    }
    
    private void showClientProjects(Client client) {
        if (client!=null) {
            lstProjects.setItems(am.getAllClientProjects(client));
        }        
        
    }
    
    @FXML
    private void newProject(ActionEvent event) {
        am.setCurrentProject(null);
        am.setCurrentClient(cbbClients.getSelectionModel().getSelectedItem());
        if (am.getCurrentClient()!=null) {
            openAddEditWindow();
        }
        else {
            AlertFactory.showErrorAlert("All projects belong to a client.\nSelect a client in the combobox to add the project to");
        }
        
    }

    @FXML
    private void editProject(ActionEvent event) {
        am.setCurrentProject(lstProjects.getSelectionModel().getSelectedItem());
        am.setCurrentClient(cbbClients.getSelectionModel().getSelectedItem());
        if (am.getCurrentClient()==null) {
            AlertFactory.showErrorAlert("All projects belong to a client.\nSelect a client in the combobox.\nThen select a project to edit");
        }        
        else if(am.getCurrentProject()!=null){
            openAddEditWindow();
        }
        else {
            AlertFactory.showErrorAlert("Select a project to edit");
        }
    }

    @FXML
    private void setSelectedProject(MouseEvent event) {
        selectedProject = lstProjects.getSelectionModel().getSelectedItem();
        am.setCurrentProject(selectedProject);
        txtSelectedProject.setText(selectedProject.toString());
        fldDescription.setText(selectedProject.getDescription());
    }

    private ObservableList<Project> getAllProjects(){
        return am.getProjects();
    }

    private void openAddEditWindow(){
        try {
            Parent loader = FXMLLoader.load(getClass().getResource(ADD_EDIT_PROJECT_PATH));
            Scene scene = new Scene(loader);
            Stage stage = new Stage();
            stage.setTitle("Create Project");
            stage.setScene(scene);
            stage.showAndWait();
            showClientProjects(am.getCurrentClient());
        } catch (IOException e) {
            AlertFactory.showErrorAlert("Could not open new window");
            Logger.getLogger(ProjectManagementViewController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void closeProgram(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        primStage.close();
    }

    @FXML
    private void logoutToLoginView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.logout(primStage);
    }

    @FXML
    private void openPasswordView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.changePasswordView(primStage);
    }

    @FXML
    private void goToTasks(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.projectsOverview(primStage);
    }

    @FXML
    private void goToUserManagement(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.userManagementView(primStage);
    }

    @FXML
    private void addClient(ActionEvent event) {
        am.setCurrentClient(null);
        Stage primStage = (Stage) btnAddClient.getScene().getWindow();
        ViewGuide.openView(ADD_EDIT_CLIENT_PATH, "New client", primStage, false, true);
    }

    @FXML
    private void editClient(ActionEvent event) {
        am.setCurrentClient(cbbClients.getSelectionModel().getSelectedItem());
        Stage primStage = (Stage) btnEditClient.getScene().getWindow();
        if (am.getCurrentClient()!=null) {
            ViewGuide.openView(ADD_EDIT_CLIENT_PATH, "Edit client", primStage, false, true);
        }
        else {
            AlertFactory.showErrorAlert("Select a client in the combobox to the left to edit");
        }
        
    }

    @FXML
    private void deleteClient(ActionEvent event) {
        am.setCurrentClient(cbbClients.getSelectionModel().getSelectedItem());
        if (am.getCurrentClient()==null) {
            AlertFactory.showErrorAlert("Select a client in the combobox to the left to delete");
            return;
        }
        Alert alert = AlertFactory.createConfirmationAlert(String.format("%s%n%s", "Are you sure you want to delete this", am.getCurrentClient()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            cbbClients.getSelectionModel().clearSelection();
            lstProjects.getItems().clear();
            am.deleteClient(am.getCurrentClient());
            am.setCurrentClient(null);
            am.fetch();            
            alert.close();
        }
        else {
            alert.close();
        }
    }

    @FXML
    private void deleteProject(ActionEvent event) {
        am.setCurrentProject(lstProjects.getSelectionModel().getSelectedItem());
        if (am.getCurrentProject()==null) {
            AlertFactory.showErrorAlert("Select a project to delete");
            return;
        }
        Alert alert = AlertFactory.createConfirmationAlert(String.format("%s%n%s", "Are you sure you want to delete this", am.getCurrentProject()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {               
            am.deleteProject(am.getCurrentProject());
            am.setCurrentProject(null);
            txtSelectedProject.setText("");
            fldDescription.setText("");
            am.fetch();            
            alert.close();
            showClientProjects(am.getCurrentClient());
        }
        else {
            alert.close();
        }
    }

    
    
}
