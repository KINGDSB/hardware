package indi.dsb.hardware.sys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.sys.dao.SysRoleDAO;
import indi.dsb.hardware.sys.entity.SysRole;
import indi.dsb.hardware.sys.mapper.SysRoleMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SysRoleDAOImpl extends AbstractDAOImpl<SysRole, Long> implements SysRoleDAO {
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public Mapper<SysRole> getMapper() {
		return sysRoleMapper;
	}

	@Override
	public List<SysRole> findUserRoles(Long userId) {
		return sysRoleMapper.findUserRoles(userId);
	}

	/*@Override
	public List<SysRole> findRoleByWhere(List<QueryParam> params) {
		Map<String, Object> requestParams = new HashMap<>();
		if (params.size() > 0)
			requestParams.put("params", params);
		return sysRoleMapper.findRoleByWhere(requestParams);
	}
*/
	@Override
	public int update(Long id, Byte status, String roleKey, String name, String description, Boolean isDeleted) {
		return sysRoleMapper.update(id, status, roleKey, name, description, isDeleted);
	}

	@Override
	public int remove(Long roleId, List<Long> removeIds) {
		Map<String, Object> requestParams = new HashMap<>();
		if (roleId != null)
			requestParams.put("roleId", roleId);
		if (removeIds.size() > 0)
			requestParams.put("removeIds", removeIds);

		return sysRoleMapper.remove(requestParams);
	}

	@Override
	public int add(Long roleId, List<Long> addIds) {
		Map<String, Object> requestParams = new HashMap<>();
		if (roleId != null)
			requestParams.put("roleId", roleId);
		if (addIds.size() > 0)
			requestParams.put("addIds", addIds);

		return sysRoleMapper.add(requestParams);
	}
}
