
package timetrackingexam.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.bll.facade.ITimeTrackBLL;
import timetrackingexam.bll.facade.TimeTrackBLLFacade;
import timetrackingexam.bll.project.ProjectManager;
import timetrackingexam.bll.threads.ThreadManager;

/**
 *
 * @author Rizvan
 */
public class AppModel
{ 
    private static AppModel instance;
    
    private User currentUser;
    private Project currentProject;
    private Task currentTask;
    private Client currentClient;
    private User selectedUser;
    
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    private final ObservableList<Client> clients = FXCollections.observableArrayList();
    private final ObservableList<TaskLog> logs = FXCollections.observableArrayList();
    private final ObservableList<TaskLog> timeLogs = FXCollections.observableArrayList();
    
    private final ITimeTrackBLL ttInterface;
    private final TimeTrackBLLFacade ttBll;
    private final ProjectManager projectManager;
    private final ThreadManager tm;

    private AppModel()
    {
        ttInterface = new TimeTrackBLLFacade();
        ttBll = new TimeTrackBLLFacade();
        projectManager = new ProjectManager();
        tm = new ThreadManager();
    }
    /**
     * Create a new instance or get the current intance of AppModel
     * @return AppModel Class
     */
    public static AppModel getInstance() {
        if (instance == null) {
            instance = new AppModel();
        }
        return instance;
    }
    
    /**
     * Updates all ObservableLists in the AppModel class
     */
    public void fetch(){
        getAllUsers();
        getTasks();   
        getTimeLogs();
        getLogs();
        getProjects();           
        getAllClients();
        getTasks();                
    }
    // Gets AllUsers
    public ObservableList<User> getAllUsers()
    {
        users.clear();
        users.addAll(ttInterface.getAllUsers());
        return users;
    }
    // Gets CurrentUser
    public User getCurrentUser()
    {
        return currentUser;
    }
    // Sets CurrentUser
    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;   
    }
    // Gets Projects
    public ObservableList<Project> getProjects(){
        projects.clear();
        projects.addAll(ttBll.getAllProjects());        
        return projects;
    }
    // Gets Tasks
    public ObservableList<Task> getTasks()
    {
        tasks.clear();
        if (currentProject!=null) {            
            tasks.addAll(ttInterface.getTasks(currentProject));
        }        
        return tasks;
        
    }
    // Gets CurrentProjects
    public Project getCurrentProject() {
        return currentProject;
    }
    // Sets CurrentProjects
    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }
    // Creates a new Project
    public boolean createNewProject(Project p){
        projects.add(p);
        return projectManager.createNewProject(p);
    }
    //Updates Selected Project
    public boolean updateProject(Project p) {
        if (projectManager.updateProject(p)) {
            getProjects();
            return true;
        }
        return false;
    }
    // Deletes a Project
    public boolean deleteProject(Project project) {
        return projectManager.deleteProject(project);
    }
    // Sets the current task
    public void setCurrentTask(Task currentTask){
        this.currentTask = currentTask;
    }
    // Gets Current task
    public Task getCurrentTask() {
        return currentTask;
    }
    // Adds Task
    public boolean addTask(Task t, Project p)
    {
        if(ttInterface.addTask(t, p)){
            fetch();
            fetchLogs();
            return true;
        }
        else{
            return false;
        }
    }
    // Updates the selected task
    public boolean updateTask(Task updateTask)
    {
        if(ttInterface.updateTask(updateTask))
        {
            fetch();
            fetchLogs();
            fetchTimeLogs();
            return true;
        }
        return false;  
    }
    // Starts the timer 
    public void startTimer(TextField sec, TextField min, TextField hours){
        tm.startTimer(sec, min, hours);
    }
    // Pauses the Timer
    public void pauseTimer(){
        tm.pauseTimer();
    }
    // Stops the timer
    public void stopTimer(){
        tm.stopTimer();
    }
    // Keeps Track of if the timer is running
    public boolean timerIsRunning(){
        return tm.isRunning();
    }
    // Gets The Selected User
    public User getSelectedUser() {
        return selectedUser;
    }
    // Sets the Selected User
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    // Checks if the new Email is Uniq
    public boolean checkIfEmailIsUsed(String email) {
        return ttInterface.checkIfEmailIsUsed(email);
    }
    // Adds a user
    public boolean addUser(User user) {
        return ttInterface.addUser(user);        
    }
    // Update a Users details
    public boolean updateUser(User user) {
        return ttInterface.updateUser(user);        
    }
    // Deletes the Selected User
    public boolean deleteUser(User user) {
        return ttInterface.deleteUser(user);        
    }
    // Gets the time from the Observeable List
    public ObservableList<TaskTime> getAllTime(){
        return ttInterface.getTime(currentTask);
    }
    // Gets the total time 
    public TaskTime getTotalTime(){
        return ttInterface.getTotalTime(currentTask);
    }
    // Deletes a task
    public boolean deleteTask() {
        
        if(ttInterface.deleteTask(currentTask))
        {
            fetchLogs();
        }return true;
    }
    
    public boolean submitTime(TaskTime tt) {
        if(ttInterface.submitTime(tt, currentTask))
        {
            fetchTimeLogs();
            fetchLogs();
            return true;
        }
        return false;
    }
    // Gets the CSV
    public void getCSV()
    {
        projectManager.getCSV();
    }    
    // Selects all the Clients from the ObserveableList <Client>
    public ObservableList<Client> getAllClients() {
        clients.clear();
        clients.addAll(ttInterface.getAllClients());
        return clients;
    }
    // Creates a Client 
    public boolean createClient(Client client) {
        return ttInterface.createClient(client);
    }
    // Updates the Client
    public boolean updateClient(Client client) {
        return ttInterface.updateClient(client);
    }
    // Deletes the Client
    public boolean deleteClient(Client client) {
        return ttInterface.deleteClient(client);
    }
    // Gets all the Client Projects from the Observeable list
    public ObservableList<Project> getAllClientProjects(Client client) {
        return ttInterface.getAllClientProjects(client);
    }
    // Gets Current Client
    public Client getCurrentClient() {
        return currentClient;
    }
    // Sets the Current Client
    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }    
    // Checks if the client already exists or not
    public boolean checkIfClientNameIsUsed(String name) {
        return ttInterface.checkIfClientNameIsUsed(name);
    }
    // Fetches the logs
    public void fetchLogs()
    {
        logs.clear();
        logs.addAll(ttBll.getAllLogs());
    }
    
    public void fetchTimeLogs()
    {
        timeLogs.clear();
        timeLogs.addAll(ttBll.getAllTimeLogs());
    }
    
    // Gets the logs of a task
    public ObservableList<TaskLog> getLogs()
    {
        logs.clear();
        logs.addAll(ttBll.getAllLogs());
        return logs;
    }
    // Creates a TimeLog 
    public void createTimeLog(TaskLog timeLog)
    {
        ttBll.createTimeLog(timeLog);
    }
    // Gets the TimeLogs
    public ObservableList<TaskLog> getTimeLogs()
    {
        timeLogs.clear();
        timeLogs.addAll(ttBll.getAllTimeLogs());
        return timeLogs;
    }
    public ObservableList<TaskTime> getUserTime() {
        return ttInterface.getUserTime(currentTask, currentUser);
    }
    
    
    
}
