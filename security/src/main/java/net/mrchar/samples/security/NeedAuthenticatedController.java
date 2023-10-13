package net.mrchar.samples.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NeedAuthenticatedController {
    @GetMapping("/need/authenticated")
    public String needAuthenticated() {
        return "You are authenticated!";
    }
}
