package cn.com.hinova.ruvs.henuo.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

public class OutPutStudent {
	@Excel(name = "姓名", orderNum = "0")
	private String name;
	@Excel(name = "班级", orderNum = "1")
	private String classs;
	public OutPutStudent(String name, String classs) {
		super();
		this.name = name;
		this.classs = classs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
}
