package haru.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML controller exposing chat UI nodes to Haru.
 */
public class UiController {
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox chat;
    @FXML
    private HBox bottom;
    @FXML
    private TextField input;
    @FXML
    private Button send;

    public ScrollPane getScroll() {
        return scroll;
    }

    public VBox getChat() {
        return chat;
    }

    public HBox getBottom() {
        return bottom;
    }

    public TextField getInput() {
        return input;
    }

    public Button getSend() {
        return send;
    }
}
