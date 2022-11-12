package antasmes.tech.demo.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
public class User {
    @MongoId
    private ObjectId objectId;

    private String username;
    private String password;
    private String email;
    private LocalTime dateAdded;
    private LocalTime lastSeen;
    private LocalTime lastTyped;
    private List<User> friends;

    public User() {

    }

    public User(String username, String password, String email, List<User> friends) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.friends = friends;
        this.lastSeen = LocalTime.now();
        this.lastTyped = LocalTime.now();
        this.dateAdded = LocalTime.now();
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        friends = new ArrayList<>();
        this.lastSeen = LocalTime.now();
        this.lastTyped = LocalTime.now();
        this.dateAdded = LocalTime.now();
    }

    // To String

    @Override
    public String toString() {
        return "User [dateAdded=" + dateAdded + ", email=" + email + ", friends=" + friends + ", lastSeen=" + lastSeen
                + ", lastTyped=" + lastTyped + ", objectId=" + objectId + ", password=" + password + ", username="
                + username + "]";
    }

    public LocalTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getlastSeen() {
        return lastSeen;
    }

    public void setlastSeen(LocalTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public LocalTime getlastTyped() {
        return lastTyped;
    }

    public void setlastTyped(LocalTime lastTyped) {
        this.lastTyped = lastTyped;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
