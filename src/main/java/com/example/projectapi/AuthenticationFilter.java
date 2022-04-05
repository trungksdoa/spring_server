package com.example.projectapi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.projectapi.dtos.ResponeMessage;
import com.example.projectapi.dtos.SuccessMessage;
import com.example.projectapi.handelError.CustomUserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.ACCEPTED;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private int count = 0;
    private boolean block_login = false;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestData = null;
        try {
            requestData = request.getReader().lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(requestData);
        log.info(obj.toString());
        String username_ = obj.getString("username");
        int password_ = obj.getInt("password");

        log.info("--------------------------------");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username_, password_);
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User user = (User) auth.getPrincipal();
        Algorithm algorithms = Algorithm.HMAC256("secret_key".getBytes(StandardCharsets.ISO_8859_1));
        String accessToken = "";
        String refreshToken = "";
        if (user.getUsername().equals("admin")) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, +30);
            Date date = cal.getTime();
            accessToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(date)
                    .withIssuer(request.getRequestURI().toString())
                    .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithms);
            refreshToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(date)
                    .withIssuer(request.getRequestURI().toString())
                    .sign(algorithms);
            log.info("Admin login success");
        } else {
            accessToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer(request.getRequestURI().toString())
                    .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithms);
            refreshToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer(request.getRequestURI().toString())
                    .sign(algorithms);
        }


        response.setHeader("ast", accessToken);
        response.setHeader("rft", refreshToken);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("ast", accessToken);
        tokens.put("rft", refreshToken);
        tokens.put("last_activity", "Đăng nhập ngày " + new Date());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        SuccessMessage<String, String> success = new SuccessMessage<String, String>(
                "",
                new ArrayList<>(),
                tokens,
                "Đăng nhập thành công",
                ACCEPTED.value()
        );
        ResponeMessage message = new ResponeMessage(
                null,
                success
        );
        new ObjectMapper().writeValue(response.getOutputStream(), message);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        throw new CustomUserNotFoundException(failed.getMessage());
    }

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        super.setFilterProcessesUrl(filterProcessesUrl);
    }


}
