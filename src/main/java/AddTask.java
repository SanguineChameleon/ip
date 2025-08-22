import java.util.ArrayList;

public abstract class AddTask extends Command {
    public AddTask(String[] names) {
        super(names);
    }

    public void add(Task task) {
        System.out.println("Okay~! I will add this task to your list:");
        System.out.println(task);
        ArrayList<Task> tasks = Haru.getTasks();
        tasks.add(task);
    }
}
