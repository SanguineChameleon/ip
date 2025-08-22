public class EmptyArgumentException extends HaruException {
    public EmptyArgumentException(String alias) {
        super(String.format("Eh?! The %s cannot be empty!", alias));
    }
}
