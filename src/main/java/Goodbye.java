import java.util.HashMap;

public class Goodbye extends Command {
    public Goodbye(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    @Override
    public void execute() {
        this.getUI().show("See you next time! Bye-bye~!");
        Haru.stop();
    }
}
