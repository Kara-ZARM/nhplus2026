package de.hitec.nhplus.logging;

import java.time.LocalDateTime;

public class LogEntry {
    private LocalDateTime timestamp;
    private OperationType operationType;
    private String tableName;
    private Long recordId;
    private String userId;

    public LogEntry(OperationType operationType, String tableName, long recordId, String userId) {
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

    public Long getRecordId() {
        return recordId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "timestamp=" + timestamp +
                ", operationType=" + operationType +
                ", tableName='" + tableName + '\'' +
                ", recordId=" + recordId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
