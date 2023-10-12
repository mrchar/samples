package net.mrchar.samples.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class GreetControllerTest {
    @Autowired
    MockMvc client;

    @Test
    void greet() throws Exception {
        client.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello World!")));
    }

    @Test
    void greetTo() throws Exception {
        String name = "someone";
        client.perform(get("/greeting/" + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", endsWith(name)));
    }

    @Test
    void greetFor() throws Exception {
        String reason = "something";
        client.perform(get("/greeting?reason={reason}", reason))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", endsWith("for " + reason)));
    }
}