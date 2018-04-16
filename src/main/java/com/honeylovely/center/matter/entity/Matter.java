package com.honeylovely.center.matter.entity;

import com.honeylovely.center.common.abstracts.AbstractEntity;
import com.honeylovely.center.sys.entity.Project;
import com.honeylovely.center.sys.entity.SysUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/7.
 */
@Entity
@Table(name = "f_matter")
public class Matter extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "project_id")
	private Long projectId;// 项目id
//	@Column(name = "type")
	private Integer type;// 类型 关联问题类型表
//	@Column(name = "grade")
	private Integer grade;// 处理等级 1：低级 2：中级 3：紧急 4：致命
	@Column(name = "feedback_uid")
	private Long feedbackUid;// 反馈人id
    @Column(name = "product_uid")
    private Long productUid;// 项目经理id
    @Column(name = "reply_uid")
    private String replyUid;// 处理人id
	@Column(name = "expect_time")
	private Date expectTime;// 预计完成日期
	@Column(name = "true_time")
	private Date trueTime;// 实际完成日期
//	@Column(name = "description")
	private String description;// 问题描述
//	@Column(name = "attachment")
	private String attachment;// 附件
	@Column(name = "process_status")
	private Integer processStatus;// 问题状态 1:待处理 2：处理中 3：待确认 4：未解决 5：已解决 6：申请延期中
//	@Column(name = "remark")
	private String remark;// 备注 存延迟申请回传的requestid

	@Transient
	private MatterType matterType;// 问题类型
	@Transient
	private Project project;// 项目
	@Transient
	private SysUser feedbackUser;// 反馈人
	@Transient
	private SysUser productUser;// 项目经理
    @Transient
    private String replyNames;// 处理人/负责人id
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Long getFeedbackUid() {
		return feedbackUid;
	}
	public void setFeedbackUid(Long feedbackUid) {
		this.feedbackUid = feedbackUid;
	}
	public Long getProductUid() {
		return productUid;
	}
	public void setProductUid(Long productUid) {
		this.productUid = productUid;
	}
	public Date getExpectTime() {
		return expectTime;
	}
	public void setExpectTime(Date expectTime) {
		this.expectTime = expectTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Integer getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public MatterType getMatterType() {
		return matterType;
	}
	public void setMatterType(MatterType matterType) {
		this.matterType = matterType;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public SysUser getFeedbackUser() {
		return feedbackUser;
	}
	public void setFeedbackUser(SysUser feedbackUser) {
		this.feedbackUser = feedbackUser;
	}
	public Date getTrueTime() {
		return trueTime;
	}
	public void setTrueTime(Date trueTime) {
		this.trueTime = trueTime;
	}
	public SysUser getProductUser() {
		return productUser;
	}
	public void setProductUser(SysUser productUser) {
		this.productUser = productUser;
	}
    public String getReplyUid() {
        return replyUid;
    }
    public void setReplyUid(String replyUid) {
        this.replyUid = replyUid;
    }
    public String getReplyNames() {
        return replyNames;
    }
    public void setReplyNames(String replyNames) {
        this.replyNames = replyNames;
    }

}
