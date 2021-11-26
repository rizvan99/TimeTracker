
package timetrackingexam.bll.facade;

import timetrackingexam.bll.client.IClientManager;
import timetrackingexam.bll.project.IProjectManager;
import timetrackingexam.bll.task.ITaskManager;
import timetrackingexam.bll.user.IUserManager;

/**
 *
 * @author Rizvan
 */
public interface ITimeTrackBLL extends ITaskManager, IProjectManager, IUserManager, IClientManager
{
  
}
