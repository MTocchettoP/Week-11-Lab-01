package businesslogic;

import dataaccess.NotesDBException;
import dataaccess.UserDB;
import domainmodel.Role;
import domainmodel.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public User get(String username) throws Exception {
        return userDB.getUser(username);
    }

    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public int update(String username, String password, String email, boolean active, String firstname, String lastname) throws Exception {
        User user = userDB.getUser(username);
        user.setPassword(password);
        user.setActive(active);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        return userDB.update(user);
    }

    public int delete(String username) throws Exception {
        User deletedUser = userDB.getUser(username);
        return userDB.delete(deletedUser);
    }

    public boolean sendRetrivalMail(String email,String path) throws NotesDBException {
        boolean userExist = false;
        User toRetrive = getUserByEmail(email);
        if(toRetrive == null)
            return false;

        PasswordChangeService ps = new PasswordChangeService();
        ps.startPasswordRetrieval(email, toRetrive,path);
  

        return true;
    }
    
    public User retrivePassword(String uuid) throws NotesDBException{
        
        PasswordChangeService pcs = new PasswordChangeService();
        return pcs.completePasswordRetrieval(uuid);
  
    }
    
    public User getUserByEmail(String email){
        
        User toRetrive = null;
        try {
            List<User> users = userDB.getAll();
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    toRetrive = user;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return toRetrive;
    }
    public int insert(String username, String password, String email, boolean active, String firstname, String lastname) throws Exception {
        User user = new User(username, password, email, active, firstname, lastname);
        Role role = new Role(2);  // default regular user role
        user.setRole(role);
        return userDB.insert(user);
    }
}
