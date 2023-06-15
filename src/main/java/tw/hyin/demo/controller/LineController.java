package tw.hyin.demo.controller;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import tw.hyin.demo.dto.line.LineMessage;
import tw.hyin.demo.service.LineService;

/**
 * @author JHying(Rita) on 2023.
 */
@LineMessageHandler
@RequiredArgsConstructor
public class LineController {

    private final LineService lineService;
    private final LineMessagingClient lineMessagingClient;

    @EventMapping
    public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent) {
        LineMessage lineMessage = this.retrieveTextMessage(messageEvent);
        String msg = lineMessage.getMessage();
        if (msg.startsWith("/c")) {
            lineMessagingClient.replyMessage(new ReplyMessage(lineMessage.getReplyToken(), lineService.askGPT(lineMessage)));
        } else if (msg.startsWith("/i")) {
            lineMessagingClient.replyMessage(new ReplyMessage(lineMessage.getReplyToken(), lineService.askGPTForImage(lineMessage)));
        } else {
            lineMessagingClient.replyMessage(new ReplyMessage(lineMessage.getReplyToken(), lineService.defaultReply(lineMessage)));
        }
    }

    @SneakyThrows
    private LineMessage retrieveTextMessage(MessageEvent<TextMessageContent> messageEvent) {
        String lineId = messageEvent.getSource().getUserId();
        return LineMessage.builder()
                .lineId(lineId)
                .replyToken(messageEvent.getReplyToken())
                .message(messageEvent.getMessage().getText())
                .userDisplayName(lineMessagingClient.getProfile(lineId).get().getDisplayName())
                .build();
    }

}
