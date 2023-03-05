package model;

public class User {
    String userName;
    String emailID;
    String password;

    public User(String userName, String emailID, String password) {
        this.userName = userName;
        this.emailID = emailID;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", emailID='" + emailID + '\'' + ", password='" + password + '\'' + '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
