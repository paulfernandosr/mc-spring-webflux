package pe.edu.unmsm.webflux.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.unmsm.webflux.course.Course;
import pe.edu.unmsm.webflux.student.Student;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@Document(collection = "registrations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Registration {

    @Id
    private String id;

    private LocalDateTime date;
    private Student student;
    private List<Course> courses;
    private Boolean status;

}
