package net.mrchar.samples.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "greet")
@RestController
public class GreetController {
    @GetMapping("/greet")
    @Operation(summary = "打招呼")
    public GreetResponse greet() {
        return new GreetResponse("Hello World!");
    }

    @GetMapping("/greet/{name}")
    @Operation(summary = "向具体的人打招呼")
    public GreetResponse greetTo(@PathVariable @Parameter(description = "自我介绍的名称") String name) {
        return new GreetResponse("Hello " + name);
    }

    @PostMapping("/friends")
    @Operation(summary = "交朋友")
    public GreetResponse makeFriends(@RequestBody FriendRequest request) {
        return new GreetResponse("Hello my friend " + request.getName());
    }
}
