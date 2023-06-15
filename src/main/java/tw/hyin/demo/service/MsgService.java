package tw.hyin.demo.service;

import tw.hyin.demo.dto.MailContent;
import tw.hyin.demo.dto.chatGPT.ChatPrompt;

/**
 * @author H-yin on 2022.
 */
public interface MsgService {

    /**
     * @author YingHan
     * @Description 寄簡易電子信件
     * @since 2023-03-15
     */
    public void sendSimpleMail(MailContent content);

    public String callChatGPT(ChatPrompt prompt);

    String chatGPTImage(ChatPrompt prompt);
}
