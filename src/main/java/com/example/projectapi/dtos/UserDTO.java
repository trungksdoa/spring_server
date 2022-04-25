package com.example.projectapi.dtos;

import com.example.projectapi.Model.Users_;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String username;

    public UserDTO(Users_ user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }
}
