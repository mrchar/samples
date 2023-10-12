package net.mrchar.samples.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {
    @GetMapping("/greeting")
    public MessageResponse greet() {
        return new MessageResponse("Hello World!");
    }

    @GetMapping("/greeting/{name}")
    public MessageResponse greetTo(@PathVariable String name) {
        return new MessageResponse("Hello " + name);
    }

    @GetMapping(value = "/greeting", params = {"reason"})
    public MessageResponse greetFor(@RequestParam String reason) {
        return new MessageResponse("Hello for " + reason);
    }
}
