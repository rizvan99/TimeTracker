
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.controller.UserDBDAOController;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author fauxtistic
 */
public class UserDatabaseDalFacade implements IUserDal {
    
    private UserDBDAOController controller;

    public UserDatabaseDalFacade() {
        controller = new UserDBDAOController(ConnectionPool.getInstance());
    }    
    
    /**
     * Retrieves a list of all users from database
     * @return List of all users
     */
    @Override
    public ObservableList<User> getAllUsers() {
        return controller.getAllUsers();
    }

    /**
     * Creates a single, new user in database
     * @param user to be created
     * @return true if creation succeeded, otherwise false
     */
    @Override
    public boolean addUser(User user) {
        return controller.addUser(user);
    }

    /**
     * Updates a single, existing user in database
     * @param user to be updated
     * @return true if update succeeded, otherwise false
     */
    @Override
    public boolean updateUser(User user) {
        return controller.updateUser(user);
    }    
    
    /**
     * Deletes a single, existing user in database
     * @param user to be deleted
     * @return true if deletion succeeded, otherwise false
     */
    @Override
    public boolean deleteUser(User user) {
        return controller.deleteUser(user);
    }
    
}
