package com.honeylovely.center.sys.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.honeylovely.center.common.abstracts.AbstractDAOImpl;
import com.honeylovely.center.sys.dao.ProjectDAO;
import com.honeylovely.center.sys.entity.Project;
import com.honeylovely.center.sys.entity.ProjectMember;
import com.honeylovely.center.sys.mapper.ProjectMapper;
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
