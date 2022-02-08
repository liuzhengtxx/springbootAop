package com.controller;

import com.annotation.clcTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luokui
 * @date 2022/2/8 10:20
 */
@RestController
public class HelloController {

    @GetMapping("/hello2")
    //通过注解的方式引入aop
    @clcTime(taskType = "myType",step = "myStep")
    public String hello1(@RequestParam String hello){
        return "hello"+hello;
    }
}
