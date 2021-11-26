
package timetrackingexam.bll.task;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.bll.facade.TimeTrackBLLFacade;
import timetrackingexam.bll.utilities.StatisticsCalculator;
import timetrackingexam.dal.facade.IProjectDal;
import timetrackingexam.dal.facade.ITaskDal;
import timetrackingexam.dal.facade.TimeTrackDalFacade;
import timetrackingexam.dal.mockdata.MockProjectManager;
/**
 *
 * @author Rizvan
 */
public class TaskManager implements ITaskManager
{
    private Task task;
    private StatisticsCalculator cal;
    private final ITaskDal taskManager;
    private ObservableList times = FXCollections.observableArrayList();

    public TaskManager()
    {
        taskManager = new TimeTrackDalFacade();
    }

    
    // Deletes a task
    @Override
    public boolean deleteTask(Task selectedTask)
    {
        return taskManager.deleteTask(task);
    }

    // Gets the tasks from an observable list
    @Override
    public ObservableList<Task> getTasks(Project p)
    {
        return taskManager.getTasks(p);
    }
    // Updates the selected Task
    @Override
    public boolean updateTask(Task updateTask)
    {
        return taskManager.updateTask(updateTask);
    }
    // Gets the time from an observablelist
    @Override
    public ObservableList<TaskTime> getTime(Task t) {
        times.removeAll();
        times.addAll(taskManager.getTime(t));
        return times;
    }
    // Updates the time of a task
    @Override
    public boolean updateTime(TaskTime tt) {
        return taskManager.updateTime(tt);
    }
    // Submits the time of a task
    @Override
    public boolean submitTime(TaskTime tt, Task t) {
            return taskManager.submitTime(tt, t);
    }
    // Adds a task
    @Override
    public boolean addTask(Task t, Project p) {
        return taskManager.addTask(t, p);
    }
    // Gets the Total time of a task
    @Override
    public TaskTime getTotalTime(Task currentTask) {
        int totalHour=0;
        int totalMin=0;
        int totalSec=0;
        
        for (TaskTime time : taskManager.getTime(currentTask)) {
            totalHour += time.getHours();
            totalMin += time.getMin();
            totalSec += time.getSec();
        }
        TaskTime totalTime = new TaskTime(totalSec, totalMin, totalHour);
        return totalTime;
    }

    @Override
    public ObservableList<TaskTime> getUserTime(Task t, User u) {
        return taskManager.getUserTime(t,u);
    }

    
    
    
}
