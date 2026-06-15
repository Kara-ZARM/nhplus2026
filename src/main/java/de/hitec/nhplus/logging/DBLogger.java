package de.hitec.nhplus.logging;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The utility class for logging database-changes to a log file locally.
 */
public class DBLogger {
    private static final String LOG_FILE = "db/dbChanges.log";

    /**
     * A Filewriter is opened to write to local disk space. Messages are seperated by the systems line seperator.
     * The log file is appending to the file if it is already existing.
     * @param logEntry The LogEntry Object which information should be persisted in the log file.
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
