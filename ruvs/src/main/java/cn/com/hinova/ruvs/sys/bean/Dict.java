package cn.com.hinova.ruvs.sys.bean;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.com.hinova.ruvs.common.annotation.CFUniqueAnnotation;
import cn.com.hinova.ruvs.common.base.SuperModel;

public class Dict extends SuperModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@CFUniqueAnnotation
	private String name;
	private String value;

	@JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
	private Dict parent;

	@JsonIgnore
	private Set<Dict> childs;

	public Set<Dict> getChilds() {
		return childs;
	}

	public void setChilds(Set<Dict> childs) {
		this.childs = childs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Dict getParent() {
		return parent;
	}

	public void setParent(Dict parent) {
		this.parent = parent;
	}


}
