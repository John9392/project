import java.util.Scanner;

public class TaskSchedulerApp {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskScheduler scheduler = new TaskScheduler();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    deleteTask();
                    break;
                case 3:
                    searchTask();
                    break;
                case 4:
                    listTasks();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("--- Task Scheduler ---");
        System.out.println("1. Add Task");
        System.out.println("2. Delete Task");
        System.out.println("3. Search Task");
        System.out.println("4. List Tasks");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addTask() {
        System.out.print("Enter task ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task priority: ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        scheduler.addTask(id, description, priority);
        System.out.println("Task added successfully.");
    }

    private static void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        scheduler.deleteTask(id);
        System.out.println("Task deleted successfully.");
    }

    private static void searchTask() {
        System.out.print("Enter task ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Task task = scheduler.searchTask(id);
        if (task != null) {
            System.out.println("Task found: " + task);
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void listTasks() {
        System.out.println("--- Task List ---");
        scheduler.listTasks();
    }
}