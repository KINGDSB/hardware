/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.honeylovely.center.sys.dao;



import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.sys.entity.SysUser;

import java.util.List;

public interface SysUserDAO extends AbstractDAO<SysUser, Long> {
	SysUser findByUsername(String username);

	int deleteByUserId(Long userId);

	int addRoles(Long userId, List<Long> roleIds);

	Long countByUsername(String username);

	int updatePassword(String password, String salt, Long id);
}
