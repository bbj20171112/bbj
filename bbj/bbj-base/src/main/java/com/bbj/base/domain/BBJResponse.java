package com.bbj.base.domain;

/**
 * 请求相应对象
 * @author bage
 *
 */
public class BBJResponse extends BBJObject{

	private static final long serialVersionUID = 1L;
	
	public static final String code_success = "200";

	
	private String code = "200"; // 消息码
	private String msg = ""; // 消息
	private Object data = ""; // 数据
	private Object bundle = ""; // 附加信息
	
	public BBJResponse() {
		super();
	}

	public BBJResponse(String code) {
		super();
		this.code = code;
	}

	public BBJResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public BBJResponse(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public BBJResponse(String code, String msg, Object data, Object bundle) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.bundle = bundle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getBundle() {
		return bundle;
	}

	public void setBundle(Object bundle) {
		this.bundle = bundle;
	}

	@Override
	public String toString() {
		return "BBJResponse [code=" + code + ", msg=" + msg + ", data=" + data + ", bundle=" + bundle + "]";
	}

}
