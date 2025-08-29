package haru.command;

import haru.model.TaskList;
import haru.ui.UI;

public class CommandContext {
    private final TaskList taskList;
    private final UI ui;

    public CommandContext(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public UI getUI() {
        return this.ui;
    }
}
