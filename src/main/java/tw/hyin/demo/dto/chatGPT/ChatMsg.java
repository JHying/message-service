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
public class ChatMsg implements Serializable {

    private String role;
    private String content;

}
