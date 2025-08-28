import java.util.HashMap;

public class ListTasks extends Command {
    public ListTasks(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    @Override
    public void execute() {
        this.getUI().show("Okay~! Here are all the tasks in your list:");
        this.getUI().show(this.getTaskList().toString());
    }
}
