package pe.edu.unmsm.webflux.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class AuthRequest {

    private final String username;
    private final String password;

}
