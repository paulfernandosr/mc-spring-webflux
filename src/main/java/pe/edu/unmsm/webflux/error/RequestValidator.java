package pe.edu.unmsm.webflux.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final Validator validator;

    public <T> Mono<T> validate(T t) {
        if (t == null) return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Set<ConstraintViolation<T>> constraints = validator.validate(t);

        String message = constraints.stream()
                .map(cons -> String.format("%s value '%s' %s", cons.getPropertyPath(),
                        cons.getInvalidValue(), cons.getMessage()))
                .collect(Collectors.joining());

        if (constraints.isEmpty()) return Mono.just(t);

        return Mono.error(new MessageException(message));
    }

}
