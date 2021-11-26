
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;

/**
 *
 * @author fauxtistic
 */
public interface IProjectDal {
    
    public ObservableList<Project> getProjects();
    public boolean createNewProject(Project p);
    public boolean addTask(Task t, Project p);
    public boolean updateProject(Project p);
    public boolean deleteProject(Project project);
    public void getCSV();

}
