public class UnknownOptionException extends HaruException {
    public UnknownOptionException(String name) {
        super(String.format("Eh?! I don't recognize the /%s option!", name));
    }
}
