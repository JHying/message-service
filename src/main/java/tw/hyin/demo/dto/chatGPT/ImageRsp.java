/**
 *
 */
package tw.hyin.demo.dto.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author YingHan 2022-03-08
 */
@Data
@AllArgsConstructor
public class ImageRsp implements Serializable {

    private Integer created;
    private List<ChatData> data;

    @Data
    @AllArgsConstructor
    public static class ChatData {

        private String url;
        private String b64_json;

        public ChatData(String url) {
            this.url = url;
        }
    }

}
