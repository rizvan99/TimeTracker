
package timetrackingexam.be;

import java.util.Objects;

/**
 *
 * @author fauxtistic
 */
public class Client {
    
    private int id;
    private String name;
    private int defaultrate; 
       
    public Client(int id, String name, int defaultrate) {     
        this.id = id;
        this.name = name;
        this.defaultrate = defaultrate;
    }    
    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefaultrate() {
        return defaultrate;
    }

    public void setDefaultrate(int defaultrate) {
        this.defaultrate = defaultrate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
                
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
    
    
}
