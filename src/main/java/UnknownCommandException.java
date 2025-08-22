public class UnknownCommandException extends HaruException {
    public UnknownCommandException(String message) {
        super("Eh?! I don't recognize that command!");
    }
}
