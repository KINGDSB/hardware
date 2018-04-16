package indi.dsb.hardware.sys.service;

import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.sys.entity.Project;
import indi.dsb.hardware.sys.entity.ProjectMember;

/**
 * Created by ryan on 2016-03-01.
 */
public interface ProjectService extends AbstractService<Project, Long> {
    
    /**
     * @Title getProjectInfo 
     * @Description 根据项目id获取详情
     * @param id
     * @return
     */
    Project getProjectInfo(Long id);
    
    /**
     * @Title getProjectMambersByProjectId 
     * @Description 根据项目id查询项目成员列表
     * @param projectId
     * @return
     */
    List<ProjectMember> getProjectMambersByProjectId(Long projectId);
}