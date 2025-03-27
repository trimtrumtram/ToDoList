import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDateTime;

public class ToDoList {


    private static List<Task> tasks;
    private static final String SAVE_FILE = "Tasks.json";
    private static Gson gson;
    private static final int MIN_PRIORITY = 1;
    private static final int MAX_PRIORITY = 3;

    public ToDoList() {
        tasks = new ArrayList<>();
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        loadTasks();
    }

    public void addTask(String title, int priority) {
        if(title.trim().isEmpty()) {
            System.out.println("Ошибка : Название не может быть пустым");
            return;
        }
        if(!isValidPriority(priority)) {
            return;
        }
        tasks.add(new Task(title, priority));
        System.out.println("Задача успешно добавлена");
    }

    public void deleteTask(int index) {
        if(isValidIndex(index)) {
            tasks.remove(index);
            System.out.println("Задача успешно удалена");
        }else{
            System.out.println("Неверный номер задачи");
        }
    }

    public void editTask(int index, String title, Integer priority) {
        if(!isValidIndex(index)) {
            System.out.println("Неверный номер задачи");
            return;
        }
        Task task = tasks.get(index);
        if(title != null && !title.trim().isEmpty()) {
            task.setTitle(title);
        }
        if(!isValidPriority(priority)) {
            return;
        }
        task.setPriority(priority);
        System.out.println("Задача отредактирована");
    }

    public void changeStatus(int index, int statusChoice) {
        if(!isValidIndex(index)) {
            System.out.println("Неверный номер задачи");
            return;
        }
        Task task = tasks.get(index);
        switch (statusChoice) {
            case 1 -> task.complete();
            case 2 -> task.inProgress();
            case 3 -> task.pending();
            default -> {
                System.out.println("Неверный статус");
                return;
            }
        }
        System.out.println("Статус изменен");
    }

    public void displayTasks(String sortBy) {
        if(tasks.isEmpty()) {
            System.out.println("Задач нет");
            return;
        }
        List<Task> sortedTasks = new ArrayList<>(tasks);
        if("priority".equals(sortBy)) {
            sortedTasks.sort(Comparator.comparing(Task :: getPriority));
        }else {
            sortedTasks.sort(Comparator.comparing(Task :: getCreationDate));
        }
        displayTaskList(sortedTasks); // func don't work
    }

    public void filterTasks(String status, Integer minPriority, Integer maxPriority) {
        List<Task> filtered = tasks.stream()
                .filter(task -> status == null || task.getStatus().equalsIgnoreCase(status))
                .filter(task -> minPriority == null || task.getPriority() >= minPriority)
                .filter(task -> maxPriority == null || task.getPriority() <= maxPriority)
                .collect(Collectors.toList());
        displayTaskList(filtered);
    }

    public void searchTask(String keyWord) {
        List<Task> found = tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyWord.toLowerCase()))
                .collect(Collectors.toList());
        displayTaskList(found);
    }

    public void showAnalytics() {
        if(tasks.isEmpty()) {
            System.out.println("Нет задач для анализа");
            return;
        }

        long completed = tasks.stream().filter(t -> t.getStatus().equals("Выполнена")).count();
        long inProgress = tasks.stream().filter(t -> t.getStatus().equals("В процессе")).count();
        long pending = tasks.stream().filter(t -> t.getStatus().equals("Невыполнена")).count();
        double avgPriority = tasks.stream().mapToInt(Task :: getPriority).average().orElse(0.0);

        System.out.println("Статистика по статусам");
        System.out.printf("Выполнена: %d\nВ процессе: %d\nНевыполнена: %d\n", completed, inProgress, pending);
        System.out.printf("Средний приоритет: %.2f\n", avgPriority);

        System.out.println("\nГруппировка по статусам:");
        for(String status : new String[] {"Выполнена", "В процессе", "Невыполнена"}) {
            System.out.println("\n" + status + ":");
            tasks.stream()
                    .filter(t -> t.getStatus().equals(status))
                    .forEach(t -> System.out.println(" " + t));
        }
    }

    public static void saveTasks() {
        try(FileWriter writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(tasks, writer);
            System.out.println("Сохранение прошло успешно в " + SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Ошибка при сохрании задач" + e.getMessage());
        }
    }

    private void loadTasks() {
        File file = new File(SAVE_FILE);
        if(file.exists()) {
            try(FileReader reader = new FileReader(file)) {
                tasks = gson.fromJson(reader, new TypeToken<List<Task>>(){}.getType());
                if(tasks == null) {
                    tasks = new ArrayList<>();
                }
            }catch (IOException e) {
                System.out.println("Оштбка при загрузке задач" + e.getMessage());
                tasks = new ArrayList<>();

            }
        }
    }

    private void displayTaskList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("[%d]. %s%n", i, taskList.get(i));
        }
    }

    private boolean isValidPriority(Integer priority) {
        return priority >= MIN_PRIORITY && priority <= MAX_PRIORITY;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
