package com.personalproject.blogapi.repository;

import com.personalproject.blogapi.models.Category;
import com.personalproject.blogapi.models.Post;
import com.personalproject.blogapi.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findAllByCategory(Category cat, Pageable p);
}
