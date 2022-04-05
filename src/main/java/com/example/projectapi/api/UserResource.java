package com.example.projectapi.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.projectapi.Model.Role;
import com.example.projectapi.Model.Users;
import com.example.projectapi.Service.User.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;


@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(userService.roleList());
    }

    @PostMapping
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PutMapping
    public ResponseEntity<Users> UpdateUser(@RequestBody Users user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("role/add-role-to-user")
    public ResponseEntity<?> addtouser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("token/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse respone) throws IOException {
        String authorization = request.getHeader(AUTHORIZATION);
        if (authorization != null && authorization.startsWith("Bearer ")) {
            try {
                String token = authorization.substring("Bearer ".length());
                Algorithm algorithms = Algorithm.HMAC256("secret_key".getBytes(StandardCharsets.UTF_8));
                JWTVerifier jwt = JWT.require(algorithms).build();
                DecodedJWT decoded_jwt = jwt.verify(token);
                String username = decoded_jwt.getSubject();

                Users user = userService.getUser(username);

                String accessToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithms);
                String refreshToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .sign(algorithms);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("ast",accessToken);
                tokens.put("rft",refreshToken);
                respone.setContentType(MediaType.APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(respone.getOutputStream(),tokens);
            } catch (Exception e) {
                respone.setHeader("error",e.getMessage());
                respone.setStatus(FORBIDDEN.value());

                Map<String,String> error_token = new HashMap<>();
                error_token.put("error",e.getMessage());
                respone.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(respone.getOutputStream(),error_token);
            }
        }else{
            throw new RuntimeException("Refresh token is mission");
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class RoleToUserForm {
        private String username;
        private String roleName;
    }
}
