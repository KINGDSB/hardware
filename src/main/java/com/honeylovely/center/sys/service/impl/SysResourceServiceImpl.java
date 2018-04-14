package com.honeylovely.center.sys.service.impl;

import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.common.abstracts.AbstractServiceImpl;
import com.honeylovely.center.sys.dao.SysResourceDAO;
import com.honeylovely.center.sys.entity.SysResource;
import com.honeylovely.center.sys.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by ryan on 2016-03-01.
 */
@Service("sysResourceService")
public class SysResourceServiceImpl extends AbstractServiceImpl<SysResource, Long> implements SysResourceService {
	@Autowired
	private SysResourceDAO sysResourceDAO;

	@Override
	public AbstractDAO<SysResource, Long> getAbstractDAO() {
		return sysResourceDAO;
	}

	@Override
	public List<SysResource> findResourcesByUserId(Long userId) {
		return sysResourceDAO.findResourcesByUserId(userId);
	}

	@Override
	public String findReskeyByResUrl(String resourceUrl) {
		SysResource res = sysResourceDAO.findByResUrl(resourceUrl);

		return res == null ? null : res.getResKey();
	}

	@Override
	public List<SysResource> findByParentId(Long resId) {
		return sysResourceDAO.findByParentIdOrderBySeq(resId);
	}

	@Override
	public List<SysResource> findByParentIdAndUserId(Long parentId, Long userId) {
		return sysResourceDAO.findByParentIdAndUserIdOrderBySeq(parentId, userId);
	}

	@Override
	public boolean exists(String resKey, String resUrl) {
		if (StringUtils.isEmpty(resKey)) {
			return sysResourceDAO.countByResUrl(resUrl) > 0;
		} else if (StringUtils.isEmpty(resUrl)) {
			return sysResourceDAO.countByResKey(resKey) > 0;
		}
		return sysResourceDAO.countByResKeyOrResUrl(resKey, resUrl) > 0;
	}

	@Override
	public List<Long> findResourceIdsByRoleId(Long roleId) {
		return sysResourceDAO.findResourceIdByRoleId(roleId);
	}

	@Override
	public List<SysResource> listByWhere(Example example) {
		return sysResourceDAO.selectByExample(example);
	}

}