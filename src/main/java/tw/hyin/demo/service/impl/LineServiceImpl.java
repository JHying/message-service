package tw.hyin.demo.service.impl;

import com.linecorp.bot.model.message.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.hyin.demo.dto.chatGPT.ChatPrompt;
import tw.hyin.demo.dto.line.LineMessage;
import tw.hyin.demo.service.LineService;
import tw.hyin.demo.service.MsgService;

/**
 * @author JHying(Rita) on 2023.
 * @description
 */
@Service
@RequiredArgsConstructor
public class LineServiceImpl implements LineService {

    private final MsgService msgService;

    @Override
    public TextMessage defaultReply(LineMessage message) {
        String answer = String.format("%s，您好: %s", message.getUserDisplayName(), "已收到您的訊息，請稍待客服人員回覆。\r\n（提醒您: 欲詢問chatGPT請在開頭加上'/c'，欲生成圖片請加上'/i'）");
        return new TextMessage(answer);
    }

    @Override
    public TextMessage askGPT(LineMessage message) {
        String rspFromGpt = msgService.callChatGPT(new ChatPrompt(message.getMessage()));
        String answer = String.format("%s，您好: %s", message.getUserDisplayName(), rspFromGpt);
        return new TextMessage(answer);
    }

    @Override
    public TextMessage askGPTForImage(LineMessage message) {
        String rspFromGpt = msgService.chatGPTImage(new ChatPrompt(message.getMessage()));
        String answer = String.format("%s，您好: %s", message.getUserDisplayName(), rspFromGpt);
        return new TextMessage(answer);
    }

    private String trimPrefix(String msgWithPrefix){
        return msgWithPrefix
                .replaceAll("/c", "")
                .replaceAll("/i", "");
    }

}
