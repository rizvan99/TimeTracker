
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;

/**
 *
 * @author math2
 */
public interface ITaskDal {
    public ObservableList<Task> getTasks(Project p);
    public boolean deleteTask(Task task);
    public boolean updateTask(Task task);
    public boolean addTask(Task t, Project p);
    
    public ObservableList<TaskTime> getTime(Task t);
    public boolean submitTime(TaskTime tt, Task t);
    public boolean updateTime(TaskTime tt);
    public ObservableList<TaskTime> getUserTime(Task t, User u);

    
    
}
