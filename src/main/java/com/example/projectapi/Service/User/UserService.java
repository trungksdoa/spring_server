package com.example.projectapi.Service.User;

import com.example.projectapi.Model.Role;
import com.example.projectapi.Model.Users;
import com.example.projectapi.Service.abtract.UserAbstract;

import java.util.List;

public interface UserService {
    Users saveUser(Users user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    Users getUser(String username);

    List<Users> getUsers();
}
