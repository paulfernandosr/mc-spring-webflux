package pe.edu.unmsm.webflux.course;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CourseRepo extends ReactiveMongoRepository<Course, String> {
}
