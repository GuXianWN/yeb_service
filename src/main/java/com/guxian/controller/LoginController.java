package com.guxian.controller;

import com.guxian.entity.Admin;
import com.guxian.entity.AdminLoginParam;
import com.guxian.entity.RespBean;
import com.guxian.service.impl.AdminServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登录
 *
 * @author GuXianWN
 * @date 2021/12/25 16:55
 **/
@Api(tags = "登录")
@RestController
public class LoginController {
    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/login")
    @ApiOperation("登录之后返回token")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(), request);
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public RespBean logout() {
        //前端删token
        return RespBean.success("注销成功");
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRole(admin.getId()));
        return admin;
    }
}
