package com.honeylovely.center.sys.service;

import java.util.List;

import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.sys.entity.Project;
import com.honeylovely.center.sys.entity.ProjectMember;

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