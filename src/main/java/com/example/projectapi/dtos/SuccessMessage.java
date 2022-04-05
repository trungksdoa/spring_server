package com.example.projectapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessMessage<T,V> {
    private T data;
    private List<T> data_list;
    private Map<T,V> secrect;
    private String successMessage;
    private int successStatus;
}
