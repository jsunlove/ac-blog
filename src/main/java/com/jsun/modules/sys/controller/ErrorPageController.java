package com.jsun.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: jsun
 * @ProjectName: adminsystem
 * @Package: com.jsun.modules.sys.controller
 * @Description: 错误页面跳转
 * @Date: 2019/9/8 0008 14:15
 **/
@Controller
public class ErrorPageController {

    @GetMapping("/404")
    public String error404(){
        return "404";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }

}
