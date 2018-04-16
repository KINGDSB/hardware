package com.honeylovely.center.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.common.abstracts.AbstractServiceImpl;
import com.honeylovely.center.sys.dao.ProjectDAO;
import com.honeylovely.center.sys.entity.Project;
import com.honeylovely.center.sys.entity.ProjectMember;
import com.honeylovely.center.sys.service.ProjectService;

/**
 * Created by ryan on 2016-03-01.
 */
@Service("projectService")
public class ProjectServiceImpl extends AbstractServiceImpl<Project, Long> implements ProjectService {
	@Autowired
	private ProjectDAO projectDAO;

	@Override
	public AbstractDAO<Project, Long> getAbstractDAO() {
		return projectDAO;
	}

    @Override
    public Project getProjectInfo(Long id) {
        return projectDAO.getProjectInfo(id);
    }

    @Override
    public List<ProjectMember> getProjectMambersByProjectId(Long projectId) {
        return projectDAO.getProjectMambersByProjectId(projectId);
    }

}