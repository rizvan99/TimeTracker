
package timetrackingexam.dal.database.controller;

import java.io.FileNotFoundException;
import java.sql.Connection;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.dal.database.dao.ProjectDBDAO;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author Rizvan
 */
public class ProjectDaoController
{
    
    private final ConnectionPool conPool;
    private final ProjectDBDAO projectDao;

    public ProjectDaoController(ConnectionPool conPool)
    {
        this.conPool = conPool;
        projectDao = new ProjectDBDAO();
    }
    // Creates Projects via Project DBDAO
    public boolean createProject(Project p)
    {
        Connection con = conPool.checkOut();
        Boolean project = projectDao.createProject(con, p);
        conPool.checkIn(con);
        return project;
    }
    // Deletes Projects via Project DBDAO
    public boolean deleteProject(Project p)
    {
        Connection con = conPool.checkOut();
        Boolean delete = projectDao.deleteProject(con, p);
        conPool.checkIn(con);
        return delete;
    }
    // Edits Projects via Project DBDAO
    public boolean editProject(Project p)
    {
        Connection con = conPool.checkOut();
        Boolean edit = projectDao.updateProject(con, p);
        conPool.checkIn(con);
        return edit;
    }
    // Gets the projects from an observablelist
    public ObservableList<Project> getProjects()
    {
        Connection con = conPool.checkOut();
        ObservableList<Project> allProjects = projectDao.getAllProjects(con);
        conPool.checkIn(con);
        return allProjects;
    }
    // Creates a txt file of a list
   public void getCSV() throws FileNotFoundException
   
   {
        Connection con = conPool.checkOut();
        projectDao.getCSV(con);
        conPool.checkIn(con);
   }
    
    
}
