import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MarkTask extends Command {
    public MarkTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        int id = Haru.parseTaskId(this.getRequiredOption("primary"));
        Task task = this.getTaskList().mark(id);
        System.out.println("Okay~! I will mark this task as done:");
        System.out.println(task);
    }
}
