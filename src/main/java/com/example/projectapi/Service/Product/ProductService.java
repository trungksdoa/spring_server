package com.example.projectapi.Service.Product;

import com.example.projectapi.Model.Catagory;
import com.example.projectapi.Model.Product;

import com.example.projectapi.Model.Product2;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Stream;



public interface ProductService extends ProductCatagoryService{
    List<Product2> getAllProduct();
    Product2 saveProduct(Product2 product);
    Product2 getProduct(Long id);
    void deleteProduct(Long id);
    void saveCatagoryToProduct(String productName,String catagoryName);
}
