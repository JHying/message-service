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
public class ImageRequest implements Serializable {

    private int n;
    private String prompt;
    private String size;

    public ImageRequest(String prompt) {
        this.n = 2;
        this.size = "512x512";
        this.prompt = prompt;
    }

}
