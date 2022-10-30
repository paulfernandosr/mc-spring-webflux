package pe.edu.unmsm.webflux.student;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StudentRepo extends ReactiveMongoRepository<Student, String> {
}
