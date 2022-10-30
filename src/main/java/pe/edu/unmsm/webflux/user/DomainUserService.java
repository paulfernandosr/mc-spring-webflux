package pe.edu.unmsm.webflux.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.webflux.security.SecurityUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainUserService implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Mono<User> saveHash(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Mono<SecurityUser> searchByUsername(String username) {
        Mono<User> monoUser = userRepo.findOneByUsername(username);

        List<String> roles = new ArrayList<>();

        return monoUser

                .flatMap(u -> Flux.fromIterable(u.getRoles())
                        .flatMap(rol -> roleRepo.findById(rol.getId())
                                .map(r -> {
                                    roles.add(r.getName());
                                    return r;
                                })
                        ).collectList().flatMap(list -> {
                            u.setRoles(list);
                            return Mono.just(u);
                        })
                )
                .flatMap(u -> Mono.just(SecurityUser.builder()
                        .username(u.getUsername())
                        .password(u.getPassword())
                        .enabled(u.getStatus())
                        .roles(roles)
                        .build()));
    }

}
