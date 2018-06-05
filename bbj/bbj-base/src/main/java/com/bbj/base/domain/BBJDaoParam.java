package com.bbj.base.domain;

public class BBJDaoParam extends BBJParam{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String keyEntity = "entity";
	public static final String keyId = "id";
	public static final String keyTagPage = "tagPage";
	public static final String keyPageSize = "pageSize";
	public static final String keySqlFilter = "sqlFilter";
	public static final String keyPrepareSql = "prepareSql";
	public static final String keyArgs = "args";
	public static final String keyBatchArgs = "batchArgs";
	public static final String keySelectFields = "selectFields";
	

	/**
	 * 不存在该 key ，则增加一个属性
	 * @param key
	 * @param value
	 */
	public BBJDaoParam addAttr(String key, Object value) {
		super.addAttr(key, value);
		return this;
	}

	/**
	 * 不存在则新增属性，存在则设置属性
	 * @param key
	 * @param value
	 */
	public BBJDaoParam setAttr(String key, Object value) {
		super.setAttr(key, value);
		return this;
	}
	
	/**
	 * 删除一个属性
	 * @param key
	 */
	public BBJDaoParam remove(String key) {
		super.remove(key);
		return this;
	}
	
}
