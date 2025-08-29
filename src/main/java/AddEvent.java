import haru.exception.HaruException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddEvent extends AddTask {
    public AddEvent(CommandContext ctx) {
        super(new HashMap<>(Map.of(
                "primary", "event name",
                "from", "start time",
                "to", "end time"
        )), ctx);
    }

    @Override
    public void execute() throws HaruException, IOException {
        String name = super.getRequiredOption("primary");
        TaskTime from = super.getRequiredTime("from");
        TaskTime to = super.getRequiredTime("to");
        add(new Event(name, from, to));
    }
}
