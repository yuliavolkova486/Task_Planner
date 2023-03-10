package Task_Planner;

import java.time.LocalDateTime;

public class OnTimeTask extends Task implements Repeatable{
    public OnTimeTask(String title, String description, Type type, LocalDateTime dateTime) throws TaskNotFoundException{
        super(title, type, dateTime, description);
    }

    @Override
    public boolean checkAppearance(LocalDateTime requestedDate) {
        return getFirstDate().toLocalDate().equals(requestedDate.toLocalDate());
    }
}
