/**
 * 
 */
package tw.hyin.demo.dto.phoneMsg;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author YingHan 2022-03-08
 * @Description 介接 EVERY8D 互動資通 企業簡訊服務平台 post url
 */
@Data
@RequiredArgsConstructor
public class PhoneMsgSendReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("UID")
	private String uid = "???";// 發送簡訊之帳號

	@JsonProperty("PWD")
	private String pwd = "???";// 發送簡訊之密碼

	@JsonProperty("SB")
	private final String sb = "【XXX忘記密碼】忘記密碼驗證";// 簡訊主旨，預設為空字串。不會隨簡訊發送，用以註記發送之用途。

	@JsonProperty("MSG")
	private final String msg = "【XXX忘記密碼】您的驗證碼: %field1%。請於 5 分鐘內完成驗證，謝謝。";// SMS訊息內容

	@JsonProperty("RETRYTIME")
	private final Integer retrytime = 5;// 簡訊有效期限(單位：分鐘)，預設1440分鐘

	@NonNull
	@JsonProperty("RecipientDataList")
	private List<RecipientData> recipientDataList;

}
