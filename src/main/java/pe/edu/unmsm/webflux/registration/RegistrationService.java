package pe.edu.unmsm.webflux.registration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RegistrationService {

    Flux<Registration> findAll();

    Mono<Registration> findById(String id);

    Mono<Registration> save(Registration registration);

    Mono<Registration> update(Registration registration);

    Mono<Void> deleteById(String id);

}
