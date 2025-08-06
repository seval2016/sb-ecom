package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author seval
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
