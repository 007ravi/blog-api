package com.personalproject.blogapi.repository;

import com.personalproject.blogapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
