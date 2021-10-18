package codes.recursive.controller;

import codes.recursive.domain.User;
import codes.recursive.repository.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller("/hello")
public class HelloController {

    private final UserRepository userRepository;
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }

    @Post("/")
    public HttpResponse saveUser(@Valid User user) {
        return HttpResponse.created(
                userRepository.save(user)
        );
    }

    @Get("/{id}")
    public HttpResponse getById(UUID id) {
        return HttpResponse.ok(
                userRepository.findById(id)
        );
    }

    @Get("/version")
    public HttpResponse<Map> getVersion() {
        return HttpResponse.ok(
                Map.of(
                        "version", "0.1"
                )
        );
    }

}