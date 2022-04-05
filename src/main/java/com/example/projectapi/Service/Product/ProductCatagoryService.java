package com.example.projectapi.Service.Product;

import com.example.projectapi.Model.Catagory;

public interface ProductCatagoryService extends PageProductService {
    Catagory saveCatagory(Catagory catagory);
}
