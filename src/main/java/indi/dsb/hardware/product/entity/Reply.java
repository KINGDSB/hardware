package indi.dsb.hardware.product.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import indi.dsb.hardware.common.abstracts.AbstractEntity;
import indi.dsb.hardware.sys.entity.SysUser;

/**
 * Created by Administrator on 2017/12/7.
 */
@Entity
@Table(name = "f_reply")
public class Reply extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "matter_id")
	private Long matterId;//问题id
	private Integer type;//类型 1:项目经理回复 2:处理人回复
	private String solution;//解决方案
	private String attachment;//附件
	@Column(name = "user_id")
	private Long userId;//处理人id
	@Column(name = "sub_status")
	private Integer subStatus;//处理状态 1：未处理 2：已处理

    @Transient
    private SysUser user;// 处理人
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMatterId() {
		return matterId;
	}
	public void setMatterId(Long matterId) {
		this.matterId = matterId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
    public Integer getSubStatus() {
        return subStatus;
    }
    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }
    public SysUser getUser() {
        return user;
    }
    public void setUser(SysUser user) {
        this.user = user;
    }
}
