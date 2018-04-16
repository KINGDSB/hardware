/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package indi.dsb.hardware.sys.dao;



import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.sys.entity.SysResource;

/**
 * @version 1.0
 * @author
 */
public interface SysResourceDAO extends AbstractDAO<SysResource, Long> {
	List<SysResource> findResourcesByUserId(Long userId);

	SysResource findByResUrl(String resourceUrl);

	List<SysResource> findByParentIdOrderBySeq(Long resId);

	List<SysResource> findByParentIdAndUserIdOrderBySeq(Long resId, Long userId);

	int countByResKeyOrResUrl(String resKey, String resUrl);

	int countByResKey(String resKey);

	int countByResUrl(String resUrl);

	List<Long> findResourceIdByRoleId(Long roleId);
}
