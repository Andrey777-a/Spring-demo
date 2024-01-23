package org.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.example.spring.model.entity.Role.ADMIN;
import static org.example.spring.model.entity.Role.USER;

@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/apt/**")
                .authorizeHttpRequests(authorize -> authorize
                                .anyRequest().hasAuthority(ADMIN.getAuthority())
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {

        http
//                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login","/users/registration", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET,"/users", "/users/{id}").hasAuthority(USER.getAuthority())
//                        .requestMatchers("/users/**").hasAuthority(ADMIN.getAuthority())
                        .anyRequest().hasAuthority(ADMIN.getAuthority())
//                        .anyRequest().authenticated()
                )
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .httpBasic(Customizer.withDefaults());
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/users"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}

