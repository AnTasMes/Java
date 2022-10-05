package antas.tech.demo.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "category")
public class ServerCategory {
    @MongoId
    private ObjectId objectId;
    private String uid;
    private String name;
    private UserRole ownerRole;

    private List<ServerChannel> children; // Add children for each Category

    public ServerCategory(String uid, String name, UserRole ownerRole) {
        this.uid = uid;
        this.name = name;
        this.ownerRole = ownerRole;
    }

    public ServerCategory(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public ServerCategory() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getOwnerRole() {
        return ownerRole;
    }

    public void setOwnerRole(UserRole ownerRole) {
        this.ownerRole = ownerRole;
    }
}
