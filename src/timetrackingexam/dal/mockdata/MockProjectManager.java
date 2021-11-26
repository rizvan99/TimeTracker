
package timetrackingexam.dal.mockdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.dal.facade.IProjectDal;

/**
 *
 * @author math2
 */
public class MockProjectManager implements IProjectDal {

    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    
    public MockProjectManager() {
        initialize();
    }
    
    private void initialize(){
//        Project p1 = new Project(1, "Project X");
//        Project p2 = new Project(2, "Project Y");
//        Project p3 = new Project(3, "Project Z");
        
//        projects.add(p1);
//        projects.add(p2);
//        projects.add(p3);
        
//        p1.setDescription("Create a program that allows the user to manage"
//                + " his/hers and their staffs time");
        
//        Task t1 = new Task(1, "Task1", 30);
//        Task t2 = new Task(2, "Task2", 5);
//        Task t3 = new Task(3, "Task3", 10);
//        Task t4 = new Task(4, "Task4", 20);
//        Task t5 = new Task(5, "Task5", 20);
//        Task t6 = new Task(6, "Task6", 20);
//        
//        Task t7 = new Task("Create something", "Description of something");
//        
//        t1.setUserId(1);
        
//        TaskTime tt1 = new TaskTime(45, 4, 1, LocalDate.parse("2020-04-30"));
//        TaskTime tt2 = new TaskTime(5, 55, 5, LocalDate.parse("2020-05-01"));
//        TaskTime tt3 = new TaskTime(14, 1, 12, LocalDate.parse("2020-05-02"));
//        TaskTime tt4 = new TaskTime(5, 1, 12, LocalDate.parse("2020-05-03"));
//        TaskTime tt5 = new TaskTime(2, 5, 4, LocalDate.parse("2020-05-04"));
//        TaskTime tt6 = new TaskTime(5, 7, 1, LocalDate.parse("2020-05-05"));
//        TaskTime tt7 = new TaskTime(1, 7, 6, LocalDate.parse("2020-05-07"));
        
        
//        t1.addTaskTime(tt1);
//        t2.addTaskTime(tt2);
//        t3.addTaskTime(tt3);
//        t1.addTaskTime(tt4);
//        t1.addTaskTime(tt5);
//        t1.addTaskTime(tt6);
//        t1.addTaskTime(tt7);
//        
//        t4.addTaskTime(tt7);
//        t4.addTaskTime(tt4);
//        t5.addTaskTime(tt3);
//        t6.addTaskTime(tt5);
        
//        p1.addTask(t1);
//        p1.addTask(t2);
//        p1.addTask(t3);
//        p2.addTask(t4);
//        p2.addTask(t5);
//        p2.addTask(t6);
//        p3.addTask(t7);
        
        
        
        
    }

    public ObservableList<Project> getProjects() {
        return projects;
    }
    
    public ObservableList<Task> getTasks()
    {
        return tasks;
    }
    
    public boolean createNewProject(Project p){
        return projects.add(p);
    }

    public boolean updateProject(Project p) {
        for (Project project : projects) {
            if(p.getId() == project.getId()){
                project = p;
                return true;
            }
        }
        return false;
    }


    public boolean updateTask(Task updateTask)
    {
        throw new UnsupportedOperationException();
    }

    public ObservableList<Task> getTimeUsed(Task t) {
        return tasks;
    }

    @Override
    public void getCSV()
    {
        throw new UnsupportedOperationException(); 
    }

    @Override
    public boolean deleteProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addTask(Task t, Project p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
