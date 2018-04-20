package indi.dsb.hardware.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import indi.dsb.hardware.common.abstracts.AbstractEntity;

@Entity
@Table(name = "sys_static_data")
public class SysStaticData extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -468637964532316295L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_key")
    private String dataKey;
    @Column(name = "data_value_en")
    private String dataValueEn;
    @Column(name = "data_value_cn")
    private String dataValueCn;
    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValueEn() {
        return dataValueEn;
    }

    public void setDataValueEn(String dataValueEn) {
        this.dataValueEn = dataValueEn;
    }

    public String getDataValueCn() {
        return dataValueCn;
    }

    public void setDataValueCn(String dataValueCn) {
        this.dataValueCn = dataValueCn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}