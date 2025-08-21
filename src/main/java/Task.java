public class Task {
    private final String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s",
                (this.isDone ? 'X' : ' '),
                this.getDescription());
    }
}
