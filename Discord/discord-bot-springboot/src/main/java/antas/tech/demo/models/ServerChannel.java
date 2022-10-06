package antas.tech.demo.models;

import kotlin.jvm.Transient;

public class ServerChannel {
    private String name;
    private String id;
    private String type;

    @Transient
    private String parentId;

    public ServerChannel(String name, String id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public ServerChannel(String name, String id, String type, String parentId) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.parentId = parentId;
    }

    public ServerChannel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
