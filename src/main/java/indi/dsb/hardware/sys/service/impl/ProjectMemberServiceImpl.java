package indi.dsb.hardware.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.sys.dao.ProjectMemberDAO;
import indi.dsb.hardware.sys.entity.ProjectMember;
import indi.dsb.hardware.sys.service.ProjectMemberService;

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