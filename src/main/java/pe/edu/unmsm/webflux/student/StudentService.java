package pe.edu.unmsm.webflux.student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Flux<Student> findAll();

    Mono<Student> findById(String id);

    Mono<Student> save(Student student);

    Mono<Student> update(Student student);

    Mono<Void> deleteById(String id);

}
