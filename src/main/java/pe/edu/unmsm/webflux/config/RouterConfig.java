package pe.edu.unmsm.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.edu.unmsm.webflux.course.CourseHandler;
import pe.edu.unmsm.webflux.registration.RegistrationHandler;
import pe.edu.unmsm.webflux.student.StudentHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> courseRoutes(CourseHandler handler) {
        return route(GET("/v2/courses"), handler::findAll)
                .andRoute(GET("/v2/courses/{id}"), handler::findById)
                .andRoute(POST("/v2/courses"), handler::create)
                .andRoute(PUT("/v2/courses/{id}"), handler::update)
                .andRoute(DELETE("/v2/courses/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> studentRoutes(StudentHandler handler) {
        return route(GET("/v2/students"), handler::findAll)
                .andRoute(GET("/v2/students/orderByAgeAsc"), handler::findAllOrderByAgeAsc)
                .andRoute(GET("/v2/students/orderByAgeDesc"), handler::findAllOrderByAgeDesc)
                .andRoute(GET("/v2/students/{id}"), handler::findById)
                .andRoute(POST("/v2/students"), handler::create)
                .andRoute(PUT("/v2/students/{id}"), handler::update)
                .andRoute(DELETE("/v2/students/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> registrationRoutes(RegistrationHandler handler) {
        return route(GET("/v2/registrations"), handler::findAll)
                .andRoute(GET("/v2/registrations/{id}"), handler::findById)
                .andRoute(POST("/v2/registrations"), handler::create);
    }

}
