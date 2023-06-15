package tw.hyin.demo.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.hyin.demo.dto.MailContent;
import tw.hyin.demo.dto.chatGPT.ChatPrompt;
import tw.hyin.demo.service.MsgService;
import tw.hyin.java.utils.http.BaseController;
import tw.hyin.java.utils.http.ResponseObj;

/**
 * @author JHying(Rita) on 2023.
 * @description
 */
@RequiredArgsConstructor
@RestController
@ResponseBody
public class MsgController extends BaseController {

    private final MsgService msgService;

    @ApiOperation(value = "寄送email", hidden = true)
    @PostMapping(value = "/mail/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj<ResponseObj.RspMsg>> sendMail(@RequestBody MailContent mailContent) {
        msgService.sendSimpleMail(mailContent);
        return super.sendSuccessRsp(ResponseObj.RspMsg.SUCCESS);
    }

    @PostMapping("gpt/chat")
    public ResponseEntity<ResponseObj<String>> chat(@RequestBody ChatPrompt text) {
        return super.sendSuccessRsp(msgService.callChatGPT(text));
    }

    @PostMapping("gpt/img")
    public ResponseEntity<ResponseObj<String>> image(@RequestBody ChatPrompt text) {
        return super.sendSuccessRsp(msgService.chatGPTImage(text));
    }

}
