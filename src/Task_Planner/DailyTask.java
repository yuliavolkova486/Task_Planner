package Task_Planner;

import java.time.LocalDateTime;

public class DailyTask extends Task implements Repeatable{
    public DailyTask(String title, String description, Type type, LocalDateTime dateTime) throws TaskNotFoundException {
        super(title, type, dateTime, description);
    }

    @Override
    public boolean checkAppearance(LocalDateTime requestedDate) {
        return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
    }
}
