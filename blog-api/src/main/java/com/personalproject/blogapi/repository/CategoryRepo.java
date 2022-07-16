package com.personalproject.blogapi.repository;

import com.personalproject.blogapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
