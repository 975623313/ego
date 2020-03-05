package com.an.ego.beans;

import java.io.Serializable;

public class EgoResult implements Serializable {
	
	//响应状态
	private Integer status;
	
	//响应状态
	private Object data;
	
	//响应消息
	private String msg;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public EgoResult(Integer status, Object data, String msg) {
		super();
		this.status = status;
		this.data = data;
		this.msg = msg;
	}

	public EgoResult(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public EgoResult(Object data) {
		super();
		this.data = data;
		this.status=200;
		this.msg="ok";
	}

	public EgoResult() {
		super();
	}
	//静态方法，返回egoresult对象
	public static EgoResult ok(){
		return new EgoResult(null);
		
	}

	@Override
	public String toString() {
		return "EgoResult{" +
				"status=" + status +
				", data=" + data +
				", msg='" + msg + '\'' +
				'}';
	}
}
