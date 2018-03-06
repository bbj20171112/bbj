package com.bbj.base.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.dao.BBJDao;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.service.BBJServiceImp;

@Controller
public class BBJController<T extends BBJServiceImp<V,K>,V extends BBJDao<K>, K extends BBJEntity> {

	protected T currentBBJServiceImp;
	protected BBJEntity currentBBJEntity;

	@SuppressWarnings("unchecked")
	public BBJController(){
		// 获取当前的泛型类型
		Type typeTemp = getClass().getGenericSuperclass();
		if(typeTemp instanceof ParameterizedType){
	        ParameterizedType type = (ParameterizedType) typeTemp;      
			Class<T> currentBBJServiceImpClass = (Class<T>) type.getActualTypeArguments()[0];
			Class<K> currentBBJEntityClass = (Class<K>) type.getActualTypeArguments()[2];

	        // 实例化一个对象
	        try {
	        	currentBBJServiceImp = currentBBJServiceImpClass.newInstance();
	        	currentBBJEntity = currentBBJEntityClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Autowired
	private BBJServiceImp<BBJDao<BBJEntity>,BBJEntity> baseBBJServiceImp;
	
	@RequestMapping(value={"/insert"})
	@ResponseBody
	public Object insert(HttpServletRequest request){
		BBJEntity bbjEntity = parseBBJEntity(request);
		return baseBBJServiceImp.insert(bbjEntity);
	}
	

	private BBJEntity parseBBJEntity(HttpServletRequest request) {
		return currentBBJEntity;
	}


	@RequestMapping(value={"/deleteById"})
	@ResponseBody
	public Object deleteById(String id){
		return baseBBJServiceImp.deleteById(id);
	}

	@RequestMapping(value={"/update"})
	@ResponseBody
	public Object update(HttpServletRequest request){
		BBJEntity bbjEntity = parseBBJEntity(request);
		return baseBBJServiceImp.update(bbjEntity );
	}
	

	@RequestMapping(value={"/queryByPage"})
	@ResponseBody
	public Object queryByPage(HttpServletRequest request,@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value="length",defaultValue="10")int length,
			@RequestParam(value="draw",defaultValue="0")int draw,
			@RequestParam(value="search[value]",defaultValue="")String searchValue

			){
		BBJEntity curruntBBJEntity = parseBBJEntity(request);
		SqlFilter<BBJEntity> sqlFilter = new SqlFilter<BBJEntity>(curruntBBJEntity );
		List<WhereFilter> list = new ArrayList<WhereFilter>();
		WhereFilter whereFilter = new WhereFilter("table_name", "like ", "%" + searchValue + "%");
		list.add(whereFilter );
		sqlFilter.addWhereFilter(list );
		Map<String, Object> map = new HashMap<String, Object>();
		int tagPage = start / length;
		if(tagPage < 1){
			tagPage = 1;
		} else {
			tagPage = tagPage + 1;
		}
		map.put("data", baseBBJServiceImp.queryByPage(tagPage, length, sqlFilter));
		map.put("recordsTotal", baseBBJServiceImp.getTotalRow(sqlFilter));
		map.put("recordsFiltered", baseBBJServiceImp.getTotalRow(sqlFilter));
		map.put("draw", draw);
		
		return map;
	}

}
