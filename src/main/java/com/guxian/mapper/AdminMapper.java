package com.guxian.mapper;

import com.guxian.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 获取所有管理员
     *
     * @param keywords
     * @param id
     * @return
     */
    List<Admin> getAllAdmin(@Param("keywords") String keywords, @Param("id") Integer id);
}
