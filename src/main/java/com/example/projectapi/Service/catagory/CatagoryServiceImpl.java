package com.example.projectapi.Service.catagory;

import com.example.projectapi.Model.Catagory;
import com.example.projectapi.Repo.CatagoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CatagoryServiceImpl implements CatagoryService {
    private final CatagoryRepo catagoryRepo;

    @Override
    public List<Catagory> getAllCatagory() {
        return catagoryRepo.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Catagory getCatagory(Long id) {
        if(catagoryRepo.getById(id) == null){
            throw new IllegalStateException("No catagory have been found");
        }else{
            return catagoryRepo.getById(id);
        }
    }
}
