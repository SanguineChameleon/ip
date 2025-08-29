package haru.command;

import haru.app.Haru;

import java.util.HashMap;

/**
 * Command to exit the application.
 */
public class Goodbye extends Command {

    /**
     * Constructs a Goodbye command.
     * @param ctx command context for execution
     */
    public Goodbye(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    /**
     * Executes the command to stop the application.
     */
    @Override
    public void execute() {
        this.getUI().show("See you next time! Bye-bye~!");
        Haru.stop();
    }
}
