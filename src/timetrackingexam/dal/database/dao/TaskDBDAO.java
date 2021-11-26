
package timetrackingexam.dal.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author math2
 */
public class TaskDBDAO{

    private ConnectionPool pool;
    private static TaskDBDAO INSTANCE;

    private TaskDBDAO() {
        pool = ConnectionPool.getInstance();
    }
// Checks if the task does exist
    public static TaskDBDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskDBDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
    // Gets the Tasks
    public ObservableList getTasks(Connection con, Project p) {
        try{

            ObservableList<Task> tasks = FXCollections.observableArrayList();

            String sql = "SELECT * FROM task "
                    + "WHERE ProjectID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, p.getId());
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Task taskToBeAdded = new Task(
                        rs.getInt("ProjectID"),
                        rs.getInt("UserID"),
                        rs.getString("Name"),
                        rs.getString("Description")
                );
                
                taskToBeAdded.setId(rs.getInt("ID"));
                
                tasks.add(taskToBeAdded);
            }
            return tasks;
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            return null;
        }
    }
// Deletes the task from the SQL Database
    public boolean deleteTask(Connection con, Task task) {
        try{
            String sql = "DELETE FROM task "
                    + "WHERE "
                    + "ID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, task.getId());

            int updatedRows = pstmt.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
// Updates the selected task on the Database
    public boolean updateTask(Connection con, Task task) {
        try{
            String sql = "UPDATE task "
                    + "SET Name = ?, Description = ? "
                    + "WHERE ID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setInt(3, task.getId());

            int updatedRows = pstmt.executeUpdate();
            
            return updatedRows > 0;
        } catch (SQLException sqlE) {
            System.out.println("Failed to manipulate element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
// Adds task to the SQL Database
    public Boolean addTask(Task t, Project p, Connection con) {
        try{
            String sql = "INSERT INTO task (ProjectID, Name, Description, UserID, TimeAssigned)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, t.getProjectId());
            pstmt.setString(2, t.getName());
            pstmt.setString(3, t.getDescription());
            pstmt.setInt(4, t.getUserId());
            pstmt.setInt(5, t.getTimeAssigned());
            
            int updatedRows = pstmt.executeUpdate();
                       
            return updatedRows > 0;
            
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
// Adds the elipised time to the Database
    public boolean submitTime(TaskTime tt, Connection con) {
        try{
            String sql = "INSERT INTO time (TaskID, UserID, Sec, Min, Hour, Date)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, tt.getTaskid());
            pstmt.setInt(2, tt.getUserId());
            pstmt.setInt(3, tt.getSec());
            pstmt.setInt(4, tt.getMin());
            pstmt.setInt(5, tt.getHours());
            pstmt.setString(6, tt.getDate().toString());
            
            int updatedRows = pstmt.executeUpdate();
                       
            return updatedRows > 0;
            
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
// Opdateres the time to the Database
    public boolean updateTime(TaskTime tt, Connection con) {
        try{
            String sql = "UPDATE time "
                    + "SET Hour = ?, Min = ?, Sec = ? "
                    + "WHERE TaskId = ? AND Date = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, tt.getHours());
            pstmt.setInt(2, tt.getMin());
            pstmt.setInt(3, tt.getSec());
            pstmt.setInt(4, tt.getTaskid());
            pstmt.setString(5, tt.getDate().toString());

            int updatedRows = pstmt.executeUpdate();
            
            return updatedRows > 0;
        } catch (SQLException sqlE) {
            System.out.println("Failed to manipulate element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
    

    // Gets the time already spend from the Database
    public ObservableList<TaskTime> getTime(Connection con, Task t) {
        try{
            ObservableList<TaskTime> times = FXCollections.observableArrayList();
            
            String sql = "SELECT * " +
                    "FROM Time " +
                    "WHERE TaskID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, t.getId());
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                TaskTime time = new TaskTime(
                        rs.getInt("TaskID"),
                        rs.getInt("UserID"),
                        rs.getInt("Sec"),
                        rs.getInt("Min"),
                        rs.getInt("Hour"),
                        LocalDate.parse(rs.getString("date"))
                );   
                time.setID(rs.getInt("ID"));
                times.add(time);
            }
            return times;
            
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return null;
        }
    }
    // Gets the time the user has spend on the Task
    public ObservableList<TaskTime> getUserTime(Connection con, Task t, User u) {
        try{
            ObservableList<TaskTime> times = FXCollections.observableArrayList();
            
            String sql = "SELECT * "
                    + "FROM Time "
                    + "WHERE UserID = ? "
                    + "AND TaskID = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, u.getId());
            pstmt.setInt(2, t.getId());
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                TaskTime time = new TaskTime(
                        rs.getInt("TaskID"),
                        rs.getInt("UserID"),
                        rs.getInt("Sec"),
                        rs.getInt("Min"),
                        rs.getInt("Hour"),
                        LocalDate.parse(rs.getString("date"))
                );   
                time.setID(rs.getInt("ID"));
                times.add(time);
            }
            return times;
            
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return null;
        }
    }
// Recieves the full list of logs from the Tasklogs in the Database
    public ObservableList<TaskLog> getLogs(Connection con)
    {
        try
        {
            ObservableList<TaskLog> logs = FXCollections.observableArrayList();
            String sql = "SELECT * FROM TaskLog JOIN Employee ON TaskLog.userID = Employee.id";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                String password = "";
                User.Role role = User.Role.valueOf(rs.getString("Role"));
                int id = rs.getInt("userId");
                User user = new User(firstName, lastName, email, password, role, id);
                TaskLog log = new TaskLog(rs.getTimestamp("date"), rs.getString("action"));
                log.setTaskName(rs.getString("taskname"));
                log.setCreatedBy(user);
                logs.add(log);
            }
            return logs;
        } catch (SQLException ex)
        {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    // Adds a timed log to the Task in the Database
    public boolean createTimeLog(Connection con, TaskLog timeLog)
    {
        try
        {
            String sql = "INSERT INTO TimeLog (startDate, endDate, submittedTime, userId, taskName) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setObject(1, timeLog.getStartDate());
            pstmt.setObject(2, timeLog.getEndDate());
            pstmt.setDouble(3, timeLog.getSubmittedTime());
            pstmt.setInt(4, timeLog.getCreatedBy().getId());
            pstmt.setString(5, timeLog.getTaskName());
            
            int updatedRows = pstmt.executeUpdate();
            
            return updatedRows > 0;

            
        } catch (SQLException ex)
        {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    // Gets the Time logs from the database
    public ObservableList getTimeLogs(Connection con)
    {
        try
        {
            ObservableList<TaskLog> timeLogs = FXCollections.observableArrayList();
            String sql = "SELECT * FROM TimeLog JOIN Employee ON TimeLog.userId = Employee.Id";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                TaskLog time = new TaskLog
                    (rs.getTimestamp("startDate").toLocalDateTime(),
                    rs.getTimestamp("endDate").toLocalDateTime(),
                    rs.getDouble("submittedTime"));
                
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                String password = "";
                String taskName = rs.getString("taskName");
                User.Role role = User.Role.valueOf(rs.getString("Role"));
                int id = rs.getInt("userId");
                User user = new User(firstName, lastName, email, password, role, id);
                time.setCreatedBy(user);
                time.setTaskName(taskName);
                timeLogs.add(time);
            }
            return timeLogs;
        } catch (SQLException ex)
        {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
}
