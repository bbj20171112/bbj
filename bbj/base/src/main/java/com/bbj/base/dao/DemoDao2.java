package com.bbj.base.dao;

import org.springframework.stereotype.Repository;

import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.DemoDomain2;

@Repository
public class DemoDao2 extends BBJDao{

	@Override
	public BBJEntity initEntity() {
		return new DemoDomain2();
	}

}
