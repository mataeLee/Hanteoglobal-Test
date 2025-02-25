package repository;

import entity.Category;

import java.util.List;

public interface CategoryRepository {
    Category findRoot();

    List<Category> findByName(String name);

    Category findById(int id);

    Category save(Category category);

    void delete(Category category);
}
