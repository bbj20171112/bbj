package com.bbj.base.domain;

public class WhereFilter extends BBJObject{
	
	private static final long serialVersionUID = 1L;
	
	private String attr = "";
	private String opt = "";
	private String value = "";
	private String conn = "and";
	
	public WhereFilter() {
		super();
	}
	
	public WhereFilter(String attr, String opt, String value) {
		super();
		this.attr = attr;
		this.opt = opt;
		this.value = value;
	}

	
	public WhereFilter(String attr, String opt, String value, String conn) {
		super();
		this.attr = attr;
		this.opt = opt;
		this.value = value;
		this.conn = conn;
	}

	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getConn() {
		return conn;
	}

	public void setConn(String conn) {
		this.conn = conn;
	}

	@Override
	public String toString() {
		return " " + attr + " " + opt + " ? ";
	}
	
}
