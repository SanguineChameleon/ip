public class AddEvent extends AddTask {
    public AddEvent() {
        super(new String[]{"by"});
    }

    @Override
    public void execute() {
        // TODO: name, from, and to can be empty
        String name = super.getOption("primary");
        String from = super.getOption("from");
        String to = super.getOption("to");
        add(new Event(name, from, to));
    }
}
