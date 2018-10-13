package indi.dsb.hardware.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
@SuppressWarnings("serial")
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
	private Integer type;// 类型 实际对应的是 series系列
    @Column(name = "amount_old")
    private BigDecimal amountOld;// 促销前金额
    private BigDecimal amount;// 金额
    private String description;// 产品描述 用来放产品文字说明了
    @Column(name = "description_es")
    private String descriptionEs;// 产品描述 用来放产品文字说明了 西班牙语
	private String picture;// 图片
    @Column(name = "new_product")
	private Integer newProduct;// 是否是新产品 1:是 2:否
	private Integer status;// 状态 1:未上架 2：上架中
	private String remark;// 备注
	private String specification;// 规格
    @Column(name = "packing_quantity")
	private String packingQuantity;// 包装量
	private String material;// 材质 用来放标题了
	private String accessories;// 附件
	private String fitting;// 配件
	private String other;// 其他 用来放表格数据了
	private String pictures;// 详情图片

	@Transient
	private ProductType productType;

	@Transient
	private List<ProductProperties> productProperties;

	@Transient
	private Set<Object> paramKeys; // 参数keys

	@Transient
	private List<Map<Object, Object>> paramTable; // 参数表格
	
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
    public String getDescriptionEs() {
		return descriptionEs;
	}
	public void setDescriptionEs(String descriptionEs) {
		this.descriptionEs = descriptionEs;
	}
	public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
	public Integer getNewProduct() {
		return newProduct;
	}
	public void setNewProduct(Integer newProduct) {
		this.newProduct = newProduct;
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
    public BigDecimal getAmountOld() {
        return amountOld;
    }
    public void setAmountOld(BigDecimal amountOld) {
        this.amountOld = amountOld;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getPackingQuantity() {
		return packingQuantity;
	}
	public void setPackingQuantity(String packingQuantity) {
		this.packingQuantity = packingQuantity;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getAccessories() {
		return accessories;
	}
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	public String getFitting() {
		return fitting;
	}
	public void setFitting(String fitting) {
		this.fitting = fitting;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	public List<ProductProperties> getProductProperties() {
		return productProperties;
	}
	public void setProductProperties(List<ProductProperties> productProperties) {
		this.productProperties = productProperties;
	}
	public List<Map<Object, Object>> getParamTable() {
		return paramTable;
	}
	public void setParamTable(List<Map<Object, Object>> paramTable) {
		this.paramTable = paramTable;
	}
	public Set<Object> getParamKeys() {
		return paramKeys;
	}
	public void setParamKeys(Set<Object> paramKeys) {
		this.paramKeys = paramKeys;
	}

}
