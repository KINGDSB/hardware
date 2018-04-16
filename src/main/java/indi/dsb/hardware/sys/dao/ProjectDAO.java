/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package indi.dsb.hardware.sys.dao;



import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.sys.entity.Project;
import indi.dsb.hardware.sys.entity.ProjectMember;

/**
 * @version 1.0
 * @author
 */
public interface ProjectDAO extends AbstractDAO<Project, Long> {

    /**
     * @Title getProjectInfo 
     * @Description 根据项目id获取信息
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
