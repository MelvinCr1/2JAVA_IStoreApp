// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE_PATH = "session_logs.txt";

    public static void log(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            // Obtenez la date et l'heure actuelles
            LocalDateTime currentDateTime = LocalDateTime.now();
            // Formatez la date et l'heure
            String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Écrivez le message de log dans le fichier
            writer.write(formattedDateTime + " - " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
