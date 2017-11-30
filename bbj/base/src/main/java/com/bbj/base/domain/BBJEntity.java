package com.bbj.base.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bbj.base.utils.StringUtils;

/**
 * 普通实体只要继承这个基本实体，即可实现基本的增删改查
 * @author bage
 *
 */
public abstract class BBJEntity extends BBJObject	{

	private static final long serialVersionUID = 1L;

	protected String tableName = ""; 									// 表名
	protected String primaryAttr = ""; 									// 主键
	private Map<String, String> attr = new HashMap<String, String>(); 	// 属性集

	// 构造函数
	public BBJEntity() {
		super();
		init();
	}
	
	// 初始化
	private void init() {
		tableName = initTable();
		String[] attrs = initAttr();
		if (attrs != null) {
			for (int i = 0; i < attrs.length; i++) {
				if (i == 0) {
					primaryAttr = attrs[i];
				}
				if (StringUtils.isNotEmpty(attrs[i])) {
					addAttr(attrs[i], "");
				}
			}
		}
	}

	/**
	 * 初始化表名
	 * 
	 * @return
	 */
	public abstract String initTable();

	/**
	 * 初始化属性,第一个为主键
	 */
	public abstract String[] initAttr();


	// 获取表名和主键
	/**
	 * 获取表名
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}

	// 属性操作
	/**
	 * 获取主键
	 * @return
	 */
	public String getPrimaryAttr() {
		return primaryAttr;
	}
	
	/**
	 * 获取所有的属性集 ( 如果需要获取某个属性值，可以使用 getAttr(key)方法 )
	 * @return
	 */
	public Map<String, String> getAttr() {
		return attr;
	}

	/**
	 * 获取一个属性值
	 * @param key
	 * @return
	 */
	public String getAttr(String key) {
		return this.attr.get(key) == null ? "" : this.attr.get(key);
	}
	
	/**
	 * 如果 attr 不为空，则设置属性集
	 * @param attr
	 */
	public void setAttr(Map<String, String> attr) {
		if(attr != null){
			this.attr = attr;
		}
	}
	
	/**
	 * 不存在该 key ，则增加一个属性
	 * @param key
	 * @param value
	 */
	public void addAttr(String key, String value) {
		if(key != null){
			if(!this.attr.containsKey(key)){
				this.attr.put(key, value);
			}
		}
	}

	/**
	 * 不存在则新增属性，存在则设置属性
	 * @param key
	 * @param value
	 */
	public void setAttr(String key, String value) {
		if(key != null){
			this.attr.put(key, value);
		}
	}
	
	/**
	 * 删除一个属性
	 * @param key
	 */
	public void removeAttr(String key) {
		if(key != null){
			if(this.attr.containsKey(key)){
				this.attr.remove(key);
			}
		}
	}

	/**
	 * 获取所有的属性 key
	 * @return
	 */
	public List<String> getAttrKeys() {
		List<String> list = new ArrayList<String>();
		for (Entry<String, String> entrySet : this.attr.entrySet()) {
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
		for (Entry<String, String> entrySet : this.attr.entrySet()) {
			keySb.append(entrySet.getKey() + ",");
		}
		if(keySb.length() > 0){
			keySb.deleteCharAt(keySb.length() - 1);
		}
		return keySb.toString();
	}

	// hashCode 和   Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((primaryAttr == null) ? 0 : primaryAttr.hashCode());
		return result;
	}

	/**
	 * 只要主键相等，即认为相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BBJEntity other = (BBJEntity) obj;
		if (primaryAttr == null) {
			if (other.primaryAttr != null)
				return false;
		} else if (!primaryAttr.equals(other.primaryAttr))
			return false;
		else if (getAttr().get(primaryAttr) == null) {
			if (other.getAttr().get(primaryAttr) != null)
				return false;
		} else if (!getAttr().get(primaryAttr).equals(other.getAttr().get(primaryAttr)))
			return false;
		return true;
	}
	
	// toString
	/**
	 * toString 方法
	 */
	@Override
	public String toString() {
		return "BBJEntity [tableName=" + tableName + ", primaryAttr=" + primaryAttr + ", attr=" + getAttr() + "]";
	}

}
