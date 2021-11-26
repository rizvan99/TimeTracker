
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;
import timetrackingexam.gui.util.NodeCustomizer;
import timetrackingexam.gui.util.TooltipFactory;
import timetrackingexam.gui.util.ViewGuide;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class ProjectsOverviewController implements Initializable {
    
    private AppModel am;
    private User currentUser;
    private Project selectedProject;
    private TaskTime selectedTime;
    private boolean hasRun;
    int totalHour;
    int totalMin;
    int totalSec;
    private LocalDateTime dateStart;
    private LocalDateTime dateStop;
    private double timeStart;
    private double timeStop;
    private boolean isDone;
    
    @FXML
    private JFXComboBox<Project> cbbProjectSelect;
    @FXML
    private JFXListView<Task> lstTaskList;
    @FXML
    private Label txtSlectedTask;
    @FXML
    private JFXTextArea txtTaskDescription;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuBar menuBar;
    @FXML
    private JFXButton btnAddTask;
    @FXML
    private JFXButton btnEditTask;
    @FXML
    private JFXButton btnDeleteTask;
    @FXML
    private Menu menuUser;
    @FXML
    private MenuItem menuItemPassword;
    @FXML
    private MenuItem menuItemAdmin;
    @FXML
    private JFXButton btnTimeButton;
    @FXML
    private Text txtTaskText;
    @FXML
    private Label txtSec;
    @FXML
    private Label txtMin;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private TextField fldSec;
    @FXML
    private TextField fldMin;
    @FXML
    private TextField fldHour;
    @FXML
    private Label txtHour;
    @FXML
    private MenuItem menuItemUser;
    @FXML
    private JFXButton btnNoneBillable;
    @FXML
    private JFXButton btnOpenLogs;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Singleton
        am = AppModel.getInstance();

        btnSubmit.setDisable(true);
        
        currentUser = am.getCurrentUser();
        menuUser.setText(currentUser.getEmail());
        cbbProjectSelect.setItems(am.getProjects());
        am.setCurrentProject(cbbProjectSelect.getItems().get(0));
        selectedProject = am.getCurrentProject();
        
       // lstTaskList.setItems(am.getTasksInProject(selectedProject));
        txtSlectedTask.setText("(Select Project)");
        

        setNodes();
        hasRun = true;

        if (currentUser.getRole()!=User.Role.Admin) {
            menuItemAdmin.setDisable(true);
            menuItemAdmin.setVisible(false);
            menuItemUser.setDisable(true);
            menuItemUser.setVisible(false);
        }
        
        initTooltips();
        initEffects();
        
        totalHour=0;
        totalMin=0;
        totalSec=0;
        
    } 
    
    /**
     * Method for tool tips that appear when you hover over buttons
     */
    private void initTooltips() {
        btnAddTask.setTooltip(TooltipFactory.create("Click here to create a new task for the selected project"));        
        btnEditTask.setTooltip(TooltipFactory.create("Click here to edit an existing task.\nSelect a task first"));
        btnDeleteTask.setTooltip(TooltipFactory.create("Click here to delete a task.\nSelect a task first"));
        btnTimeButton.setTooltip(TooltipFactory.create("Click here to start or pause registering the time you work on the task"));
        btnNoneBillable.setTooltip(TooltipFactory.create("Click here to start or pause Nonebillable time when working on this task"));
    }
    
    /**
     * Method for more button UI effects
     */
    private void initEffects() {        
        NodeCustomizer.nodeEffect(btnAddTask);
        NodeCustomizer.nodeEffect(btnEditTask);
        NodeCustomizer.nodeEffect(btnDeleteTask);
        NodeCustomizer.nodeEffect(btnTimeButton);
        NodeCustomizer.nodeEffect(btnSubmit);        
    }

    public ProjectsOverviewController()
    {
    }
    
    /**
     * Handler for new task button
     * @param event 
     */
    @FXML
    private void addTask(ActionEvent event) {
        am.setCurrentTask(null);
        openAddEdit();
    }

    /**
     * Sets all tasks in the list view 
     * @param event 
     */
    @FXML
    private void setItemsOnList(ActionEvent event) {
        am.setCurrentProject(cbbProjectSelect.getSelectionModel().getSelectedItem());        
        lstTaskList.setItems(am.getTasks());
        selectedProject = am.getCurrentProject();
    }
    
    /**
     * Sets the current task and displays information for task and time
     * @param event 
     */
    @FXML
    private void setCurrentTask(MouseEvent event) {
        if (lstTaskList.getSelectionModel().getSelectedItem() != null) {
            am.setCurrentTask(lstTaskList.getSelectionModel().getSelectedItem());
            txtSlectedTask.setText(am.getCurrentTask().getName());
            
        
        if (am.getCurrentTask().getDescription() == null)
        {
            txtTaskDescription.setStyle("-fx-font-style: italic;");
            txtTaskDescription.setText("No available description found for this task");
        }
        else
        {
            txtTaskDescription.setStyle("-fx-font-style: normal;");
            txtTaskDescription.setText(am.getCurrentTask().getDescription());
        } 
            totalHour = 0;
            totalMin = 0;
            totalSec = 0;
            
            if(am.getAllTime() != null){
                if(currentUser.getRole()==User.Role.Admin){
                for (TaskTime time : am.getAllTime()) {
                    totalHour += time.getHours();
                    totalMin += time.getMin();
                    totalSec += time.getSec();
                }
                fldHour.setText(totalHour+ "");
                fldMin.setText(totalMin+ "");
                fldSec.setText(totalSec+ "");
                }
                else{
                    for (TaskTime time : am.getUserTime()) {
                    totalHour += time.getHours();
                    totalMin += time.getMin();
                    totalSec += time.getSec();
                }
                fldHour.setText(totalHour+ "");
                fldMin.setText(totalMin+ "");
                fldSec.setText(totalSec+ "");
                }
            }
            else{
                fldHour.setText("0");
                fldMin.setText("0");
                fldSec.setText("0");
            }
        }
    }
    
    /**
     * Closes the program if cancel is clicked from the menu
     * @param event 
     */
    @FXML
    private void closeProgram(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        primStage.close();
    }
    
    /**
     * Logs out through the menu
     * @param event 
     */
    @FXML
    private void logoutToLoginView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.logout(primStage);
    }
    
    /**
     * Opens up the "New task" window
     */
    private void openAddEdit()
    {
        try
        {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/timetrackingexam/gui/view/promts/AddTask.fxml"));
            Parent root1 = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            stage.setTitle("New Task");
        } catch (IOException ex)
        {
            Logger.getLogger(ProjectsOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Edits a task
     * @param event 
     */
    @FXML
    private void handleEditTask(ActionEvent event)
    {
        if (am.getCurrentTask() != null)
        {
            openAddEdit();
        }
    }
    
    /**
     * Deletes the selected task
     * @param event 
     */
    @FXML
    private void handleDeleteTask(ActionEvent event)
    {
        Task selectedTask = lstTaskList.getSelectionModel().getSelectedItem();
        if (selectedTask != null && am.deleteTask())
        {
           am.fetch();
        }
        else
        {
            System.out.println("Please choose the task you want to delete");
        }
    }
    
    /**
     * Opens password view through the menu
     * @param event 
     */
    @FXML
    private void openPasswordView(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.changePasswordView(primStage);
    }

   
    /**
     * Opens up the chart view through the menu
     * @param event 
     */
    @FXML
    private void handlePieChart(ActionEvent event)
    {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.openView("/timetrackingexam/gui/view/DiagramView.fxml", "Charts", stage, false, false);
    }

    /**
     * Opens up admin view through the menu
     * @param event 
     */
    @FXML
    private void goToAdmin(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.projectManagementView(primStage);
    }

    /**
     * Event for starting and stopping the time
     * @param event 
     */
    @FXML
    private void btnStopStart(ActionEvent event)
    {
        isDone = false;
        if(!am.timerIsRunning()||btnTimeButton.getText().equals("Start")){
            am.startTimer(fldSec, fldMin, fldHour);
            btnSubmit.setDisable(true);
            btnTimeButton.setText("Pause");
            
            dateStart = LocalDateTime.now();
            timeStart = new Date().getTime();
        }
        else{
            am.stopTimer();
            btnTimeButton.setText("Start");
            btnSubmit.setDisable(false);
            dateStop = LocalDateTime.now();
            timeStop = new Date().getTime();
            isDone = true;
        }
    }
    
    /**
     * Non billable, non working
     * @param event 
     */
    private void btnNoneBillable (ActionEvent event)
    {
        if(!am.timerIsRunning()||btnNoneBillable.getText().equals("None-Billable")){
            am.startTimer(fldSec, fldMin, fldHour);
            btnSubmit.setDisable(true);
            btnNoneBillable.setText("Pause");
        }
        else if (btnNoneBillable.getText().equals("Pause")){
            am.stopTimer();
            btnNoneBillable.setText("Start");
            btnSubmit.setDisable(false);
        }
    }
    
    /**
     * Submits the new time and creates a log
     * @param event 
     */
    @FXML
    private void handleSubmit(ActionEvent event)
    {
        TaskTime tt = new TaskTime(
                am.getCurrentTask().getId(),
                currentUser.getId(),
                Integer.parseInt(fldSec.getText())-totalSec,
                Integer.parseInt(fldMin.getText())-totalMin,
                Integer.parseInt(fldHour.getText())-totalHour,
                LocalDate.now()
        );
        
        am.submitTime(tt);
        if (isDone)
        {
            TaskLog timeLog = new TaskLog(dateStart, dateStop, (timeStop - timeStart) / 1000);
            timeLog.setCreatedBy(currentUser);
            timeLog.setTaskName(am.getCurrentTask().getName());
            am.createTimeLog(timeLog); 
        }
        am.fetch();
        
    }

    /**
     * Opens up user management view through the menu bar
     * @param event 
     */
    @FXML
    private void goToUserManagement(ActionEvent event) {
        Stage primStage = (Stage) menuBar.getScene().getWindow();
        ViewGuide.userManagementView(primStage);
    }
        
    /**
     * Method for setting nodes
     */
    private void setNodes(){
        
        
        txtSlectedTask.setText("(Select Project)");
        cbbProjectSelect.setItems(am.getProjects());
        btnSubmit.setDisable(true);
        
        if(!hasRun){
            currentUser = am.getCurrentUser();
            am.setCurrentProject(cbbProjectSelect.getItems().get(0));
            selectedProject = am.getCurrentProject();
        }
        
        lstTaskList.setItems(am.getTasks());
        menuUser.setText(currentUser.getEmail());
    }

    /**
     * Non billable, non working
     * @param event 
     */
    @FXML
    private void handleNoneBillable(ActionEvent event) {
    
    }

    /**
     * Handles CSV export
     * @param event 
     */
    @FXML
    private void handleCsv(ActionEvent event) {
        am.getCSV();
    }


    /**
     * Opens up the log view
     * @param event 
     */
    @FXML
    private void handleOpenLogs(ActionEvent event)
    {
        Stage stage = (Stage) btnOpenLogs.getScene().getWindow();
        ViewGuide.openView("/timetrackingexam/gui/view/promts/Logs.fxml", "Logs", stage, false, false);
    }
     
}
    
    
    

