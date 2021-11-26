
package timetrackingexam.bll.user;

import javafx.collections.ObservableList;
import timetrackingexam.be.User;
import timetrackingexam.dal.facade.IUserDal;
import timetrackingexam.dal.facade.TimeTrackDalFacade;

/**
 *
 * @author Rizvan
 */
public class UserManager implements IUserManager
{
    
    private IUserDal userDal;

    public UserManager()
    {
        userDal = new TimeTrackDalFacade();
    }

    /**
     * Retrieves a list of all users from DAL layer
     * @return List of all users
     */
    @Override
    public ObservableList<User> getAllUsers()
    {
        return userDal.getAllUsers();
    }
    
    /**
     * Checks in DAL layer to see if email is already in use by existing user
     * @param email to be searched for
     * @return true if email is used by existing user, otherwise false
     */
    @Override
    public boolean checkIfEmailIsUsed(String email) {
        
        boolean used = false;
        
        for (User user : getAllUsers()) {
            if (user.getEmail().equals(email)) {
                used = true;
            }
        }
        
        return used;
    }
    
    /**
     * Creates a single, new user in DAL layer
     * @param user to be created
     * @return true if creation succeeded, otherwise false
     */
    @Override
    public boolean addUser(User user) {
        return userDal.addUser(user);
    }
    
    /**
     * Updates a single, existing user in DAL layer
     * @param user to be updated
     * @return true if update succeeded, otherwise false
     */
    @Override
    public boolean updateUser(User user) {
        return userDal.updateUser(user);
    }
    
    /**
     * Deletes a single, existing user in DAL layer
     * @param user to be deleted
     * @return true if deletion succeeded, otherwise false
     */
    @Override
    public boolean deleteUser(User user) {
        return userDal.deleteUser(user);
    }

}
