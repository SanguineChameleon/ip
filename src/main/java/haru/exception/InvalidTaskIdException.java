package haru.exception;

public class InvalidTaskIdException extends HaruException {
    public InvalidTaskIdException(int length) {
        super((length == 0)
                ? "Eh?! The list is currently empty!"
                : String.format("Eh?! The task ID must be a number between 1 and %d!", length)
        );
    }
}
