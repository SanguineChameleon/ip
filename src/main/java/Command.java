import haru.exception.EmptyArgumentException;
import haru.exception.HaruException;
import haru.exception.UnknownOptionException;
import haru.model.TaskList;
import haru.model.TaskTime;
import haru.ui.UI;

import java.io.IOException;
import java.util.HashMap;

public abstract class Command {
    private final HashMap<String, String> options = new HashMap<>();
    private final HashMap<String, String> aliases;
    private final CommandContext ctx;

    public Command(HashMap<String, String> aliases, CommandContext ctx) {
        this.aliases = new HashMap<>(aliases);
        for (String name : aliases.keySet()) {
            this.options.put(name, "");
        }
        if (!aliases.containsKey("primary")) {
            this.aliases.put("primary", "main value");
            this.options.put("primary", "");
        }
        this.ctx = ctx;
    }

    public TaskList getTaskList() {
        return this.ctx.getTaskList();
    }

    public UI getUI() {
        return this.ctx.getUI();
    }

    public String getRequiredOption(String name) throws HaruException {
        String value = this.options.get(name);
        if (value.isEmpty()) {
            String alias = this.aliases.get(name);
            throw new EmptyArgumentException(alias);
        }
        return value;
    }

    public TaskTime getRequiredTime(String name) throws HaruException {
        String strTime = getRequiredOption(name);
        String alias = this.aliases.get(name);
        return new TaskTime(alias, strTime);
    }

    public void parse(String[] tokens) throws HaruException {
        StringBuilder sb = new StringBuilder();
        String name = "primary";
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.charAt(0) == '/') {
                this.options.put(name, sb.toString());
                name = token.substring(1);
                if (!this.options.containsKey(name)) {
                    throw new UnknownOptionException(name);
                }
                sb = new StringBuilder();
                continue;
            }
            if (!sb.isEmpty()) {
                sb.append(' ');
            }
            sb.append(token);
        }
        this.options.put(name, sb.toString());
    }

    public abstract void execute() throws HaruException, IOException;
}