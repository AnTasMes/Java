package antas.tech.demo.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
public class User {
    @MongoId
    private ObjectId objectId;
    private String username;
    private String discriminator;
    private String uid;

    private List<UserRole> roles;

    public User(String username, String discriminator, String uid, List<UserRole> roles) {
        this.username = username;
        this.discriminator = discriminator;
        this.uid = uid;
        this.roles = roles;
    }

    public User(String username, String discriminator, String uid) {
        this.username = username;
        this.discriminator = discriminator;
        this.uid = uid;
    }

    public User() {

    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

}
