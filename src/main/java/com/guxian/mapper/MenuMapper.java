package com.guxian.mapper;

import com.guxian.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据id获取菜单列表
     *
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    List<Menu> getMenusByRole();

    /**
     * 所有菜单
     *
     * @return
     */
    List<Menu> getAllMenus();
}
