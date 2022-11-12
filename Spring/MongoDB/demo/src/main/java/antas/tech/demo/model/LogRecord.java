package antas.tech.demo.model;

import java.time.LocalTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stdlog")
public class LogRecord extends Record {
    @Id
    private String id;
    private String message;
    private LocalTime timestamp;
    private String type;

    private Map<String, String> components;

    public LogRecord(String message, LocalTime timestamp, Map<String, String> components) {
        this.message = message;
        this.timestamp = timestamp;
        this.type = "SIMPLE";
        this.components = components;
    }

    public LogRecord(String message, LocalTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.type = "SIMPLE";
    }

    public Map<String, String> getComponents() {
        return components;
    }

    public void setComponents(Map<String, String> components) {
        this.components = components;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

}
