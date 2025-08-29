package haru.exception;

public class EmptyCommandException extends HaruException {
    public EmptyCommandException() {
        super("Eh?! You should give me a command!");
    }
}
