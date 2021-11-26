
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import timetrackingexam.be.Task;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class AddTaskController implements Initializable
{
   private AppModel am;
   private Task currentTask;
   
   
    @FXML
    private TextField txtAddTaskName;
    @FXML
    private TextArea txtAddTaskDescription;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        am = AppModel.getInstance();
        
        if (am.getCurrentTask() != null)
        {
            currentTask = am.getCurrentTask();
            txtAddTaskName.setText(am.getCurrentTask().getName());
            txtAddTaskDescription.setText(am.getCurrentTask().getDescription());
        }
    }    

    /**
     * Event that determines whether you create or edit a task
     * @param event 
     */
    @FXML
    private void handleSaveAddTask(ActionEvent event)
    {
        if (am.getCurrentTask() != null)
        {
            editTask();
        }
        else
        {
            newTask();
        }
        
    }
    
    /**
     * Cancel the task and close the window
     * @param event 
     */
    @FXML
    private void handleCancelTask(ActionEvent event)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Creates a new task object
     */
    private void newTask()
    {
        Task newTask = new Task(
                am.getCurrentProject().getId(),
                am.getCurrentUser().getId(),
                txtAddTaskName.getText(),
                txtAddTaskDescription.getText()
               );
        am.addTask(newTask, am.getCurrentProject());
        

        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Edits a task object
     */
    private void editTask()
    {
        Task updateTask = new Task(
                am.getCurrentProject().getId(),
                am.getCurrentUser().getId(),
                txtAddTaskName.getText(),
                txtAddTaskDescription.getText()
               );
        
        updateTask.setId(currentTask.getId());
        
        am.updateTask(updateTask);
        
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
        
    }
    
}
