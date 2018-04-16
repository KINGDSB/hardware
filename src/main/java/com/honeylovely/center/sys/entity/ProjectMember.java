package com.honeylovely.center.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.honeylovely.center.common.abstracts.AbstractEntity;

/**
 * Created by Administrator on 2017/12/7.
 */
@Entity
@Table(name = "f_project_member")
public class ProjectMember extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name = "project_id")
    private Long projectId;// 项目id
    @Column(name = "member_id")
    private Long memberId;// 成员id
	private Integer type;// 成员类型1:项目经理 2:普通成员

    @Transient
	private SysUser member;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

    public SysUser getMember() {
        return member;
    }

    public void setMember(SysUser member) {
        this.member = member;
    }

}
