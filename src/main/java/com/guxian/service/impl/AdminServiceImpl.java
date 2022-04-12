package com.guxian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guxian.AdminUtils;
import com.guxian.config.security.component.JwtTokenUtil;
import com.guxian.entity.Admin;
import com.guxian.entity.AdminRole;
import com.guxian.entity.RespBean;
import com.guxian.entity.Role;
import com.guxian.mapper.AdminMapper;
import com.guxian.mapper.AdminRoleMapper;
import com.guxian.mapper.RoleMapper;
import com.guxian.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GuXianWN
 * @since 2021-12-23
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (captcha == null || captcha.equals("")) {
            return RespBean.error("请刷新验证码");
        }
        if (StringUtils.isEmpty(code) || !captcha.equals(code)) {
            return RespBean.error("验证码错误,请重新登录");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("账号或密码错误");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用");
        }
        //更新security用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        return RespBean.success("登录成功").data("token", token).data("tokenHead", tokenHead);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("username", username));
    }

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRole(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 获取所有管理员
     *
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmin(String keywords) {
        return adminMapper.getAllAdmin(keywords, AdminUtils.getCurrentAdmin().getId());
    }

    /**
     * 更新管理员角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if (rids.length == result) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
