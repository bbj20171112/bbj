package com.bbj.base.utils;

import java.util.regex.Pattern;

public class RegularUtils
{
	
	public static boolean isSqlValid(String str) {  
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
	            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";  
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);  
	    if (sqlPattern.matcher(str).find()) {  
	        return false;  
	    }  
	    return true;  
	} 

}
