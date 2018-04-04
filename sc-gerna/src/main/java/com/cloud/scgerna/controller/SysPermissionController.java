package com.cloud.scgerna.controller;


import com.cloud.scgerna.entity.SysPermission;
import com.cloud.scgerna.service.SysPermissionService;
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
@RequestMapping("/sysPermission")
public class SysPermissionController extends SuperController<SysPermissionService,SysPermission,Long> {

}

