import java.util.ArrayList;
import java.util.HashMap;

public abstract class AddTask extends Command {
    public AddTask(HashMap<String, String> aliases, CommandContext ctx) {
        super(aliases, ctx);
    }

    public void add(Task task) {
        System.out.println("Okay~! I will add this task to your list:");
        System.out.println(task);
        ArrayList<Task> tasks = Haru.getTasks();
        tasks.add(task);
    }
}
