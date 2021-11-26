
package timetrackingexam.be;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Rizvan
 */
public class TaskLog
{
    private Date date;
    private String action;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double submittedTime;
    private String submittedTimeStringFormat;
    private User createdBy;
    private String taskName;

    /**
     * For logging CRUD
     * @param date
     * @param action 
     */
    public TaskLog(Date date, String action)
    {
        this.date = date;
        this.action = action;
    }

    /**
     * For logging Time
     * @param startDate
     * @param endDate 
     * @param submittedTime 
     */
    public TaskLog(LocalDateTime startDate, LocalDateTime endDate, double submittedTime)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.submittedTime = submittedTime;
        submittedTimeStringFormat = submittedTime + " sek";
    }
    // Date Getter
    public Date getDate()
    {
        return date;
    }
    // Date Setter
    public void setDate(Date date)
    {
        this.date = date;
    }
    // Action Getter
    public String getAction()
    {
        return action;
    }
    // Action Setter
    public void setAction(String action)
    {
        this.action = action;
    }
    // SubmittedTime Get
    public double getSubmittedTime()
    {
        return submittedTime;
    }
    // SubmittedTimeStingFormat Getter
    public String getSubmittedTimeStringFormat()
    {
        return submittedTimeStringFormat;
    }
    // SubmittedTime Setter
    public void setSubmittedTime(double submittedTime)
    {
        submittedTimeStringFormat = submittedTime + " sek";
        this.submittedTime = submittedTime;
    }
    // StartDate Getter
    public LocalDateTime getStartDate()
    {
        return startDate;
    }
    // StartDate Setter
    public void setStartDate(LocalDateTime startDate)
    {
        this.startDate = startDate;
    }
    // EndDate Getter
    public LocalDateTime getEndDate()
    {
        return endDate;
    }
    // EndDate Setter
    public void setEndDate(LocalDateTime endDate)
    {
        this.endDate = endDate;
    }
    // CreatedBy Getter
    public User getCreatedBy()
    {
        return createdBy;
    }
    // CreatedBy Setter
    public void setCreatedBy(User createdBy)
    {
        this.createdBy = createdBy;
    }
    // TaskName Getter
    public String getTaskName()
    {
        return taskName;
    }
    //TaskName Setter 
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }
}
