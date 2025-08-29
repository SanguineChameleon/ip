import haru.exception.HaruException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UnmarkTask extends Command {
    public UnmarkTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        TaskList taskList = this.getTaskList();
        int id = taskList.parseTaskId(this.getRequiredOption("primary"));
        Task task = taskList.unmark(id);
        this.getUI().show("Okay~! I will unmark this task as not done:");
        this.getUI().show(task.toString());
    }
}
