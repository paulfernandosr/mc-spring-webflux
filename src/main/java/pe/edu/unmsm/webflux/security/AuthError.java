package pe.edu.unmsm.webflux.security;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder(toBuilder = true)
public class AuthError {

    private final String message;
    private final Date timestamp;

}
