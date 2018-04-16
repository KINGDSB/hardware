package indi.dsb.hardware.sys.service;



import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.sys.entity.SysUser;

/**
 * Created by ryan on 2016-03-01.
 */
public interface SysUserService extends AbstractService<SysUser, Long> {

	SysUser findByUsername(String username);

	SysUser add(SysUser user);

	void saveUserRoles(Long userId, List<Long> roleIds, boolean hasRoles);

	boolean exist(String username);

	int updatePassword(String password, String salt, Long id);
}