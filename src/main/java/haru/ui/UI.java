package haru.ui;

import java.util.Scanner;

/**
 * Handles user input and output.
 */
public class UI {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads a line of input from the user.
     * @return the input line
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     * @param msg the message to display
     */
    public void show(String msg) {
        System.out.println(msg);
    }
}
