public class Deadline extends Task {
    private final String by;

    public Deadline(String name, String by) {
        super(name, 'D');
        this.by = by;
    }

    @Override
    public String getDescription() {
        return String.format("%s (by %s)", this.getName(), this.by);
    }
}
