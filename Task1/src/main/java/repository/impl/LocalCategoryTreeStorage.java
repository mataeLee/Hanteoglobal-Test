package repository.impl;

import entity.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalCategoryTreeStorage {
    private final String ROOT_NAME = "root";
    private final String EMPTY_STRING = "";

    // auto increment primary key
    private int categoryIdSeq;

    // tree root
    private Category root;

    // 트리 순회를 이용하여 데이터를 가져올 수 있지만 빠른 탐색을 위해 별도 인덱스를 구현
    private HashMap<String, List<Category>> nameIndex;
    private HashMap<Integer, Category> primaryIndex;

    public LocalCategoryTreeStorage() {
        categoryIdSeq = 1;

        nameIndex = new HashMap<>();
        primaryIndex = new HashMap<>();

        root = new Category(ROOT_NAME);

        primaryIndex.put(0, root);
        nameIndex.put(ROOT_NAME, new ArrayList<>());
        nameIndex.get(ROOT_NAME).add(root);
    }

    public Category findRoot() {
        if (root == null) {
            throw new RuntimeException("storage error");
        }
        return root;
    }

    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        if (nameIndex.containsKey(name)) {
            for (Category category : nameIndex.get(name)) {
                if (primaryIndex.containsKey(category.getId())) {
                    categories.add(category);
                }
            }
        }
        return categories;
    }

    public Category findById(int id) {
        return primaryIndex.get(id);
    }

    public Category save(Category category) {
        String categoryName = category.getName();

        if (EMPTY_STRING.equals(categoryName) || ROOT_NAME.equals(categoryName)) {
            return null;
        }

        if (category.getId() == 0 || !primaryIndex.containsKey(category.getId())) { // create

            category.setId(categoryIdSeq++);

            // 중복 이름일 경우 분기처리
            if (nameIndex.containsKey(categoryName)) {
                nameIndex.get(categoryName).add(category);
            } else {
                nameIndex.put(categoryName, new ArrayList<>());
                nameIndex.get(categoryName).add(category);
            }

            primaryIndex.put(category.getId(), category);
        } else if (category.getId() > 0 && primaryIndex.containsKey(category.getId())) { // update
            primaryIndex.put(category.getId(), category);

            List<Category> categories = nameIndex.get(category.getName());
            categories.remove(category);
            categories.add(category);
        } else {
            return null;
        }

        return category;
    }

    public void delete(Category category) {
        primaryIndex.remove(category.getId());
        List<Category> categories = nameIndex.get(category.getName());
        categories.remove(category);
    }
}
