import java.util.ArrayList;
import java.util.HashMap;

public class ListTasks extends Command {
    public ListTasks(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    @Override
    public void execute() {
        System.out.println("Okay~! Here are all the tasks in your list:");
        ArrayList<Task> tasks = Haru.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
