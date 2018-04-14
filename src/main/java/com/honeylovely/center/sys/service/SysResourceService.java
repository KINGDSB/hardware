package com.honeylovely.center.sys.service;

import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.sys.entity.SysResource;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by ryan on 2016-03-01.
 */
public interface SysResourceService extends AbstractService<SysResource, Long> {
	List<SysResource> findResourcesByUserId(Long userId);

	String findReskeyByResUrl(String resourceUrl);

	List<SysResource> findByParentId(Long resId);

	List<SysResource> findByParentIdAndUserId(Long parentId, Long userId);

	boolean exists(String resKey, String resUrl);

	List<Long> findResourceIdsByRoleId(Long roleId);

	List<SysResource> listByWhere(Example example);
}