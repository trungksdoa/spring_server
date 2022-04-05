package com.example.projectapi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.projectapi.dtos.ErrorMessage;
import com.example.projectapi.dtos.ResponeMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS, PUT, DELETE");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me,Authorization");
        if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refreshToken")) {
            filterChain.doFilter(request, response);
        } else {
            String authorization = request.getHeader(AUTHORIZATION);
            if (authorization != null && authorization.startsWith("Bearer ")) {
                try {
                    String token = authorization.substring("Bearer ".length());
                    Algorithm algorithms = Algorithm.HMAC256("secret_key".getBytes(StandardCharsets.ISO_8859_1));
                    JWTVerifier jwt = JWT.require(algorithms).build();
                    DecodedJWT decoded_jwt = jwt.verify(token);
                    String username = decoded_jwt.getSubject();
                    String[] roles = decoded_jwt.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(data -> {
                        authorities.add(new SimpleGrantedAuthority(data));
                    });
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                    log.info("Hello {}", username);
                } catch (Exception e) {
                    String exeptionName = e.getClass().getSimpleName();
                    response.setHeader("error", e.getMessage());
                    response.setStatus(500);
                    log.error("Error in " + CustomAuthorizationFilter.class.getName());
                    ErrorMessage errorMessage = new ErrorMessage(
                            e.getMessage(),
                            exeptionName,
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR
                    );
                    ResponeMessage message = new ResponeMessage(
                            errorMessage, null
                    );

                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), message);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
