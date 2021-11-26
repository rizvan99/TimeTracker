
package timetrackingexam.dal.database.controller;

import java.sql.Connection;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.dao.TaskDBDAO;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author Rizvan
 */
public class TaskDaoController
{
    private final ConnectionPool conPool;
    private final TaskDBDAO taskDAO;

    public TaskDaoController(ConnectionPool conPool)
    {
        this.conPool = conPool;
        taskDAO = TaskDBDAO.getInstance();
    }
    // Adds a task to a project
    public boolean addTask(Task t, Project p)
    {
        Connection con = conPool.checkOut();
        Boolean create = taskDAO.addTask(t, p, con);
        conPool.checkIn(con);
        return create;
    }
    // Deletes a task from a project
    public boolean deleteTask(Task task)
    {
        Connection con = conPool.checkOut();
        boolean deleted = taskDAO.deleteTask(con, task);
        conPool.checkIn(con);
        return deleted;
    }
    // Updates a task at a project
    public boolean updateTask(Task task)
    {
        Connection con = conPool.checkOut();
        boolean updated = taskDAO.updateTask(con, task);
        conPool.checkIn(con);
        return updated;
    }
    // Gets the Time of the TaskTime Observablelist
    public ObservableList<TaskTime> getTime(Task t) {
        Connection con = conPool.checkOut();
        ObservableList<TaskTime> time = taskDAO.getTime(con, t);
        conPool.checkIn(con);
        return time;
    }
    // Updates the time of a task
    public boolean updateTime(TaskTime tt) {
        Connection con = conPool.checkOut();
        boolean updated = taskDAO.updateTime(tt, con);
        conPool.checkIn(con);
        return updated;
    }
    // gets the Tasks of an observeable list
    public ObservableList<Task> getTasks(Project p) {
        Connection con = conPool.checkOut();
        ObservableList tasks = taskDAO.getTasks(con, p);
        conPool.checkIn(con);
        return tasks;
    }
    // Submits the Time on a task
    public boolean submitTime(TaskTime tt) {
        Connection con = conPool.checkOut();
        boolean created = taskDAO.submitTime(tt, con);
        conPool.checkIn(con);
        return created;
    }
    // Gets the Task logs from an observableList
    public ObservableList<TaskLog> getTaskLogs()
    {
        Connection con = conPool.checkOut();
        ObservableList logs = taskDAO.getLogs(con);
        conPool.checkIn(con);
        return logs;
    }
    // Creates a time log for a task
    public boolean createTimeLog(TaskLog timeLog)
    {
        Connection con = conPool.checkOut();
        boolean add = taskDAO.createTimeLog(con, timeLog);
        conPool.checkIn(con);
        return add;
    }
    // Gets the time logs from an observable list
    public ObservableList<TaskLog> getTimeLogs()
    {
        Connection con = conPool.checkOut();
        ObservableList timelogs = taskDAO.getTimeLogs(con);
        conPool.checkIn(con);
        return timelogs;
    }
    // Gets the userTime from the observable list
    public ObservableList<TaskTime> getUserTime(Task t, User u) {
        Connection con = conPool.checkOut();
        ObservableList userTime = taskDAO.getUserTime(con, t, u);
        conPool.checkIn(con);
        return userTime;
    }

}
