package haru.app;

import java.io.IOException;
import java.util.stream.Stream;

import haru.command.AddDeadline;
import haru.command.AddEvent;
import haru.command.AddToDo;
import haru.command.Command;
import haru.command.CommandContext;
import haru.command.DeleteTask;
import haru.command.FindTasks;
import haru.command.Goodbye;
import haru.command.Hello;
import haru.command.ListTasks;
import haru.command.MarkTask;
import haru.command.UnmarkTask;
import haru.exception.EmptyCommandException;
import haru.exception.HaruException;
import haru.exception.UnknownCommandException;
import haru.model.TaskList;
import haru.ui.UI;

/**
 * Main application class for Haru.
 */
public class Haru {
    private static final String TASK_FILE_PATH = "tasks.ser";
    private static boolean isRunning = true;
    private static CommandContext ctx;

    /**
     * Stops the application.
     */
    public static void stop() {
        Haru.isRunning = false;
    }

    /**
     * Runs a command from user input.
     *
     * @param str the command string
     * @throws HaruException if command parsing or execution fails
     * @throws IOException   if IO error occurs
     */
    private static void runCommand(String str) throws HaruException, IOException {
        String[] tokens = Stream.of(str.split(" "))
                .filter(t -> !t.isEmpty())
                .toArray(String[]::new);
        if (tokens.length == 0) {
            throw new EmptyCommandException();
        }
        String name = tokens[0];
        // IntelliJ IDEA auto formatting doesn't work for switch expressions
        // @formatter:off
        Command command = switch (name) {
        case "bye" -> new Goodbye(Haru.ctx);
        case "todo" -> new AddToDo(Haru.ctx);
        case "deadline" -> new AddDeadline(Haru.ctx);
        case "event" -> new AddEvent(Haru.ctx);
        case "list" -> new ListTasks(Haru.ctx);
        case "mark" -> new MarkTask(Haru.ctx);
        case "unmark" -> new UnmarkTask(Haru.ctx);
        case "delete" -> new DeleteTask(Haru.ctx);
        case "find" -> new FindTasks(Haru.ctx);
        default -> throw new UnknownCommandException();
        };
        // @formatter:on
        command.parse(tokens);
        command.execute();
    }

    /**
     * Entry point of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        TaskList taskList;
        try {
            taskList = TaskList.fromFile(TASK_FILE_PATH);
        } catch (IOException | ClassNotFoundException e) {
            taskList = TaskList.empty(TASK_FILE_PATH);
        }
        UI ui = new UI();
        Haru.ctx = new CommandContext(taskList, ui);
        new Hello(Haru.ctx).execute();
        while (Haru.isRunning) {
            try {
                Haru.runCommand(ui.readLine());
            } catch (HaruException | IOException e) {
                if (e instanceof HaruException) {
                    ui.show(e.getMessage());
                } else {
                    ui.show("Eh?! Something went wrong with reading/saving your file!");
                }
                ui.show("It's okay, you can try again~!");
            }
        }
    }
}
