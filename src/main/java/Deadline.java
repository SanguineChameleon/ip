public class Deadline extends Task {
    private final TaskTime by;

    public Deadline(String name, TaskTime by) {
        super(name, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return String.format("%s (by %s)", this.getName(), this.by);
    }
}
