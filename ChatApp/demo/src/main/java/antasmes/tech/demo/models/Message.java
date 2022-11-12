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

    private ObjectId fromUserId;
    private ObjectId toUserId;

    public Message() {
    }

    public Message(ObjectId fromUserId, ObjectId toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.timestamp = LocalDate.now();
    }

    public Message(String testValue, ObjectId fromUserID, ObjectId toUserID) {
        this.text_value = testValue;
        this.fromUserId = fromUserID;
        this.toUserId = toUserID;
        this.timestamp = LocalDate.now();
    }

    public Message(String text_value, LocalDate timestamp, ObjectId fromUserID,
            ObjectId toUserID) {
        this.text_value = text_value;
        this.timestamp = timestamp;
        this.fromUserId = fromUserID;
        this.toUserId = toUserID;
    }

    // toString

    @Override
    public String toString() {
        return "Message [fromUserID=" + fromUserId + ", objectId=" + objectId + ", text_value=" + text_value
                + ", timestamp=" + timestamp + ", toUserID=" + toUserId + "]";
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

    public ObjectId getfromUserId() {
        return fromUserId;
    }

    public void setfromUserId(ObjectId fromUserId) {
        this.fromUserId = fromUserId;
    }

    public ObjectId gettoUserId() {
        return toUserId;
    }

    public void settoUserId(ObjectId toUserId) {
        this.toUserId = toUserId;
    }
}
