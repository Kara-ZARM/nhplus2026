package de.hitec.nhplus.logging;

import java.time.LocalDateTime;

/**
 * The class for creating LogEntry objects for {@link DBLogger}
 * Provides JSON-like strings through the toString method.
 */
public class LogEntry {
    private LocalDateTime timestamp;
    private OperationType operationType;
    private String tableName;
    private String recordId;
    private String userId;

    /**
     * Generates a LogEntry to capsule basic information of a database operation.
     * @param operationType Enum of class <code>OperationType</code> for create, update and delete
     * @param tableName Name of the modified table
     * @param recordId The ID of the modified record
     * @param userId The ID of the user that modified the record
     */
    public LogEntry(OperationType operationType, String tableName, String recordId, String userId) {
        this.timestamp =  LocalDateTime.now();
        this.operationType = operationType;
        this.tableName = tableName;
        this.recordId = recordId;
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getTableName() {
        return tableName;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getUserId() {
        return userId;
    }

    /**
     * @return String representation of the LogEntry in a JSON-like format.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"timestamp\": \"").append(timestamp).append("\",\n");
        sb.append("  \"operationType\": \"").append(operationType).append("\",\n");
        sb.append("  \"tableName\": \"").append(tableName).append("\",\n");
        sb.append("  \"recordId\": ").append(recordId).append(",\n");
        sb.append("  \"userId\": \"").append(userId).append("\"\n");
        sb.append("}");
        return sb.toString();
    }
}
