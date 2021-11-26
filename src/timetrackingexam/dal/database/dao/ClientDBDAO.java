package timetrackingexam.dal.database.dao;


import timetrackingexam.be.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
/**
 *
 * @author jonas
 */
// Collects all the clients to an Observable List
public class ClientDBDAO {
        
    public ObservableList<Client> getAllClients(Connection con) throws SQLException {

        String sql = "SELECT * FROM Client;";
        try (Statement stmt = con.createStatement()) {

            ObservableList<Client> clients = FXCollections.observableArrayList();
            ResultSet rs = stmt.executeQuery(sql);            

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int defaultrate = rs.getInt("Defaultrate");

                Client client = new Client(id, name, defaultrate);

                clients.add(client);
            }
            return clients;
        }
    }
    // Creates Client on the SQL Database
    public boolean createClient(Connection con, Client client) throws SQLException {
        String sql = "INSERT INTO Client (Name, Defaultrate) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {            
            ps.setString(1, client.getName());
            ps.setInt(2, client.getDefaultrate());            
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        }
    }
    // Updates the Client on the  SQL Database
    public boolean updateClient(Connection con, Client client) throws SQLException {
        String sql = "UPDATE Client SET Name = ?, Defaultrate = ? WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, client.getName());
            ps.setInt(2, client.getDefaultrate());
            ps.setInt(3, client.getId());            
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        }
    }
    // Deletes the Client on the SQL Database
    public boolean deleteClient(Connection con, Client client) throws SQLException {
        String sql = "DELETE FROM Client WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, client.getId());
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        }
    }
    // Gets all the Projects from a client on the SQL Database
    public ObservableList<Project> getAllClientProjects(Connection con, Client client) throws SQLException {
        String sql = "SELECT * FROM Project WHERE ClientID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ObservableList<Project> clientProjects = FXCollections.observableArrayList();
            ps.setInt(1, client.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                int rate = rs.getInt("Rate");
                int clientId = rs.getInt("ClientID");
                
                Project project = new Project(name, clientId, description, rate);
                
                project.setId(id);
                clientProjects.add(project);
            }
            return clientProjects;
        }
    }
    
}
