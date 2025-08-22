public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String name, String from, String to) {
        super(name, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return String.format("%s (from %s to %s)", this.getName(), this.from, this.to);
    }
}
