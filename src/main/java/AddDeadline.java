import java.util.HashMap;
import java.util.Map;

public class AddDeadline extends AddTask {
    public AddDeadline() {
        super(new HashMap<>(Map.of(
                "primary", "task name",
                "by", "deadline"
        )));
    }

    @Override
    public void execute() throws HaruException {
        String name = super.getRequiredOption("primary");
        String by = super.getRequiredOption("by");
        add(new Deadline(name, by));
    }
}
