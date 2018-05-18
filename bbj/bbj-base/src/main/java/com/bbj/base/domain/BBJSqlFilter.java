package com.bbj.base.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL 过滤
 * @author bage
 *
 */
public class BBJSqlFilter extends BBJObject implements SqlFilter{

	private static final long serialVersionUID = 1L;
	
	// 操作符
	public static final String where_opt_eq = "="; // 等于号
	public static final String where_opt_like = "like"; // like
	public static final String where_opt_lt = ">"; // 大于
	public static final String where_opt_lqt = ">="; // 大于等于
	public static final String where_opt_st = "<"; // 小于
	public static final String where_opt_sqt = "<="; // 小于等于


	private BBJEntity currentBBJEntity = null; // 当前的实体对象
	private StringBuilder whereString = new StringBuilder(); // 过滤语句
	private StringBuilder orderbyString = new StringBuilder(); // 排序语句
	private StringBuilder havingString = new StringBuilder(); // having语句
	private List<Object> listParam = new ArrayList<Object>(); // 参数列表

	/**
	 * 
	 * @param <T>
	 * @param curruntBBJEntity 目标实体,必须设置
	 */
	public <T> BBJSqlFilter(Class<T> currentBBJEntityClass){
		try {
			T t =  currentBBJEntityClass.newInstance();
			if(t instanceof BBJEntity){
				this.currentBBJEntity = (BBJEntity) t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bbj.base.domain.SqlFilter2#addWhereFilter(java.util.List)
	 */
	public SqlFilter addWhereFilter(List<WhereFilter> list){
		for (int i = 0; i < list.size(); i++) {
			WhereFilter whereFilter = list.get(i);
			if(null == whereFilter.getAttr() || "".equals(whereFilter.getAttr())){
				addToWhereFilter(whereFilter);
			} else if(currentBBJEntity.getAttrKeys().contains(whereFilter.getAttr())){
				addToWhereFilter(whereFilter);
			}
		}
		return this;
	}

	private void addToWhereFilter(WhereFilter whereFilter) {
		if(whereString.length() > 0){
			whereString.append(whereFilter.getConn());
		}
		whereString.append(whereFilter.toString());
		if(whereFilter.getValue() != null){
			if(where_opt_like.equals(whereFilter.getOpt())){
				listParam.add("%" + whereFilter.getValue() + "%");
			} else {
				listParam.add(whereFilter.getValue());
			}
		}
	}

	
	/* (non-Javadoc)
	 * @see com.bbj.base.domain.SqlFilter2#getSqlString()
	 */
	public String getSqlString() {
		return whereString.toString() + orderbyString.toString() + havingString.toString();
	}

	/* (non-Javadoc)
	 * @see com.bbj.base.domain.SqlFilter2#getListParam()
	 */
	public List<Object> getListParam() {
		return listParam;
	}

	/* (non-Javadoc)
	 * @see com.bbj.base.domain.SqlFilter2#toString()
	 */
	@Override
	public String toString() {
		return "SqlFilter [whereString=" + whereString + ", orderbyString=" + orderbyString + ", havingString="
				+ havingString + ", listParam=" + listParam + "]";
	}

	/* (non-Javadoc)
	 * @see com.bbj.base.domain.SqlFilter2#clear()
	 */
	public SqlFilter clear() {
		whereString = new StringBuilder(); // 过滤语句
		orderbyString = new StringBuilder(); // 排序语句
		havingString = new StringBuilder(); // having语句
		listParam = new ArrayList<Object>(); // 参数列表
		return this;
	}

}
