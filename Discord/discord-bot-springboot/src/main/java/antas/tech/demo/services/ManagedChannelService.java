package antas.tech.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antas.tech.demo.models.ServerCategory;
import antas.tech.demo.models.UserRole;

@Service
public class ManagedChannelService {

    // Remove this service.

    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public ManagedChannelService(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public String isAuthorized(List<UserRole> roles) {
        for (UserRole role : roles) {
            Optional<ServerCategory> optCategory = categoryService.getCategoryByRoleId(role.getId());
            if (optCategory.isPresent()) {
                return optCategory.get().getUid();
            }
        }
        return null;
    }
}
