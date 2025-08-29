package haru.command;

import java.util.HashMap;

/**
 * Command to greet the user.
 */
public class Hello extends Command {

    /**
     * Constructs a Hello command.
     *
     * @param ctx command context for execution
     */
    public Hello(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    /**
     * Executes the command to show a greeting message.
     */
    @Override
    public void execute() {
        this.getUI().show("Hi~! I'm Haru. What can I do for you today?");
    }
}
