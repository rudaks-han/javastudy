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
	eer_version varchar(1000),
	customize_name varchar(1000),
	filepath varchar(20000),
	filename varchar(1000),
	ext varchar(100),
	file_modified_type varchar(100)
);


create or replace FUNCTION f_get_module_name(filepath varchar(20000)) RETURNS Varchar
as $BODY$
 declare v_module_type varchar(100);
 BEGIN
 select case when position('commons' in filepath) > 0 then
			'commons'
		when position('spectra/base' in filepath) > 0 then
			'base'
		when position('gateway' in filepath) > 0 then
			'gateway'
		when position('router' in filepath) > 0 then
			'router'
		when position('monitoring' in filepath) > 0 then
			'monitoring'
		when position('engine' in filepath) > 0
				or position('interpreter' in filepath) > 0
				or position('interface' in filepath) > 0
				or position('framework-core' in filepath) > 0
			then
			'engine'
		when position('webapps' in filepath) > 0 or position('WEB-INF/jsp' in filepath) > 0 or position('ui-framework' in filepath) > 0 then
			'webapps'
		when position('restapi' in filepath) > 0 or position('rest-api-inmethod' in filepath) > 0 then
			'restapi'
		when position('proxy' in filepath) > 0
				or position('/js/spectra' in filepath) > 0
				or position('jspf' in filepath) > 0
				or position('/WEB-INF/config/' in filepath) > 0
				or position('/jsp/talk' in filepath) > 0 then
			'proxy'
		when position('scheduler' in filepath) > 0 then
			'scheduler'
		when position('thirdparty' in filepath) > 0 then
			'thirdparty'
		when position('commandline' in filepath) > 0 then
			'commandline'
		when position('notification' in filepath) > 0 then
			'notification'
		when position('/postgresql/' in filepath) > 0
				or position('/oracle/' in filepath) > 0
				or position('/mssql/' in filepath) > 0 then
			'sql'
		when position('conf/' in filepath) > 0 then
			'conf'
		when position('/bin/' in filepath) > 0 or filepath like '%.sh' then
			'bin'
		when position('/dbscript/' in filepath) > 0 then
			'dbscript'
		when position('/m2repository/' in filepath) > 0 then
			'm2repository'
		when filepath like '%pom.xml' then
			'pom.xml'
		else
			'#unknown'
		end into v_module_type;

		return v_module_type;
END;
$BODY$ LANGUAGE plpgsql stable;
