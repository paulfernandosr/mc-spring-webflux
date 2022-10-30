package pe.edu.unmsm.webflux.security;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder(toBuilder = true)
public class AuthResponse {

    private final String token;
    private final Date expiration;

}
