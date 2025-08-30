package haru.command;

import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;

/**
 * Command to find tasks matching a string.
 */
public class FindTasks extends Command {

    /**
     * Constructs a FindTasks command with required options.
     *
     * @param ctx command context for execution
     */
    public FindTasks(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "search string")), ctx);
    }

    /**
     * Executes the command to list matching tasks.
     *
     * @throws HaruException if search input is missing
     */
    @Override
    public void execute() throws HaruException {
        String str = super.getRequiredOption("primary");
        this.getUi().show("Okay~! Here are all the tasks that match:");
        this.getUi().show(this.getTaskList().find(str).toString());
    }
}
