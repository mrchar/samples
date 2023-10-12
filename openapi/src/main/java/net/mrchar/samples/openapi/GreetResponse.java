package net.mrchar.samples.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "问候的响应")
public class GreetResponse {
    @Schema(description = "返回的消息")
    private String message;

    public GreetResponse() {
    }

    public GreetResponse(String message) {
        this.message = message;
    }
}
