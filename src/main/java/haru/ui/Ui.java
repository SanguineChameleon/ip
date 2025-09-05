package haru.ui;

import javafx.scene.layout.VBox;

public class Ui {
    private final VBox chat;

    public Ui(VBox chat) {
        this.chat = chat;
    }

    public void show(String msg) {
        this.chat.getChildren().add(new HaruMessage(msg));
    }
}
