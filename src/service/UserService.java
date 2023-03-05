package service;

import database.user.UserDBConnection;
import model.User;

import java.util.List;

public class UserService {

    //private UserDatabase UserDatabase ;
    private final UserDBConnection UserDatabase;

    //	public UserService(UserDatabase UserDatabase) {
//		// TODO Auto-generated constructor stub
//		this.UserDatabase = UserDatabase;
//	}
    public UserService(UserDBConnection UserDatabase) {
        // TODO Auto-generated constructor stub
        this.UserDatabase = UserDatabase;
    }

    public List<User> getAllUsers() throws Exception {
        if (UserDatabase.getAllUsers().size() == 0) throw new Exception("No Users registered yet");

        return this.UserDatabase.getAllUsers();
    }

    public boolean registerUser(User User) throws Exception {
        if (User.getEmailID() == null || User.getEmailID().isEmpty())
            throw new Exception("email cannot be empty or null");
        try {
            UserDatabase.insertUser(User);

        } catch (Exception e) {
            // lint errors
            throw new Exception(e.getMessage());
        }
        return true;
    }

    public boolean validateCredentials(String email, String password) throws Exception {
        if (email == null || email.isEmpty()) throw new Exception("email cannot be empty or null");

        return this.UserDatabase.login(email, password);
    }

    public boolean deleteUser(String email) throws Exception {
        try {
            this.UserDatabase.deleteUser(email);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return true;
    }

    public boolean changePassword(String email, String newPassword) throws Exception {
        try {
            this.UserDatabase.updatePassword(newPassword, email);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return true;
    }
}