
package timetrackingexam.bll.client;

import javafx.collections.ObservableList;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
import timetrackingexam.dal.facade.IClientDal;
import timetrackingexam.dal.facade.TimeTrackDalFacade;

/**
 *
 * @author fauxtistic
 */
public class ClientManager implements IClientManager {
    
    private IClientDal clientDal;
    
    public ClientManager() {
        clientDal = new TimeTrackDalFacade();
    }

    /**
     * Retrieves a list of all clients from DAL layer
     * @return List of all clients
     */
    @Override
    public ObservableList<Client> getAllClients() {
        return clientDal.getAllClients();
    }

    /**
     * Creates a single, new client in DAL layer
     * @param client to be created
     * @return true if creation succeeded, otherwise false
     */
    @Override
    public boolean createClient(Client client) {
        return clientDal.createClient(client);
    }

    /**
     * Updates a single, existing client in DAL layer
     * @param client to be updated
     * @return true if update succeeded, otherwise false
     */
    @Override
    public boolean updateClient(Client client) {
        return clientDal.updateClient(client);
    }

    /**
     * Deletes a single, existing client in DAL layer
     * @param client to be deleted
     * @return true if deletion succeeded, otherwise false
     */
    @Override
    public boolean deleteClient(Client client) {
        return clientDal.deleteClient(client);
    }

    /**
     * Retrieves a list of all projects belonging to client from DAL layer
     * @param client whose projects to make a list of
     * @return List of all projects belonging to client
     */
    @Override
    public ObservableList<Project> getAllClientProjects(Client client) {
        return clientDal.getAllClientProjects(client);
    }

    /**
     * Checks in DAL layer to see if a client name is already in use by an existing client
     * @param name to be searched for
     * @return true if name is already in use by existing client, otherwise false
     */
    @Override
    public boolean checkIfClientNameIsUsed(String name) {
        boolean used = false;
        
        for (Client client : getAllClients()) {
            if (client.getName().equals(name)) {
                used = true;
            }
        }
        
        return used;
    }
    
    
    
    
    
}
