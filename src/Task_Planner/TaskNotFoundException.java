package Task_Planner;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
    }
    public TaskNotFoundException(String message) {
        super(message);
    }
}
