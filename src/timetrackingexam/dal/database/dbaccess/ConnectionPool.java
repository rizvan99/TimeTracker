
package timetrackingexam.dal.database.dbaccess;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rizvan
 */

public class ConnectionPool extends ObjectPool<Connection>
{
    
    private static ConnectionPool INSTANCE;
    private DBSettings dbSettings;
    
    public static synchronized ConnectionPool getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    private ConnectionPool()
    {
        super();
        dbSettings = new DBSettings();
    }
    
    
    
    @Override
    protected Connection create()
    {
        try
        {
            return dbSettings.getConnection();
        } catch (SQLServerException ex)
        {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean validate(Connection o)
    {
        try
        {
            return !o.isClosed();
        } catch (SQLException ex)
        {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }

    @Override
    public void expire(Connection o)
    {
        try
        {
            o.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
