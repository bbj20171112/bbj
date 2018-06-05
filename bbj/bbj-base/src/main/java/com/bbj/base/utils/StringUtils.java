package com.bbj.base.utils;

import java.util.List;

public class StringUtils
{

    /**
     *  对象是否为空
     *  @param obj
     *  @return    
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ("".equals(obj.toString().trim())) {
            return true;
        }
        return false;
    }

    /**
     *  对象是否不为空
     *  @param obj
     *  @return    
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     *  是否由数字组成
     *  @param string
     *  @return    
     */
    public static boolean isDigit(String string) {
        if (isEmpty(string)) {
            return false;
        }
        return string.matches("^[0-9]*$");
    }

    /**
     *  生成连续几个相同的字符串
     *  @param string
     *          "0"
     *  @param length
     *          3
     *  @return   
     *          "000" 
     */
    public static String getMultipleStrings(String string, int length) {
        if (isNotEmpty(string)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(string);
            }
            return sb.toString();
        }
        return "";
    }

	public static String getListString(List<String> selectFields,String separator) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < selectFields.size(); i++) {
			sb.append(selectFields.get(i));
			if(i < selectFields.size() - 1){
				sb.append(separator);
			}
		}
		return sb.toString();
	}

}
