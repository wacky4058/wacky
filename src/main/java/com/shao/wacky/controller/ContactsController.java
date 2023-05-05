package com.shao.wacky.controller;


import cn.hutool.core.bean.BeanUtil;
import com.shao.wacky.entity.Contacts;
import com.shao.wacky.service.ContactsServiceImpl;
import com.shao.wacky.vo.ContactsVo;
import com.shao.wacky.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@RequestMapping("/contacts")
public class ContactsController extends BaseController {

    @Resource
    private ContactsServiceImpl contactsService;

    @PostMapping("/createContact")
    public ResultVo createContacts(@RequestBody ContactsVo contacts) {
        Contacts c = new Contacts();
        BeanUtil.copyProperties(contacts, c);
        c.setCreateBy(getUserId());
        c.setUpdateBy(getUserId());
        c.setCreateTime(new Timestamp(System.currentTimeMillis()));
        c.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        contactsService.save(c);
        return ResultVo.ok();
    }

    @PostMapping("/updateContact")
    public ResultVo updateContacts(@RequestBody ContactsVo contacts) {
        Contacts c = contactsService.getById(contacts.getId());
        if (null == c) {
            return ResultVo.fail("原数据不存在");
        }
        BeanUtil.copyProperties(contacts, c);
        c.setUpdateBy(getUserId());
        c.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        contactsService.updateById(c);
        return ResultVo.ok();
    }

    @PostMapping("/deleteContact")
    public ResultVo deleteContacts(@RequestParam(name = "id") String id) {
        Contacts c = contactsService.getById(id);
        if (null == c) {
            return ResultVo.fail("原数据不存在");
        }
        c.setUpdateBy(getUserId());
        c.setDeleteFlag(1);
        c.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        contactsService.updateById(c);
        return ResultVo.ok();
    }
}
