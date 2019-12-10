package com.example.demo1.controller;

import com.example.demo1.entity.IfoUser;
import com.example.demo1.service.IfoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: LiuZW
 * @CreateDate: 2019/10/16/016 15:28
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "user")
public class IfoUserController {

    @Autowired
    private IfoUserService userService;
    /**
     * @MethodName: listUser
     * @Description: 获取用户列表
     * @Param: []
     * @Return: java.util.List<com.example.demo.entity.IfoUser>
     * @Author: LiuZW
     * @Date: 2019/10/16/016 15:29
     **/
    @RequestMapping("list")
    public List<IfoUser> listUser(){
           return userService.listUser();
    }

    @RequestMapping("list/{nickName}")
    public List<IfoUser> listUser(@PathVariable("nickName") String nickName){
           return userService.listUser(nickName);
    }
}
