package com.example.projectapi.Repo;

import com.example.projectapi.Model.Catagory;
import com.example.projectapi.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatagoryRepo extends JpaRepository<Catagory,Long> {
    Catagory findByName(String username);
}
