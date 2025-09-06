package haru.app;

import java.io.IOException;
import java.util.stream.Stream;

import haru.command.AddDeadlineCommand;
import haru.command.AddEventCommand;
import haru.command.AddToDoCommand;
import haru.command.Command;
import haru.command.CommandContext;
import haru.command.DeleteTaskCommand;
import haru.command.FindTasksCommand;
import haru.command.GoodbyeCommand;
import haru.command.HelloCommand;
import haru.command.ListTasksCommand;
import haru.command.MarkTask;
import haru.command.UnmarkTask;
import haru.exception.EmptyCommandException;
import haru.exception.HaruException;
import haru.exception.UnknownCommandException;
import haru.model.TaskList;
import haru.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main application class for Haru.
 */
public class Haru extends Application {
    private static final String TASK_FILE_PATH = "tasks.ser";
    private CommandContext ctx;
    private Ui ui;
    private VBox chat;

    /**
     * Runs a command from user input.
     *
     * @param str the command string
     * @throws HaruException if command parsing or execution fails
     * @throws IOException   if IO error occurs
     */
    private void runCommand(String str) throws HaruException, IOException {
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
        case "bye" -> new GoodbyeCommand(ctx);
        case "todo" -> new AddToDoCommand(ctx);
        case "deadline" -> new AddDeadlineCommand(ctx);
        case "event" -> new AddEventCommand(ctx);
        case "list" -> new ListTasksCommand(ctx);
        case "mark" -> new MarkTask(ctx);
        case "unmark" -> new UnmarkTask(ctx);
        case "delete" -> new DeleteTaskCommand(ctx);
        case "find" -> new FindTasksCommand(ctx);
        default -> throw new UnknownCommandException();
        };
        // @formatter:on
        command.parse(tokens);
        command.execute();
    }

    private void setStage(Stage stage) {
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("/view/main.fxml")
        );
        AnchorPane root;
        try {
            root = loader.load();
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to load FXML", e);
        }

        haru.ui.UiController c = loader.getController();

        this.chat = c.getChat();
        this.chat.setFillWidth(true);
        c.getScroll().setFitToWidth(true);

        this.chat.heightProperty().addListener(
                (o, oldH, newH) -> c.getScroll().setVvalue(1.0)
        );

        c.getBottom().heightProperty().addListener(
                (obs, oldH, newH) -> AnchorPane.setBottomAnchor(c.getScroll(), newH.doubleValue())
        );

        c.getSend().setOnAction(event -> handleInput(c.getInput().getText()));
        c.getInput().setOnAction(event -> handleInput(c.getInput().getText()));

        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.setTitle("Haru");
        stage.setResizable(false);
        stage.show();
    }

    private void handleInput(String str) {
        ui.showUserMessage(str);
        try {
            runCommand(str);
        } catch (HaruException | IOException e) {
            if (e instanceof HaruException) {
                ui.showHaruMessage(e.getMessage());
            } else {
                ui.showHaruMessage("Eh?! Something went wrong with reading/saving your file!");
            }
            ui.showHaruMessage("It's okay, you can try again~!");
        }
    }

    @Override
    public void start(Stage stage) {
        this.setStage(stage);

        TaskList taskList;
        try {
            taskList = TaskList.fromFile(TASK_FILE_PATH);
        } catch (IOException | ClassNotFoundException e) {
            taskList = TaskList.empty(TASK_FILE_PATH);
        }
        ui = new Ui(chat);
        ctx = new CommandContext(taskList, ui);
        new HelloCommand(ctx).execute();
    }
}
