
package timetrackingexam.gui.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import timetrackingexam.be.Project;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class AddEditProjectViewController implements Initializable {

    private AppModel am;
    private Project updatedProject;
    
    @FXML
    private JFXTextField fldName;
    @FXML
    private JFXTextField fldRate;
    @FXML
    private JFXTextField fldDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        am = AppModel.getInstance();
        
       if(am.getCurrentProject()!=null){
           updatedProject = am.getCurrentProject();
           fldName.setText(updatedProject.getName());            
           fldRate.setText(updatedProject.getRate()+"");
           fldDescription.setText(updatedProject.getDescription());
       }
       else {
           fldRate.setText(am.getCurrentClient().getDefaultrate()+"");
       }
       
        
        
    }    

    @FXML
    private void CreateProject(ActionEvent event) {

        if(am.getCurrentProject()!=null){
            updateProject();
            am.getProjects();
        }
        else{
            createProject();
            am.getProjects();
        }
            
    }
    
    private void createProject(){
        
        
        String name = fldName.getText();
        String desc = fldDescription.getText();
        int rate = Integer.parseInt(fldRate.getText());
        int clientId = am.getCurrentClient().getId();        
        
        Project newProject = new Project(name, clientId, desc, rate);
         
        
        if(am.createNewProject(newProject)){
                Stage primStage = (Stage) fldRate.getScene().getWindow();
                primStage.close();
            }
    }
    
    private void updateProject(){
        updatedProject.setName(fldName.getText());
        updatedProject.setRate(Integer.parseInt(fldRate.getText()));       
        
        updatedProject.setClientID(am.getCurrentClient().getId());        
        updatedProject.setDescription(fldDescription.getText());
        am.updateProject(updatedProject);
        Stage primStage = (Stage) fldName.getScene().getWindow();
        primStage.close();
    }
    
}
