package Task_Planner;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Repeatable {
    private final String title;
    private final Type type;
    private final Integer id;
    private final LocalDateTime firstDate;
    private final String description;
    private static Integer counter = 1;

    public Task(String title, Type type, LocalDateTime dateTime, String description) throws TaskNotFoundException {
        this.title = ValidateUtils.checkString(title);
        this.type = type;
        this.firstDate = dateTime;
        this.description = ValidateUtils.checkString(description);
        id = counter++;
    }

    public Integer getId() {
        return id;
    }


    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    @Override
    public abstract boolean checkAppearance(LocalDateTime requestedDate);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && type == task.type && Objects.equals(id, task.id) && Objects.equals(firstDate, task.firstDate) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, firstDate, description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", dateTime=" + firstDate +
                ", description='" + description + '\'' +
                '}';
    }

}
