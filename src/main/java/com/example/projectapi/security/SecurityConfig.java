package com.example.projectapi.security;

import com.example.projectapi.CustomAuthorizationFilter;
import com.example.projectapi.AuthenticationFilter;
import com.example.projectapi.dtos.ErrorMessage;
import com.example.projectapi.dtos.ResponeMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:1212");  // TODO: lock down before deploying
        config.addAllowedHeader("*");
        config.addExposedHeader(HttpHeaders.AUTHORIZATION);
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationFilter customfilter = new AuthenticationFilter(authenticationManagerBean());
        customfilter.setFilterProcessesUrl("/api/login");

        http
                .cors()
                .and() //AND NEXT
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and() //AND NEXT
                .authorizeRequests().antMatchers("/api/login/**", "/api/v1/user/**", "/api/token/refreshToken/**", "/api/v1/image/**").permitAll()
                .and() //AND NEXT
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/user/**").hasAnyAuthority("ROLE_USER")
                .and() //AND NEXT
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyAuthority("ROLE_MANAGER")
                .and() //AND NEXT
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/image/**", "/api/v1/user/**", "/api/v1/product/**").hasAnyAuthority("ROLE_MANAGER")
                .and() //AND NEXT
                .authorizeRequests().anyRequest().authenticated()
                .and() //AND NEXT
                .addFilter(customfilter)
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and() //AND NEXT
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
////        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refreshToken/**").permitAll();
//        //User Routers
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/user/**", "/api/v1/image/files/get/**").hasAnyAuthority("ROLE_USER");
//        //Admin Routers
//        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/image/**", "/api/v1/user/**", "/api/v1/product/**").hasAnyAuthority("ROLE_MANAGER");
//        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyAuthority("ROLE_MANAGER");
//        http.cors().and();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(customfilter);
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            ServletOutputStream out = response.getOutputStream();

            ErrorMessage errorMessage = new ErrorMessage(
                    ex.getMessage(),
                    "FORBIDDEN",
                    HttpServletResponse.SC_FORBIDDEN
            );
            ResponeMessage message = new ResponeMessage(
                    errorMessage,
                    null
            );
            new ObjectMapper().writeValue(out, message);
            out.flush();
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:1212"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
