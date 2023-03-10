package Task_Planner;

import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repeatable{
    public YearlyTask(String title, String description, Type type, LocalDateTime dateTime) throws TaskNotFoundException{
        super(title, type, dateTime, description);
    }

    @Override
    public boolean checkAppearance(LocalDateTime requestedDate) {
        return getFirstDate().getYear() == requestedDate.getYear();
    }
}
