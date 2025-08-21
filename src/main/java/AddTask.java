import java.util.ArrayList;

public class AddTask extends Command {
    public AddTask() {
        super(new String[]{});
    }

    @Override
    public void execute() {
        // TODO: task name can be empty
        String name = super.getOption("primary");
        Task task = new Task(name);
        System.out.println("Okay~! I will add this task to your list:");
        System.out.println(task);
        ArrayList<Task> tasks = Haru.getTasks();
        tasks.add(task);
    }
}
