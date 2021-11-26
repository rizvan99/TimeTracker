
package timetrackingexam.bll.facade;

import java.util.List;
import javafx.collections.ObservableList;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.dal.facade.TimeTrackDalFacade;

/**
 *
 * @author Rizvan
 */
public class TimeTrackBLLFacade implements ITimeTrackBLL
{
    
    private TimeTrackDalFacade dalFacade;

    public TimeTrackBLLFacade() {
        this.dalFacade = new TimeTrackDalFacade();
    }
    // Updates a task
    @Override
    public boolean updateTask(Task updateTask)
    {
        return dalFacade.updateTask(updateTask);
    }
    // Gets all the projects of a list
    @Override
    public List<Project> getAllProjects()
    {
        return dalFacade.getProjects();
    }
    // Creates a new project
    @Override
    public boolean createNewProject(Project p)
    {
        return dalFacade.createNewProject(p);
    }
    // Updates a project
    @Override
    public boolean updateProject(Project p)
    {
        return dalFacade.updateProject(p);
    }
    // Gets all the users from an observable List
    @Override
    public ObservableList<User> getAllUsers()
    {
        return dalFacade.getAllUsers();
    }
    // Adds a user
    @Override
    public boolean addUser(User user)
    {
        return dalFacade.addUser(user);
    }
    // Updates a user
    @Override
    public boolean updateUser(User user)
    {
        return dalFacade.updateUser(user);
    }
    // Deletes a User
    @Override
    public boolean deleteUser(User user)
    {
        return dalFacade.deleteUser(user);
    }
    // Updates the time from a task
    @Override
    public boolean updateTime(TaskTime tt) {
        return dalFacade.updateTime(tt);
    }
    // Get the time from an observableList
    @Override
    public ObservableList<TaskTime> getTime(Task t) {
        dalFacade.getTime(t);
        return dalFacade.getTime(t);
        
    }
    // Get tasks from an observable list
    @Override
    public ObservableList<Task> getTasks(Project p) {
        return dalFacade.getTasks(p);
    }
    // Adds tasks
    @Override
    public boolean addTask(Task t, Project p) {
        return dalFacade.addTask(t, p);
    }
    // Deletes the Task
    @Override
    public boolean deleteTask(Task selectedTask)
    {
        return dalFacade.deleteTask(selectedTask);
    }
    // Submits the time
    @Override
    public boolean submitTime(TaskTime tt, Task t) {
        return dalFacade.submitTime(tt, t);
    }
    // Gets the total time logged
    @Override
    public TaskTime getTotalTime(Task currentTask) {
        return null;
    }
    // Checks if the email is in use
    @Override
    public boolean checkIfEmailIsUsed(String email) {
        boolean used = false;
        
        for (User user : getAllUsers()) {
            if (user.getEmail().equals(email)) {
                used = true;
            }
        }
        
        return used;
    }
    // Get all the clients form a Client observable list
    @Override
    public ObservableList<Client> getAllClients() {
        return dalFacade.getAllClients();
    }
    // Creates a Client
    @Override
    public boolean createClient(Client client) {
        return dalFacade.createClient(client);
    }
    // Updates a Client
    @Override
    public boolean updateClient(Client client) {
        return dalFacade.updateClient(client);
    }
    // Deletes a client
    @Override
    public boolean deleteClient(Client client) {
        return dalFacade.deleteClient(client);
    }
    // Get all the clients and their project from an observable list
    @Override
    public ObservableList<Project> getAllClientProjects(Client client) {
        return dalFacade.getAllClientProjects(client);
    }
    // Checks if a client is uniq 
    @Override
    public boolean checkIfClientNameIsUsed(String name) {
        boolean used = false;
        
        for (Client client : getAllClients()) {
            if (client.getName().equals(name)) {
                used = true;
            }
        }
        
        return used;
    }


    public ObservableList<TaskLog> getAllLogs()
    {
        return dalFacade.readLogs();
    }
	
    @Override
    public boolean deleteProject(Project project) {
        return dalFacade.deleteProject(project);
    }

    public boolean createTimeLog(TaskLog timeLog)
    {
        return dalFacade.addTimeLog(timeLog);
    }

    public ObservableList<TaskLog> getAllTimeLogs()
    {
        return dalFacade.readTimeLogs();
    }

    @Override
    public ObservableList<TaskTime> getUserTime(Task t, User u) {
        return dalFacade.getUserTime(t, u);
    }

    
    

    
}
