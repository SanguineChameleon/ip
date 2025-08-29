import haru.exception.HaruException;
import haru.model.Task;
import haru.model.TaskList;

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
        this.getUI().show("Okay~! I will mark this task as done:");
        this.getUI().show(task.toString());
    }
}
