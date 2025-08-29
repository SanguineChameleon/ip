package haru.command;

import haru.exception.HaruException;
import haru.model.Task;
import haru.model.TaskList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkTask extends Command {

    /**
     * Constructs an UnmarkTask command with required options.
     * @param ctx command context for execution
     */
    public UnmarkTask(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task number")), ctx);
    }

    /**
     * Executes the command to unmark a task as not done.
     * @throws HaruException if task update fails
     * @throws IOException if IO error occurs
     */
    @Override
    public void execute() throws HaruException, IOException {
        TaskList taskList = this.getTaskList();
        int id = taskList.parseTaskId(this.getRequiredOption("primary"));
        Task task = taskList.unmark(id);
        this.getUI().show("Okay~! I will unmark this task as not done:");
        this.getUI().show(task.toString());
    }
}
