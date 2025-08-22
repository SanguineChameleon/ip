import java.util.HashMap;

public class Hello extends Command {
    public Hello() {
        super(new HashMap<>());
    }

    @Override
    public void execute() {
        System.out.println("Hi~! I'm Haru. What can I do for you today?");
    }
}
