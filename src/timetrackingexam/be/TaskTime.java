
package timetrackingexam.be;

import java.time.LocalDate;

/**
 *
 * @author math2
 */
public class TaskTime {
    private int ID;
    private int taskid;
    private int userId;
    private int sec;
    private int min;
    private int hours;
    private LocalDate date;

    public TaskTime(int sec, int min, int hours) {
        this.sec = sec;
        this.min = min;
        this.hours = hours;
        date = LocalDate.now();
    }

    public TaskTime(int taskId, int userId, int sec, int min, int hours, LocalDate dateOfWeek) {
        this.taskid = taskId;
        this.userId = userId;
        this.sec = sec;
        this.min = min;
        this.hours = hours;
        this.date = dateOfWeek;
    }
    // Date getter
    public LocalDate getDate() {
        return date;
    }
    // Date Setter
    public void setDate(LocalDate date) {
        this.date = date;
    }
    // Sec Getter
    public int getSec() {
        return sec;
    }
    // Sec Setter
    public void setSec(int sec) {
        this.sec = sec;
    }
    // Min Getter
    public int getMin() {
        return min;
    }
    // Min Setter
    public void setMin(int min) {
        this.min = min;
    }
    // Hours getter
    public int getHours() {
        return hours;
    }
    // Hours Setter
    public void setHours(int hours) {
        this.hours = hours;
    }
    // Taskid Getter
    public int getTaskid() {
        return taskid;
    }
    // Taskid Setter
    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }
    // UserId Getter
    public int getUserId() {
        return userId;
    }
    // UserId Setter
    public void setUserId(int userId) {
        this.userId = userId;
    }
    // ID Getter
    public int getID() {
        return ID;
    }
    // ID setter
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return taskid + " " + userId;
    }

    
   
    
}
