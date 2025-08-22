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

    public static int parseTaskId(String str) throws HaruException {
        int length = Haru.tasks.size();
        try {
            int id = Integer.parseInt(str);
            if (1 <= id && id <= length) {
                return id - 1;
            }
            else {
                throw new InvalidTaskIdException(length);
            }
        }
        catch (NumberFormatException e) {
            throw new InvalidTaskIdException(length);
        }
    }

    private static void runCommand(String str) throws HaruException {
        String[] tokens = Stream.of(str.split(" "))
                .filter(t -> !t.isEmpty())
                .toArray(String[]::new);
        if (tokens.length == 0) {
            throw new EmptyCommandException();
        }
        String name = tokens[0];
        Command command = switch (name) {
            case "bye" -> new Goodbye();
            case "todo" -> new AddToDo();
            case "deadline" -> new AddDeadline();
            case "event" -> new AddEvent();
            case "list" -> new ListTasks();
            case "mark" -> new MarkTask();
            case "unmark" -> new UnmarkTask();
            case "delete" -> new DeleteTask();
            default -> throw new UnknownCommandException();
        };
        command.parse(tokens);
        command.execute();
    }

    public static void main(String[] args) {
        new Hello().execute();
        while (Haru.isRunning) {
            try {
                Haru.runCommand(System.console().readLine());
            }
            catch (HaruException e) {
                System.out.println(e.getMessage());
                System.out.println("It's okay, you can try again~!");
            }
        }
    }
}
