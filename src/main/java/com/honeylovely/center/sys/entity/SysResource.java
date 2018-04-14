package com.honeylovely.center.sys.entity;



import com.honeylovely.center.common.abstracts.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sys_resource")
public class SysResource extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -468637964532316295L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "res_key")
    private String resKey;
    @Column(name = "res_url")
    private String resUrl;
    private Integer level;
    private String icon;
    @Column(name = "is_hidden")
    private Boolean isHidden;
    @Column(name = "seq")
    private Integer seq;
    private String description;
    
    @Transient
    List<SysRole> roles;
    @Transient
    List<SysResource> subRes;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setParentId(Long parentId){
        this.parentId = parentId;
    }

    public Long getParentId(){
        return this.parentId;
    }

    public void setResKey(String resKey){
        this.resKey = resKey;
    }

    public String getResKey(){
        return this.resKey;
    }

    public void setResUrl(String resUrl){
        this.resUrl = resUrl;
    }

    public String getResUrl(){
        return this.resUrl;
    }

    public String getAction() {
        if (resKey == null)
            return null;
        String[] keys = resKey.split(":");

        return keys[keys.length - 1];
    }

    public void setLevel(Integer level){
        this.level = level;
    }

    public Integer getLevel(){
        return this.level;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getIcon(){
        return this.icon;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public List<SysResource> getSubRes() {
		return subRes;
	}

	public void setSubRes(List<SysResource> subRes) {
		this.subRes = subRes;
	}

    public enum Level {
        CATALOG(0, "目录"),
        MENU(1, "菜单"),
        BUTTON(2, "按钮");
        private int value;
        private String desc;

        Level(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }
}