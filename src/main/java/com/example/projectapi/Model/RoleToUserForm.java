package com.example.projectapi.Model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleToUserForm {
    private String username;
    private String roleName;
}
