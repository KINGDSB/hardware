package com.honeylovely.center.sys.mapper;

import com.honeylovely.center.sys.entity.SysRole;
import com.honeylovely.center.sys.mapper.sqlprovider.SysRoleSqlProvider;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends Mapper<SysRole> {

	@Results(id = "resourceMap", value = { @Result(column = "id", property = "id"),
			@Result(column = "name", property = "name"), @Result(column = "status", property = "status"),
			@Result(column = "role_key", property = "roleKey"),
			@Result(column = "description", property = "description"),
			@Result(column = "created_date", property = "createdDate"),
			@Result(column = "updated_date", property = "updatedDate"),
			@Result(column = "is_deleted", property = "isDeleted"),
			@Result(column = "created_by", property = "createdBy"),
			@Result(column = "updated_by", property = "updatedBy") })
	@SelectProvider(method = "findUserRoles", type = SysRoleSqlProvider.class)
	List<SysRole> findUserRoles(@Param("userId") Long userId);

	/*@ResultMap("resourceMap")
	@SelectProvider(method = "findUserRoles", type = SysRoleSqlProvider.class)
	List<SysRole> findRoleByWhere(Map<String, Object> requestParams);*/

	@ResultType(Integer.class)
	@Update("update sys_role r set r.status = #{status}, r.role_key = #{roleKey}, r.name = #{name}, r.description = #{description}, is_deleted = #{isDeleted} where r.id = #{id}")
	Integer update(@Param("id") Long id, @Param("status") Byte status, @Param("roleKey") String roleKey,
                   @Param("name") String name, @Param("description") String description,
                   @Param("isDeleted") Boolean isDeleted);

	@ResultType(Integer.class)
	@DeleteProvider(method = "remove", type = SysRoleSqlProvider.class)
	Integer remove(Map<String, Object> requestParams);

	@ResultType(Integer.class)
	@InsertProvider(method = "add", type = SysRoleSqlProvider.class)
	Integer add(Map<String, Object> requestParams);
}