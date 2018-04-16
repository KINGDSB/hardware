package com.honeylovely.center.sys.service;



import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.sys.entity.SysUser;

import java.util.List;

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