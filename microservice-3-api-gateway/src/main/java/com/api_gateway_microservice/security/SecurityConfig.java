package com.api_gateway_microservice.security;

import com.api_gateway_microservice.model.Role;
import com.api_gateway_microservice.security.jwt.JwtAuthorizationFilter;
import com.api_gateway_microservice.security.jwt.JwtProvider;
import com.api_gateway_microservice.security.jwt.JwtProviderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
   //@Autowired//autoinyectamos ya el bean que se ceo en esta misma calse para poder usarlo como paremtro en el addfilterbefore y donde deba usarse
    //private final JwtAuthorizationFilter jwtAuthorizationFilter;//la verdad no, esta alo lo que ije cereo, es que sucede que apsar tipo una refrecnia cirular entocnes por eso da erorees si hacemmos una inyteccion de algo que etaos autorefernciadno y tabnijense ana a ejecutar
   //@Autowired//UTIL SI QUEREMOS usar inyeccion por cosntructoren  el jwtfilteer
    //private final JwtProvider jwtProvider;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        AuthenticationManager authenticationManager = auth.build();

        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/api/v1/authentication/sign-in",
                                        "/api/v1/authentication/sign-up").permitAll()
                                .requestMatchers(HttpMethod.GET,"/gateway/inmueble").permitAll()
                                .requestMatchers("/gateway/inmueble/**").hasRole(Role.ADMIN.name())
                              //  .requestMatchers("/gateway/compras/**").hasRole(Role.ADMIN.name())//para compras no es necesario ya que esto es una accion que caulquier yusauri autentiucadoi opuede realizar, pyuede crear conporar y obtener sus ukltiumas compraas
                                .anyRequest().authenticated()
                )
                .authenticationManager(authenticationManager)
              //  .httpBasic(withDefaults())
               //.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults());

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

   /* @Bean//este no se no porque esta
    public JwtAuthorizationFilter jwtAuthorizationFilter(JwtProvider jwtProvider) {
        return new JwtAuthorizationFilter(jwtProvider);
    }*/



    /*@Bean//este usariamos usi usarammmso intyeccion por construtor en jwtfilter
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtProvider);
    }*/
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }
}
