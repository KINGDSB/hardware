package com.honeylovely.center.sys.mapper.sqlprovider;

public class SysResourceSqlProvider {

	private static final String findResourcesByUserId = "select r.* from sys_user u"
			+ " join sys_user_role ul on ul.user_id = u.id join sys_role_resource rr on rr.role_id = ul.role_id"
			+ " join sys_resource r on r.id = rr.resource_id"
			+ " where u.id = #{userId} and r.is_hidden is false and r.is_deleted is false  and r.level <> 2 "
			+ " group by r.id order by r.level asc, r.seq asc";

	private static final String findByParentIdAndUserIdOrderBySeq = "select r.* from sys_user u"
			+ " join sys_user_role ul on ul.user_id = u.id join sys_role_resource rr on rr.role_id = ul.role_id"
			+ " join sys_resource r on r.id = rr.resource_id"
			+ " where u.id = #{userId} and r.parent_id = #{parentId} and r.is_hidden is false and r.is_deleted is false "
			+ " group by r.id order by r.seq asc";

	private static final String findResourceIdByRoleId = "select resource_id from sys_role_resource where role_id = #{roleId} ";

	public String findResourcesByUserId() {
		return findResourcesByUserId;
	}

	public String findByParentIdAndUserIdOrderBySeq() {
		return findByParentIdAndUserIdOrderBySeq;
	}

	public String findResourceIdByRoleId() {
		return findResourceIdByRoleId;
	}
}
