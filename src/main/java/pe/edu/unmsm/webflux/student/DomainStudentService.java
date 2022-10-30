package pe.edu.unmsm.webflux.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DomainStudentService implements StudentService {

    private final StudentRepo repo;

    @Override
    public Flux<Student> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Student> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public Mono<Student> save(Student student) {
        return repo.save(student);
    }

    @Override
    public Mono<Student> update(Student student) {
        return repo.save(student);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repo.deleteById(id);
    }

}
