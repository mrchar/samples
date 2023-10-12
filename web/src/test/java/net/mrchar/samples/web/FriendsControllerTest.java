package net.mrchar.samples.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class FriendsControllerTest {
    @Autowired
    MockMvc client;

    @Test
    void testFriends() throws Exception {
        // 初始化时列表为空
        client.perform(get("/friends"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        // 创建一个对象后，返回结果不为空
        client.perform(post("/friends")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{\"name\": \"someone\"}"))
                .andExpect(status().isOk());
        client.perform(get("/friends"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

        // 修改用户名
        client.perform(put("/friends/{name}", "someone")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{\"name\": \"new-name\"}"))
                .andExpect(status().isOk());

        // 删除对象后，列表为空
        client.perform(delete("/friends/{name}", "new-name")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{\"name\": \"new-name\"}"))
                .andExpect(status().isOk());
        client.perform(get("/friends"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}