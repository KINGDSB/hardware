package indi.dsb.hardware.sys.mapper;

import org.apache.ibatis.annotations.*;

import indi.dsb.hardware.sys.entity.SysUser;
import indi.dsb.hardware.sys.mapper.sqlprovider.SysUserSqlProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {

	@Results(value = { @Result(column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "username", property = "username"), @Result(column = "password", property = "password"),
			@Result(column = "gender", property = "gender"), @Result(column = "birthday", property = "birthday"),
			@Result(column = "credentials_salt", property = "credentialsSalt"),
			@Result(column = "mobile", property = "mobile"), @Result(column = "email", property = "email"),
			@Result(column = "description", property = "description"),
			@Result(column = "is_locked", property = "isLocked"),
			@Result(column = "created_date", property = "createdDate"),
			@Result(column = "updated_date", property = "updatedDate"),
			@Result(column = "is_deleted", property = "isDeleted"),
			@Result(column = "created_by", property = "createdBy"),
			@Result(column = "updated_by", property = "updatedBy") })
	@Select("select u.* from sys_user u where u.username = #{username} and u.is_deleted is false")
	SysUser findByUsername(@Param("username") String username);

	@ResultType(Integer.class)
	@Delete("delete from sys_user_role where user_id = #{userId} ")
	Integer deleteByUserId(@Param("userId") Long userId);

	@ResultType(Integer.class)
	@InsertProvider(method = "addRoles", type = SysUserSqlProvider.class)
	Integer addRoles(Map<String, Object> requestParams);

	@ResultType(Integer.class)
	@Select("select count(*) from sys_user u where u.username = #{username} and u.is_deleted is false")
	Integer countByUsername(@Param("username") String username);

	@ResultType(Integer.class)
	@Update("update sys_user u set u.password = #{password}, u.credentials_salt = #{salt} where u.id = #{id}")
	Integer updatePassword(@Param("password") String password, @Param("salt") String salt, @Param("id") Long id);
}