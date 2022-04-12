package com.guxian.controller;


import com.guxian.entity.Admin;
import com.guxian.entity.RespBean;
import com.guxian.entity.Role;
import com.guxian.service.impl.AdminServiceImpl;
import com.guxian.service.impl.RoleServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private RoleServiceImpl roleService;

    @ApiOperation("获取所有管理员")
    @GetMapping("/")
    public List<Admin> getAllAdmin(String keywords) {
        return adminService.getAllAdmin(keywords);
    }

    @ApiOperation("更新管理员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation("删除管理员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRole(){
        return roleService.list();
    }

    @ApiOperation("更新管理员角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }
}
