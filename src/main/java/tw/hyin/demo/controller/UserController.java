package tw.hyin.demo.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.hyin.demo.dto.member.MemberAccCheckReq;
import tw.hyin.demo.dto.member.MemberAccUpdateReq;
import tw.hyin.demo.service.UserService;
import tw.hyin.java.utils.http.BaseController;
import tw.hyin.java.utils.http.ResponseObj;

/**
 * API 接口
 * 
 * @author YingHan 2022
 *
 */
@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController extends BaseController {

	private final UserService userService;

    @ApiOperation(value = "發送驗證碼", hidden = true)
    @PostMapping(value = "/forgetPwd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj<ResponseObj<String>>> forgetPwd(@RequestBody MemberAccCheckReq memberAccCheckReq) {
        return super.sendSuccessRsp(userService.forgetPwd(memberAccCheckReq));
    }

    @ApiOperation(value = "更新密碼", hidden = true)
    @PostMapping(value = "/updatePwd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj<ResponseObj<Object>>> updatePwd(@RequestBody MemberAccUpdateReq memberAccUpdateReq) {
        return super.sendSuccessRsp(userService.updatePwd(memberAccUpdateReq));
    }

}
