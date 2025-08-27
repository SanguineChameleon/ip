import java.util.HashMap;
import java.util.Map;

public class UnmarkTask extends Command {
    public UnmarkTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException {
        int id = Haru.parseTaskId(this.getRequiredOption("primary"));
        Task task = Haru.getTasks().get(id);
        task.unmark();
        System.out.println("Okay~! I will unmark this task as not done:");
        System.out.println(task);
    }
}
