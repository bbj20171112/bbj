package com.bbj.base.domain;

import java.util.ArrayList;
import java.util.List;

public class SqlFilter extends BBJObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private StringBuilder whereString = new StringBuilder();
	private StringBuilder orderbyString = new StringBuilder();
	private StringBuilder havingString = new StringBuilder();
	private List<Object> listParam = new ArrayList<Object>();

	
	
	public SqlFilter addWhereFilter(List<WhereFilter> list){
		for (int i = 0; i < list.size(); i++) {
			WhereFilter whereFilter = list.get(i);
			if(whereString.length() > 0){
				whereString.append(whereFilter.getConn());
			}
			whereString.append(whereFilter.toString());
			listParam.add(whereFilter.getValue());
		}
		return this;
	}

	
	
	public String getSqlString() {
		return whereString.toString() + orderbyString.toString() + havingString.toString();
	}


	public List<Object> getListParam() {
		return listParam;
	}

	@Override
	public String toString() {
		return "SqlFilter [whereString=" + whereString + ", orderbyString=" + orderbyString + ", havingString="
				+ havingString + ", listParam=" + listParam + "]";
	}

}
