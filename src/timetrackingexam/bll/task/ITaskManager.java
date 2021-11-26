
package timetrackingexam.bll.task;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;

/**
 *
 * @author Rizvan
 */
public interface ITaskManager
{
    
    public boolean addTask(Task t, Project p);
    public ObservableList<Task> getTasks(Project ps);
    public boolean deleteTask(Task selectedTask);
    public boolean updateTask(Task updateTask);
    
    public ObservableList<TaskTime> getTime(Task t);
    public ObservableList<TaskTime> getUserTime(Task t, User u);
    public TaskTime getTotalTime(Task currentTask);
    public boolean updateTime(TaskTime tt);
    public boolean submitTime(TaskTime tt, Task t);
    
     
}
