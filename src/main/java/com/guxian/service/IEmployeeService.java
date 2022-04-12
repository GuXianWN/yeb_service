package com.guxian.service;

import com.guxian.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guxian.entity.RespBean;
import com.guxian.entity.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 获取所有员工(分页)
     * @param currenPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currenPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取工号
     * @return
     */
    RespBean maxWorkID();

    /**
     * 添加员工
     * @param employee
     * @return
     */
    RespBean addEmp(Employee employee);

    /**
     *
     */
    List<Employee> getEmployee(Integer id);
}
