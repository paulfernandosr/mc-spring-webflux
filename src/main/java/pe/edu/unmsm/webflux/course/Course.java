package pe.edu.unmsm.webflux.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@Document(collection = "courses")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course {

    @Id
    private String id;
    private String name;
    private String acronym;
    private Boolean status;

}
