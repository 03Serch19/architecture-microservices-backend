package com.inmueble_microservice.microservice_1_inmueble.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Value("${service.security.secure-key-username}")
    private String SECURE_KEY_USERNAME;
    @Value("${service.security.secure-key-password}")
    private String SECURE_KEY_PASSWORD;
    @Value("${service.security.secure-key-username2}")
    private String SECURE_KEY_USERNAME2;
    @Value("${service.security.secure-key-password2}")
    private String SECURE_KEY_PASSWORD2;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
      authenticationManagerBuilder.inMemoryAuthentication()
              .withUser(SECURE_KEY_USERNAME)
              .password(new BCryptPasswordEncoder().encode(SECURE_KEY_PASSWORD))
              .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"))
              .and()
              .withUser(SECURE_KEY_USERNAME2)
              .password(new BCryptPasswordEncoder().encode(SECURE_KEY_PASSWORD2))
              .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEV"))
              .and()
              .passwordEncoder(new BCryptPasswordEncoder());

      http.authorizeHttpRequests(authorize ->
              authorize.requestMatchers("/**").hasAnyRole("ADMIN", "DEV")
                      .anyRequest().authenticated()

              ).httpBasic(withDefaults())
              .csrf(AbstractHttpConfigurer::disable)//si no estoy tranbajnado con una sesion propiamente dicho no nececito usar este exceptin handling, ya que no estoy con seciones sino que con basic tokens
              /*.exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendError(403, "Access Denied: You do not have permission to access this resource.");
              }))*/;

      return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }
}
