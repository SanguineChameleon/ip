package haru.command;

import haru.model.TaskList;
import haru.ui.UI;

/**
 * Holds context information for command execution.
 */
public class CommandContext {
    private final TaskList taskList;
    private final UI ui;

    /**
     * Constructs a CommandContext with task list and UI.
     *
     * @param taskList the task list
     * @param ui       the UI
     */
    public CommandContext(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Returns the task list.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the UI.
     *
     * @return the UI
     */
    public UI getUI() {
        return this.ui;
    }
}
