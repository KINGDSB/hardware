package indi.dsb.hardware.sys.service;



import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.sys.entity.SysRole;


public interface SysRoleService extends AbstractService<SysRole, Long> {
    List<SysRole> findUserRoles(Long userId);

    int update(SysRole sysRole);

    void saveResourceChange(Long roleId, List<Long> addIds, List<Long> removeIds);
}