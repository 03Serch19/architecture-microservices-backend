package com.api_gateway_microservice.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username", nullable = false, unique = true, length = 100)
    private String username;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="nombre", nullable = false)
    private String nombre;
    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;
    @Transient
    private String token;
}
