package indi.dsb.hardware.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import indi.dsb.hardware.sys.entity.Project;
import indi.dsb.hardware.sys.entity.ProjectMember;
import indi.dsb.hardware.sys.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectMapper extends Mapper<Project> {
    
    @Select("SELECT id, name FROM sys_user WHERE id = #{memberId}")  
    SysUser getUserByMemberId(@Param("memberId")int memberId); 
    
    @Select(" SELECT id, type, member_id FROM f_project_member WHERE is_deleted = 0 AND project_id = #{projectId} ORDER BY type, created_date")
    @Results(id = "projectMemberMap", value = { 
        @Result(column = "id", property = "id", id = true),
        @Result(column = "type", property = "type"), 
        @Result(column = "member_id", property = "member", one = @One(select = "com.honeylovely.center.sys.mapper.ProjectMapper.getUserByMemberId"))})
    List<ProjectMember> getProjectMambersByProjectId(@Param("projectId") Long projectId);
    
    /**
     * @Title getProjectInfo 
     * @Description 获取项目信息
     * @param id
     * @return
     */
    @Select(" SELECT * FROM f_project WHERE is_deleted = 0 AND id = #{projectId} ")
    @Results(id = "projectMap", value = { 
        @Result(column = "id", property = "id", id = true),
        @Result(column = "name", property = "name"), 
        @Result(column = "version", property = "version"),
        @Result(column = "id", property = "members", 
        many = @Many(select="com.honeylovely.center.sys.mapper.ProjectMapper.getProjectMambersByProjectId"))})
    Project getProjectInfo(@Param("projectId") Long projectId);
}