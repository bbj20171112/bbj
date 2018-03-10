package com.bbj.base.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bbj.base.domain.BBJEntity;

public class BBJEntityUtils {

	
	/**
	 * 从request中解析参数
	 * @param request
	 * @param currentBBJEntityClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseFrom(HttpServletRequest request,Class<T> currentBBJEntityClass){
		// 实例化一个对象
        try {
			T t =  currentBBJEntityClass.newInstance();
			if(t instanceof BBJEntity){
				BBJEntity currentBBJEntity = (BBJEntity) t;
				List<String> list = currentBBJEntity.getAttrKeys();
				for (int i = 0; i < list.size(); i++) {
					if(request == null){
						break;
					}
					currentBBJEntity.setAttr(list.get(i), request.getParameter(list.get(i)));
				}
				return (T) currentBBJEntity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
