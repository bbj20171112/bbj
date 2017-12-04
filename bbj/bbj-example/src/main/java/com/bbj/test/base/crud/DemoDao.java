package com.bbj.test.base.crud;

import org.springframework.stereotype.Repository;

import com.bbj.base.dao.BBJDao;
import com.bbj.base.domain.BBJEntity;

@Repository
public class DemoDao extends BBJDao{

	@Override
	public BBJEntity initEntity() {
		return new DemoDomain();
	}

}
