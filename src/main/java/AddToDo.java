import java.util.HashMap;
import java.util.Map;

public class AddToDo extends AddTask {
    public AddToDo() {
        super(new HashMap<>(Map.of("primary", "task name")));
    }

    @Override
    public void execute() throws HaruException {
        String name = super.getRequiredOption("primary");
        add(new ToDo(name));
    }
}
