package haru.command;

import haru.model.Task;

import java.io.IOException;
import java.util.HashMap;

/**
 * Base command for adding tasks.
 */
public abstract class AddTask extends Command {

    /**
     * Constructs an AddTask command with option aliases.
     * @param aliases map of option aliases
     * @param ctx command context for execution
     */
    public AddTask(HashMap<String, String> aliases, CommandContext ctx) {
        super(aliases, ctx);
    }

    /**
     * Adds a task to the task list and shows confirmation.
     * @param task the task to add
     * @throws IOException if UI output fails
     */
    public void add(Task task) throws IOException {
        this.getUI().show("Okay~! I will add this task to your list:");
        this.getUI().show(task.toString());
        this.getTaskList().add(task);
    }
}
