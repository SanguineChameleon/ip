import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeleteTask extends Command {
    public DeleteTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException {
        int id = Haru.parseTaskId(this.getRequiredOption("primary"));
        ArrayList<Task> tasks = Haru.getTasks();
        Task task = tasks.get(id);
        System.out.println("Okay~! I will delete this task:");
        System.out.println(task);
        tasks.remove(id);
    }
}
