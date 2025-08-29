import haru.exception.HaruException;
import haru.model.Task;
import haru.model.TaskList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteTask extends Command {
    public DeleteTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        TaskList taskList = this.getTaskList();
        int id = taskList.parseTaskId(this.getRequiredOption("primary"));
        Task task = taskList.remove(id);
        this.getUI().show("Okay~! I will delete this task:");
        this.getUI().show(task.toString());
    }
}
