package pe.edu.unmsm.webflux.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder = true)
@Document(collection = "students")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Size(min = 8, max = 8)
    private String dni;

    @Min(3)
    private Integer age;

}
