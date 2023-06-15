package tw.hyin.demo.dto.line;

import lombok.Builder;
import lombok.Data;

/**
 * @author JHying(Rita) on 2023.
 * @description
 */
@Data
@Builder
public class LineMessage {

    private String lineId;
    private String replyToken;
    private String message;
    private String userDisplayName;

}
