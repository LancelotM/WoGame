CREATE DATABASE wogamecenter DEFAULT CHARSET utf8;

--页面编号
create table page_map(
	page_id int(20) primary key,
	page_desc varchar(50) not null
)engine=innodb default charset=utf8;

insert into page_map(page_id, page_desc) values(1, "首页"); 
insert into page_map(page_id, page_desc) values(2, "分类");
insert into page_map(page_id, page_desc) values(3, "一周热榜");
insert into page_map(page_id, page_desc) values(4, "最新");


--状态 
create table status_map(
	status_id int(20) primary key,
	status varchar(20) not null
)engine=innodb default charset=utf8;

insert into status_map(status_id, status) values(70, "pending");
insert into status_map(status_id, status) values(90, "active");
insert into status_map(status_id, status) values(99, "inactive");


--渠道编号
create table channel_info(
	channel_id int(20) primary key,
	channel_name varchar(20) not null,
	cp_id varchar(20),
	status_id int(20) not null default 70, 
	date_created TIMESTAMP not null,
	date_modified TIMESTAMP not null
)engine=innodb default charset=utf8;

alter table channel_info add constraint Channel_Status_FK foreign key (status_id) references status_map(status_id);


--游戏
create table product(
	product_id int(20) primary key,
	product_name varchar(50) not null,
	product_icon varchar(255),
	date_created TIMESTAMP not null
)engine=innodb default charset=utf8;


--新老用户统计
create table user_count(
	id int(20) primary key auto_increment,
	new_user_count int(30) not null default 0,
	old_user_count int(30) not null default 0,
	channel_id int(20) not null,
	date_created TIMESTAMP not null
)engine=innodb default charset=utf8;

alter table user_count add constraint User_Count_Channel_FK foreign key (channel_id) references channel_info(channel_id);


--页面流量统计
create table page_traffic(
	id int(20) primary key auto_increment,
	page_id int(20) not null,
	channel_id int(20) not null,
	click_through int(20) not null default 0,
	date_created TIMESTAMP not null
)engine=innodb default charset=utf8;

alter table page_traffic add constraint Page_Traffic_Page_FK foreign key (page_id) references  page_map(page_id);
alter table page_traffic add constraint Page_Traffic_Channel_FK foreign key (channel_id) references  channel_info(channel_id);


--前30游戏和Banner点击次数统计
create table game_traffic(
	id int(20) primary key auto_increment,
	channel_id int(20) not null,
	product_id int(20) not null,
	click_through int(20) not null default 0,
	date_created TIMESTAMP not null,
	banner_flag boolean not null default false
)engine=innodb default charset=utf8;

alter table game_traffic add constraint Game_Traffic_Product_FK foreign key (product_id) references  product(product_id);
alter table game_traffic add constraint Game_Traffic_Channel_FK foreign key (channel_id) references  channel_info(channel_id);


--热词
create table keyword(
	id int(20) primary key auto_increment,
	keyword varchar(50) not null,
	count int(20) not null default 0,
	date_created TIMESTAMP not null,
	date_modified TIMESTAMP not null
)engine=innodb default charset=utf8;


--下载游戏统计
create table download_info(
	id int(20) primary key auto_increment,
	product_id int(20) not null,
	channel_id int(20) not null,
	download_count int(20) default 0,
	date_created TIMESTAMP not null
)engine=innodb default charset=utf8;


alter table download_info add constraint Download_Product_FK foreign key (product_id) references  product(product_id);
alter table download_info add constraint Download_Channel_FK foreign key (channel_id) references  channel_info(channel_id);


--用户
create table account(
	account_id int(20) primary key auto_increment,
	account_name varchar(20) not null,
	password varchar(20) not null,
	date_created TIMESTAMP not null,
	date_modified TIMESTAMP not null	
)engine=innodb default charset=utf8;


--打包信息
create table package_info(
	channel_id int(20) not null,
	product_id int(20) not null,
	date_created TIMESTAMP not null,
	date_modified TIMESTAMP not null,
	primary key(channel_id,product_id)	
)engine=innodb default charset=utf8;

