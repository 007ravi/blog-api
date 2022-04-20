package com.personalproject.blogapi.repository;

import com.personalproject.blogapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
