package com.example.projectapi.Repo;

import com.example.projectapi.Model.Product;
import com.example.projectapi.Model.Product2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product2, Long> {
    Product2 findByName(String username);

//    Page<Product> findAllBy(Sort sort);
}
