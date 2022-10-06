package antas.tech.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antas.tech.demo.models.ServerCategory;
import antas.tech.demo.models.ServerChannel;
import antas.tech.demo.models.UserRole;
import antas.tech.demo.repositories.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String isAuthorized(List<UserRole> roles) {
        for (UserRole role : roles) {
            Optional<ServerCategory> optCategory = categoryRepository.findCategoryByRoleId(role.getId());
            if (optCategory.isPresent()) {
                return optCategory.get().getUid();
            }
        }
        return null;
    }

    @Transactional
    public void bindCategoryRole(ServerCategory cat) {
        Optional<ServerCategory> optCategory = categoryRepository.findCategoryByUID(cat.getUid());

        if (!optCategory.isPresent()) {
            categoryRepository.save(cat);
            return;
        }

        ServerCategory category = optCategory.get();
        category.setOwnerRole(cat.getOwnerRole());
        category.setChildren(cat.getChildren());

        categoryRepository.save(category);
    }

    public void createCategory(ServerCategory cat) {

    }

    public void deleteCategory(String categoryUID) {

    }

    @Transactional
    public void updateCategory(String categoryUID, ServerCategory cat) {

    }

    @Transactional
    public void addChild(String categoryUID, ServerChannel child) {
        Optional<ServerCategory> optCategory = categoryRepository.findCategoryByUID(categoryUID);

        if (!optCategory.isPresent()) {
            return;
        }
        ServerCategory category = optCategory.get();
        category.getChildren().add(child);

        categoryRepository.save(category);
    }

    public Optional<ServerCategory> getCategoryByRoleId(String roleId) {
        return categoryRepository.findCategoryByRoleId(roleId);
    }

    public List<ServerCategory> getAll() {
        return categoryRepository.findAll();
    }
}
