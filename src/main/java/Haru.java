import java.util.stream.Stream;

public class Haru {
    private static boolean isRunning = true;

    public static void stop() {
        Haru.isRunning = false;
    }

    private static void parse(String str) {
        System.out.println("You typed: " + str);
        String[] tokens = Stream.of(str.split(" "))
                .filter(t -> !t.isEmpty())
                .toArray(String[]::new);
        // TODO: tokens can be empty
        if (tokens[0].equals("bye")) {
            new Goodbye().execute();
        }
    }

    public static void main(String[] args) {
        new Hello().execute();
        while (Haru.isRunning) {
            Haru.parse(System.console().readLine());
        }
    }
}
