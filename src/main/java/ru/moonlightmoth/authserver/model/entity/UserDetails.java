package ru.moonlightmoth.authserver.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_details")
@Schema(description = "User details from database")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Id")
    @NotNull
    private Long id;

    @Size(min = 1, max = 30, message = "Invalid name length, must be from 1 to 30")
    @Schema(description = "Name")
    @NotNull
    private String name;

    @Size(min = 1, max = 30, message = "Invalid surname length, must be from 1 to 30")
    @Schema(description = "Surname")
    @NotNull
    private String surname;

    @NotNull
    @Schema(description = "Role. Must be ADMIN or USER")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Size(min = 1, max = 30, message = "Invalid login length, must be from 1 to 30")
    @Schema(description = "Login")
    @NotNull
    private String login;

    @NotNull
    @Schema(description = "SHA-256 of password")
    private String passwordHash;
}
