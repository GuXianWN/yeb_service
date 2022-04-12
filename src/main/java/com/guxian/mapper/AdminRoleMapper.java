package com.guxian.mapper;

import com.guxian.entity.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guxian.entity.RespBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新管理员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
