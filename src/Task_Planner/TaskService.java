package Task_Planner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskService {
    private static final Map<Integer, Task> actualTasks = new HashMap<>();

    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Введите название задачи: ");
            String title = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите описание задачи: ");
            String description = ValidateUtils.checkString(scanner.nextLine());
            System.out.println("Введите тип задачи: 0 - Рабочая, 1 - Личная");
            Type type = Type.values()[scanner.nextInt()];
            System.out.println("Введите повторяемость задачи: 0 - Однократная, 1 - Ежедневная, " +
                    "2 - Еженедельная, 3 - Ежемесячная, 4 - Ежегодная");
            int appearance = scanner.nextInt();
            System.out.println("Введите дату: dd.MM.yyyy HH:mm ");
            scanner.nextLine();
            createEvent(scanner, title, description, type, appearance);
            System.out.println("Для выхода из меню нажмите Enter\n ");
            scanner.nextLine();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void createEvent(Scanner scanner, String title, String description, Type type, int appearance) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Repeatable task = null;
            try {
                task = createTask(appearance, title, description, type, eventDate);
                System.out.println("Создана задача " + task);
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты, попробуйте еще раз");
            createEvent(scanner, title, description, type, appearance);
        }
    }

    public static void getTasksByDay(Scanner scanner) {
        System.out.println("Введите дату в формате dd.MM.yyyy:");
        try {
            String date = scanner.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestedDate = LocalDate.parse(date, dateTimeFormatter);
            List<Repeatable> foundEvents = findTaskByDate(requestedDate);
            System.out.println("Задачи на " + requestedDate + ":");
            for (Repeatable task : foundEvents) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Проверьте формат даты dd.MM.yyyy и попробуйте еще раз.");
        }
        scanner.nextLine();
        System.out.println("Для выхода из меню нажмите Enter\n");
    }

    private static List<Repeatable> findTaskByDate(LocalDate date) {
        List<Repeatable> tasks = new ArrayList<>();
        for (Repeatable task : actualTasks.values()) {
            if (task.checkAppearance(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    private static Repeatable createTask(int appearance, String title, String description, Type type, LocalDateTime localDateTime)
        throws TaskNotFoundException {
        return switch (appearance) {
            case 0 -> {
                OnTimeTask task = new OnTimeTask(title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 1 -> {
                DailyTask task = new DailyTask(title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 2 -> {
                WeeklyTask task = new WeeklyTask(title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 3 -> {
                MonthlyTask task = new MonthlyTask(title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            case 4 -> {
                YearlyTask task = new YearlyTask(title, description, type, localDateTime);
                actualTasks.put(task.getId(), task);
                yield task;
            }
            default -> null;
        };
    }

    private static void printActualTasks() {
        for (Repeatable task : actualTasks.values()) {
            System.out.println(task);
        }
    }

    public static void deleteTask(Scanner scanner) {
        System.out.println("Текущие задачи\n");
        printActualTasks();
        try {
            System.out.println("Для удаления введите Id задачи\n");
            int id = scanner.nextInt();
            if (actualTasks.containsKey(id)) {
                System.out.println("Задача " + id + " удалена\n");
            } else {
                throw new TaskNotFoundException();
            }
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println("Такой задачи не существует\n");
        }
    }

}
