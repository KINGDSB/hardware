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

/**
 * Created by Administrator on 2017/12/7.
 */
@Entity
@Table(name = "f_product")
public class Product extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name = "name_cn")
    private String nameCn;// 中文名称
    @Column(name = "name_en")
    private String nameEn;// 英文名称
	private Integer type;// 类型 关联产品类型表
    private String description;// 产品描述
    @Column(name = "pic_urls")
	private String picUrls;// 图片
	private Integer status;// 状态 1:未上架 2：上架中
	private String remark;// 备注

	@Transient
	private ProductType productType;
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPicUrls() {
        return picUrls;
    }
    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

}
