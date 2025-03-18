import java.time.LocalDateTime;

public class Task {

    private String title;
    private int priority;
    private LocalDateTime creationDate;
    private String status;
    private LocalDateTime completionDate;

    public Task(String title, int priority) {
        this.title = title;
        this.priority = priority;
        this. creationDate = LocalDateTime.now();
        this.status = "Невыполнена";
        this.completionDate = null;
    }

    protected void complete() {
        this.status = "Выполнена";
        this.completionDate = LocalDateTime.now();
    }

    protected void inProgress() {
        this.status = "В процессе";
        this.completionDate = null;
    }

    protected void pending() {
        this.status = "Невыполнена";
        this.completionDate = null;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    @Override
    public String toString() {
        return TasksFormatter.tasksFormatter(title, priority, creationDate, status, completionDate);
    }
}
