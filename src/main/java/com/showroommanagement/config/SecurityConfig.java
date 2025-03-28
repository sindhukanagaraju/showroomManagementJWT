package com.showroommanagement.config;

import com.showroommanagement.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("api/v1/user/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/v1/user/**").hasAuthority(UserType.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "api/v1/user/**").hasAuthority(UserType.ADMIN.name())
                        .requestMatchers("api/v1/user/customer").hasAnyAuthority(UserType.CUSTOMER.name())
                        .requestMatchers("api/v1/user/employee").hasAnyAuthority(UserType.ADMIN.name(), UserType.EMPLOYEE.name())
                        .requestMatchers("api/v1/user/admin").hasAnyAuthority(UserType.ADMIN.name())
                        .requestMatchers("api/v1/user/signIn").permitAll()
                        .requestMatchers("api/v1/department/**").hasAnyAuthority(UserType.ADMIN.name())
                        .requestMatchers("api/v1/branch/**").permitAll()
                        .requestMatchers("api/v1/brand/**").permitAll()
                        .requestMatchers("api/v1/admin/**").hasAuthority(UserType.ADMIN.name())
                        .requestMatchers("api/v1/employee/**").hasAnyAuthority(UserType.ADMIN.name(), UserType.EMPLOYEE.name())
                        .requestMatchers("api/v1/customer/**").hasAnyAuthority(UserType.EMPLOYEE.name(), UserType.ADMIN.name(), UserType.CUSTOMER.name())
                        .requestMatchers(HttpMethod.GET, "api/v1/product/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/product/{id}").hasAuthority(UserType.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "api/v1/product/{id}").hasAuthority(UserType.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "api/v1/product/{id}").hasAuthority(UserType.ADMIN.name())
                        .requestMatchers("api/v1/showroom/**").hasAnyAuthority(UserType.ADMIN.name(), UserType.EMPLOYEE.name(), UserType.CUSTOMER.name())
                        .requestMatchers("api/v1/sale/**").hasAnyAuthority(UserType.ADMIN.name(), UserType.EMPLOYEE.name(), UserType.CUSTOMER.name())
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}