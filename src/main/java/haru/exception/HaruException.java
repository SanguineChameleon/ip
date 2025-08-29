package haru.exception;

public abstract class HaruException extends Exception {
    public HaruException(String message) {
        super(message);
    }
}