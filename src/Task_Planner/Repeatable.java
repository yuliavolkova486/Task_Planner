package Task_Planner;

import java.time.LocalDateTime;

public interface Repeatable {
    boolean checkAppearance(LocalDateTime requestedDate);
    LocalDateTime getFirstDate();
}
