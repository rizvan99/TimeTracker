
package timetrackingexam.gui.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.User;
import timetrackingexam.gui.model.AppModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class LogsController implements Initializable
{
    
    private AppModel am;

    @FXML
    private TableView<TaskLog> tblCRUD;
    @FXML
    private TableColumn<TaskLog, Date> clm1date;
    @FXML
    private TableColumn<TaskLog, String> clm1action;
    @FXML
    private TableColumn<TaskLog, User> clm1byuser;
    @FXML
    private TableColumn<TaskLog, String> clm1name;
    
    @FXML
    private TableView<TaskLog> tblTimelogs;
    @FXML
    private TableColumn<TaskLog, LocalDateTime> clm2start;
    @FXML
    private TableColumn<TaskLog, LocalDateTime> clm2end;
    @FXML
    private TableColumn<TaskLog, String> clm2stime;
    @FXML
    private TableColumn<TaskLog, User> clm2sby;
    @FXML
    private TableColumn<TaskLog, String> clm2taskName2;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        am = AppModel.getInstance();
        
        taskLogTable();
        timeLogTable();
    }    
    
    
    public void taskLogTable()
    {
        clm1action.setCellValueFactory(new PropertyValueFactory<>("action"));
        clm1date.setCellValueFactory(new PropertyValueFactory<>("date"));
        clm1byuser.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        clm1name.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        tblCRUD.getColumns().clear();
        tblCRUD.getColumns().addAll(clm1action, clm1date, clm1byuser, clm1name);
        tblCRUD.setItems(am.getLogs());  
    }
    
    public void timeLogTable()
    {
        clm2start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        clm2end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        clm2stime.setCellValueFactory(new PropertyValueFactory<>("submittedTimeStringFormat"));
        clm2sby.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        clm2taskName2.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        tblTimelogs.getColumns().clear();
        tblTimelogs.getColumns().addAll(clm2start, clm2end, clm2stime, clm2sby, clm2taskName2);
        tblTimelogs.setItems(am.getTimeLogs());
        
    }
}
