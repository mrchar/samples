package net.mrchar.samples.openapi;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class GreetControllerTest {
    @Autowired
    MockMvc client;

    @Test
    void greet() throws Exception {
        client.perform(get("/greet"))
                .andExpect(status().isOk());
    }

    @Test
    void greetTo() throws Exception {
        client.perform(get("/greet/someone"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", endsWith("someone")));
    }

    @Test
    void makeFriends() throws Exception {
        client.perform(post("/friends")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"someone\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", endsWith("someone")));
    }
}