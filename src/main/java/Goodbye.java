public class Goodbye extends Command {
    @Override
    public void execute() {
        System.out.println("See you next time! Bye-bye~");
        Haru.stop();
    }
}
