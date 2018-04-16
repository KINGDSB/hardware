package com.honeylovely.center.sys.service.impl;

import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.common.abstracts.AbstractServiceImpl;
import com.honeylovely.center.sys.dao.SysRoleDAO;
import com.honeylovely.center.sys.entity.SysRole;
import com.honeylovely.center.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("sysRoleService")
public class SysRoleServiceImpl extends AbstractServiceImpl<SysRole, Long> implements SysRoleService {
	@Autowired
	private SysRoleDAO sysRoleDAO;

	public SysRoleServiceImpl() {
	}

	public AbstractDAO<SysRole, Long> getAbstractDAO() {
		return this.sysRoleDAO;
	}

	public List<SysRole> findUserRoles(Long userId) {
		return this.sysRoleDAO.findUserRoles(userId);
	}


	public int update(SysRole sysRole) {
		return this.sysRoleDAO.update(sysRole.getId(), sysRole.getStatus(), sysRole.getRoleKey(), sysRole.getName(), sysRole.getDescription(), sysRole.getIsDeleted());
	}

	@Transactional
	public void saveResourceChange(Long roleId, List<Long> addIds, List<Long> removeIds) {
		if(removeIds != null && removeIds.size() > 0) {
			this.sysRoleDAO.remove(roleId, removeIds);
		}

		if(addIds != null && addIds.size() > 0) {
			this.sysRoleDAO.add(roleId, addIds);
		}

	}
}