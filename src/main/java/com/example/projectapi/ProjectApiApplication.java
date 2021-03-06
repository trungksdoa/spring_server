package com.example.projectapi;

import com.example.projectapi.Model.Catagory;
import com.example.projectapi.Model.Product;
import com.example.projectapi.Model.Role;
import com.example.projectapi.Model.Users_;
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

//            //N?????c
//            productService.saveCatagory(new Catagory(null, "N?????c su???i",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "N?????c ng???t lon",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "N?????c ng???t chai",Long.parseLong(0+"")));
//
//            //Kem
//            productService.saveCatagory(new Catagory(null, "Kem ???c qu???",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem ly",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem c??y",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem kh??c",Long.parseLong(0+"")));
//
////            B??nh
//            productService.saveCatagory(new Catagory(null, "B??nh",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem ly",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem c??y",Long.parseLong(0+"")));
//            productService.saveCatagory(new Catagory(null, "Kem kh??c",Long.parseLong(0+"")));
//
//            //K???o
//            productService.saveCatagory(new Catagory(null, "K???o",Long.parseLong(0+"")));
//
//            //Gia v???
//            productService.saveCatagory(new Catagory(null, "Gia v???",Long.parseLong(0+"")));
//
//            //Cafe
//            productService.saveCatagory(new Catagory(null, "C?? ph??",Long.parseLong(0+"")));
//
//            //Sinh ho???t
//            productService.saveCatagory(new Catagory(null, "D???u g???i chai",Long.parseLong(0+"")));
////            productService.saveCatagory(new Catagory(null, "S???a t???m chai",Long.parseLong(0+"")));
////            productService.saveCatagory(new Catagory(null, "D???u g???i g??i",Long.parseLong(0+"")));
////            productService.saveCatagory(new Catagory(null, "S???a t???m g??i",Long.parseLong(0+"")));
////            productService.saveCatagory(new Catagory(null, "Kem ????nh r??ng",Long.parseLong(0+"")));
////            productService.saveCatagory(new Catagory(null, "Dao c???o r??u",Long.parseLong(0+"")));
////            productService.saveCatagory(new Catagory(null, "B??n ch???i ????nh r??ng",Long.parseLong(0+"")));
////            productService.saveCatagory(new Catagory(null, "Th??ng C???u C???ng",Long.parseLong(0+"")));
////            productService.saveCatagory(new Catagory(null, "G???i v?? X???",Long.parseLong(0+"")));
        };
    }
}
