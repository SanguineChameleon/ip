package haru.command;

import java.io.IOException;
import java.util.HashMap;

import haru.model.Task;

public abstract class AddTask extends Command {
    public AddTask(HashMap<String, String> aliases, CommandContext ctx) {
        super(aliases, ctx);
    }

    public void add(Task task) throws IOException {
        this.getUI().show("Okay~! I will add this task to your list:");
        this.getUI().show(task.toString());
        this.getTaskList().add(task);
    }
}
