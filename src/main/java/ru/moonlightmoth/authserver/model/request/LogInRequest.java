package ru.moonlightmoth.authserver.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Log in request")
public class LogInRequest {

    @NotNull
    @Schema(description = "Login")
    String login;

    @NotNull
    @Schema(description = "Password")
    String password;
}
