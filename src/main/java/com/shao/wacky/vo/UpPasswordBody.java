package com.shao.wacky.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpPasswordBody {

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 旧密码
     */
    private String oldPassword;
}
