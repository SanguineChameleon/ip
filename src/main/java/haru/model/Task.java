package haru.model;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private final String name;
    private final TaskType type;
    private boolean isDone = false;

    public Task(String name, TaskType type) {
        this.name = name;
        this.type = type;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.getName();
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s",
                this.type.getCode(),
                (this.isDone ? 'X' : ' '),
                this.getDescription());
    }
}
