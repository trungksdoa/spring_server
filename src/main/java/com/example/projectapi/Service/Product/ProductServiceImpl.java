package com.example.projectapi.Service.Product;

import com.example.projectapi.Model.Catagory;
import com.example.projectapi.Model.Product;
import com.example.projectapi.Model.Product2;
import com.example.projectapi.Repo.CatagoryRepo;
import com.example.projectapi.Repo.PageProductRepo;
import com.example.projectapi.Repo.ProductRepo;
import com.example.projectapi.handelError.CustomAlreadyExistsException;
import com.example.projectapi.handelError.CustomNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final PageProductRepo pageproductRepo;
    private final CatagoryRepo catagoryRepo;

    @Override
    public Product2 saveProduct(Product2 product) {
        log.info("Save product {} to database", product.getName());
        return productRepo.save(product);
    }

    @Override
    public List<Product2> getAllProduct() {
        return productRepo.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Stream<Product2> getAllProductPage(Pageable pageable) {
        Page<Product2> allProductsSortedByName = productRepo.findAll(pageable);
        log.info(String.valueOf(allProductsSortedByName.getTotalPages()));
//        Page<Product> allProductsSortedByName = pageproductRepo.findAll(Sort.by("name"));
        return allProductsSortedByName.get();
    }

    @Override
    public Product2 getProduct(Long id) {
        boolean exist = productRepo.existsById(id);
        if (!exist) {
            throw new CustomNotFoundException("Kh??ng t??m th???y s???n ph???m");
        }
        return productRepo.getById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        boolean exist = productRepo.existsById(id);
        if (!exist) {
            throw new CustomNotFoundException("Kh??ng t??m th???y s???n ph???m");
        }
        productRepo.deleteById(id);

    }

    @Override
    public Catagory saveCatagory(Catagory catagory) {
        log.info("Save catagory {} to database", catagory.getName());
        if (catagoryRepo.findByName(catagory.getName().toLowerCase(Locale.ROOT)).equals(catagory))
            throw new CustomAlreadyExistsException("Catagory already exists");
        return catagoryRepo.save(catagory);
    }

    @Override
    public void saveCatagoryToProduct(String productName, String catagoryName) {
        log.info("Add Catagory {} to Product {}", catagoryName, productName);
        Product2 product = productRepo.findByName(productName);
        Catagory catagory = catagoryRepo.findByName(catagoryName);
        product.setCategory(catagory);
        productRepo.save(product);
    }

}
