/**
 * 
 */
package tw.hyin.demo.dto.phoneMsg;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author YingHan 2022-03-08
 * @Description 介接 EVERY8D 互動資通 企業簡訊服務平台
 */
@Data
public class PhoneMsgSendRsp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("Result")
	private String result;//處理結果：true/false。
	
	@JsonProperty("Status")
	private Integer status;// 0 為成功狀態
	
	@JsonProperty("Msg")
	private String rspMsg;//發送後剩餘點數,發送通數,本次發送扣除點數,此次發送批號(可藉由本識別碼查詢發送狀態)
	
}
