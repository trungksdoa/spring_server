package com.example.projectapi;

import com.example.projectapi.Model.Catagory;
import com.example.projectapi.Model.Product;
import com.example.projectapi.Model.Role;
import com.example.projectapi.Model.Users;
import com.example.projectapi.Service.Product.ProductService;
import com.example.projectapi.Service.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class ProjectApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApiApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("**").allowedOrigins("http://localhost:1212");
            }
        };
    }

    @Bean
    CommandLineRunner run(ProductService productService, UserService userService) {
        return args -> {
//            List<User> userList = userService.getUsers();
//            Pageable pageable = PageRequest.of(0,2);
//            List<Product> products = productService.getAllProductPage(pageable).collect(Collectors.toList());
//            products.stream().forEach(data -> log.info(data.getName()));
//            log.info(String.valueOf(products.size()));
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_MANAGER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
////
////
//            userService.saveUser(new Users(null, "John Travolta", "admin", "1234", new ArrayList<>()));
//            userService.saveUser(new Users(null, "John David", "David", "1234", new ArrayList<>()));
//            userService.saveUser(new Users(null, "John Json", "Json", "1234", new ArrayList<>()));
//            userService.saveUser(new Users(null, "John Camoniac", "Camoniac", "1234", new ArrayList<>()));
////
//            userService.addRoleToUser("admin", "ROLE_MANAGER");
//            userService.addRoleToUser("admin", "ROLE_USER");
//            userService.addRoleToUser("David", "ROLE_MANAGER");
//            , ProductService productService, CatagoryService catagoryService

//            //Nước
//            productService.saveCatagory(new Catagory(null, "Nước suối",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Nước ngọt lon",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Nước ngọt chai",Long.parseLong(0+"")));
//
//            //Kem
//            productService.saveCatagory(new Catagory(null, "Kem ốc quế",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem ly",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem cây",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem khác",Long.parseLong(0+"")));
//
////            Bánh
//            productService.saveCatagory(new Catagory(null, "Bánh",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem ly",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem cây",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem khác",Long.parseLong(0+"")));
//
//            //Kẹo
//            productService.saveCatagory(new Catagory(null, "Kẹo",Long.parseLong(0+"")));
//
//            //Gia vị
//            productService.saveCatagory(new Catagory(null, "Gia vị",Long.parseLong(0+"")));
//
//            //Cafe
//            productService.saveCatagory(new Catagory(null, "Cà phê",Long.parseLong(0+"")));
//
//            //Sinh hoạt
//            productService.saveCatagory(new Catagory(null, "Dầu gội chai",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Sữa tắm chai",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Dầu gội gói",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Sữa tắm gói",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem đánh răng",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Dao cạo râu",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Bàn chải đánh răng",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Thông Cầu Cống",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Gội và Xả",Long.parseLong(0+"")));
        };
    }
}
