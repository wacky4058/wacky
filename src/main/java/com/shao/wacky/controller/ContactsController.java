package com.shao.wacky.controller;


import cn.hutool.core.bean.BeanUtil;
import com.shao.wacky.entity.Contacts;
import com.shao.wacky.service.ContactsServiceImpl;
import com.shao.wacky.vo.ContactsVo;
import com.shao.wacky.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@RequestMapping("/contacts")
public class ContactsController  extends BaseController{

    @Resource
    private ContactsServiceImpl contactsService;

    @RequestMapping("/createContact")
    public ResultVo createContacts(@RequestBody ContactsVo contacts){
        Contacts c = new Contacts();
        BeanUtil.copyProperties(contacts,c);
        c.setCreateBy(getUserId());
        c.setUpdateBy(getUserId());
        c.setCreateTime(new Timestamp(System.currentTimeMillis()));
        c.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        contactsService.save(c);
        return ResultVo.ok();
    }
}
