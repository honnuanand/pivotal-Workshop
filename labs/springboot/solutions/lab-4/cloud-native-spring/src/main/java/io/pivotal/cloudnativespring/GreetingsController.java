package io.pivotal.cloudnativespring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope
@RestController
public class GreetingsController {

    @Value("${greeting:Hola}")
    private String greeting;


    @RequestMapping("/")
    public String hello() {
        return greeting;
    }
}
