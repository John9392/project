public class TaskScheduler {
    private RedBlackTree taskTree;

    public TaskScheduler() {
        this.taskTree = new RedBlackTree();
    }

    public void addTask(int id, String description, int priority) {
        Task task = new Task(id, description, priority);
        taskTree.insert(task);
    }

    public void deleteTask(int id) {
        taskTree.deleteTask(id);
    }

    public Task searchTask(int id) {
        return taskTree.searchTask(id);
    }

    public void listTasks() {
        taskTree.listTasks();
    }
}