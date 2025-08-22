import java.util.HashMap;

public abstract class Command {
    private final HashMap<String, String> options = new HashMap<>();
    private final HashMap<String, String> aliases;

    public Command(HashMap<String, String> aliases) {
        this.aliases = new HashMap<>(aliases);
        for (String name: aliases.keySet()) {
            this.options.put(name, "");
        }
    }

    public String getOption(String name) {
        return this.options.get(name);
    }

    public String getRequiredOption(String name) throws HaruException {
        String value = this.options.get(name);
        if (value.isEmpty()) {
            String alias = this.aliases.get(name);
            throw new EmptyArgumentException(alias);
        }
        return value;
    }

    public void parse(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        String name = "primary";
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.charAt(0) == '/') {
                this.options.put(name, sb.toString());
                name = token.substring(1);
                // TODO: check if name is invalid
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

    public abstract void execute();
}