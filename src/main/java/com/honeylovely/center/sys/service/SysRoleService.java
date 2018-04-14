package com.honeylovely.center.sys.service;



import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.sys.entity.SysRole;

import java.util.List;


public interface SysRoleService extends AbstractService<SysRole, Long> {
    List<SysRole> findUserRoles(Long userId);

    int update(SysRole sysRole);

    void saveResourceChange(Long roleId, List<Long> addIds, List<Long> removeIds);
}