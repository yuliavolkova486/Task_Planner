package Task_Planner;

import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Repeatable{
    public MonthlyTask(String title, String description, Type type, LocalDateTime dateTime) throws TaskNotFoundException{
        super(title, type, dateTime, description);
    }

    @Override
    public boolean checkAppearance(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfMonth() == (requestedDate.getDayOfMonth());
    }
}
