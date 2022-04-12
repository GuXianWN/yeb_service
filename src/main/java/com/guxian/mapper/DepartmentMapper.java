package com.guxian.mapper;

import com.guxian.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guxian.entity.RespBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartment(@Param("parentId") Integer parentId);

    /**
     * 添加部门
     * @param dep
     */
    void addDep(Department dep);

    /**
     * 删除部门
     * @param dep
     * @return
     */
    void deleteDep(Department dep);
}
