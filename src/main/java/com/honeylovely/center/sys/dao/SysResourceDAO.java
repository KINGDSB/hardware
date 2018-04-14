/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.honeylovely.center.sys.dao;



import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.sys.entity.SysResource;

import java.util.List;

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
