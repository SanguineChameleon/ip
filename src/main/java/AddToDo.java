public class AddToDo extends AddTask {
    public AddToDo() {
        super(new String[]{});
    }

    @Override
    public void execute() {
        // TODO: task name can be empty
        String name = super.getOption("primary");
        add(new ToDo(name));
    }
}
