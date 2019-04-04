package cn.com.hinova.ruvs.common.base;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.com.hinova.ruvs.sys.bean.User;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SuperModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
	private User editUser;
	@JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatBaseModel.class)
	private User createUser;
	@JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatDate.class)
	private Date createTime=new Date();
	@JsonSerialize(using = cn.com.hinova.ruvs.common.json.JsonFromatDate.class)
	private Date editTime = new Date();

	public int isDelete = 0; // 0正常，1删除

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsDelete() {

		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getEditUser() {
		return editUser;
	}

	public void setEditUser(User editUser) {
		this.editUser = editUser;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	
	public String getName(){
		return id;
	}

}
