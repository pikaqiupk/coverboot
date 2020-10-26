package com.tcbd.coverboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class WebController {

    @RequestMapping("/begin")
    @ResponseBody
    private String toIndex() {
        return "接收程序启动！！";

    }
}
