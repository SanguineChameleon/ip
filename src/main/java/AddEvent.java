import java.util.HashMap;
import java.util.Map;

public class AddEvent extends AddTask {
    public AddEvent() {
        super(new HashMap<>(Map.of(
                "primary", "event name",
                "from", "start time",
                "to", "end time"
        )));
    }

    @Override
    public void execute() throws HaruException {
        String name = super.getRequiredOption("primary");
        String from = super.getRequiredOption("from");
        String to = super.getRequiredOption("to");
        add(new Event(name, from, to));
    }
}
