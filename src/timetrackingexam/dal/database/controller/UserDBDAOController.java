
package timetrackingexam.dal.database.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.dao.UserDBDAO;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author fauxtistic
 */
public class UserDBDAOController {
    
    private ConnectionPool pool;
    private UserDBDAO userDao;

    public UserDBDAOController(ConnectionPool pool) {
        this.pool = pool;
        userDao = new UserDBDAO();
    }
    
    /**
     * Retrieves a list of all users from database
     * @return List of all users
     */
    public ObservableList<User> getAllUsers() {
        
        try {
            Connection con = pool.checkOut();
            ObservableList<User> users = userDao.getAllUsers(con);
            pool.checkIn(con);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    /**
     * Creates a single, new user in database
     * @param user to be created
     * @return true if creation succeeded, otherwise false
     */
    public boolean addUser(User user) {
        
        try {
            Connection con = pool.checkOut();
            boolean databaseUpdated = userDao.createUser(con, user);
            pool.checkIn(con);
            return databaseUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    /**
     * Updates a single, existing user in database
     * @param user to be updated
     * @return true if update succeeded, otherwise false
     */
    public boolean updateUser(User user) {
        
        try {
            Connection con = pool.checkOut();
            boolean databaseUpdated = userDao.updateUser(con, user);
            pool.checkIn(con);
            return databaseUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Deletes a single, existing user in database
     * @param user to be deleted
     * @return true if deletion succeeded, otherwise false
     */
    public boolean deleteUser(User user) {
        try {
            Connection con = pool.checkOut();
            boolean databaseUpdated = userDao.deleteUser(con, user);
            pool.checkIn(con);
            return databaseUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }  
       
       
}
