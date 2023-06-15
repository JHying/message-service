package tw.hyin.demo.service;

import com.linecorp.bot.model.message.TextMessage;
import tw.hyin.demo.dto.line.LineMessage;

/**
 * @author JHying(Rita) on 2023.
 * @description
 */
public interface LineService {

    TextMessage defaultReply(LineMessage message);

    TextMessage askGPT(LineMessage message);

    TextMessage askGPTForImage(LineMessage message);
}
