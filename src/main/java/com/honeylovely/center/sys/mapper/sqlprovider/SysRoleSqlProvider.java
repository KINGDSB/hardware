package com.honeylovely.center.sys.mapper.sqlprovider;


import com.honeylovely.center.common.utils.OperateType;
import com.honeylovely.center.common.utils.QueryParam;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

public class SysRoleSqlProvider {

	private static final String findUserRoles = "select r.* "
			+ " from sys_role r join sys_user_role ur on r.id = ur.role_id"
			+ " where ur.user_id = #{userId} and r.is_deleted is false";

	private static final String findRoleByWhere = "select r.* from sys_role r join sys_user_role ur on r.id = ur.role_id where 1=1 ";

	public String findUserRoles() {
		return findUserRoles;
	}

	public String findRoleByWhere(Map<String, Object> requestParams) {
		String condition = buildWhere(requestParams);

		StringBuilder sql = new StringBuilder(findRoleByWhere);

		if (StringUtils.isNotEmpty(condition)) {
			sql.append(condition);
		}

		return sql.toString();
	}

	public String add(Map<String, Object> requestParams) {
		Long roleId = requestParams.containsKey("roleId") ? (Long) requestParams.get("roleId") : -1;
		List<Long> addIds = requestParams.containsKey("addIds") ? (List<Long>) requestParams.get("addIds") : null;

		StringBuilder sql = new StringBuilder("insert into sys_role_resource (resource_id, role_id) values ");
		for (int i = 0; i < addIds.size(); i++) {
			sql.append("(");
			sql.append(addIds.get(i));
			sql.append(",");
			sql.append(roleId);
			sql.append(")");

			if (i < addIds.size() - 1)
				sql.append(",");
		}

		return sql.toString();
	}

	public String remove(Map<String, Object> requestParams) {
		Long roleId = requestParams.containsKey("roleId") ? (Long) requestParams.get("roleId") : -1;
		List<Long> removeIds = requestParams.containsKey("removeIds") ? (List<Long>) requestParams.get("removeIds")
				: null;

		StringBuilder sql = new StringBuilder(
				"delete from sys_role_resource where role_id = " + roleId + " and resource_id in ");

		sql.append("(");
		for (int i = 0; i < removeIds.size(); i++) {
			sql.append(removeIds.get(i));

			if (i < removeIds.size() - 1)
				sql.append(",");
		}
		sql.append(")");

		return sql.toString();
	}

	public String buildWhere(Map<String, Object> requestParams) {
		List<QueryParam> params = requestParams.containsKey("params") ? (List<QueryParam>) requestParams.get("params")
				: null;

		StringBuilder where = new StringBuilder();

		if (params == null || params.size() == 0) {
			return where.toString();
		}

		for (int i = 0; i < params.size(); i++) {
			QueryParam queryParam = params.get(i);
			OperateType operateType = queryParam.getOperateType();
			if (i > 0) {
				where.append(" and ");
			}
			where.append(queryParam.getPropertyName());
			switch (operateType.ordinal()) {
			case 1:
				where.append(queryParam.getOperateType().operater);
				where.append(queryParam.getValue());
				break;
			case 2:
				where.append(queryParam.getOperateType().operater);
				where.append(queryParam.getValue());
				break;
			case 3:
				where.append(queryParam.getOperateType().operater);
				where.append(queryParam.getValue());
				break;
			case 4:
				where.append(queryParam.getOperateType().operater);
				where.append(queryParam.getValue());
				break;
			case 5:
				where.append(queryParam.getOperateType().operater);
				where.append(queryParam.getValue());
				break;
			case 6:
				where.append(queryParam.getOperateType().operater);
				where.append(queryParam.getValue());
				break;
			case 7:
				where.append(queryParam.getOperateType().operater);
				break;
			case 8:
				where.append(queryParam.getOperateType().operater);
				break;
			case 9:
				where.append(queryParam.getOperateType().operater);
				break;
			case 10:
				where.append(queryParam.getOperateType().operater);
				break;
			default:
				where.append(OperateType.EQ.operater);
				where.append(queryParam.getValue());
			}
		}

		return where.toString();
	}
}
