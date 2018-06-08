package com.bbj.base.dictionary.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bbj.base.domain.BBJObject;

/**
 * 参照值数据字典实体
 * @author bage
 *
 */
public class DictionaryReference extends BBJObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String tableName = "tableName"; 
	public static final String referenceFieldName = "referenceFieldName"; 
	public static final String referenceFieldValue = "referenceFieldValue";
	
	private Map<String, Object> attr = new HashMap<String, Object>(); 	// 属性集
	
	public DictionaryReference() {
		super();
		addAttr(tableName, "");
		addAttr(referenceFieldName, "");
		addAttr(referenceFieldValue, new ArrayList<String>());
	}

	
	/**
	 * 获取一个属性值
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return this.attr.get(key);
	}
	
	/**
	 * 获取String一个属性值
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return String.valueOf(this.attr.get(key) == null ? "" : this.attr.get(key));
	}
	
	/**
	 * 获取Int一个属性值
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	
	/**
	 * 获取一个属性值
	 * @param <T>
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key,Class<T> cls) {
		Object value = this.get(key);
		return (T) value;
	}
	
	/**
	 * 不存在该 key ，则增加一个属性
	 * @param key
	 * @param value
	 */
	public DictionaryReference addAttr(String key, Object value) {
		if(key != null){
			if(!this.attr.containsKey(key)){
				this.attr.put(key, value);
			}
		}
		return this;
	}

	/**
	 * 不存在则新增属性，存在则设置属性
	 * @param key
	 * @param value
	 */
	public DictionaryReference setAttr(String key, Object value) {
		if(key != null){
			this.attr.put(key, value);
		}
		return this;
	}
	
	/**
	 * 删除一个属性
	 * @param key
	 */
	public DictionaryReference remove(String key) {
		if(key != null){
			if(this.attr.containsKey(key)){
				this.attr.remove(key);
			}
		}
		return this;
	}

	/**
	 * 获取所有的属性 key
	 * @return
	 */
	public List<Object> getAttrKeys() {
		List<Object> list = new ArrayList<Object>();
		for (Entry<String, Object> entrySet : this.attr.entrySet()) {
			list.add(entrySet.getKey());
		}
		return list;
	}
	
	/**
	 * 获取key的string，中间用逗号隔开
	 * @return
	 */
	public String getAttrKeysStr() {
		StringBuffer keySb = new StringBuffer();
		for (Entry<String, Object> entrySet : this.attr.entrySet()) {
			keySb.append(entrySet.getKey() + ",");
		}
		if(keySb.length() > 0){
			keySb.deleteCharAt(keySb.length() - 1);
		}
		return keySb.toString();
	}


	@Override
	public String toString() {
		return "DictionaryReference [attr=" + attr + "]";
	}
	
	
}
