/**
 *
 */
package tw.hyin.demo.dto.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YingHan 2022-03-08
 */
@Data
@AllArgsConstructor
public class ChatRequest implements Serializable {

    private String model;
//    private List<ChatMsg> messages; for chat model
    private int n;
    private String prompt;
    private double temperature;
    private int max_tokens;

    public ChatRequest(String prompt) {
        this.temperature = 0.5;
        this.n = 1;
        this.max_tokens = 2000;
        this.model = "text-davinci-003";
        this.prompt = prompt;
//        this.messages = new ArrayList<>();
//        this.messages.add(new ChatMsg("system", "You are a helpful assistant."));
//        this.messages.add(new ChatMsg("user", prompt));
    }

}
