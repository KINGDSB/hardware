package indi.dsb.hardware.sys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.sys.dao.ProjectMemberDAO;
import indi.dsb.hardware.sys.entity.ProjectMember;
import indi.dsb.hardware.sys.mapper.ProjectMemberMapper;
import tk.mybatis.mapper.common.Mapper;

@Repository
public class ProjectMemberDAOImpl extends AbstractDAOImpl<ProjectMember, Long> implements ProjectMemberDAO {
    @Autowired
    private ProjectMemberMapper projectMemberMapper;

    @Override
    public Mapper<ProjectMember> getMapper() {
        return projectMemberMapper;
    }


}
