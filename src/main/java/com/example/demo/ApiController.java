package com.example.demo;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ApiController {
    @GetMapping("/api/employees")
    Flux<Employee> employees() {
        return Flux.just(
                new Employee("alicja", "it"),
                new Employee("bob", "payroll"));
    }

    public static Map<String, Employee> DATABASE = new LinkedHashMap<>();

    @PostMapping("/api/employees")
    Mono<Employee> add(@RequestBody Mono<Employee> newEmployee) {
        return newEmployee //
                .map(employee -> {
                    DATABASE.put(employee.name(), employee);
                    return employee;
                });
    }
}

//    Flux<String> a = Flux.just("alpha", "bravo");
//    Flux<String> b = Flux.just("charlie", "delta");
//        a.concatWith(b);
//        a.mergeWith(b);