package com.atguigu.auth.service.impl;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.auth.service.SysRoleService;
import com.atguigu.auth.service.SysUserRoleService;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.vo.system.AssignRoleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Override
    public Map<String, Object> findRoleByAdminId(Long userId) {
        //查询所有的角色
        List<SysRole> allRolesList = baseMapper.selectList(null);
        //根据拥有的角色id，查询对应所有id
        LambdaQueryWrapper<SysUserRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> existUserRoleList  = sysUserRoleService.list(wrapper);
        List<Long> existUserIdList = existUserRoleList.stream().map(c -> c.getRoleId()).collect(Collectors.toList());

        //根据查询所有角色ID，找到对应角色信息
        //根据角色ID到所有角色的list集合进行比较
        List<SysRole> assignedRoleList=new ArrayList<>();
        for(SysRole sysRole:allRolesList){
            if(existUserIdList.contains((sysRole.getId()))){
                assignedRoleList.add(sysRole);
            }
        }
        //把得到的两部分数据封装后返回
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignedRoleList", assignedRoleList);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    @Transactional
    @Override
    public void doAssign(AssignRoleVo assignRoleVo) {
        //把之前分配的角色删除
        LambdaQueryWrapper<SysUserRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, assignRoleVo.getUserId());
        sysUserRoleService.remove(wrapper);

        //重新进行分配
        List<Long> roleIdList = assignRoleVo.getRoleIdList();
        for(Long roleId:roleIdList){
            if(StringUtils.isEmpty(roleId)){
                continue;
            }
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assignRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleService.save(userRole);
        }

    }
//    @Autowired
//    private SysRoleMapper sysRoleMapper;

}
