package tw.hyin.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import tw.hyin.demo.dto.member.MemberAccCheckReq;
import tw.hyin.demo.dto.member.MemberAccUpdateReq;
import tw.hyin.demo.dto.member.MemberStatusCode;
import tw.hyin.demo.dto.phoneMsg.PhoneMsgSendReq;
import tw.hyin.demo.dto.phoneMsg.PhoneMsgSendRsp;
import tw.hyin.demo.dto.phoneMsg.RecipientData;
import tw.hyin.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import tw.hyin.java.utils.JsonUtil;
import tw.hyin.java.utils.Log;
import tw.hyin.java.utils.http.ResponseObj;
import tw.hyin.java.utils.http.RestTemplateUtil;
import tw.hyin.java.utils.security.CaptchaUtil;

/**
 * 
 * @author YingHan 2022
 *
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final CaptchaUtil captchaUtil;
	private final RestTemplateUtil restTemplateUtil;

	@Value(value = "${member.center.check.account.url}")
	private String check_account_url;

	@Value(value = "${member.center.update.account.url}")
	private String update_account_url;

	@Value(value = "${phone.msg.send.url}")
	private String send_phone_msg_url;

	@SneakyThrows
	@Override
	public ResponseObj<String> forgetPwd(MemberAccCheckReq accCheckReq) {
		ResponseObj<String> rsp = restTemplateUtil.doPostByJson(check_account_url, JsonUtil.objToJson(accCheckReq),
				ResponseObj.class);
		if (rsp.getResult().equals(MemberStatusCode.OK.getCode())) {
			// 會員存在產生隨機碼
			String captcha = captchaUtil.genNum(accCheckReq.getMobilePhone(), 5);
			captchaUtil.saveToCache(accCheckReq.getMobilePhone(), captcha);
			// 寄信
			this.sendCaptchaMsg(accCheckReq.getMobilePhone(), captcha);
		}
		return rsp;
	}

	@SneakyThrows
	@Override
	public ResponseObj<Object> updatePwd(MemberAccUpdateReq accUpdateReq) {
		if (captchaUtil.validate(accUpdateReq.getMobilePhone(), accUpdateReq.getCaptcha())) {
			// 驗證成功，更新密碼
			ResponseObj<Object> rsp = restTemplateUtil.doPostByJson(update_account_url, JsonUtil.objToJson(accUpdateReq),
					ResponseObj.class);
			Log.info(">> update pwd success: UID={}", accUpdateReq.getUID());
			return rsp;
		} else {
			List<String> errors = new ArrayList<>();
			errors.add("驗證碼輸入錯誤");
 			Log.info(">> update pwd validate failed: UID={}", accUpdateReq.getUID());
			return ResponseObj.builder()
					.status(HttpStatus.UNAUTHORIZED)
					.result(MemberStatusCode.FAIL.getCode())
					.errors(errors).build();
		}
	}

	/**
	 * @author YingHan
	 * @since 2022-03-21
	 * 
	 * @Description 傳簡訊驗證碼
	 */
	@SneakyThrows
	private void sendCaptchaMsg(String phone, String captcha) {
		List<RecipientData> data = new ArrayList<>();
		data.add(new RecipientData(phone, captcha + "||||"));
		PhoneMsgSendReq msgSendReq = new PhoneMsgSendReq(data);
		String req = JsonUtil.objToJson(msgSendReq);
		PhoneMsgSendRsp phoneMsgSendRsp = restTemplateUtil.doPostByJson(send_phone_msg_url, req, PhoneMsgSendRsp.class);
		Log.info(">> send phone msg response: {}", phoneMsgSendRsp.toString());
	}

}
