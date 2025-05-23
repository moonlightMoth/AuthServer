package ru.moonlightmoth.authserver.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.moonlightmoth.authserver.model.entity.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Authorisation response")
public class AuthorisationResponse {

    @NotNull
    @Schema(description = "Must be 'OK' or 'FAIL'")
    Status status;

    @NotNull
    @Schema(description = "Login of user")
    String login;

    @NotNull
    @Schema(description = "Role of user")
    Role role;

    @Schema(description = "Message for clarity")
    String message;
}
