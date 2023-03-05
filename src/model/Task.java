package model;


//Ctrl + Fn + F12 - To see all the available functions in a class when using lombok

public class Task {
    private int taskID;
    private String taskTitle;
    private String taskText;
    private String assignedTo;
    private boolean taskCompleted;

    public Task() {
    }

    public Task(int taskID, String taskTitle, String taskText, String assignedTo, boolean taskCompleted) {
        this.taskID = taskID;
        this.taskTitle = taskTitle;
        this.taskText = taskText;
        this.assignedTo = assignedTo;
        this.taskCompleted = taskCompleted;
    }

    @Override
    public String toString() {
        return "Task{" + "taskID=" + taskID + ", taskTitle='" + taskTitle + '\'' + ", taskText='" + taskText + '\'' + ", assignedTo='" + assignedTo + '\'' + ", taskCompleted=" + taskCompleted + '}';
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public boolean getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }
}
