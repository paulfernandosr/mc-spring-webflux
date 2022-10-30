package pe.edu.unmsm.webflux.user;

import pe.edu.unmsm.webflux.security.SecurityUser;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> saveHash(User user);
    Mono<SecurityUser> searchByUsername(String username);

}
