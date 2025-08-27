import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UnmarkTask extends Command {
    public UnmarkTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        int id = Haru.parseTaskId(this.getRequiredOption("primary"));
        Task task = this.getTaskList().unmark(id);
        System.out.println("Okay~! I will unmark this task as not done:");
        System.out.println(task);
    }
}
