package cn.com.hinova.ruvs.sys.bean;

import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.com.hinova.ruvs.common.annotation.CFUniqueAnnotation;
import cn.com.hinova.ruvs.common.base.SuperModel;

public class Resource extends SuperModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@CFUniqueAnnotation
	private String name;
	private int type;// 1、模块，2、菜单or连接，3、按钮or连接
	private int status;// 0正常，-1禁用
	private String url;
	private String icon;
	private int sort;

	@JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
	private Resource parent;
	
	@JsonIgnore
	private Set<Resource> childs = new LinkedHashSet<Resource>();

	@JsonIgnore
	private Set<Role> roles;
	
	
	public Set<Role> getRoles() {
		return roles;
	}

	public String getIcon() {
		return icon;
	}

	public Set<Resource> getChilds() {
		return childs;
	}

	public void setChilds(Set<Resource> childs) {
		this.childs = childs;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

}
