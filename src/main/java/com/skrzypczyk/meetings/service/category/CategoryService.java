package com.skrzypczyk.meetings.service.category;

import com.skrzypczyk.meetings.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void save(Category category);
    Optional<Category> findCategoryById(Long id);
    List<Category> findAll();
}
