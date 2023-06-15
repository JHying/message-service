/**
 * 
 */
package tw.hyin.demo.dto.member;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YingHan 2022-03-08
 * @Description 介接會員中心，確認會員是否存在
 */
@Data
public class MemberAccCheckReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "RequestID", required = true)
	private String requestID;

	@ApiModelProperty(notes = "手機號碼", example = "0912345678", required = true)
	private String mobilePhone;

}
