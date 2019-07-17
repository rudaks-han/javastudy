CREATE SEQUENCE customize_seq START 1;
CREATE SEQUENCE customize_changed_file_seq START 1;


create table t_customize_item (
	seq integer DEFAULT nextval('customize_seq') primary key,
	file_name varchar(1000),
	site_name varchar(1000),
	eer_version varchar(1000),
	customize_name varchar(1000),
	requirements varchar(50000),
	java_changes varchar(50000),
	db_changes varchar(50000),
	changed_files varchar(50000),
	customize_type varchar(1000),
	changed_service varchar(1000),
	developer varchar(1000),
	solution varchar(1000),
	cause varchar(1000)
);

create table t_customize_changed_file (
	seq integer DEFAULT nextval('customize_changed_file_seq') primary key,
	site_name varchar(1000),
	customize_name varchar(1000),
	filepath varchar(20000),
	filename varchar(1000),
	ext varchar(100)
);
