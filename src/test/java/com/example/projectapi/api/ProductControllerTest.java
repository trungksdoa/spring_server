package com.example.projectapi.api;

import com.example.projectapi.Model.Catagory;
import com.example.projectapi.Model.Product2;
import com.example.projectapi.Repo.CatagoryRepo;
import com.example.projectapi.Repo.ProductRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductController productController;

    @MockBean
    private CatagoryRepo catagoryRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void getProducts() {

    }

    @Test
    void getCatagorys() {
        Catagory catagory = new Catagory();
        catagory.setName("aloha");

        List<Catagory> catagories = singletonList(catagory);

        Mockito.when(catagoryRepo.findAll()).thenReturn(catagories);
    }

    @Test
    void saveProduct() {
    }

    @Test
    void saveCatagorys() {
    }
}