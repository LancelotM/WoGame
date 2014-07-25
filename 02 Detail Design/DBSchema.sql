CREATE DATABASE wogamecenter DEFAULT CHARSET utf8;

--渠道编号
create table wogamecenter.channel_info(
	channel_id int(20) primary key auto_increment,
	channel_name varchar(200) not null,
	channel_code varchar(40) not null,
	cp_id varchar(40),
	status boolean not null default false, 
	sync_type int(8) not null default 0,
	sync_status boolean not null default true,
	wap_token varchar(128),
	log_token varchar(128),
	date_modified date not null,
	date_created date not null
)engine=innodb default charset=utf8;

alter table wogamecenter.channel_info add unique key (channel_name);
alter table wogamecenter.channel_info add unique key (channel_code);


--游戏
create table wogamecenter.product(
	product_id varchar(40) primary key,
	product_name varchar(200) not null,
	product_icon varchar(1024),
	date_created date not null
)engine=innodb default charset=utf8;


--新老用户统计
create table wogamecenter.user_count(
	id int(20) primary key auto_increment,
	new_user_count int(30) not null default 0,
	old_user_count int(30) not null default 0,
	channel_id int(20) not null,
	date_created date not null
)engine=innodb default charset=utf8;

alter table wogamecenter.user_count add constraint User_Count_Channel_FK foreign key (channel_id) references wogamecenter.channel_info(channel_id);


--页面流量统计
create table wogamecenter.page_traffic(
	id int(20) primary key auto_increment,
	channel_id int(20) not null,
	homepage int(20) not null default 0,
	category int(20) not null default 0,
	hotlist int(20) not null default 0,
	latest int(20) not null default 0,
	date_created date not null
)engine=innodb default charset=utf8;

alter table wogamecenter.page_traffic add constraint Page_Traffic_Channel_FK foreign key (channel_id) references  wogamecenter.channel_info(channel_id);


--推荐页广告位和Banner点击次数统计
create table wogamecenter.ad_traffic(
	id int(20) primary key auto_increment,
	channel_id int(20) not null,
	ad_id int(8) not null,
	ad_type int(8) not null,
	sort int(8) not null,
	click_through int(20) not null default 0,
	date_created date not null
)engine=innodb default charset=utf8;

alter table wogamecenter.ad_traffic add constraint Ad_Traffic_Channel_FK foreign key (channel_id) references  wogamecenter.channel_info(channel_id);


--热词
create table wogamecenter.keyword(
	id int(20) primary key auto_increment,
	channel_id int(20) not null,
	keyword varchar(512) not null,
	count int(20) not null default 0,
	date_modified date not null,
	date_created date not null
)engine=innodb default charset=utf8;
alter table wogamecenter.keyword add constraint Keyword_Channel_FK foreign key (channel_id) references wogamecenter.channel_info(channel_id); 


--下载游戏统计
create table wogamecenter.download_info(
	id int(20) primary key auto_increment,
	product_id varchar(40) not null,
	channel_code varchar(40) not null,
	download_count int(20) default 0,
	date_created date not null
)engine=innodb default charset=utf8;


alter table wogamecenter.download_info add constraint Download_Product_FK foreign key (product_id) references  wogamecenter.product(product_id);

--用户
create table wogamecenter.account(
	account_id int(20) primary key auto_increment,
	account_name varchar(50) not null,
	password varchar(128) not null,
	date_modified date not null,
	date_created date not null
)engine=innodb default charset=utf8;

alter table wogamecenter.account add unique key (account_name);

insert into account(account_name, password, date_modified, date_created) values('admin', 'A5CA78141C08D41452F6A7A2B1752E76', now(), now());

--打包信息
create table wogamecenter.package_info(
	channel_id varchar(40) not null,
	cp_id varchar(40) not null,
	app_id varchar(40) not null,
	app_name varchar(200),
	update_type int(4),
	soft_id varchar(40),
	onlinetime varchar(14),
	original_file_path varchar(512),
	apk_file_path varchar(512),
	apk_online_time varchar(14),
	status varchar(8),
	productIndex varchar(200) not null,
	reserve2 varchar(200),
	reserve3 varchar(200),
	reserve4 varchar(200),
	reserve5 varchar(200),
	date_modified date not null,
	date_created date,
	primary key(channel_id,app_id)	
)engine=innodb default charset=utf8;


CREATE TRIGGER defaulttime
BEFORE INSERT ON wogamecenter.package_info
FOR EACH ROW
	SET new.date_created=now();

create table wogamecenter.package_report(
	id int(20) primary key auto_increment,
	appid varchar(40) not null,
	appname varchar(30) not null,
	channel_code varchar(40) not null,
	package_status int(8) not null,
	receipt_status int(8),
	date_created date not null
)engine=innodb default charset=utf8; 

create table wogamecenter.zte_report(
	id int(20) primary key auto_increment,
	appid varchar(40) not null,
	appname varchar(30) not null,
	channel_code varchar(40) not null,
	operate_result int(8) not null,
	date_created date not null
)engine=innodb default charset=utf8;

grant all privileges on wogamecenter.* to 'front_user'@'%' identified by 'Pass4front' with grant option;

flush privileges;

外网IP: 123.125.219.110
内网IP: 172.16.13.198

现网IP：172.16.18.8

