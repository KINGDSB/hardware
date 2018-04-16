package indi.dsb.hardware.sys.mapper.sqlprovider;

import java.util.List;
import java.util.Map;

public class SysUserSqlProvider {

	public String addRoles(Map<String, Object> requestParams) {
		Long userId = requestParams.containsKey("userId") ? (Long) requestParams.get("userId") : -1;
		List<Long> roleIds = requestParams.containsKey("roleIds") ? (List<Long>) requestParams.get("roleIds") : null;

		StringBuilder sql = new StringBuilder("insert into sys_user_role (user_id, role_id) values ");
		for (int i = 0; i < roleIds.size(); i++) {
			sql.append("(");
			sql.append(userId);
			sql.append(",");
			sql.append(roleIds.get(i));
			sql.append(")");

			if (i < roleIds.size() - 1)
				sql.append(",");
		}

		return sql.toString();
	}
}
