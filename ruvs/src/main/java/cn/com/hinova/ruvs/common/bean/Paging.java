package cn.com.hinova.ruvs.common.bean;

import java.util.List;

public class Paging {

	private int currentPager = 1;
	private int pageSize = 30;

	private String sort;
	private String order;

	private List<? extends Object> list;
	private long count;

	public int getFirstIndex() {
		return (currentPager - 1) * pageSize;
	}

	public int getMaxIndex() {
		return getFirstIndex() + pageSize;
	}
	
	//easyui的当前页码
	public void setPage(int page){
		this.currentPager=page;
	}
	//easyui的每页行数
	public void setRows(int rows){
		this.pageSize=rows;
	}
	//easyui的数据内容
	public List<? extends Object> getRows(){
		return this.list;
	}
	//easyui的记录行数
	public long getTotal(){
		return this.count;
	}
	

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setList(List<? extends Object> list) {
		this.list = list;
	}
	

	public void setCount(long count) {
		this.count = count;
	}

}
