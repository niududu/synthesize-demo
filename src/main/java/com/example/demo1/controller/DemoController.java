package com.example.demo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: LiuZW
 * @CreateDate: 2019/10/16/016 14:46
 * @Version: 1.0
 */
@RestController
@RequestMapping
public class DemoController  {

    @RequestMapping(value = "demo")
    public String demo(){
        return "hello Spring Boot";
    }

}
