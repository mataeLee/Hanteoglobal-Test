package repository.impl;

import entity.Category;
import repository.CategoryRepository;

import java.util.*;

public class CategoryTreeRepository implements CategoryRepository {

    private LocalCategoryTreeStorage localCategoryTreeStorage;

    public CategoryTreeRepository() {
        localCategoryTreeStorage = new LocalCategoryTreeStorage();
    }

    @Override
    public Category findRoot() {
        return localCategoryTreeStorage.findRoot();
    }

    @Override
    public List<Category> findByName(String name) {
        return localCategoryTreeStorage.findByName(name);
    }

    @Override
    public Category findById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("category id can not be null");
        }

        Category category = localCategoryTreeStorage.findById(id);

        if (category == null) {
            throw new NoSuchElementException("category not found");
        }

        return category;
    }

    @Override
    public Category save(Category category) {
        if (category != null && category.getId() < 0) {
            throw new IllegalArgumentException("category id can not be null");
        }

        if (localCategoryTreeStorage.save(category) == null) {
            throw new IllegalArgumentException("check category name. can not be empty or root");
        }

        return localCategoryTreeStorage.save(category);
    }

    @Override
    public void delete(Category category) {
        localCategoryTreeStorage.delete(category);
    }
}
