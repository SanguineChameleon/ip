import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddToDo extends AddTask {
    public AddToDo(CommandContext ctx) {
        super(new HashMap<>(Map.of("primary", "task name")), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        String name = super.getRequiredOption("primary");
        add(new ToDo(name));
    }
}
