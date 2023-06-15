package tw.hyin.demo.service;

import tw.hyin.demo.dto.member.MemberAccCheckReq;
import tw.hyin.demo.dto.member.MemberAccUpdateReq;
import tw.hyin.java.utils.http.ResponseObj;

/**
 * @author H-yin on 2022.
 */
public interface UserService {
	
    /**
	 * @author YingHan
	 * @Description 傳簡訊驗證碼
	 * @since 2022-03-09
	 */
    public ResponseObj<String> forgetPwd(MemberAccCheckReq memberAccCheckReq);
    
    /**
	 * @author YingHan
	 * @Description 更新密碼
	 * @since 2022-03-21
	 */
    public ResponseObj<Object> updatePwd(MemberAccUpdateReq accUpdateReq);

}
