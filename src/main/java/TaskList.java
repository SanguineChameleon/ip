import java.io.*;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private final String filePath;
    private final ArrayList<Task> tasks;

    private TaskList(String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }

    public static TaskList empty(String filePath) {
        return new TaskList(filePath, new ArrayList<>());
    }

    public static TaskList fromFile(String filePath)
            throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            return (TaskList) in.readObject();
        }
    }

    public void writeToFile()
        throws IOException {
        try (FileOutputStream fos = new FileOutputStream(this.filePath);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(this);
        }
    }

    // todo: replace this, better to get and set elements
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}