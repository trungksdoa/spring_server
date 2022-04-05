package com.example.projectapi.Service.Product;

import com.example.projectapi.Model.Product;
import com.example.projectapi.Model.Product2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

public interface PageProductService {
    Stream<Product2> getAllProductPage(Pageable pageable);
}
