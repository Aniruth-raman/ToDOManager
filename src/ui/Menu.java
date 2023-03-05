package ui;

import database.task.TaskDBConnection;
import database.user.UserDBConnection;
import model.Task;
import model.User;
import service.TaskService;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
//        UserDatabase userDb = new UserDatabase();
        UserDBConnection userDBConnection = new UserDBConnection();
        UserService userService = new UserService(userDBConnection);
//        TaskDatabase TaskDb = new TaskDatabase();
        TaskDBConnection taskDBConnection = new TaskDBConnection();
        TaskService taskService = new TaskService(taskDBConnection);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int choice;
        do {
            System.out.println("Choose one of the options");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("0 - Exit");
            choice = sc.nextInt();
            sc.nextLine();  // Consume newline left-over
//             //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
////            sc.next();
            switch (choice) {
                case 1 -> {
                    //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    System.out.println("*******Login Menu*******");
                    System.out.println("Enter your email\n");
                    String email = sc.nextLine();
                    System.out.println("Enter your password\n");
                    String password = sc.nextLine();
                    try {
                        if (userService.validateCredentials(email, password)) {
                            userDashboard(sc, email, taskService);
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR!");
                        System.err.println(e.getMessage());
//                        throw new RuntimeException(e.getMessage());
                    }
                }
                case 2 -> {
                    //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    System.out.println("Please enter details to register");
                    System.out.println("Enter your username");
                    String username = sc.next();
                    System.out.println("Enter your emailID");
                    String email = sc.next();
                    System.out.println("Enter your password");
                    String password = sc.next();
                    User u1 = new User(username, email, password);
                    try {
                        userService.registerUser(u1);
                        System.out.println("User Registered Successfully. Please login again to proceed");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 0 -> {
                    //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    System.out.println("Thanks for using the application. Exiting now!");
                    flag = false;
                }
                default -> System.err.println("You have entered an invalid input. Please choose the correct option");
            }
        } while (flag);
    }

    private static void userDashboard(Scanner sc, String email, TaskService taskService) throws Exception {
        System.out.println("\n*************** User Dashboard **********************\n");
        boolean flag = true;
        do {
            System.out.println("1 - Add Task");
            System.out.println("2 - Update a Task");
            System.out.println("3 - Delete a Task");
            System.out.println("4 - Search a Task");
            System.out.println("5 - See all your Tasks");
            System.out.println("6 - See all complete Tasks");
            System.out.println("7 - See all incomplete Tasks");
            System.out.println("8 - Logout");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n*************** Add a new Task ******************\n");
                    System.out.println("Enter the Task Title");
                    String taskTitle = sc.nextLine();
                    System.out.println("Enter the Task Text");
                    String taskText = sc.nextLine();
                    System.out.println("Enter the Email of the user the task is assigned to");
                    String assignedTo = sc.nextLine();
                    Task t1 = new Task();
                    t1.setTaskTitle(taskTitle);
                    t1.setTaskText(taskText);
                    t1.setAssignedTo(assignedTo);
                    try {
                        taskService.createTask(t1);
                        System.out.println("Task created successfully!");
                    } catch (Exception e) {
                        if (e instanceof SQLException sqlException) {
                            int errorCode = sqlException.getErrorCode();
                            if (errorCode == 1452) {
                                // Handle foreign key constraint failure
                                System.err.println("Invalid assignedTo value. Please select a valid user.");
                            } else {
                                // Handle other SQL exceptions
                                System.err.println("Error inserting/updating record: " + e.getMessage());
                            }
                        } else {
                            // Handle non-SQL exceptions
                            System.err.println("ERROR!");
                            System.err.println(e.getMessage());
                        }
                    }
                }
                case 2 -> {
                    System.out.println("\n*************** Update a Task ******************\n");
                    System.out.println("Your current tasks are:");
                    try {
                        for (Task tsk : taskService.getAllTasksByEmailID(email)) {
                            System.out.println(tsk);
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR!");
                        System.err.println(e.getMessage());
//                        throw new RuntimeException(e);
                    }
                    System.out.println("Enter the TaskID of the task you want to update");
                    int taskID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the Task Title");
                    String taskTitle = sc.nextLine();
                    System.out.println("Enter the Task Text");
                    String taskText = sc.nextLine();
                    System.out.println("Enter the Email of the user the task is assigned to");
                    String assignedTo = sc.nextLine();
                    System.out.println("Is the task completed? Enter y/n");
                    boolean taskCompleted = sc.nextLine().equals("y");
                    Task t1 = new Task(taskID, taskTitle, taskText, assignedTo, taskCompleted);
                    try {
                        taskService.updateTask(t1);
                        System.out.println("Task updated successfully!");
                    } catch (Exception e) {
                        System.err.println("ERROR!");
                        System.err.println(e.getMessage());
//                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    System.out.println("\n*************** Delete a Task ******************\n");
                    System.out.println("Your current tasks are:");
                    try {
                        for (Task tsk : taskService.getAllTasksByEmailID(email)) {
                            System.out.println(tsk);
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR!");
                        System.err.println(e.getMessage());
//                        throw new RuntimeException(e);
                    }
                    System.out.println("Enter the TaskID of the task you want to update");
                    int taskID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Are you sure you want to delete the Task with TaskID " + taskID + " Enter y/n");
                    boolean deleteConfirmation = sc.nextLine().equals("y");
                    if (deleteConfirmation) {
                        try {
                            taskService.deleteTask(taskID, email);
                            System.out.println("Task deleted successfully");
                        } catch (Exception e) {
                            System.err.println("ERROR!");
                            System.err.println(e.getMessage());
//                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("Delete cancelled as per your input!");
                    }
                }
                case 4 -> {
                    //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    System.out.println("\n*************** Search a Task ******************\n");
                    System.out.println("Enter a part of Task Title to search");
                    String searchText = sc.nextLine();
                    try {
                        for (Task tsk : taskService.searchTaskByTaskTitle(searchText)) {
                            System.out.println(tsk);
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR!");
                        System.err.println(e.getMessage());
//                        throw new Exception(e.getMessage());
                    }
                }
                case 5 -> {
                    //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    System.out.println("\n*************** View all your Tasks ******************\n");
                    try {
                        for (Task tsk : taskService.getAllTasksByEmailID(email)) {
                            System.out.println(tsk);
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR!");
                        System.err.println(e.getMessage());
//                        throw new RuntimeException(e);
                    }
                }
                case 6 -> {
                    //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    System.out.println("\n*************** See all completed Tasks ******************\n");
                    try {
                        for (Task tsk : taskService.searchTaskByTaskCompletedFlag(true)) {
                            System.out.println(tsk);
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR!");
                        System.err.println(e.getMessage());
//                        throw new RuntimeException(e);
                    }
                }
                case 7 -> {
                    //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    System.out.println("\n*************** See all incomplete Tasks ******************\n");
                    try {
                        for (Task tsk : taskService.searchTaskByTaskCompletedFlag(false)) {
                            System.out.println(tsk);
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR!");
                        System.err.println(e.getMessage());
//                        throw new RuntimeException(e);
                    }
                }
                case 8 -> {
                    //   sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    System.out.println("Logging off");
                    flag = false;
                }
                default -> System.err.println("Unexpected value: " + choice + ". Please try again!");
            }
        } while (flag);

    }

}
