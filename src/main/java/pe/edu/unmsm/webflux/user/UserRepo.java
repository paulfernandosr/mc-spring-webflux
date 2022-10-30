package pe.edu.unmsm.webflux.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveMongoRepository<User, String> {

    Mono<User> findOneByUsername(String username);

}
