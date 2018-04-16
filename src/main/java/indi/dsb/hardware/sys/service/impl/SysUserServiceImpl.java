package indi.dsb.hardware.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.sys.dao.SysUserDAO;
import indi.dsb.hardware.sys.entity.SysUser;
import indi.dsb.hardware.sys.service.SysUserService;

import java.util.List;


@Service("sysUserService")
public class SysUserServiceImpl extends AbstractServiceImpl<SysUser, Long> implements SysUserService {
	@Autowired
	private SysUserDAO sysUserDAO;

	@Override
	public SysUser findByUsername(String username) {
		return sysUserDAO.findByUsername(username);
	}

	@Override
	@Transactional
	public SysUser add(SysUser user) {
		sysUserDAO.insert(user);
		return user;
	}

	@Override
	@Transactional
	public void saveUserRoles(Long userId, List<Long> roleIds, boolean hasRoles) {
		if (hasRoles)
			sysUserDAO.deleteByUserId(userId);

		sysUserDAO.addRoles(userId, roleIds);
	}

	@Override
	public boolean exist(String username) {
		return sysUserDAO.countByUsername(username) > 0;
	}

	@Override
	public int updatePassword(String password, String salt, Long id) {
		return sysUserDAO.updatePassword(password, salt, id);
	}

	@Override
	public AbstractDAO<SysUser, Long> getAbstractDAO() {
		return sysUserDAO;
	}

}