package com.atguigu.auth.service;


import com.atguigu.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-03-09
 */
public interface SysUserService extends IService<SysUser> {
    SysUser getByUsername(String username);

    //更新状态
    void updateStatus(Long id, Integer status);
    /**
     * 根据用户名获取用户登录信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);

    Map<String, Object> getCurrentUser();
}
