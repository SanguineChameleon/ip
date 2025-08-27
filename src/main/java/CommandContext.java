public class CommandContext {
    private final TaskList taskList;

    public CommandContext(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
