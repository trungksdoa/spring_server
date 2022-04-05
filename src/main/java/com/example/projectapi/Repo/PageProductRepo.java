package com.example.projectapi.Repo;

import com.example.projectapi.Model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageProductRepo extends PagingAndSortingRepository<Product,Long> {
}
