import java.util.Scanner;

public class TasksController {

    private ToDoList toDoList;
    private Scanner scanner;
    private int index;

    public TasksController() {
        this.toDoList = new ToDoList();
        this.scanner = new Scanner(System.in);
        this.index = Integer.parseInt(scanner.nextLine()) - 1;
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
            toDoList.deleteTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Приоритет должен быть числом");
        }
    }

    public void editTask() {
        System.out.println("Введите номер задачи");
        try{
            System.out.println("Введите новое название (Enter для пропуска): ");
            String newTitle = scanner.nextLine();
            System.out.println("Введите новый приоритет (Enter для пропуска): ");
            String priorityStr = scanner.nextLine();
            int newPriority = priorityStr.isEmpty() ? null : Integer.parseInt(priorityStr);
            toDoList.editTask(index, newTitle.isEmpty() ? null : newTitle, newPriority);
        }catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат ввода");
        }
    }

    public void changeStatus() {
        System.out.println("Введите номер задачи: ");
        try {
            System.out.println("1. Выполнена\n2. В процессе\n3. Невыполнена");
            System.out.println("Новый статус: ");
            int statusChoice = Integer.parseInt(scanner.nextLine());
            toDoList.changeStatus(index, statusChoice);
        }catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат ввода");
        }
    }

    public void displayTasks() {

    }

    public void filterTasks() {

    }

    public void searchTask() {

    }

    public void showAnalytics() {

    }

    public void exit() {

    }

    public void printMenu() {
        System.out.println("\n1. Добавить задачу\n2. Удалить задачу\n3. Редактировать задачу\n4. Изменить статус\n" +
                "5. Показать задачи\n6. Фильтровать задачи\n7. Поиск задач\n8. Аналитика\n9. Выход");
        System.out.print("Выберите действие: ");
    }

    public String userChoice() {
        return scanner.nextLine();
    }
}
