package com.bbj.base.utils;

public class StringUtils
{

    /**
     *  是否为空
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
     *  是否不为空
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

}
