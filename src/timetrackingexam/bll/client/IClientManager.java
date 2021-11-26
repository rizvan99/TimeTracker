
package timetrackingexam.bll.client;

import javafx.collections.ObservableList;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;

/**
 *
 * @author fauxtistic
 */
public interface IClientManager {
    
    public ObservableList<Client> getAllClients();
    public boolean createClient(Client client);
    public boolean updateClient(Client client);
    public boolean deleteClient(Client client);
    public ObservableList<Project> getAllClientProjects(Client client);
    public boolean checkIfClientNameIsUsed(String name);
    
}
