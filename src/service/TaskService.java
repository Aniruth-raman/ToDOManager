package service;

import database.task.TaskDBConnection;
import model.Task;

import java.util.List;

public class TaskService {

    //private TaskDatabase TaskDatabase ;
    private final TaskDBConnection taskDatabase;

    //	public TaskService(TaskDatabase TaskDatabase) {
//		this.taskDatabase = TaskDatabase;
//	}
    public TaskService(TaskDBConnection TaskDatabase) {
        // TODO Auto-generated constructor stub
        this.taskDatabase = TaskDatabase;
    }

    public void updateTask(Task t1) throws Exception {
        try {
            if (t1 == null) {
                throw new Exception("The task cannot be null");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        taskDatabase.updateTask(t1);
    }

    public List<Task> getAllTasksByEmailID(String emailID) throws Exception {
        List<Task> tasks = taskDatabase.getAllTasksByEmailID(emailID);
        if (tasks.size() == 0) throw new Exception("No Task available for current user");
        return tasks;
    }

    public void deleteTask(int taskID, String email) throws Exception {
        if (!taskDatabase.getTaskByTaskID(taskID).getAssignedTo().equals(email)) {
            throw new Exception("You cannot delete a task that is not assigned to you! Please try again");
        }
        try {
            this.taskDatabase.deleteTask(taskID);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void createTask(Task t1) throws Exception {
//        Task tsk = taskDatabase.getLastRecord();
//        t1.setTaskID(tsk.getTaskID() + 1);
        t1.setTaskCompleted(false);
        taskDatabase.insertTask(t1);
    }

    public List<Task> searchTaskByTaskTitle(String searchText) throws Exception {
        return taskDatabase.searchTaskByTaskTitle(searchText);
    }

    public List<Task> searchTaskByTaskCompletedFlag(boolean flag) throws Exception {
        return taskDatabase.searchTaskByTaskCompletedFlag(flag);
    }
}