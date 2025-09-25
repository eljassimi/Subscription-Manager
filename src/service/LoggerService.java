package service;

import java.io.FileWriter;
import java.time.LocalDateTime;

public class LoggerService {

    private static final String LOG_FILE = "logs.txt";

    public static void log(String message) {
        try {
            FileWriter fw = new FileWriter(LOG_FILE, true);
            fw.write(LocalDateTime.now() + " - " + message + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Erreur log: " + e.getMessage());
        }
    }
}

