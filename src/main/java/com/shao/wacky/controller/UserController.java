package com.shao.wacky.controller;

import com.shao.wacky.entity.User;
import com.shao.wacky.exception.WackyException;
import com.shao.wacky.service.UserService;
import com.shao.wacky.utils.PasswordUtil;
import com.shao.wacky.vo.RegisterBody;
import com.shao.wacky.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public String GetUser(Integer id) {

        if (null == id) {
            logger.debug("id未输入正确");
            throw new WackyException(400, "请输入正确的id");
        }
        User u = userService.Sel(id);
        if (null == u) {
            throw new WackyException(401, "未找到数据");
        } else {
            return null == u ? "" : u.toString();
        }

    }

    /**
     * 用户注册
     *
     * @param form
     * @return
     */
    @RequestMapping("/register")
    public ResultVo register(@Validated @RequestBody RegisterBody form) {
        //对密码加密
        String pwdEncode = PasswordUtil.encode(form.getPassword());
        User user = new User();
        user.setPassWord(pwdEncode);
        user.setUserName(form.getUsername());
        user.setRealName(form.getRealName());
        userService.insert(user);
        return ResultVo.ok();
    }


}
