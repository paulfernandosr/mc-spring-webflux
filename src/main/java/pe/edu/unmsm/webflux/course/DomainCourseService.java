package pe.edu.unmsm.webflux.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DomainCourseService implements CourseService {

    private final CourseRepo repo;

    @Override
    public Flux<Course> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Course> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public Mono<Course> save(Course course) {
        return repo.save(course);
    }

    @Override
    public Mono<Course> update(Course course) {
        return repo.save(course);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repo.deleteById(id);
    }

}
