package com.example.springboot_4_initial.security;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

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
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .authorizeHttpRequests(request -> request
//                                .requestMatchers(HttpMethod.OPTIONS).permitAll()

                                // ! RUTAS PUBLICAS
                                .requestMatchers(
                                        "/auth/**",
                                        "/vacancy/list",
                                        "/vacancy/find/**",
                                        "/vacancy/category/**",
                                        "/vacancy/name/**",
                                        "/vacancy/search",
                                        "/vacancy/save_vacancies",
                                        "/category/list",
                                        "/profiles/list"
                                ).permitAll()

                                // ! RUTAS PARA RECLUTADORES
                                .requestMatchers(
                                        "/vacancy/save_vacancy",
                                        "/vacancy/update/**",
                                        "/vacancy/delete/**",
                                        "/vacancy/save_img_vacancy/**",
                                        "/candidate/find_candidate/**",
                                        "/recruiter/update_recruiter/**",
                                        "/recruiter/show_recruiter",
                                        "/candidate/find_candidate/**",
                                        "/industrial_sector/list",
                                        "/industrial_sector/find/**",
                                        "/contract_type/list",
                                        "/contract_type/find/**",
                                        "/progress_status/list",
                                        "/progress_status/find/**",
                                        "/recruiter/recruiter_in_session",
                                        "/vacancy/list/by_recruiter"
                                ).hasAnyRole("RECLUTADOR", "ADMINISTRADOR")

                                // ! RUTAS PARA CANDIDATOS AUTENTICADOS
                                .requestMatchers("/users/update_user/**", "/users/update/img_profile/**").hasAnyRole("RECLUTADOR", "CANDIDATO")
                                .requestMatchers("/users/user_in_session").authenticated()
                                .requestMatchers("/candidate/destroy_candidate/**").hasAnyRole("CANDIDATO", "ADMINISTRADOR")
                                .requestMatchers(
                                        "/candidate/update_candidate",
                                        "/candidate/show_candidate",
                                        "/candidate/update_img_profile/**",
                                        "/candidate/update_candidate",
                                        "/application/save",
                                        "/application/list/by_candidate/**",
                                        "/application/find/**",
                                        "/application/update",
                                        "/application/delete/**",
                                        "/application/destroy/**"
                                ).hasAnyRole("CANDIDATO", "RECLUTADOR")

                                // ! RUTAS PARA SUPER ADMIN
                                .requestMatchers(
                                        "/admin/**",
//                                "/users/list",
//                                "/users/find/**",
                                        "/users/add_profile/**",
                                        "/users/remove_profiles/**",

                                        "/candidate/list",
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
                                        "/profiles/delete/**",

                                        "/recruiter/list",
                                        "/recruiter/find_recruiter/**",
                                        "/recruiter/delete_recruiter/**",
                                        "/recruiter/destroy_recruiter/**",

                                        "/industrial_sector/save",
                                        "/industrial_sector/delete/**",
                                        "/industrial_sector/destroy/**",
                                        "/industrial_sector/**",

                                        "/contract_type/save",
                                        "/contract_type/delete/**",
                                        "/contract_type/destroy/**",
                                        "/contract_type/update/**",

                                        "/progress_status/save",
                                        "/progress_status/update/**",
                                        "/progress_status/delete/**",
                                        "/progress_status/destroy/**"
                                ).hasRole("ADMINISTRADOR")
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new org.springframework.web.filter.CorsFilter(source);
    }
}
