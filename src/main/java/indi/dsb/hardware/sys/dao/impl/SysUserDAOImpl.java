package indi.dsb.hardware.sys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.sys.dao.SysUserDAO;
import indi.dsb.hardware.sys.entity.SysUser;
import indi.dsb.hardware.sys.mapper.SysUserMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/18.
 */
@Repository
public class SysUserDAOImpl extends AbstractDAOImpl<SysUser, Long> implements SysUserDAO {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public Mapper<SysUser> getMapper() {
		return sysUserMapper;
	}

	@Override
	public SysUser findByUsername(String username) {
		return sysUserMapper.findByUsername(username);
	}

	@Override
	public int deleteByUserId(Long userId) {
		return sysUserMapper.deleteByUserId(userId);
	}

	@Override
	public int addRoles(Long userId, List<Long> roleIds) {
		Map<String, Object> requestParams = new HashMap<>();
		if (userId != null)
			requestParams.put("userId", userId);
		if (roleIds.size() > 0)
			requestParams.put("roleIds", roleIds);

		return sysUserMapper.addRoles(requestParams);
	}

	@Override
	public Long countByUsername(String username) {
		return sysUserMapper.countByUsername(username).longValue();
	}

	@Override
	public int updatePassword(String password, String salt, Long id) {
		return sysUserMapper.updatePassword(password, salt, id);
	}
}
