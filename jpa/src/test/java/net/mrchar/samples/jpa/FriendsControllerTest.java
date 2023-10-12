package net.mrchar.samples.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FriendsControllerTest {
    static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    FriendsRepository friendsRepository;
    @Autowired
    MockMvc client;

    @Test
    void testFriend() throws Exception {
        this.friendsRepository.save(new FriendEntity("someone"));

        // 查询结果不为空
        client.perform(get("/friends"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());

        // 创建对象不报错
        MvcResult result = client.perform(post("/friends")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\": \"another\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("another")))
                .andReturn();
        FriendEntity entity = objectMapper.readValue(result.getResponse().getContentAsByteArray(), FriendEntity.class);
        Assertions.assertThat(entity.getId()).isNotNull();

        // 修改对象名称成功
        client.perform(put("/friends/{id}", entity.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\": \"new-name\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("new-name")));

        // 删除对象成功
        client.perform(delete("/friends/{id}", entity.getId().toString()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}