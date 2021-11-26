
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.User;

/**
 *
 * @author fauxtistic
 */
public interface IUserDal {
    
    public ObservableList<User> getAllUsers();    
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);   
    
}
