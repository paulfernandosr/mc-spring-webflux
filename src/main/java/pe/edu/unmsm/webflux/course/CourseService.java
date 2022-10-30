package pe.edu.unmsm.webflux.course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {

    Flux<Course> findAll();

    Mono<Course> findById(String id);

    Mono<Course> save(Course course);

    Mono<Course> update(Course course);

    Mono<Void> deleteById(String id);

}
