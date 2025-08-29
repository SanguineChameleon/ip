package haru.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import haru.exception.HaruException;
import haru.model.Deadline;
import haru.model.TaskTime;

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
        TaskTime by = super.getRequiredTime("by");
        add(new Deadline(name, by));
    }
}
