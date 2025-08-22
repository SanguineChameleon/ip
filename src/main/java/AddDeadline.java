public class AddDeadline extends AddTask {
    public AddDeadline() {
        super(new String[]{"by"});
    }

    @Override
    public void execute() {
        // TODO: task name can be empty, by can by empty
        String name = super.getOption("primary");
        String by = super.getOption("by");
        add(new Deadline(name, by));
    }
}
