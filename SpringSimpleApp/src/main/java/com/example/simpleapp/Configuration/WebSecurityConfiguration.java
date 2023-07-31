package com.example.simpleapp.Configuration;

import com.example.simpleapp.fiter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final JwtRequestFilter filter;

    public WebSecurityConfiguration(JwtRequestFilter filter) {
        this.filter = filter;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http.csrf(crsf -> crsf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/register").permitAll();
                    auth.requestMatchers("/authentication").permitAll();
                    auth.anyRequest().authenticated();
                });
         http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class));

         return http.build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
