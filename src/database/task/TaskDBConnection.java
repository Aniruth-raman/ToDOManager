package database.task;

import database.DBConnection;
import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDBConnection {

    Connection conn = DBConnection.getConnection();

    public List<Task> getAllTasksByEmailID(String emailID) throws Exception {
        String sql = "select * from Task where AssignedTo=?";
        //2. create statements
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, emailID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Task t1 = new Task();
                t1.setTaskID(rs.getInt(1));
                t1.setTaskTitle(rs.getString(2));
                t1.setTaskText(rs.getString(3));
                t1.setAssignedTo(rs.getString(4));
                t1.setTaskCompleted(rs.getBoolean(5));
                tasks.add(t1);
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return tasks;
    }

    public Task getTaskByTaskID(int taskID) throws Exception {
        String sql = "select * from Task where taskID=?";
        /*
         * 1. get DB connection
         */
        //2. create statements
//        List<Task> tasks = new ArrayList<>();
        Task t1;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, taskID);
            ResultSet rs = statement.executeQuery();
            t1 = new Task();
            while (rs.next()) {
                t1.setTaskID(rs.getInt(1));
                t1.setTaskTitle(rs.getString(2));
                t1.setTaskText(rs.getString(3));
                t1.setAssignedTo(rs.getString(4));
                t1.setTaskCompleted(rs.getBoolean(5));
//                tasks.add(t1);
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return t1;
    }

    public void insertTask(Task tsk) throws Exception {
        String sql = "insert into Task(taskTitle, taskText, assignedTo, taskCompleted) values(?,?,?,?)";
        System.out.println(sql);
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setInt(1, tsk.getTaskID());
            statement.setString(1, tsk.getTaskTitle());
            statement.setString(2, tsk.getTaskText());
            statement.setString(3, tsk.getAssignedTo());
            statement.setBoolean(4, tsk.getTaskCompleted());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }


    }

    public boolean updateTask(Task t1) throws Exception {
        String sql = "update Task set taskTitle=?,taskText=?,assignedTo=?,taskCompleted=? where taskID=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, t1.getTaskTitle());
            statement.setString(2, t1.getTaskText());
            statement.setString(3, t1.getAssignedTo());
            statement.setBoolean(4, t1.getTaskCompleted());
            statement.setInt(5, t1.getTaskID());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return true;
    }

    public boolean deleteTask(int taskID) throws Exception {
        String sql = "delete from Task where taskID=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, taskID);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR");
            throw new Exception(ex.getMessage());
        }
        return true;
    }

    public List<Task> searchTaskByTaskCompletedFlag(boolean flag) throws Exception {
        String sql = "select * from Task where taskCompleted=?";
        /*
         * 1. get DB connection
         */
        //2. create statements
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setBoolean(1, flag);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Task t1 = new Task();
                t1.setTaskID(rs.getInt(1));
                t1.setTaskTitle(rs.getString(2));
                t1.setTaskText(rs.getString(3));
                t1.setAssignedTo(rs.getString(4));
                t1.setTaskCompleted(rs.getBoolean(5));
                tasks.add(t1);
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return tasks;

    }

    public List<Task> searchTaskByTaskTitle(String searchText) throws Exception {
        String sql = "SELECT * FROM Task WHERE taskTitle LIKE ?";
        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchText + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Task t1 = new Task();
                t1.setTaskID(rs.getInt(1));
                t1.setTaskTitle(rs.getString(2));
                t1.setTaskText(rs.getString(3));
                t1.setAssignedTo(rs.getString(4));
                t1.setTaskCompleted(rs.getBoolean(5));
                tasks.add(t1);
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return tasks;
    }

    public Task getLastRecord() {
        String sql = "select * from Task order by taskID desc limit 1";
        Task t1 = new Task();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                t1.setTaskID(rs.getInt(1));
                t1.setTaskTitle(rs.getString(2));
                t1.setTaskText(rs.getString(3));
                t1.setAssignedTo(rs.getString(4));
                t1.setTaskCompleted(rs.getBoolean(5));
            }
            return t1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}