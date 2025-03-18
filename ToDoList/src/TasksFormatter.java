import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TasksFormatter {

    public static String tasksFormatter(String title, int priority, LocalDateTime creationDate, String status, LocalDateTime completionDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String priorityColor;
        switch (priority) {
            case 1 -> priorityColor = "\u001B[32m";
            case 2 -> priorityColor = "\u001B[33m";
            case 3 -> priorityColor = "\u001B[31m";
            default -> priorityColor = "\u001B[0m";
        }
        String resetColor = "\u001B[0m";
        return String.format("%s | Приоритет: %s%d%s | Дата создания: %s | Статус: %s | Дата выполнения %s",
                title,
                priorityColor,priority,resetColor,
                creationDate.format(formatter),
                status,
                completionDate != null ? completionDate.format(formatter) : "отсутсвует");
    }
}
