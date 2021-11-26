
package timetrackingexam.dal.database.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
import timetrackingexam.dal.database.dao.ClientDBDAO;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author fauxtistic
 */
public class ClientDBDAOController {
    
    private ConnectionPool pool;
    private ClientDBDAO clientDao;

    public ClientDBDAOController(ConnectionPool pool) {
        this.pool = pool;
        clientDao = new ClientDBDAO();
    }
  // Gets clients i Databasen. via Client DBDAO
    public ObservableList<Client> getAllClient() {
        
        try {
            Connection con = pool.checkOut();
            ObservableList<Client> clients = clientDao.getAllClients(con);
            pool.checkIn(con);
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
        
    }
    // Tilføjer nye Clients til Databasen via Client DBDAO
    public boolean createClient(Client client) {
         try {
            Connection con = pool.checkOut();
            boolean databaseUpdated = clientDao.createClient(con, client);
            pool.checkIn(con);
            return databaseUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // opdatere Clienten med det nye tilføjede data
    public boolean updateClient(Client client) {
        
        try {
            Connection con = pool.checkOut();
            boolean databaseUpdated = clientDao.updateClient(con, client);
            pool.checkIn(con);
            return databaseUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    // Sletter Selected Client
    public boolean deleteClient(Client client) {
        try {
            Connection con = pool.checkOut();
            boolean databaseUpdated = clientDao.deleteClient(con, client);
            pool.checkIn(con);
            return databaseUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }  
    // Henter alle Clients Projecter, hver client kan have flere Projecter
    public ObservableList<Project> getAllClientProjects(Client client) {
        try {
            Connection con = pool.checkOut();
            ObservableList<Project> clientProjects = clientDao.getAllClientProjects(con, client);
            pool.checkIn(con);
            return clientProjects;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
}
