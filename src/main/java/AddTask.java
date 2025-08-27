import java.io.IOException;
import java.util.HashMap;

public abstract class AddTask extends Command {
    public AddTask(HashMap<String, String> aliases, CommandContext ctx) {
        super(aliases, ctx);
    }

    public void add(Task task) throws IOException {
        System.out.println("Okay~! I will add this task to your list:");
        System.out.println(task);
        this.getTaskList().add(task);
    }
}
