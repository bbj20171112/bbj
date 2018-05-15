package com.bbj.admin.menu.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.admin.menu.dao.MenuDao;
import com.bbj.admin.menu.domain.Menu;
import com.bbj.base.domain.SqlFilter;

@Service
public class MenuService {

	
	@Autowired
	private MenuDao menuDao;
	
	
	/**
	 * 增
	 * @param menu
	 * @return
	 */
	public int insert(Menu menu){
		if(menu == null){
			return 0;
		}
		return menuDao.insert(menu);
	}
	
	
	/**
	 * 删
	 * @param id
	 * @return
	 */
	public int deleteById(String id){
		if(id == null){
			return 0;
		}
		return menuDao.deleteById(id);
	}
	
	
	/**
	 * 改
	 * @param menu
	 * @return
	 */
	public int update(Menu menu){
		return menuDao.update(menu);
	}
	
	
	/**
	 * 查
	 * @param id
	 * @return
	 */
	public Menu queryById(String id){
		return menuDao.queryById(id);
	}
	
	
	/**
	 * 查（分页）
	 * @param tagPage
	 * @param pageSize
	 * @param sqlFilter
	 * @return
	 */
	public List<Menu> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return menuDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	
	/**
	 * 获取总数
	 * @param sqlFilter
	 * @return
	 */
	public int getTotalRow(SqlFilter sqlFilter){
		return menuDao.getTotalRow(sqlFilter);
	}


}