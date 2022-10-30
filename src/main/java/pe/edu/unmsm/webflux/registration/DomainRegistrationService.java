package pe.edu.unmsm.webflux.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DomainRegistrationService implements RegistrationService{

    private final RegistrationRepo repo;

    @Override
    public Flux<Registration> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Registration> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public Mono<Registration> save(Registration registration) {
        return repo.save(registration);
    }

    @Override
    public Mono<Registration> update(Registration registration) {
        return repo.save(registration);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repo.deleteById(id);
    }

}
