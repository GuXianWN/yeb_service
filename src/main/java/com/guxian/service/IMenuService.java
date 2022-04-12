package com.guxian.service;

import com.guxian.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据用户id查询查单列表
     *
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据权限获取菜单列表
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
