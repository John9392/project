import java.util.LinkedList;
import java.util.Queue;

public class PriorityQueue {

    private Queue<String> highPriorityQueue;
    private Queue<String> mediumPriorityQueue;
    private Queue<String> lowPriorityQueue;

    public PriorityQueue() {
        highPriorityQueue = new LinkedList<>();
        mediumPriorityQueue = new LinkedList<>();
        lowPriorityQueue = new LinkedList<>();
    }

    // Method to add a task with a specified priority
    public void addTask(String task, Priority priority) {
        switch (priority) {
            case HIGH:
                highPriorityQueue.offer(task);
                System.out.println("Added to high priority queue: " + task);
                break;
            case MEDIUM:
                mediumPriorityQueue.offer(task);
                System.out.println("Added to medium priority queue: " + task);
                break;
            case LOW:
                lowPriorityQueue.offer(task);
                System.out.println("Added to low priority queue: " + task);
                break;
        }
    }

    // Method to retrieve tasks in order of priority
    public String retrieveTask() {
        if (!highPriorityQueue.isEmpty()) {
            return highPriorityQueue.poll();
        } else if (!mediumPriorityQueue.isEmpty()) {
            return mediumPriorityQueue.poll();
        } else if (!lowPriorityQueue.isEmpty()) {
            return lowPriorityQueue.poll();
        } else {
            return "No tasks available";
        }
    }

    // Method to simulate task scheduling by dequeuing tasks from the highest priority queue first
    public void processTasks() {
        while (!highPriorityQueue.isEmpty() || !mediumPriorityQueue.isEmpty() || !lowPriorityQueue.isEmpty()) {
            String task = retrieveTask();
            if (!task.equals("No tasks available")) {
                System.out.println("Processing task: " + task);
            }
        }
        System.out.println("All tasks processed.");
    }

    // Enum to represent different priority levels
    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();

        // Simulating adding tasks with different priorities
        pq.addTask("Task A", Priority.HIGH);
        pq.addTask("Task B", Priority.MEDIUM);
        pq.addTask("Task C", Priority.LOW);
        pq.addTask("Task D", Priority.HIGH);
        pq.addTask("Task E", Priority.MEDIUM);

        // Simulate task scheduling
        pq.processTasks();
    }
}