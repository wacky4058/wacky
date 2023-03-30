package com.shao.wacky.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录用户信息
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginUserInfo implements Serializable {

    private static final long serialVersionUID = -6412903077146617198L;

    /**
     * token
     */
    private String token;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     *
     */
    private String realName;

    /**
     * 创建时间
     */
    private Date createTime = new Date();
}
