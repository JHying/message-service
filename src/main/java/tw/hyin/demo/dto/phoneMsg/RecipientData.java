/**
 *
 */
package tw.hyin.demo.dto.phoneMsg;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tw.hyin.java.utils.DateDeserializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YingHan 2022-03-08
 * @Description 介接 EVERY8D 互動資通 企業簡訊服務平台 post url
 * 帳號???
 * 密碼???
 */
@Data
@RequiredArgsConstructor
public class RecipientData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("Name")
    private String name;// 收訊人名稱

    @NonNull
    @JsonProperty("Mobile")
    private String mobile;// 手機號碼

    @JsonProperty("Email")
    private String email;// 收訊人電子郵件，預設為空字串。寄簡訊時會一同寄郵件給收訊人。

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonProperty("SendTime")
    private Date sendtime;// 預設為空字串。另外預約發送時間用。格式：yyyyMMddHHmmss

    @NonNull
    @JsonProperty("Param")
    private String param;// 填入參數內容，系統支援五組參數，接受空字串，且分隔符號不可省略，如: ABCDE|888|2999-01-31||

    @JsonProperty("MR")
    private String mr;// 每筆簡訊的發送代號，預設為空字串。此代碼於同批發送資料必須是唯一值。

}
