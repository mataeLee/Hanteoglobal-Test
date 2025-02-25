package service;

import entity.Category;
import repository.CategoryRepository;
import repository.impl.CategoryTreeRepository;

import java.util.List;

public class CategoryTreeService {
    public CategoryTreeService() {
    }

    private final CategoryRepository categoryRepository = new CategoryTreeRepository();

    // relation mapping
    private void addParentRelation(Category target, int parentId) {
        Category parent = categoryRepository.findById(parentId);

        if (parentId < 0 || parent == null) {
            System.out.println("add parent failed, parent is null");
            return;
        }

        target.getParents().add(parent);
        parent.getChildren().add(target);
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    public Category findRoot() {
        return categoryRepository.findRoot();
    }

    public Category insert(Category category, int parentId) {
        if (category.getId() > 0 && categoryRepository.findById(category.getId()) != null) {
            throw new IllegalArgumentException("category already exists");
        }

        // relation mapping
        addParentRelation(category, parentId);

        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(int id) {
        Category category = categoryRepository.findById(id);

        if (category == null) {
            throw new IllegalArgumentException("delete failed, id is null");
        }

        categoryRepository.delete(category);
    }
}
