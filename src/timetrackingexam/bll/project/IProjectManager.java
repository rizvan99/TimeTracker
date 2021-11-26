
package timetrackingexam.bll.project;

import java.util.List;
import timetrackingexam.be.Project;

/**
 *
 * @author Rizvan
 */
public interface IProjectManager
{
    public List<Project> getAllProjects();
    
    public boolean createNewProject(Project p);

    public boolean updateProject(Project p);
    
    public boolean deleteProject(Project project);
    
}
