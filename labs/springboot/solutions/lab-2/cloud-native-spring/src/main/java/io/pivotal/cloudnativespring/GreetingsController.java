package io.pivotal.cloudnativespring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }
}
