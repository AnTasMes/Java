package antas.tech.demo.models;

import java.util.EnumSet;

import org.springframework.data.annotation.Transient;

import net.dv8tion.jda.api.Permission;

public class UserRole {
    private String id;
    private String name;

    @Transient
    private EnumSet<Permission> permissions;

    public UserRole() {
    }

    public UserRole(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public UserRole(String id, String name, EnumSet<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumSet<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(EnumSet<Permission> permissions) {
        this.permissions = permissions;
    }
}
