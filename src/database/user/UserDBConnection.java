package database.user;

import database.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBConnection {

    Connection conn = DBConnection.getConnection();

    public List<User> getAllUsers() throws Exception {
        String sql = "select * from user";
        List<User> Users = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User u1 = new User();
                u1.setUserName(rs.getString(1));
                u1.setEmailID(rs.getString(2));
                u1.setPassword(rs.getString(3));
                Users.add(u1);
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return Users;
    }

    public void insertUser(User User) throws Exception {
        String sql = "insert into user values(?,?,?)";
        System.out.println(sql);
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, User.getUserName());
            statement.setString(2, User.getEmailID());
            statement.setString(3, User.getPassword());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }


    }

    public void updatePassword(String newPassword, String email) throws Exception {
        String sql = "update user set password=? where emailID=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, email);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }

    }

    public void deleteUser(String email) throws Exception {
        String sql = "delete from user where email=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
    }

    public boolean login(String email, String password) throws Exception {

        String sql = "select password from user where emailID=?";
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString(1))) return true;
            }
            throw new Exception("Invalid credentials");
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }
}