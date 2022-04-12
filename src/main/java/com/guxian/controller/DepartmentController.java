package com.guxian.controller;


import com.guxian.entity.Department;
import com.guxian.entity.RespBean;
import com.guxian.service.impl.DepartmentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartment() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return (List<Department>) valueOperations.get("department");
    }

    @ApiOperation("添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        updateDepFoRedis();
        return departmentService.addDep(dep);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id) {
        updateDepFoRedis();
        return departmentService.deleteDep(id);
    }

    public void updateDepFoRedis() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("department", departmentService.getAllDepartment());
    }
}
