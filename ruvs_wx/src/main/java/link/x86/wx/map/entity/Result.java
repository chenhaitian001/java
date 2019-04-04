package link.x86.wx.map.entity;

import java.util.Map;

public class Result {

	public Result() {
	}

	public Result(int code, String msg,String errorMsg, Map<String, String> errors) {
		super();
		this.code = code;
		this.msg = msg;
		this.errorMsg=errorMsg;
		this.errors = errors;
	}

	private int code = 200;
	private String msg = "操作成功!";

	private String errorMsg;
	private Map<String, String> errors;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
