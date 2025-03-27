public class Main {
    public static void main(String[] args) {
        TasksController controller = new TasksController();

        while(true) {
            controller.printMenu();

            String choice = controller.userChoice();
            switch (choice) {
                case "1" -> controller.addTask();
                case "2" -> controller.deleteTask();
                case "3" -> controller.editTask();
                case "4" -> controller.changeStatus();
                case "5" -> controller.displayTasks();
                case "6" -> controller.filterTasks();
                case "7" -> controller.searchTask();
                case "8" -> controller.showAnalytics();
                case "9" -> controller.exit();
                case "10" -> controller.saveTasks();
                default -> System.out.println("Неверный выбор");
            }
        }
    }
}