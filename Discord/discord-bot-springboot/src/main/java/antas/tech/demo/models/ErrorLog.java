package antas.tech.demo.models;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "errlog")
public class ErrorLog {
    @MongoId
    private ObjectId objectId;
    private Exception exception;
    private LocalTime timestamp;
    private String stacktrace;

    public ErrorLog(Exception exception) {
        this.exception = exception;

        this.timestamp = LocalTime.now();
        this.stacktrace = getStacktrace();
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStacktrace() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        this.exception.printStackTrace(pw);

        return sw.toString();
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

}
