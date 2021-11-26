
package timetrackingexam.bll.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timetrackingexam.be.User;
import timetrackingexam.gui.util.AlertFactory;

/**
 *
 * @author fauxtistic
 */
public class LoginTools {    
    
    /**
     * Retrieves a user matching email and password from list of users, if any
     * @param email user's email must match
     * @param password user's password must match
     * @param users List in which to search for matching user
     * @return Matching user if any, otherwise null
     */
    public static User getVerifiedUser(String email, String password, List<User> users)
    {       
        User verifiedUser = null;
        
        for (User user : users)
        {
            if (user.getEmail().equals(email) && user.getPassword().equals(hashPassword(password)))
            {
                verifiedUser = user;                
            }
        }      
        return verifiedUser;
    }
    
    /**
     * Checks if old password matches user's existing password, and then returns hashed version of new password
     * @param user whose password to check
     * @param oldPassword should match user's current password
     * @param newPassword the new password user should have
     * @return hashed version of new password, if old password matches user's existing password; otherwise null
     */
    public static String getVerifiedNewPassword(User user, String oldPassword, String newPassword) {
        String password = null;
        if (user.getPassword().equals(hashPassword(oldPassword))) {
            password = hashPassword(newPassword);
        }
        
        return password;
    }
    
    /**
     * Checks to see if email abides by regex (includes @ sign etc.) 
     * @param email to check
     * @return true if email matches regex, otherwise false
     */
    public static boolean validateEmail(String email) {
        return email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+");
    }
    
    /**
     * Password must contain one number
     * @param password
     * @return 
     */
    public static boolean validatePassword(String password)
    {
        return password.matches("^(?=.*[0-9])$");
    }
    
    /**
     * Retrieves hashed version of password
     * @param password to hash
     * @return hashed password
     */
    public static String hashPassword(String password) { 
        String base = password;
        try {            
            MessageDigest digest = null;
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();      
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginTools.class.getName()).log(Level.SEVERE, null, ex);
            AlertFactory.showErrorAlert("Error in user login system");
        }
        return null;
    }
}
