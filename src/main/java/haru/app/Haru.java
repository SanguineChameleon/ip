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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
        AnchorPane layout = new AnchorPane();

        chat = new VBox();
        chat.setFillWidth(true);

        ScrollPane scrollPane = new ScrollPane(chat);
        layout.getChildren().add(scrollPane);
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        scrollPane.setFitToWidth(true);

        chat.heightProperty().addListener(
                (o, oldH, newH)
                        -> scrollPane.setVvalue(1.0));

        TextField input = new TextField();
        Button send = new Button("Send");

        HBox bottom = new HBox(0, input, send);
        layout.getChildren().add(bottom);
        HBox.setHgrow(input, Priority.ALWAYS);
        AnchorPane.setLeftAnchor(bottom, 0.0);
        AnchorPane.setRightAnchor(bottom, 0.0);
        AnchorPane.setBottomAnchor(bottom, 0.0);

        bottom.heightProperty().addListener(
                (obs, oldH, newH)
                        -> AnchorPane.setBottomAnchor(scrollPane, newH.doubleValue())
        );

        send.setOnMouseClicked((event) -> {
            handleInput(input.getText());
            input.clear();
        });

        input.setOnAction((event) -> {
            handleInput(input.getText());
            input.clear();
        });

        Scene scene = new Scene(layout, 400, 600);
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
        new Hello(ctx).execute();
    }
}
