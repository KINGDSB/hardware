package indi.dsb.hardware.common.abstracts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import indi.dsb.hardware.sys.entity.SysUser;

@MappedSuperclass
public class AbstractEntity {
	@Column(name = "created_date")
	@CreatedDate
	private Date createdDate;
	@Column(name = "updated_date")
	@LastModifiedDate
	private Date updatedDate;
	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;
	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "is_deleted")
	private Boolean isDeleted = false;

	@Transient
	private String keyWord;// 关键词查询
    @Transient
    private Long queryUserId;// 根据用户过滤数据
    @Transient
    private String createdDateStr;// 创建日期字符串用做sql条件查询

    @Transient
    private SysUser createdUser;// 创建人
    
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

    public Long getQueryUserId() {
        return queryUserId;
    }

    public void setQueryUserId(Long queryUserId) {
        this.queryUserId = queryUserId;
    }

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }

    public SysUser getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(SysUser createdUser) {
        this.createdUser = createdUser;
    }

}