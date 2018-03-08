package com.bbj.base.domain.sequence;

import com.bbj.base.domain.BBJEntity;

public class Sequence extends BBJEntity{
	private static final long serialVersionUID = 1L;

	@Override
	public String initTable() {
		return "sequence";
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				"id", 
				BBJEntity.create_time,
				BBJEntity.update_time,
				BBJEntity.create_staff_id,
				BBJEntity.update_staff_id,
				BBJEntity.delete_state
		};
		return attrs;
	}

}
