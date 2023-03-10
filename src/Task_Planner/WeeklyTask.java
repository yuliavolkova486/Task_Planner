package Task_Planner;


import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable{
    public WeeklyTask(String title, String description, Type type, LocalDateTime dateTime) throws TaskNotFoundException{
        super(title, type, dateTime, description);
    }

    @Override
    public boolean checkAppearance(LocalDateTime requestedDate) {
        return getFirstDate().getDayOfWeek().equals(requestedDate.getDayOfWeek());
    }


}
