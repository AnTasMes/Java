package antas.tech.demo.handlers;

import java.util.List;
import java.util.stream.Collectors;

import antas.tech.demo.models.UserRole;
import net.dv8tion.jda.api.entities.Role;

public class RoleHandler {

    /**
     * Resolves roles from type {@code List<Role>} to type
     * {@code List<Map<String,String>>}
     * 
     * @param roles List of roles to resolve
     * @return
     */
    public static final List<UserRole> resolveRoles(List<Role> roles) {
        return roles.stream()
                .map(role -> new UserRole(role.getId(), role.getName(), role.getPermissions()))
                .collect(Collectors.toList());
    }
}
