package com.example.projectapi.Service.User;

import com.example.projectapi.Model.Role;
import com.example.projectapi.Model.Users_;

import java.util.List;

public interface UserService {
    Users_ saveUser(Users_ user);

    List<Role> roleList();

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    Users_ getUser(String username);

    List<Users_> getUsers_();
}
