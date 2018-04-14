package com.honeylovely.center.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.common.abstracts.AbstractServiceImpl;
import com.honeylovely.center.sys.dao.ProjectMemberDAO;
import com.honeylovely.center.sys.entity.ProjectMember;
import com.honeylovely.center.sys.service.ProjectMemberService;

/**
 * Created by ryan on 2016-03-01.
 */
@Service("projectMemberService")
public class ProjectMemberServiceImpl extends AbstractServiceImpl<ProjectMember, Long> implements ProjectMemberService {
    @Autowired
    private ProjectMemberDAO projectMemberDAO;

    @Override
    public AbstractDAO<ProjectMember, Long> getAbstractDAO() {
        return projectMemberDAO;
    }

}