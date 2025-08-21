public class MarkTask extends Command {
    public MarkTask() {
        super(new String[]{});
    }

    @Override
    public void execute() {
        int id = Haru.parseTaskId(this.getOption("primary"));
        Task task = Haru.getTasks().get(id);
        task.mark();
        System.out.println("Okay~! I will mark this task as done:");
        System.out.println(task);
    }
}
