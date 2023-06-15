/**
 * 
 */
package tw.hyin.demo.dto.member;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YingHan 2022-03-09
 * @Description 介接會員中心，更新密碼
 */
@Data
public class MemberAccUpdateReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "RequestID", required = true)
	private String requestID;

	@JsonProperty("uID")
	@ApiModelProperty(notes = "會員編號", required = true)
	private String uID;

	@ApiModelProperty(notes = "手機號碼", example = "0912345678", required = true)
	private String mobilePhone;

	@ApiModelProperty(notes = "會員密碼經 MD5 處理", example = "202cb962ac59075b964b07152d234b70", required = true)
	private String password;

	@ApiModelProperty(notes = "驗證碼", required = true)
	private String captcha;

}
