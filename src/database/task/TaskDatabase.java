package database.task;

import model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskDatabase {

    private final List<Task> tasks = new ArrayList<>();

    public TaskDatabase() {
        tasks.add(new Task(1, "New Task 1", "This is a new Task", "a@a.c", false));
        tasks.add(new Task(2, "New Task 2", "This is a new 2 Task", "b@a.c", false));
        tasks.add(new Task(3, "New Task 3", "This is a new 3 Task", "c@a.c", false));
    }

    public List<Task> getAllTasksByEmailID(String emailID) {
//        List<Task> tasks = getAllTasks();
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getAssignedTo().equals(emailID)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    private List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getTaskByTaskID(int taskID) {
//        List<Task> tasks = getAllTasks();
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskID() == taskID) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public void insertTask(Task tsk) {
        tasks.add(tsk);
    }

    public boolean updateTask(Task t1) {
        for (Task task : tasks) {
            if (task.getTaskID() == t1.getTaskID()) {
                task.setTaskTitle(t1.getTaskTitle());
                task.setTaskText(t1.getTaskText());
                task.setAssignedTo(t1.getAssignedTo());
                task.setTaskCompleted(t1.getTaskCompleted());
                return true;
            }
        }
        return false;
    }


    public boolean deleteTask(int taskID) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getTaskID() == taskID) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }


    public List<Task> searchTaskByTaskCompletedFlag(boolean flag) throws Exception {
        List<Task> filteredTask = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskCompleted() == flag) {
                filteredTask.add(task);
            }
        }
        return filteredTask;
    }

    public List<Task> searchTaskByTaskTitle(String searchText) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskTitle().contains(searchText)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    public Task getLastRecord() {
        return tasks.get(tasks.size() - 1);
    }

}