package com.bbj.base.crud;

import org.springframework.stereotype.Repository;

import com.bbj.base.dao.BBJDao;
import com.bbj.base.domain.BBJEntity;

@Repository
public class DemoDao2 extends BBJDao{

	@Override
	public BBJEntity initEntity() {
		return new DemoDomain2();
	}

}
