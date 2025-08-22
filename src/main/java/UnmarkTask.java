import java.util.HashMap;

public class UnmarkTask extends Command {
    public UnmarkTask() {
        super(new HashMap<>());
    }

    @Override
    public void execute() {
        int id = Haru.parseTaskId(this.getOption("primary"));
        Task task = Haru.getTasks().get(id);
        task.unmark();
        System.out.println("Okay~! I will unmark this task as not done:");
        System.out.println(task);
    }
}
