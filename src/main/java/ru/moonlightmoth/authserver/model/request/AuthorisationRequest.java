package ru.moonlightmoth.authserver.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.moonlightmoth.authserver.model.JwtToken;
import ru.moonlightmoth.authserver.model.entity.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Auth request with token provided")
public class AuthorisationRequest {
    @NotNull
    @Schema(description = "JWT token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cC.I6MTYyMjUwNj")
    private JwtToken token;

    @Schema(description = "Message for clarity")
    private String message;
}
