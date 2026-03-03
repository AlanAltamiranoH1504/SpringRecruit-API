package com.example.springboot_4_initial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {
                })
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        // ! RUTAS PUBLICAS
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/vacancy/list").permitAll()
                        .requestMatchers("/vacancy/find/**").permitAll()
                        .requestMatchers("/vacancy/category/**").permitAll()
                        .requestMatchers("/vacancy/name/**").permitAll()
                        .requestMatchers("/category/list").permitAll()
                        .requestMatchers("/profiles/list").permitAll()

                        // ! RUTAS PARA RECLUTADORES
                        .requestMatchers("/vacancy/save").hasAnyRole("RECLURADOR", "ADMINISTRADOR")
                        .requestMatchers("/vacancy/update/**").hasAnyRole("RECLURADOR", "ADMINISTRADOR")
                        .requestMatchers("/vacancy/delete/**").hasAnyRole("RECLURADOR", "ADMINISTRADOR")
                        .requestMatchers("/vacancy/save_img_vacancy/**").hasAnyRole("RECLURADOR", "ADMINISTRADOR")
                        .requestMatchers("/candidate/find_candidate/**").hasAnyRole("RECLURADOR", "ADMINISTRADOR")


                        // ! RUTAS PARA CANDIDATOS AUTENTICADOS
                        .requestMatchers("/users/update_user/**", "/users/update/img_profile/**").hasAnyRole("RECLUTADOR", "CANDIDATO")
                        .requestMatchers("/users/perfil").authenticated()
                        .requestMatchers("/candidate/update_candidate").hasAnyRole("CANDIDATO")

                        // ! RUTAS PARA SUPER ADMIN
                        .requestMatchers(
                                "/users/list",
                                "/users/find/**",
                                "/users/add_profile/**",
                                "/users/remove_profiles/**",

                                "/candidate/list",
                                "/candidate/destroy_candidate/**",
                                "/candidate/delete_candidate/**",

                                "/category/save_categories",
                                "/category/save",
                                "/category/search/**",
                                "/category/update/**",
                                "/category/delete/**",
                                "/category/delete_all",
                                "/category/save_categories",

                                "/profiles/save",
                                "/profiles/find/**",
                                "/profiles/delete/**"
                        ).hasRole("ADMINISTRADOR")
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
