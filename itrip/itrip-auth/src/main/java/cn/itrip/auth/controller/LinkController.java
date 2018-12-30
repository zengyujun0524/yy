package cn.itrip.auth.controller;

import cn.itrip.auth.service.TokenService;
import cn.itrip.auth.service.UserService;
import cn.itrip.common.ValidationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/link")
public class LinkController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Resource
    private ValidationToken validationToken;

}
