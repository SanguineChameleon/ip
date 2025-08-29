package haru.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import haru.exception.HaruException;
import haru.exception.InvalidTaskIdException;

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

    public static TaskList fromList(ArrayList<Task> tasks) {
        return new TaskList(null, tasks);
    }

    public void writeToFile()
            throws IOException {
        if (this.filePath == null) {
            return;
        }
        try (FileOutputStream fos = new FileOutputStream(this.filePath);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(this);
        }
    }

    public int parseTaskId(String str) throws HaruException {
        int length = this.tasks.size();
        try {
            int id = Integer.parseInt(str);
            if (1 <= id && id <= length) {
                return id - 1;
            } else {
                throw new InvalidTaskIdException(length);
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskIdException(length);
        }
    }

    public void add(Task task) throws IOException {
        this.tasks.add(task);
        this.writeToFile();
    }

    public Task remove(int index) throws IOException {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        this.writeToFile();
        return task;
    }

    public Task mark(int index) throws IOException {
        Task task = this.tasks.get(index);
        task.mark();
        this.writeToFile();
        return task;
    }

    public Task unmark(int index) throws IOException {
        Task task = this.tasks.get(index);
        task.unmark();
        this.writeToFile();
        return task;
    }

    /**
     * Finds tasks whose names contain the given string.
     *
     * @param str the string to search for
     * @return a TaskList of matching tasks
     */
    public TaskList find(String str) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getName().contains(str)) {
                matches.add(task);
            }
        }
        return TaskList.fromList(matches);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                sb.append('\n');
            }
            sb.append(i + 1).append(". ").append(tasks.get(i));
        }
        return sb.toString();
    }
}