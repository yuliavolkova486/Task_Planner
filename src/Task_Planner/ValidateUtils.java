package Task_Planner;

public class ValidateUtils {
    public static String checkString(String str) throws TaskNotFoundException {
        if (str == null || str.isBlank() || str.isEmpty()) {
            throw new TaskNotFoundException("Некорректный ввод данных");
        } else {
            return str;
        }
    }
}
