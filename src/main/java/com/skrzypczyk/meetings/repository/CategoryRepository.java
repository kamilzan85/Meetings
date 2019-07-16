package com.skrzypczyk.meetings.repository;

import com.skrzypczyk.meetings.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
