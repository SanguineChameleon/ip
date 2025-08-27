import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Haru {
    private static final String TASK_FILE_PATH = "tasks.ser";
    private static boolean isRunning = true;
    private static CommandContext ctx;

    public static void stop() {
        Haru.isRunning = false;
    }

    public static int parseTaskId(String str) throws HaruException {
        int length = Haru.ctx.getTaskList().getTasks().size();
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

    private static void runCommand(String str) throws HaruException, IOException {
        String[] tokens = Stream.of(str.split(" "))
                .filter(t -> !t.isEmpty())
                .toArray(String[]::new);
        if (tokens.length == 0) {
            throw new EmptyCommandException();
        }
        String name = tokens[0];
        Command command = switch (name) {
            case "bye" -> new Goodbye(Haru.ctx);
            case "todo" -> new AddToDo(Haru.ctx);
            case "deadline" -> new AddDeadline(Haru.ctx);
            case "event" -> new AddEvent(Haru.ctx);
            case "list" -> new ListTasks(Haru.ctx);
            case "mark" -> new MarkTask(Haru.ctx);
            case "unmark" -> new UnmarkTask(Haru.ctx);
            case "delete" -> new DeleteTask(Haru.ctx);
            default -> throw new UnknownCommandException();
        };
        command.parse(tokens);
        command.execute();
    }

    public static void main(String[] args) {
        TaskList taskList;
        try {
            taskList = TaskList.fromFile(TASK_FILE_PATH);
        }
        catch (IOException | ClassNotFoundException e) {
            taskList = TaskList.empty(TASK_FILE_PATH);
        }
        Haru.ctx = new CommandContext(taskList);
        new Hello(Haru.ctx).execute();
        while (Haru.isRunning) {
            try {
                Haru.runCommand(System.console().readLine());
            }
            catch (HaruException | IOException e) {
                if (e instanceof HaruException) {
                    System.out.println(e.getMessage());
                }
                else {
                    System.out.println("Eh?! Something went wrong with reading/saving your file!");
                }
                System.out.println("It's okay, you can try again~!");
            }
        }
    }
}
