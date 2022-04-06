package com.example.projectapi.Service.catagory;

import com.example.projectapi.Model.Catagory;

import java.util.List;

public interface CatagoryService {
    List<Catagory> getAllCatagory();
    Catagory getCatagory(Long id);

    void deleteCatagory(Long id);

}
