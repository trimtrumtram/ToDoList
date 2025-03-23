import java.util.Scanner;

public class TasksController {

    private final ToDoList toDoList;
    private final Scanner scanner;
    private int index;

    public TasksController() {
        this.toDoList = new ToDoList();
        this.scanner = new Scanner(System.in);
    }

    public void addTask() {
        System.out.println("Название задачи: ");
        String title = scanner.nextLine();
        System.out.println("Приоритет: ");
        try {
            int priority = Integer.parseInt(scanner.nextLine());
            toDoList.addTask(title, priority);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: приоритет должен быть числом");
        }
    }

    public void deleteTask() {
        System.out.println("Введите номер задачи: ");
        try {
            index = Integer.parseInt(scanner.nextLine());
            toDoList.deleteTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Приоритет должен быть числом");
        }
    }

    public void editTask() {
        System.out.println("Введите номер задачи");
        try{
            index = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите новое название (Enter для пропуска): ");
            String newTitle = scanner.nextLine();
            System.out.println("Введите новый приоритет (Enter для пропуска): ");
            String priorityStr = scanner.nextLine();
            int newPriority = priorityStr.isEmpty() ? null : Integer.parseInt(priorityStr);
            // make func on infinity circle for request
            toDoList.editTask(index, newTitle.isEmpty() ? null : newTitle, newPriority);
        }catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат ввода");
        }
    }

    public void changeStatus() {

        System.out.println("Введите номер задачи: ");
        try {
            index = Integer.parseInt(scanner.nextLine());
            System.out.println("1. Выполнена\n2. В процессе\n3. Невыполнена");
            System.out.println("Новый статус: ");
            int statusChoice = Integer.parseInt(scanner.nextLine());
            toDoList.changeStatus(index, statusChoice);
        }catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат ввода");
        }
    }

    public void displayTasks() {
        System.out.println("Сортировать по (1 - Приоритет, 2 - дата)");
        String sortChoice = scanner.nextLine();
        String sortBy = sortChoice.equals("2") ? "date" : "priority";
        toDoList.displayTasks(sortBy);
    }

    public void filterTasks() {
        System.out.println("Статус (Enter для пропуска)");
        String status = scanner.nextLine();
        System.out.println("Минимальный приоритет (Enter для пропуска)");
        String minPriorityStr = scanner.nextLine();
        Integer minPriority = minPriorityStr.isEmpty() ? null : Integer.parseInt(minPriorityStr);
        System.out.println("Максимальный приоритет (Enter для пропуска)");
        String maxPriorityStr = scanner.nextLine();
        Integer maxPriority = maxPriorityStr.isEmpty() ? null : Integer.parseInt(maxPriorityStr);
        toDoList.filterTasks(status.isEmpty() ? null : status, minPriority, maxPriority);
    }

    public void searchTask() {
        System.out.println("Ключевое слово: ");
        String keyWord = scanner.nextLine();
        toDoList.searchTask(keyWord);
    }

    public void showAnalytics() {
        toDoList.showAnalytics();
    }

    public void exit() {
        System.out.println("До свидания");
        scanner.close();
    }

    public void printMenu() {
        System.out.println("""
                1. Добавить задачу
                2. Удалить задачу
                3. Редактировать задачу
                4. Изменить статус
                5. Показать задачи
                6. Фильтровать задачи
                7. Поиск задач
                8. Аналитика
                9. Выход""");
        System.out.print("Выберите действие: ");
    }

    public String userChoice() {
        return scanner.nextLine();
    }
}
