package antas.tech.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "errorlog")
public class ErrorLogRecord extends Record {
    @Id
    private String id;
    private String exception;
    private String type;
    private String stacktrace;

    public String getStacktrace() {
        return "Some stacktrace";
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = "some stacktrace";
    }

    public ErrorLogRecord(String exception) {
        this.exception = exception;
        this.type = "ERROR";
        this.stacktrace = getStacktrace();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

}
