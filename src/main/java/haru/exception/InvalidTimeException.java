package haru.exception;

public class InvalidTimeException extends HaruException {
    public InvalidTimeException(String alias) {
        super(String.format("Eh?! The %s must be in a valid format!", alias));
    }
}
