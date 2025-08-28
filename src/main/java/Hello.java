import java.util.HashMap;

public class Hello extends Command {
    public Hello(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    @Override
    public void execute() {
        this.getUI().show("Hi~! I'm Haru. What can I do for you today?");
    }
}
