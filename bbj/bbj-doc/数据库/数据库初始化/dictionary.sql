
USE bbj;
CREATE TABLE dictionary_table(
	id VARCHAR(64),
	table_name VARCHAR(256), 
	table_comment VARCHAR(64), 
	table_description VARCHAR(256),
	sequence_name VARCHAR(256),
	
	table_remark VARCHAR(1024),
	create_time DATETIME,
	update_time DATETIME,
	create_staff_id VARCHAR(64),
	update_staff_id VARCHAR(64),
	
	delete_state VARCHAR(2)
);


