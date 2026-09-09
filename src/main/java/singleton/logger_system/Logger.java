package singleton.logger_system;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger instance;

    private String fileName;
    private BufferedWriter writer;

    private Logger() {
        fileName = "log.txt";

        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("Could not open log file.");
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void write(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Could not write to log file.");
        }
    }

    public void setFileName(String fileName) {
        close();

        this.fileName = fileName;

        try {
            writer = new BufferedWriter(
                    new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("Could not open log file.");
        }
    }

    public void close() {
        if (writer == null) {
            return;
        }

        try {
            writer.close();
            writer = null;
        } catch (IOException e) {
            System.out.println("Could not close log file.");
        }
    }
}
