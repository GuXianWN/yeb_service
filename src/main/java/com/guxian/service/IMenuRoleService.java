package com.guxian.service;

import com.guxian.entity.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guxian.entity.RespBean;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mid
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mid);
}
