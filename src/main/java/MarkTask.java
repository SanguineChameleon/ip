import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MarkTask extends Command {
    public MarkTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        TaskList taskList = this.getTaskList();
        int id = taskList.parseTaskId(this.getRequiredOption("primary"));
        Task task = taskList.mark(id);
        System.out.println("Okay~! I will mark this task as done:");
        System.out.println(task);
    }
}
