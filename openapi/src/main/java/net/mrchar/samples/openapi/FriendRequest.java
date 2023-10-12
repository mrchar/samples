package net.mrchar.samples.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "朋友信息")
public class FriendRequest {
    @Schema(description = "名称")
    private String name;
}
