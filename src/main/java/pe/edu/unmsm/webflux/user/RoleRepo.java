package pe.edu.unmsm.webflux.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RoleRepo extends ReactiveMongoRepository<Role, String> {
}
