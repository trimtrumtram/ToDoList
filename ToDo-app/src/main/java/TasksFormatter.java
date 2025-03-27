import java.time.format.DateTimeFormatter;

public class TasksFormatter {
    public static String tasksFormatter(Task task) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String priorityColor;
        switch (task.getPriority()) {
            case 1 -> priorityColor = "\u001B[31m";
            case 2 -> priorityColor = "\u001B[33m";
            case 3 -> priorityColor = "\u001B[32m";
            default -> priorityColor = "\u001B[0m";
        }
        String resetColor = "\u001B[0m";
        return String.format("%s | Приоритет: %s%d%s | Дата создания: %s | Статус: %s | Дата выполнения %s",
                task.getTitle(),
                priorityColor, task.getPriority(),resetColor,
                task.getCreationDate().format(formatter),
                task.getStatus(),
                task.getCompletionDate() != null ? task.getCompletionDate().format(formatter) : "отсутсвует");
    }
}
