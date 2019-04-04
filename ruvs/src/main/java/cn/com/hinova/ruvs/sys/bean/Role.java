package cn.com.hinova.ruvs.sys.bean;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import cn.com.hinova.ruvs.common.annotation.CFUniqueAnnotation;
import cn.com.hinova.ruvs.common.base.SuperModel;

public class Role extends SuperModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CFUniqueAnnotation
	private String name;

	@JsonIgnore
	private Set<Resource> resources;

	
	
	public Role() {
	}

	public Role(String roleId) {
		this.setId(roleId);
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
