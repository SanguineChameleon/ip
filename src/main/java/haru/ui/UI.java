package haru.ui;

import java.util.Scanner;

public class UI {
    private final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }

    public void show(String msg) {
        System.out.println(msg);
    }
}
