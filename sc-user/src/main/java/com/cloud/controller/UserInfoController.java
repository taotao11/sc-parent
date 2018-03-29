package com.cloud.controller;


import com.cloud.entity.UserInfo;
import com.cloud.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.cloud.ctl.controller.SuperController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taotao
 * @since 2018-03-29
 */
@Controller
@Api(value = "用户接口")
@RequestMapping("/userInfo")
public class UserInfoController extends SuperController<UserInfoService,UserInfo,Long> {

}

