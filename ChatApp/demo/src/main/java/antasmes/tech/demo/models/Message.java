package antasmes.tech.demo.models;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "messages")
public class Message {
    @MongoId
    private ObjectId objectId;
    private String text_value;
    private LocalDate timestamp;

    private String from_user_id;
    private String to_user_id;

    public Message() {
    }

    public Message(String text_value, LocalDate timestamp, String from_user_id,
            String to_user_id) {
        this.text_value = text_value;
        this.timestamp = timestamp;
        this.from_user_id = from_user_id;
        this.to_user_id = to_user_id;
    }

    public String getText_value() {
        return text_value;
    }

    public void setText_value(String text_value) {
        this.text_value = text_value;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(String from_user_id) {
        this.from_user_id = from_user_id;
    }

    public String getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(String to_user_id) {
        this.to_user_id = to_user_id;
    }
}
