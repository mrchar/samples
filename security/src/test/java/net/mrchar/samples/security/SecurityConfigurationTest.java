package net.mrchar.samples.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseCookie;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SecurityConfigurationTest {
    WebTestClient client;

    public SecurityConfigurationTest(@LocalServerPort Integer port) {
        this.client = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();
    }

    @Test
    void login() {
        EntityExchangeResult<byte[]> result = client.post().uri("/login")
                .body(BodyInserters
                        .fromFormData("username", "user")
                        .with("password", "password"))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectBody().returnResult();
        ResponseCookie jsession = result.getResponseCookies().getFirst("JSESSIONID");
        Assertions.assertThat(jsession).isNotNull();

        client.get().uri("/need/authenticated")
                .cookie("JSESSIONID", jsession.getValue())
                .exchange()
                .expectStatus().isOk();
    }

}