package indi.dsb.hardware.sys.mapper;

import org.apache.ibatis.annotations.*;

import indi.dsb.hardware.sys.entity.SysResource;
import indi.dsb.hardware.sys.mapper.sqlprovider.SysResourceSqlProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {

	@ResultMap("resourceMap")
	@SelectProvider(method = "findResourcesByUserId", type = SysResourceSqlProvider.class)
	List<SysResource> findResourcesByUserId(@Param("userId") Long userId);

	@Results(id = "resourceMap", value = { @Result(column = "id", property = "id"),
			@Result(column = "name", property = "name"), @Result(column = "parent_id", property = "parentId"),
			@Result(column = "res_key", property = "resKey"), @Result(column = "res_url", property = "resUrl"),
			@Result(column = "level", property = "level"), @Result(column = "icon", property = "icon"),
			@Result(column = "is_hidden", property = "isHidden"), @Result(column = "seq", property = "seq"),
			@Result(column = "description", property = "description"),
			@Result(column = "created_date", property = "createdDate"),
			@Result(column = "updated_date", property = "updatedDate"),
			@Result(column = "is_deleted", property = "isDeleted"),
			@Result(column = "created_by", property = "createdBy"),
			@Result(column = "updated_by", property = "updatedBy") })
	@Select("select res.* from sys_resource res where res.is_deleted is false and res.res_url = #{resourceUrl}")
	SysResource findByResUrl(@Param("resourceUrl") String resourceUrl);

	@ResultMap("resourceMap")
	@Select("select res.* from sys_resource res where res.is_deleted is false and res.parent_id = #{resId} order by res.seq")
	List<SysResource> findByParentIdOrderBySeq(@Param("resId") Long resId);

	@ResultMap("resourceMap")
	@SelectProvider(method = "findByParentIdAndUserIdOrderBySeq", type = SysResourceSqlProvider.class)
	List<SysResource> findByParentIdAndUserIdOrderBySeq(@Param("parentId") Long resId, @Param("userId") Long userId);

	@ResultType(Integer.class)
	@Select("select count(*) from sys_resource r where (r.res_key = #{resKey} or r.res_url = #{resUrl}) and r.is_deleted is false")
	Integer countByResKeyOrResUrl(@Param("resKey") String resKey, @Param("resUrl") String resUrl);

	@ResultType(Integer.class)
	@Select("select count(*) from sys_resource r where r.res_key = #{resKey} and r.is_deleted is false")
	Integer countByResKey(@Param("resKey") String resKey);

	@ResultType(Integer.class)
	@Select("select count(*) from sys_resource r where r.res_url = #{resUrl} and r.is_deleted is false")
	Integer countByResUrl(@Param("resUrl") String resUrl);

	@ResultType(Long.class)
	@SelectProvider(method = "findResourceIdByRoleId", type = SysResourceSqlProvider.class)
	List<Long> findResourceIdByRoleId(@Param("roleId") Long roleId);
}