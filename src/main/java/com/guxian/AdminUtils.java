package com.guxian;

import com.guxian.entity.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 管理员工具类
 *
 * @author GuXianWN
 * @date 2022/02/11 10:43
 **/
public class AdminUtils {
    public static Admin getCurrentAdmin() {
        return ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
