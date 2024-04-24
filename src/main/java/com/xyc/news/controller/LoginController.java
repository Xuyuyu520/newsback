package com.xyc.news.controller;


import com.xyc.news.common.WrapMapper;
import com.xyc.news.common.Wrapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器* @author xuyuyu
 * @ClassName LoginController
 * @Description TODO
 * @date 2024-04-23 10:08:32
 */
@CrossOrigin
@RestController
public class LoginController {
    @GetMapping("/login_page")
    public Wrapper<String> daylist() {

        return WrapMapper.wrap(Wrapper.NOT_LOGIN_CODE, Wrapper.NOT_LOGIN_MESSAGE,"请登录");
    }

}
