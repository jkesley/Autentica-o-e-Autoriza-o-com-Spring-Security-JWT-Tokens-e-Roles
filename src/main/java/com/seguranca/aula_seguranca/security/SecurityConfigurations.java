package com.seguranca.aula_seguranca.security;


import com.seguranca.aula_seguranca.infrastructure.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurations {

    private final UserRepository userRepository;

    public SecurityConfigurations(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // Bean para criptografia de senha
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuração de autorização dos endpoints
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para teste no Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()       // libera registro/login
                        .requestMatchers("/cidades/**").hasRole("ADMIN") // apenas ADMIN pode cadastrar cidades
                        .anyRequest().authenticated()                  // resto exige autenticação
                )
                .httpBasic(); // Autenticação básica (teste)
        return http.build();
    }

    // Configuração de autenticação
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(username -> {
                    var user = userRepository.findByUsername(username);
                    if (user == null) {
                        throw new RuntimeException("Usuário não encontrado");
                    }
                    return org.springframework.security.core.userdetails.User
                            .withUsername(user.getUsername())
                            .password(user.getPassword())
                            .roles(user.getRole().name()) // Converte enum para role
                            .build();
                })
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}


