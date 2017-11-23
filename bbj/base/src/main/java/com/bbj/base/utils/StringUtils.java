package com.bbj.base.utils;

public class StringUtils
{

    /**
     *  对象是否为空
     *  @param obj
     *  @return    
     */
    public static boolean isBlank(Object obj) {
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
    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    /**
     *  是否由数字组成
     *  @param string
     *  @return    
     */
    public static boolean isDigit(String string) {
        if (isBlank(string)) {
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
        if (isNotBlank(string)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(string);
            }
            return sb.toString();
        }
        return "";
    }

}
