CREATE DATABASE wogamecenter DEFAULT CHARSET utf8;

--页面编号
create table wogamecenter.page_map(
	page_id int(20) primary key,
	page_desc varchar(50) not null
)engine=innodb default charset=utf8;

insert into wogamecenter.page_map(page_id, page_desc) values(1, "首页"); 
insert into wogamecenter.page_map(page_id, page_desc) values(2, "分类");
insert into wogamecenter.page_map(page_id, page_desc) values(3, "一周热榜");
insert into wogamecenter.page_map(page_id, page_desc) values(4, "最新");


--状态 
create table wogamecenter.status_map(
	status_id int(20) primary key,
	status varchar(20) not null
)engine=innodb default charset=utf8;

insert into wogamecenter.status_map(status_id, status) values(70, "pending");
insert into wogamecenter.status_map(status_id, status) values(90, "active");
insert into wogamecenter.status_map(status_id, status) values(99, "inactive");


--渠道编号
create table wogamecenter.channel_info(
	channel_id int(20) primary key,
	channel_name varchar(200) not null,
	status_id int(20) not null default 70, 
	date_modified TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
	date_created TIMESTAMP not null DEFAULT '2014-06-20 00:00:00'
)engine=innodb default charset=utf8;

alter table wogamecenter.channel_info add constraint Channel_Status_FK foreign key (status_id) references wogamecenter.status_map(status_id);
insert into wogamecenter.channel_info(channel_id, channel_name) values(18129,"安徽联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18130,"北京联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18131,"重庆联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18132,"福建联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18133,"甘肃联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18082,"广东联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18134,"广西联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18135,"贵州联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18136,"海南联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18137,"河北联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18138,"河南联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18139,"黑龙江联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18085,"湖北联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18128,"湖南联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18140,"吉林联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18141,"江苏联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18142,"江西联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18143,"辽宁联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18144,"内蒙古联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18145,"宁夏联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18146,"青海联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18147,"山东联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18148,"山西联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18149,"陕西联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18150,"上海联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18180,"四川联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18181,"天津联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18182,"西藏联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18183,"新疆联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18184,"云南联通");
insert into wogamecenter.channel_info(channel_id, channel_name) values(18185,"浙江联通");

--游戏
create table wogamecenter.product(
	product_id varchar(40) primary key,
	product_name varchar(200) not null,
	product_icon varchar(512),
	date_created TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP
)engine=innodb default charset=utf8;


--新老用户统计
create table wogamecenter.user_count(
	id int(20) primary key auto_increment,
	new_user_count int(30) not null default 0,
	old_user_count int(30) not null default 0,
	channel_id int(20) not null,
	date_created TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP
)engine=innodb default charset=utf8;

alter table wogamecenter.user_count add constraint User_Count_Channel_FK foreign key (channel_id) references wogamecenter.channel_info(channel_id);


--页面流量统计
create table wogamecenter.page_traffic(
	id int(20) primary key auto_increment,
	page_id int(20) not null,
	channel_id int(20) not null,
	click_through int(20) not null default 0,
	date_created TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP
)engine=innodb default charset=utf8;

alter table wogamecenter.page_traffic add constraint Page_Traffic_Page_FK foreign key (page_id) references  wogamecenter.page_map(page_id);
alter table wogamecenter.page_traffic add constraint Page_Traffic_Channel_FK foreign key (channel_id) references  wogamecenter.channel_info(channel_id);


--前30游戏和Banner点击次数统计
create table wogamecenter.game_traffic(
	id int(20) primary key auto_increment,
	channel_id int(20) not null,
	product_id varchar(40) not null,
	sort int(20) not null default 0,
	click_through int(20) not null default 0,
	download_count int(20) default 0,
	date_created TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
	banner_flag boolean not null default false
)engine=innodb default charset=utf8;

alter table wogamecenter.game_traffic add constraint Game_Traffic_Product_FK foreign key (product_id) references  wogamecenter.product(product_id);
alter table wogamecenter.game_traffic add constraint Game_Traffic_Channel_FK foreign key (channel_id) references  wogamecenter.channel_info(channel_id);


--热词
create table wogamecenter.keyword(
	id int(20) primary key auto_increment,
	keyword varchar(512) not null,
	count int(20) not null default 0,
	date_modified TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
	date_created TIMESTAMP not null DEFAULT '2014-06-20 00:00:00'
)engine=innodb default charset=utf8;


--下载游戏统计
create table wogamecenter.download_info(
	id int(20) primary key auto_increment,
	product_id varchar(40) not null,
	channel_id int(20) not null,
	download_count int(20) default 0,
	date_created TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP
)engine=innodb default charset=utf8;


alter table wogamecenter.download_info add constraint Download_Product_FK foreign key (product_id) references  wogamecenter.product(product_id);
alter table wogamecenter.download_info add constraint Download_Channel_FK foreign key (channel_id) references  wogamecenter.channel_info(channel_id);


--用户
create table wogamecenter.account(
	account_id int(20) primary key auto_increment,
	account_name varchar(50) not null,
	password varchar(128) not null,
	date_modified TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
	date_created TIMESTAMP not null DEFAULT '2014-06-20 00:00:00'
)engine=innodb default charset=utf8;

alter table wogamecenter.account add unique key (account_name);


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
	reserve1 varchar(200),
	reserve2 varchar(200),
	reserve3 varchar(200),
	reserve4 varchar(200),
	reserve5 varchar(200),
	date_modified TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
	date_created TIMESTAMP not null DEFAULT '2014-06-20 00:00:00'
	primary key(channel_id,app_id)	
)engine=innodb default charset=utf8;

