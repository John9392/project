public class Task {
    private int id;
    private String description;
    private int priority;

    public Task(int id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task[ID=" + id + ", Priority=" + priority + ", Description=" + description + "]";
    }
}