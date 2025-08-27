import java.util.HashMap;
import java.util.Map;

public class MarkTask extends Command {
    public MarkTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException {
        int id = Haru.parseTaskId(this.getRequiredOption("primary"));
        Task task = Haru.getTasks().get(id);
        task.mark();
        System.out.println("Okay~! I will mark this task as done:");
        System.out.println(task);
    }
}
