package database.user;

import model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDatabase {

    private final List<User> users = new ArrayList<>();

    public UserDatabase() {
        users.add(new User("aa", "a@a.c", "aa"));
        users.add(new User("bb", "b@a.c", "bb"));
        users.add(new User("cc", "c@a.c", "cc"));
    }

    public List<User> getAllUsers() {
        return this.users;
    }

    public boolean insertUser(User user) throws Exception {
        for (User usr : users) {
            if (usr.getEmailID().equals(user.getEmailID()))
                throw new Exception("User cannot be registered as email already exists");
        }

        users.add(user);
        return true;
    }

    public void updatePassword(String newPassword, String email) throws Exception {
        for (User usr : users) {
            if (usr.getEmailID().equals(email)) {
                usr.setPassword(newPassword);
            }
        }

    }

    public boolean deleteUser(String email) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getEmailID().equals(email)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


    public boolean login(String email, String password) {
        for (User usr : users) {
            if (usr.getEmailID().equals(email)) {
                if (usr.getPassword().equals(password)) return true;
            }
        }
        return false;
    }

}