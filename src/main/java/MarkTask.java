import java.util.ArrayList;

public class MarkTask extends Command {
    public MarkTask() {
        super(new String[]{});
    }

    @Override
    public void execute() {
        // TODO: strId may be invalid
        String strId = super.getOption("primary");
        int id = Integer.parseInt(strId);
        ArrayList<Task> tasks = Haru.getTasks();
        Task task = tasks.get(id - 1);
        task.mark();
        System.out.println("Okay~! I will mark this task as done:");
        System.out.println(task);
    }
}
