import java.util.HashMap;

public abstract class Command {
    private HashMap<String, String> options;

    public Command(String[] names) {
        this.options = new HashMap<>();
        for (String name: names) {
            this.options.put(name, "");
        }
    }

    public String getOption(String name) {
        return this.options.get(name);
    }

    public void parse(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (!sb.isEmpty()) {
                sb.append(' ');
            }
            sb.append(token);
        }
        this.options.put("primary", sb.toString());
    }

    public abstract void execute();
}