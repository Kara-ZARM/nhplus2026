package de.hitec.nhplus.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The utility class for logging database-changes to a log file locally in the db/ folder.
 */
public class DBLogger {
    private static final String LOG_FILE = "logging/dbChanges.log";

    /**
     * A Filewriter is opened to write to local disk space. Messages are seperated by the systems line seperator.
     * The log file is appending to the file if it is already existing.
     * @param message The message that is written into the log file
     */
    public static void log(String message) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE,true)) {
            fileWriter.write(LocalDateTime.now() + message + System.lineSeparator());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
