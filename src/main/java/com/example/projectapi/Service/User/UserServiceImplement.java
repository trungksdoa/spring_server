package com.example.projectapi.Service.User;

import com.example.projectapi.Model.Role;
import com.example.projectapi.Model.Users_;
import com.example.projectapi.Repo.RoleRepo;
import com.example.projectapi.Repo.UserRepo;
import com.example.projectapi.handelError.CustomAlreadyExistsException;
import com.example.projectapi.handelError.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplement implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users_ user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found : {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public Users_ saveUser(Users_ user) {
        log.info("Save user {} to database", user.getUsername());
        if (userRepo.findByUsername(user.getUsername()).equals(user))
            throw new CustomAlreadyExistsException("User already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public List<Role> roleList() {
        return roleRepo.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Save role {} to database", role.getName());
        if (roleRepo.findByName(role.getName()).equals(role))
            throw new CustomAlreadyExistsException("Role already exists");
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Add Role {} to User {}", roleName, username);
        Users_ requestUser = new Users_();
        Role requestRole = new Role();
        requestUser.setUsername(username);
        requestRole.setName(roleName);
        if (!userRepo.findByUsername(username).equals(requestUser))
            throw new CustomNotFoundException("User not found");
        if (!roleRepo.findByName(roleName).equals(requestRole))
            throw new CustomNotFoundException("Role not found");
        Users_ user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public Users_ getUser(String username) {
        log.info("find User {} in database", username);
        Users_ requestUser = new Users_();
        requestUser.setUsername(username);
        if (!userRepo.findByUsername(username).equals(requestUser))
            throw new CustomNotFoundException("User not found");
        return userRepo.findByUsername(username);
    }

    @Override
    public List<Users_> getUsers_() {
        return userRepo.findAll().stream().collect(Collectors.toList());
    }


}
