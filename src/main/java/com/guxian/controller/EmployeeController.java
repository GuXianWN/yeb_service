package com.guxian.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.guxian.entity.*;
import com.guxian.mapper.EmployeeMapper;
import com.guxian.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("获取所有员工(分页)")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currenPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee, LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currenPage, size, employee, beginDateScope);
    }

    @ApiOperation("获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return (List<PoliticsStatus>) valueOperations.get("politicsStatus");
    }

    @ApiOperation("获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJoblevels() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return (List<Joblevel>) valueOperations.get("joblevel");
    }

    @ApiOperation("获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return (List<Nation>) valueOperations.get("nation");
    }

    @ApiOperation("获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return (List<Position>) valueOperations.get("position");
    }

    @ApiOperation("获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDeps() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return (List<Department>) valueOperations.get("department");
    }

    @ApiOperation("获取工号")
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        return employeeService.maxWorkID();
    }

    @ApiOperation("添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        return employeeService.addEmp(employee);
    }

    @ApiOperation("更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateById(employee)) {
            return RespBean.success("更新成功");
        } else {
            return RespBean.error("更新失败");
        }
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

    @ApiOperation("导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmps(HttpServletResponse response) {
        List<Employee> employees = employeeService.getEmployee(null);
        ExportParams exportParams = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook excel = ExcelExportUtil.exportExcel(exportParams, Employee.class, employees);
        ServletOutputStream outputStream = null;
        try {
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
            outputStream = response.getOutputStream();
            excel.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation("导入员工数据")
    @PutMapping("/import")
    public RespBean importEmps(MultipartFile file) {
        ImportParams params = new ImportParams();
        //去掉标题
        params.setTitleRows(1);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<PoliticsStatus> politicsStatusList = (List<PoliticsStatus>) valueOperations.get("politicsStatus");
        List<Joblevel> joblevelList = (List<Joblevel>) valueOperations.get("joblevel");
        List<Nation> nationList = (List<Nation>) valueOperations.get("nation");
        List<Position> positionList = (List<Position>) valueOperations.get("position");
        List<Department> departmentList = (List<Department>) valueOperations.get("department");
        try {
            List<Employee> emps = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            emps.forEach(emp -> {
                emp.setNationid(nationList.get(nationList.indexOf(new Nation(emp.getNation().getName()))).getId());
                emp.setPoliticid(politicsStatusList.get(politicsStatusList.indexOf(new PoliticsStatus(emp.getPoliticsStatus().getName()))).getId());
                emp.setDepartmentid(departmentList.get(departmentList.indexOf(new Department(emp.getDepartment().getName()))).getId());
                emp.setJoblevelid(joblevelList.get(joblevelList.indexOf(new Joblevel(emp.getJoblevel().getName()))).getId());
                emp.setPosid(positionList.get(positionList.indexOf(new Position(emp.getPosition().getName()))).getId());
            });
            if (employeeService.saveBatch(emps)) {
                return RespBean.success("导入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败");
    }
}
