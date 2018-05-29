package com.bbj.base.domain;

public class BBJServiceParam extends BBJDaoParam{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 不存在该 key ，则增加一个属性
	 * @param key
	 * @param value
	 */
	public BBJServiceParam addAttr(String key, Object value) {
		super.addAttr(key, value);
		return this;
	}

	/**
	 * 不存在则新增属性，存在则设置属性
	 * @param key
	 * @param value
	 */
	public BBJServiceParam setAttr(String key, Object value) {
		super.setAttr(key, value);
		return this;
	}
	
	/**
	 * 删除一个属性
	 * @param key
	 */
	public BBJServiceParam remove(String key) {
		super.remove(key);
		return this;
	}

}
