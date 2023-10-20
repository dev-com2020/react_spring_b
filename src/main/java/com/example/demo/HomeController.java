package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.demo.ApiController.DATABASE;

public class HomeController {
    @GetMapping("/")
    public Mono<Rendering> index() {
        return Flux.fromIterable(DATABASE.values())
                .collectList()
                .map(employees -> Rendering
                        .view("index")
                        .modelAttribute("employees", employees)
                        .modelAttribute("newEmployee", new Employee("",""))
                        .build());
    }
}
