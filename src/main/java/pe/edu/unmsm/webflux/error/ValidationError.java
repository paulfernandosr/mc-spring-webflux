package pe.edu.unmsm.webflux.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ValidationError {

    private final String field;
    private final String message;

}
