import java.util.HashMap;

public class ListTasks extends Command {
    public ListTasks(CommandContext ctx) {
        super(new HashMap<>(), ctx);
    }

    @Override
    public void execute() {
        System.out.println("Okay~! Here are all the tasks in your list:");
        System.out.println(this.getTaskList());
    }
}
