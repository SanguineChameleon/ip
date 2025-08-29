package haru.command;

import haru.model.Task;

import java.io.IOException;
import java.util.HashMap;

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
