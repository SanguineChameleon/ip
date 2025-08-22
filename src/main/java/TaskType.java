public enum TaskType {
    TODO('T'),
    DEADLINE('D'),
    EVENT('E');

    private final char code;

    TaskType(char code) {
        this.code = code;
    }

    public char getCode() {
        return this.code;
    }
}
