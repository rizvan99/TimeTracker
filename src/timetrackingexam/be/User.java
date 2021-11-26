
package timetrackingexam.be;

import java.util.Objects;

/**
 *
 * @author Rizvan
 */
public class User
{
    private int Id;
    private String firstName;
    private String lastName;
    private final String email;
    private String password;
    private Role role;

    public User(String firstName, String lastName, String email, String password, Role role, int Id)
    {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    // ID Getter
    public int getId() {
        return Id;
    }
    // ID Setter
    public void setId(int Id) {
        this.Id = Id;
    }
    // FirstName Getter
    public String getFirstName()
    {
        return firstName;
    }
    // FirstName Setter
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    // LastName Getter
    public String getLastName()
    {
        return lastName;
    }
    //LastName Setter
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    // Email Getter
    public String getEmail()
    {
        return email;
    }
    // Password Getter
    public String getPassword()
    {
        return password;
    }
    // Password Setter
    public void setPassword(String password)
    {
        this.password = password;
    }
    // Role Getter
    public Role getRole()
    {
        return role;
    }
    // Role Setter
    public void setRole(Role role)
    {
        this.role = role;
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
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }
    
    
        
    /**
     * LeadAdmin can make other users into admins
     * Admin can create projects and see overview of users (who works on what, hours etc.)
     * Default is just a normal user who can time track and see his own work etc.
     */
    public enum Role
    {
        Admin, Default
    }
    
    
    
    
}
