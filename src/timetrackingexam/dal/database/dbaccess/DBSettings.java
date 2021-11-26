
package timetrackingexam.dal.database.dbaccess;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
public class DBSettings
        
/**
 *
 * @author Jonas
 */

{

    private SQLServerDataSource dataSource;

// Uses a TXT file rather than hard coding it. This keeps the database safer.
    public DBSettings()
    {
        Properties props = new Properties();

        try{
            props.load(new FileReader("DBSettings.txt"));

            dataSource = new SQLServerDataSource();
            dataSource.setDatabaseName(props.getProperty("database"));
            dataSource.setUser(props.getProperty("user"));
            dataSource.setPassword(props.getProperty("password"));
            dataSource.setServerName(props.getProperty("server"));
        }
        catch(IOException ioE){
            System.out.println("File could not be read");
        }
    }

    /**
     * establishes a connection to the database
     *
     * @return a Connection to the database
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException
    {
            return dataSource.getConnection();
        
    }
}
