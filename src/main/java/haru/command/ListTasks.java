package haru.command;

import java.util.HashMap;

/**
 * Command to list all tasks.
 */
public class ListTasks extends Command {

    /**
     * Constructs a ListTasks command.
     * @param ctx command context for execution
     */
    public ListTasks(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    /**
     * Executes the command to display all tasks.
     */
    @Override
    public void execute() {
        this.getUI().show("Okay~! Here are all the tasks in your list:");
        this.getUI().show(this.getTaskList().toString());
    }
}
