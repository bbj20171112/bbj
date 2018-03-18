
USE bbj;
CREATE TABLE dictionary_table(
	id VARCHAR(64),
	table_name VARCHAR(256), 
	table_comment VARCHAR(64), 
	table_description VARCHAR(256),
	sequence_id VARCHAR(256),
	
	table_remark VARCHAR(1024),
	create_time DATETIME,
	update_time DATETIME,
	create_staff_id VARCHAR(64),
	update_staff_id VARCHAR(64),
	
	delete_state VARCHAR(2)
);


CREATE TABLE dictionary_field(
	id 	VARCHAR(64),
	table_id	VARCHAR(256),
	field_name	VARCHAR(1024),
	field_name_comment	VARCHAR(64),
	field_type	VARCHAR(256),
	
	field_key_type	VARCHAR(256),
	field_key_type_comment	VARCHAR(1024),
	field_constraint	VARCHAR(1024),
	field_constraint_comment	VARCHAR(1024),
	field_reference	VARCHAR(64),
	
	field_reference_comment	VARCHAR(1024),
	field_remark	VARCHAR(1024),	
	create_time DATETIME,
	update_time DATETIME,
	create_staff_id VARCHAR(64),
	
	update_staff_id VARCHAR(64),	
	delete_state VARCHAR(2)
);



CREATE TABLE dictionary_reference(
	id 	VARCHAR(64),
	table_id	VARCHAR(256),
	field_name	VARCHAR(1024),
	field_name_comment	VARCHAR(64),
	field_type	VARCHAR(256),
	
	field_key_type	VARCHAR(256),
	field_key_type_comment	VARCHAR(1024),
	field_constraint	VARCHAR(1024),
	field_constraint_comment	VARCHAR(1024),
	field_reference	VARCHAR(64),
	
	field_reference_comment	VARCHAR(1024),
	field_remark	VARCHAR(1024),	
	create_time DATETIME,
	update_time DATETIME,
	create_staff_id VARCHAR(64),
	
	update_staff_id VARCHAR(64),	
	delete_state VARCHAR(2)
);


CREATE TABLE dictionary_reference(
	id 	VARCHAR(64),
	reference_value	VARCHAR(64),
	reference_name	VARCHAR(1024),
	reference_remark	VARCHAR(1024),
	
	create_time DATETIME,
	update_time DATETIME,
	create_staff_id VARCHAR(64),
	
	update_staff_id VARCHAR(64),	
	delete_state VARCHAR(2)
);


