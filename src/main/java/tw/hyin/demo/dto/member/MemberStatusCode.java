package tw.hyin.demo.dto.member;

import lombok.Getter;

/**
 * @author H-yin on 2020.
 */
public enum MemberStatusCode {

    OK("1"),
    FAIL("0"),
    USER_ANORMAL("2"),
    INTERNAL_ERROR("9");

    @Getter
    private String code;

    MemberStatusCode(String code) {
        this.code = code;
    }
}
