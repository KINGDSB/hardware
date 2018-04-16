package indi.dsb.hardware.sys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.sys.dao.SysResourceDAO;
import indi.dsb.hardware.sys.entity.SysResource;
import indi.dsb.hardware.sys.mapper.SysResourceMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public class SysResourceDAOImpl extends AbstractDAOImpl<SysResource, Long> implements SysResourceDAO {
	@Autowired
	private SysResourceMapper sysResourceMapper;

	@Override
	public Mapper<SysResource> getMapper() {
		return sysResourceMapper;
	}

	@Override
	public List<SysResource> findResourcesByUserId(Long userId) {
		return sysResourceMapper.findResourcesByUserId(userId);
	}

	@Override
	public SysResource findByResUrl(String resourceUrl) {
		return sysResourceMapper.findByResUrl(resourceUrl);
	}

	@Override
	public List<SysResource> findByParentIdOrderBySeq(Long resId) {
		return sysResourceMapper.findByParentIdOrderBySeq(resId);
	}

	@Override
	public List<SysResource> findByParentIdAndUserIdOrderBySeq(Long resId, Long userId) {
		return sysResourceMapper.findByParentIdAndUserIdOrderBySeq(resId, userId);
	}

	@Override
	public int countByResKeyOrResUrl(String resKey, String resUrl) {
		return sysResourceMapper.countByResKeyOrResUrl(resKey, resUrl);
	}

	@Override
	public int countByResKey(String resKey) {
		return sysResourceMapper.countByResKey(resKey);
	}

	@Override
	public int countByResUrl(String resUrl) {
		return sysResourceMapper.countByResUrl(resUrl);
	}

	@Override
	public List<Long> findResourceIdByRoleId(Long roleId) {
		return sysResourceMapper.findResourceIdByRoleId(roleId);
	}
}
