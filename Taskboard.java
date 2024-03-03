import java.util.ArrayList;

class Taskboard {
    private static ArrayList<String> tasks = new ArrayList<>(); // Task names
    private static ArrayList<Integer> values = new ArrayList<>(); // Task values
    
    /**
     * Initialize the taskboard.
     * @param pTasks The names of the tasks
     * @param pValues The values of the tasks
     */
    public static void initialize(ArrayList<String> pTasks, ArrayList<Integer> pValues) {
        tasks = pTasks;
        values = pValues;
    }

    /**
     * Add a new task to the taskboard.
     * @param task The name of the task
     * @param value The value of the task
     */
    public static void addTask(String task, int value) {
        tasks.add(task);
        values.add(value);
    }

    /**
     * Complete a task.
     * @param name The name of the player
     * @param task The name of the task
     */
    public static void completeTask(String name, String task) {
        if (tasks.contains(task)) Scoreboard.addScore(name, values.get(tasks.indexOf(task)));
        else System.out.println("Task not found.");
    }

    public static void removeTask(String taskName){
        values.remove(tasks.indexOf(taskName));
        tasks.remove(taskName);
    }
}