package com.utils;

import java.io.Serializable;

public class ReturnStatus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7649060641604551386L;

	public static final int FAILED = 0;
	public static final int SUCCESS = 1;

	/**
	 * 返回的域对象
	 */
	//private DataEntity entity;
	/**
	 * 返回的其他数据对象
	 */
	private int status;
	private String message;//说明
	private Object data;


	public ReturnStatus(int status) {
		this.status = status;
	}

	public ReturnStatus(int status, String message) {
		this.status = status;
		this.message = message;
	}

	//下面是构造器
	public ReturnStatus(boolean isSuccess) {
		if (isSuccess) {
			this.status = ReturnStatus.SUCCESS;
		} else {
			this.status = ReturnStatus.FAILED;
		}
	}

	public ReturnStatus(boolean isSuccess,String message) {
		this(isSuccess);
		this.message = message;
	}
	
	public ReturnStatus(int status,String message,Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ReturnStatus(Object data, int status) {
		this.status = status;
		this.data = data;
	}


	public ReturnStatus() {
	}
	
    //下面是静态构造器
    public static ReturnStatus build(Integer status, String msg, Object data) {
        return new ReturnStatus(status, msg, data);
    }
    public static ReturnStatus build(Integer status, String msg) {
        return new ReturnStatus(status, msg, null);
    }
    public static ReturnStatus ok(Object data) {
        return new ReturnStatus(data,1);
    }
    public static ReturnStatus ok() {
    	 return new ReturnStatus(true); 
    }
	
	
	
	
	//下面get/set方法
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setSuccess(boolean isSuccess) {
		if (isSuccess) {
			this.status = ReturnStatus.SUCCESS;
		} else {
			this.status = ReturnStatus.FAILED;
		}
	}

	
}
