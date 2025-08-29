package haru.model;

public class Event extends Task {
    private final TaskTime from;
    private final TaskTime to;

    public Event(String name, TaskTime from, TaskTime to) {
        super(name, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return String.format("%s (from %s to %s)", this.getName(), this.from, this.to);
    }
}
