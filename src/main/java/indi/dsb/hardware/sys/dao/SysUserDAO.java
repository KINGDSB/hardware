/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package indi.dsb.hardware.sys.dao;



import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.sys.entity.SysUser;

public interface SysUserDAO extends AbstractDAO<SysUser, Long> {
	SysUser findByUsername(String username);

	int deleteByUserId(Long userId);

	int addRoles(Long userId, List<Long> roleIds);

	Long countByUsername(String username);

	int updatePassword(String password, String salt, Long id);
}
