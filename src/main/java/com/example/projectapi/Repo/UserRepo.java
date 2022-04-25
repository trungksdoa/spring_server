package com.example.projectapi.Repo;

import com.example.projectapi.Model.Users_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users_, Long> {
    Users_ findByUsername(String username);
}
