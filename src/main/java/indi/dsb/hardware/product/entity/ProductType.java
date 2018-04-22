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
 * Created by Administrator on 2017/12/7.
 */
@Entity
@Table(name = "f_product_type")
public class ProductType extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name = "name_cn")
	private String nameCn;// 产品类型名称
    @Column(name = "name_en")
	private String nameEn;// 产品类型名称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
