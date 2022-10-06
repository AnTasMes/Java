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
    private List<ServerChannel> children;

    public ServerCategory(String uid, String name, UserRole ownerRole, List<ServerChannel> children) {
        this.uid = uid;
        this.name = name;
        this.ownerRole = ownerRole;
        this.children = children;
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

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public List<ServerChannel> getChildren() {
        return children;
    }

    public void setChildren(List<ServerChannel> children) {
        this.children = children;
    }
}
