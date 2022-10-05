package antas.tech.demo.models;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import antas.tech.demo.domains.LogKey;
import antas.tech.demo.domains.LogType;

@Document(collection = "stdlog")
public class StdLog {
    @MongoId
    private ObjectId objectId;
    private LocalTime timestamp;
    private LogType type;
    private Map<LogKey, Object> contents;

    public StdLog(LogType type) {
        this.type = type;

        this.timestamp = LocalTime.now();
        this.contents = new HashMap<>();
    }

    public StdLog(LogType type, Map<LogKey, Object> contents) {
        this.type = type;
        this.contents = contents;

        this.timestamp = LocalTime.now();
    }

    public StdLog(LocalTime timestamp, LogType type, Map<LogKey, Object> contents) {
        this.timestamp = timestamp;
        this.type = type;
        this.contents = contents;

        this.contents = new HashMap<>();
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public Map<LogKey, Object> getContents() {
        return contents;
    }

    public void setContents(Map<LogKey, Object> contents) {
        this.contents = contents;
    }

    public StdLog addContent(LogKey key, Object value) {
        this.contents.put(key, value);
        return this;
    }
}
