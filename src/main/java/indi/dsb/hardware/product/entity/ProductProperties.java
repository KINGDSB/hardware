package indi.dsb.hardware.product.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import indi.dsb.hardware.common.abstracts.AbstractEntity;

/**
 * @Title: ProductSeries.java 
 * @Package indi.dsb.hardware.product.entity 
 * @Description: 产品属性
 * @author dsb
 * @date 2018年5月16日 下午2:58:19
 */
@Entity
@Table(name = "f_product_properties")
public class ProductProperties extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name = "product_id")
	private Long productId;// 产品id
    @Column(name = "param_name_cn")
	private String paramNameCn;// 商品参数名称
    @Column(name = "param_name_en")
    private String paramNameEn;// 商品参数名称
    @Column(name = "param_value")
    private String paramValue;// 参数值

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getParamNameCn() {
		return paramNameCn;
	}

	public void setParamNameCn(String paramNameCn) {
		this.paramNameCn = paramNameCn;
	}

	public String getParamNameEn() {
		return paramNameEn;
	}

	public void setParamNameEn(String paramNameEn) {
		this.paramNameEn = paramNameEn;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}


}
