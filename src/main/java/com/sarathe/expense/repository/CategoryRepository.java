package com.sarathe.expense.repository;

import com.sarathe.expense.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findCategoriesByCategoryName(String name);
}
