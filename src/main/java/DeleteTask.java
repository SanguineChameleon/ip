import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteTask extends Command {
    public DeleteTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        int id = Haru.parseTaskId(this.getRequiredOption("primary"));
        Task task = this.getTaskList().remove(id);
        System.out.println("Okay~! I will delete this task:");
        System.out.println(task);
    }
}
