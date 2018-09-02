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
 * @Description: 产品表格列值
 * @author dsb
 * @date 2018年5月16日 下午2:58:19
 */
@Entity
@Table(name = "f_product_columns_value")
public class ProductColumnsValue extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name = "columns_id")
	private Long columnsId;
    @Column(name = "columns_value")
	private String columnsValue; // 产品类型名称
    @Column(name = "batch_number")
	private Long batchNumber; // 批次号

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getColumnsId() {
		return columnsId;
	}

	public void setColumnsId(Long columnsId) {
		this.columnsId = columnsId;
	}

	public String getColumnsValue() {
		return columnsValue;
	}

	public void setColumnsValue(String columnsValue) {
		this.columnsValue = columnsValue;
	}

	public Long getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(Long batchNumber) {
		this.batchNumber = batchNumber;
	}

}
