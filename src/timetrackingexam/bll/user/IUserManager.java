
package timetrackingexam.bll.user;

import javafx.collections.ObservableList;
import timetrackingexam.be.User;

/**
 *
 * @author Rizvan
 */
public interface IUserManager
{
    public ObservableList<User> getAllUsers();
    
    public boolean addUser(User user);
    
    public boolean updateUser(User user);
    
    public boolean deleteUser(User user);    
   
    public boolean checkIfEmailIsUsed(String email);
}
