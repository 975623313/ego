package com.an.ego.beans;
/***
 * 封装上传图片之后的返回结果
 * **/
public class PictureResult {

	private Integer error; //状态  1 失败  0 成功
	private String url; //上传图片后，图片在服务器的url
	private String message;//响应到客户端的提示消息
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "PictureResult [error=" + error + ", url=" + url + ", message=" + message + "]";
	}
	
	
}
