package com.example.projectapi.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponeMessage {
    private ErrorMessage error;
    private SuccessMessage success;
}
