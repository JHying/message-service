package tw.hyin.demo.service.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tw.hyin.demo.dto.MailContent;
import tw.hyin.demo.dto.chatGPT.*;
import tw.hyin.demo.service.MsgService;
import tw.hyin.java.utils.JsonUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JHying(Rita) on 2023.
 * @description
 */
@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("openaiRestTemplate")
    private RestTemplate openaiRestTemplate;

    @Value("${openai.chatgpt.chat.url}")
    private String apiUrl;

    @Value("${openai.chatgpt.image.url}")
    private String imageUrl;

    @Override
    public void sendSimpleMail(MailContent content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(content.getTo());
        message.setCc(content.getCc());
        message.setSubject(content.getSubject());
        message.setText(content.getContent());
        javaMailSender.send(message);
    }

    @SneakyThrows
    @Override
    public String callChatGPT(ChatPrompt prompt) {
        // create a request
        ChatRequest request = new ChatRequest(prompt.getText());
        // call the API
        String repStr = openaiRestTemplate.postForObject(apiUrl, request, String.class);
        ChatRsp response = JsonUtil.jsonToBean(repStr, ChatRsp.class);
        if (response == null || response.getChoices().isEmpty()) {
            return "No response.";
        } else {
            List<String> choices = response.getChoices().stream()
                    .map(c -> {
                        c.setText(c.getText().replaceAll("\n\n", "\r\n"));
                        return c.getText();
                    }).collect(Collectors.toList());
            return String.join("\r\n", choices);
        }
    }

    @SneakyThrows
    @Override
    public String chatGPTImage(ChatPrompt prompt) {
        // create a request
        ImageRequest request = new ImageRequest(prompt.getText());
        // call the API
        String repStr = openaiRestTemplate.postForObject(imageUrl, request, String.class);
        ImageRsp response = JsonUtil.jsonToBean(repStr, ImageRsp.class);
        if (response == null || response.getData().isEmpty()) {
            return "No response.";
        } else {
            List<String> choices = response.getData().stream()
                    .map(ImageRsp.ChatData::getUrl).collect(Collectors.toList());
            return String.join("\r\n", choices);
        }
    }

}
