package br.edu.atitus.greetingservice.controllers;

import br.edu.atitus.greetingservice.configs.GreetingConfig;
import br.edu.atitus.greetingservice.dto.PersonRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingConfig config;

    public GreetingController(GreetingConfig config) {
        this.config = config;
    }

    @GetMapping({"", "/"})
    public String getGreeting(
            @RequestParam(required = false) String name) {
        if (name == null || name.isEmpty()) {
            name = config.getDefaultName();
        }
        return String.format("%s %s!!!", config.getGreeting(), name);
    }

    @GetMapping("/{name}")
    public String getGreetingWithPath(@PathVariable String name) {
        return String.format("%s %s!!!", config.getGreeting(), name);
    }

    @PostMapping({"", "/"})
    public String postGreeting(@RequestBody PersonRequest request) {
        String name = request.getName();

        if (name == null || name.trim().isEmpty()) {
            name = config.getDefaultName();
        }

        return String.format("%s %s!!!", config.getGreeting(), name);
    }
}