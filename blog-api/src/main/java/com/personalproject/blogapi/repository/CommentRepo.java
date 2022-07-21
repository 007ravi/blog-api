package com.personalproject.blogapi.repository;

import com.personalproject.blogapi.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
