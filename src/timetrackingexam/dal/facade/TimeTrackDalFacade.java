
package timetrackingexam.dal.facade;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.controller.ClientDBDAOController;
import timetrackingexam.dal.database.controller.ProjectDaoController;
import timetrackingexam.dal.database.controller.TaskDaoController;
import timetrackingexam.dal.database.controller.UserDBDAOController;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author Rizvan
 */
public class TimeTrackDalFacade implements ITimeTrackDalFacade
{

    private ProjectDaoController projectController;
    private UserDBDAOController userController;
    private TaskDaoController taskController;
    private ClientDBDAOController clientController;

    public TimeTrackDalFacade()
    {
        projectController = new ProjectDaoController(ConnectionPool.getInstance());
        userController = new UserDBDAOController(ConnectionPool.getInstance());
        taskController = new TaskDaoController(ConnectionPool.getInstance());
        clientController = new ClientDBDAOController(ConnectionPool.getInstance());
        
    }
    
    
    
    @Override
    public ObservableList<Project> getProjects()
    {
        return projectController.getProjects();
    }

    @Override
    public boolean createNewProject(Project p)
    {
        return projectController.createProject(p);
    }

    @Override
    public boolean updateProject(Project p)
    {
        return projectController.editProject(p);
    }

    @Override
    public boolean updateTask(Task updateTask)
    {
        return taskController.updateTask(updateTask);
    }

    @Override
    public ObservableList<User> getAllUsers()
    {
        return userController.getAllUsers();
    }

    @Override
    public boolean addUser(User user)
    {
        return userController.addUser(user);
    }

    @Override
    public boolean updateUser(User user)
    {
        return userController.updateUser(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return userController.deleteUser(user);
    }
    
    @Override
    public ObservableList<Task> getTasks(Project p) {
        return taskController.getTasks(p);
    }

    @Override
    public ObservableList<TaskTime> getTime(Task t) {
        return taskController.getTime(t);
    }
    
    @Override
    public boolean updateTime(TaskTime tt) {
        return taskController.updateTime(tt);
    }
    

    @Override
    public boolean addTask(Task t, Project p) {
        return taskController.addTask(t, p);
    }

    @Override
    public boolean deleteTask(Task selectedTask)
    {
        return taskController.deleteTask(selectedTask);
    }
    public void getCSV()
    {

        try
        {
            projectController.getCSV();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(TimeTrackDalFacade.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public boolean submitTime(TaskTime tt, Task t) {
        ObservableList<TaskTime> times = taskController.getTime(t);
            for (TaskTime time : times) {
                if(tt.getDate().toString().equals(time.getDate().toString()) &&
                tt.getUserId() == time.getUserId()){
                    return updateTime(tt);
            }
        }
        return taskController.submitTime(tt);
    }

    @Override
    public ObservableList<Client> getAllClients() {
        return clientController.getAllClient();
    }

    @Override
    public boolean createClient(Client client) {
        return clientController.createClient(client);
    }

    @Override
    public boolean updateClient(Client client) {
        return clientController.updateClient(client);
    }

    @Override
    public boolean deleteClient(Client client) {
        return clientController.deleteClient(client);
    }

    @Override
    public ObservableList<Project> getAllClientProjects(Client client) {
        return clientController.getAllClientProjects(client);
    }

    public ObservableList<TaskLog> readLogs()
    {
        return taskController.getTaskLogs();
    }	
	
    @Override
    public boolean deleteProject(Project project) {
        return projectController.deleteProject(project);
    }

    public boolean addTimeLog(TaskLog timeLog)
    {
        return taskController.createTimeLog(timeLog);
    }

    public ObservableList<TaskLog> readTimeLogs()
    {
        return taskController.getTimeLogs();
    }

    @Override
    public ObservableList<TaskTime> getUserTime(Task t, User u) {
        return taskController.getUserTime(t,u);
    }
}
