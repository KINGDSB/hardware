package indi.dsb.hardware.sys.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.sys.dao.ProjectDAO;
import indi.dsb.hardware.sys.entity.Project;
import indi.dsb.hardware.sys.entity.ProjectMember;
import indi.dsb.hardware.sys.mapper.ProjectMapper;
import tk.mybatis.mapper.common.Mapper;

@Repository
public class ProjectDAOImpl extends AbstractDAOImpl<Project, Long> implements ProjectDAO {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Mapper<Project> getMapper() {
        return projectMapper;
    }

    @Override
    public Project getProjectInfo(Long id) {
        return projectMapper.getProjectInfo(id);
    }

    @Override
    public List<ProjectMember> getProjectMambersByProjectId(Long projectId) {
        return projectMapper.getProjectMambersByProjectId(projectId);
    }

}
