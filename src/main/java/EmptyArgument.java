public class EmptyArgument extends HaruException {
    public EmptyArgument(String alias) {
        super(String.format("Eh?! The %s cannot be empty!", alias));
    }
}
