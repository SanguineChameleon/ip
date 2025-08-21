public class Goodbye extends Command {
    public Goodbye() {
        super(new String[]{});
    }

    @Override
    public void execute() {
        System.out.println("See you next time! Bye-bye~");
        Haru.stop();
    }
}
