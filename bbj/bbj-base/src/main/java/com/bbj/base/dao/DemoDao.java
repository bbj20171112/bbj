package com.bbj.base.dao;

import org.springframework.stereotype.Repository;

import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.DemoDomain;

@Repository
public class DemoDao extends BBJDao{

	@Override
	public BBJEntity initEntity() {
		return new DemoDomain();
	}

}
