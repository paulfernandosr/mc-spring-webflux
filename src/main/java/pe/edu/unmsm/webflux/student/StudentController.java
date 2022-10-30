package pe.edu.unmsm.webflux.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Comparator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Student>>> findAll() {
        Flux<Student> fx = service.findAll();
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fx));
    }

    @GetMapping("/orderByAgeAsc")
    public Mono<ResponseEntity<Flux<Student>>> findAllOrderByAgeAsc() {
        Flux<Student> fx = service.findAll().sort(Comparator.comparingInt(Student::getAge));
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fx));
    }

    @GetMapping("/orderByAgeDesc")
    public Mono<ResponseEntity<Flux<Student>>> findAllOrderByAgeDesc() {
        Flux<Student> fx = service.findAll().sort(Comparator.comparingInt(Student::getAge).reversed());
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fx));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Student>> findById(@PathVariable("id") String id) {
        return service.findById(id)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Student>> save(@Valid @RequestBody Student student, final ServerHttpRequest req) {
        return service.save(student)
                .map(e -> ResponseEntity
                        .created(URI.create(req.getURI().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Student>> update(@PathVariable("id") String id, @RequestBody @Valid Student student) {
        Mono<Student> monoBody = Mono.just(student);
        Mono<Student> monoBD = service.findById(id);

        return monoBD.zipWith(monoBody, (bd, c) -> bd.toBuilder()
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dni(c.getDni())
                        .age(c.getAge())
                        .build())
                .flatMap(service::update)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return service.findById(id)
                .flatMap(e -> service.deleteById(e.getId())
                        .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}
