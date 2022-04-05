package com.example.projectapi.Repo;

import com.example.projectapi.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
