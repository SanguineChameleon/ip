package haru.command;

import java.util.HashMap;

import javafx.application.Platform;

/**
 * Command to exit the application.
 */
public class Goodbye extends Command {

    /**
     * Constructs a Goodbye command.
     *
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
        this.getUi().show("See you next time! Bye-bye~!");
        Platform.exit();
    }
}
