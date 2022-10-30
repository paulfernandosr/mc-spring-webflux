package pe.edu.unmsm.webflux.registration;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RegistrationRepo extends ReactiveMongoRepository<Registration, String> {
}
