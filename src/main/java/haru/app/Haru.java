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
        assert str != null : "input cannot be null";
        assert ctx != null : "CommandContext must be initialized";
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
        case "bye" -> new Goodbye(ctx);
        case "todo" -> new AddToDo(ctx);
        case "deadline" -> new AddDeadline(ctx);
        case "event" -> new AddEvent(ctx);
        case "list" -> new ListTasks(ctx);
        case "mark" -> new MarkTask(ctx);
        case "unmark" -> new UnmarkTask(ctx);
        case "delete" -> new DeleteTask(ctx);
        case "find" -> new FindTasks(ctx);
        default -> throw new UnknownCommandException();
        };
        // @formatter:on
        command.parse(tokens);
        command.execute();
    }

    private void setStage(Stage stage) {
        assert javafx.application.Platform.isFxApplicationThread() : "Must be on FX thread";
        assert stage != null : "stage is required";
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("/view/main.fxml")
        );
        assert loader.getLocation() != null : "/view/main.fxml not found on classpath";
        AnchorPane root;
        try {
            root = loader.load();
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to load FXML", e);
        }

        haru.ui.UiController c = loader.getController();
        assert c != null : "UiController not wired in FXML";

        this.chat = c.getChat();
        assert this.chat != null : "chat VBox missing";
        assert c.getScroll() != null : "ScrollPane missing";
        assert c.getBottom() != null : "bottom pane missing";
        assert c.getSend() != null : "send button missing";
        assert c.getInput() != null : "input field missing";
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
        assert ui != null : "Ui must be initialized";
        assert str != null : "input cannot be null";
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
        assert javafx.application.Platform.isFxApplicationThread() : "start() must be on FX thread";
        assert stage != null : "stage is required";
        this.setStage(stage);
        assert chat != null : "chat must be set by setStage()";

        TaskList taskList;
        try {
            taskList = TaskList.fromFile(TASK_FILE_PATH);
        } catch (IOException | ClassNotFoundException e) {
            taskList = TaskList.empty(TASK_FILE_PATH);
        }
        ui = new Ui(chat);
        ctx = new CommandContext(taskList, ui);
        new Hello(ctx).execute();
    }
}