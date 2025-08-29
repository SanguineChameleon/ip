package haru.model;

/**
 * Represents the type of a task.
 */
public enum TaskType {
    TODO('T'),
    DEADLINE('D'),
    EVENT('E');

    private final char code;

    /**
     * Constructs a TaskType with code.
     * @param code the task type code
     */
    TaskType(char code) {
        this.code = code;
    }

    /**
     * Returns the task type code.
     * @return the task type code
     */
    public char getCode() {
        return this.code;
    }
}
