
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;

/**
 *
 * @author fauxtistic
 */
public interface IClientDal {
    
    public ObservableList<Client> getAllClients();
    public boolean createClient(Client client);
    public boolean updateClient(Client client);
    public boolean deleteClient(Client client);
    public ObservableList<Project> getAllClientProjects(Client client);
    
}
