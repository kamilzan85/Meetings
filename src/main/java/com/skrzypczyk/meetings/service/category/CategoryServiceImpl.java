package com.skrzypczyk.meetings.service.category;

import com.skrzypczyk.meetings.model.Category;
import com.skrzypczyk.meetings.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
}
