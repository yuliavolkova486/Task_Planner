package Task_Planner;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Выберите пункт меню:");
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            TaskService.addTask(scanner);
                            break;
                        case 2:
                            TaskService.getTasksByDay(scanner);
                            break;
                        case 3:
                            TaskService.deleteTask(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню:");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
               1.Добавить задачу
               2.Получить задачи на указанный день
               3.Удалить задачу
               0.Выход из меню"""
        );
    }
}
