import java.util.ArrayList;
import java.util.stream.Stream;

public class Haru {
    private static boolean isRunning = true;
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void stop() {
        Haru.isRunning = false;
    }

    public static ArrayList<Task> getTasks() {
        return Haru.tasks;
    }

    private static void parse(String str) {
        String[] tokens = Stream.of(str.split(" "))
                .filter(t -> !t.isEmpty())
                .toArray(String[]::new);
        // TODO: tokens can be empty
        String name = tokens[0];
        Command command = switch (name) {
            case "bye" -> new Goodbye();
            default -> null;
        };
        command.parse(tokens);
        command.execute();
    }

    public static void main(String[] args) {
        new Hello().execute();
        while (Haru.isRunning) {
            Haru.parse(System.console().readLine());
        }
    }
}
