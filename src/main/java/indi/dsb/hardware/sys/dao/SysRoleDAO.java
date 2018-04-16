/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package indi.dsb.hardware.sys.dao;



import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.sys.entity.SysRole;

/**
 * @version 1.0
 * @author
 */
public interface SysRoleDAO extends AbstractDAO<SysRole, Long> {
	List<SysRole> findUserRoles(Long userId);

	/*List<SysRole> findRoleByWhere(List<QueryParam> params);*/

	int update(Long id, Byte status, String roleKey, String name, String description, Boolean isDeleted);

	int remove(Long roleId, List<Long> removeIds);

	int add(Long roleId, List<Long> addIds);
}
