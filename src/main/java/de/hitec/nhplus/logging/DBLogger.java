package de.hitec.nhplus.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The utility class for logging database-changes to a log file locally in the db/ folder.
 */
public class DBLogger {
    private static final String LOG_FILE = "db/dbChanges.log";

    /**
     * A Filewriter is opened to write to local disk space. Messages are seperated by the systems line seperator.
     * The log file is appending to the file if it is already existing.
     *
     */
    public static void log(LogEntry logEntry) {
        System.out.println();
        try (FileWriter fileWriter = new FileWriter(LOG_FILE,true)) {
            fileWriter.write(logEntry + System.lineSeparator());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
