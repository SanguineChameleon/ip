import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddDeadline extends AddTask {
    public AddDeadline(CommandContext ctx) {
        super(new HashMap<>(Map.of(
                "primary", "task name",
                "by", "deadline"
        )), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        String name = super.getRequiredOption("primary");
        String by = super.getRequiredOption("by");
        add(new Deadline(name, by));
    }
}
