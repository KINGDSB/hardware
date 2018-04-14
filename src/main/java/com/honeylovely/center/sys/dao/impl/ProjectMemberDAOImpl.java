package com.honeylovely.center.sys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.honeylovely.center.common.abstracts.AbstractDAOImpl;
import com.honeylovely.center.sys.dao.ProjectMemberDAO;
import com.honeylovely.center.sys.entity.ProjectMember;
import com.honeylovely.center.sys.mapper.ProjectMemberMapper;

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
