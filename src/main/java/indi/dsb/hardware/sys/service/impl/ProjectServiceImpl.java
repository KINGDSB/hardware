package indi.dsb.hardware.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.sys.dao.ProjectDAO;
import indi.dsb.hardware.sys.entity.Project;
import indi.dsb.hardware.sys.entity.ProjectMember;
import indi.dsb.hardware.sys.service.ProjectService;

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