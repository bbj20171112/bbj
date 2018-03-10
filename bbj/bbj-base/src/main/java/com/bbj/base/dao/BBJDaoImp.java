package com.bbj.base.dao;

import com.bbj.base.domain.BBJEntity;

/**
 * 目前仅提供了基本的增删改查<br>
 * 如果需要额外的查询操作，可以通过 jdbcTemplate 进行调用执行相应的查询操作逻辑
 * 
 * @author bage
 *
 * @param <T>
 */
public class BBJDaoImp<T extends BBJEntity> extends BBJDaoMySQLImp<T>{ 
}
