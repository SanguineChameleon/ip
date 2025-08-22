public class Deadline extends Task {
    private final String by;

    public Deadline(String name, String by) {
        super(name, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return String.format("%s (by %s)", this.getName(), this.by);
    }
}
