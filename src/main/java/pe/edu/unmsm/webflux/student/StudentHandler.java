package pe.edu.unmsm.webflux.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.edu.unmsm.webflux.course.Course;
import pe.edu.unmsm.webflux.error.RequestValidator;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Comparator;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class StudentHandler {

    private final StudentService service;
    private final RequestValidator validator;

    public Mono<ServerResponse> findAll(ServerRequest req) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Course.class);
    }

    public Mono<ServerResponse> findAllOrderByAgeAsc(ServerRequest req) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll().sort(Comparator.comparingInt(Student::getAge)), Course.class);
    }

    public Mono<ServerResponse> findAllOrderByAgeDesc(ServerRequest req) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll().sort(Comparator.comparingInt(Student::getAge).reversed()), Course.class);
    }

    public Mono<ServerResponse> findById(ServerRequest req) {
        String id = req.pathVariable("id");
        return service.findById(id)
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(c))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest req) {
        Mono<Student> monoStudent = req.bodyToMono(Student.class);

        return monoStudent
                .flatMap(validator::validate)
                .flatMap(service::save)
                .flatMap(c -> ServerResponse.created(URI.create(req.uri().toString().concat("/").concat(c.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(c))
                );

    }

    public Mono<ServerResponse> update(ServerRequest req) {
        String id = req.pathVariable("id");

        Mono<Student> monoStudent = req.bodyToMono(Student.class);
        Mono<Student> monoDB = service.findById(id);

        return monoDB
                .zipWith(monoStudent, (db, course) -> {
                    db.setFirstName(course.getFirstName());
                    db.setLastName(course.getLastName());
                    db.setDni(course.getDni());
                    db.setAge(course.getAge());
                    return db;
                })
                .flatMap(validator::validate)
                .flatMap(service::update)
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(c))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        String id = req.pathVariable("id");

        return service.findById(id)
                .flatMap(c -> service.deleteById(c.getId())
                        .then(ServerResponse.noContent().build())
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
