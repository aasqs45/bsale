drop table if exists t_user_role;
drop table if exists t_role_access;
drop table if exists t_user;
drop table if exists t_role;
drop table if exists t_access;

-- 基础资料
drop table if exists t_branch;
drop table if exists t_branch_class;
drop table if exists t_employee;
drop table if exists t_payment;
drop table if exists t_item;
drop table if exists t_item_class;
drop table if exists t_item_brand;
drop table if exists t_item_property;
drop table if exists t_sup_info;
drop table if exists t_sup_class;
drop table if exists t_cus_info;
drop table if exists t_cus_class;


--
-- 表的结构 用户角色表
--

create table t_user_role (
    user_id smallint not null,
    role_id smallint not null,
    primary key (user_id, role_id)
)  ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户角色';

--
-- 表的结构 角色资源表
--

create table t_role_access (
    role_id smallint not null,
    access_id smallint not null,
    primary key (role_id, access_id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='角色资源';

--
-- 表的结构 用户表
--

create table t_user (
	id smallint auto_increment,
	loginname varchar(32) not null unique,
	name varchar(32) not null,
	password varchar(32) not null,
	sex tinyint not null default 0,
	age tinyint not null default 0,
	usertype tinyint not null default 0,
	isdefault tinyint not null default 0,
	state tinyint not null default 0,
	createdatetime timestamp not null default 0,
	branch_id int not null,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='用户' ;

--
-- 表的结构 角色表
--

create table t_role(
	id smallint auto_increment,
	name varchar(32) not null,
	seq tinyint not null default 0,
	description varchar(255),
	isdefault tinyint not null default 0,
 	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='角色';

--
-- 表的结构 资源权限表
--

create table t_access (
	id smallint auto_increment,
	name varchar(32) not null,
	url varchar(100),
	description varchar(255),
	icon varchar(20),
	pid smallint,
	seq tinyint not null default 0,
	state tinyint not null default 0,
	accesstype tinyint not null default 0,
	createdatetime timestamp not null default 0,
    primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='资源';



--
-- 表的结构 机构档案
--

create table t_branch (
	id int auto_increment,
	code varchar(4) not null,
	name varchar(64) not null,
	icon varchar(20),
	seq tinyint not null default 0,
	pid int,
	manager varchar(32),
	tel varchar(32),
	address varchar(200),
	createdatetime timestamp not null default 0,
	branchtype tinyint not null default 0,
	branchclass_id int not null default 0,
    primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='机构档案';


--
-- 表的结构 店组档案
--
create table t_branch_class (
	id smallint auto_increment,
	name varchar(32) not null,
	code varchar(32),
	isdefault tinyint not null default 0,
    primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='机构分组';

--
-- 表的结构 员工档案
--
create table t_employee (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	sex tinyint not null default 0,
	age tinyint not null default 0,
	race varchar(16),
	hiredate timestamp not null default 0,
	tel varchar(32),
	cardid varchar(32),
	basesalary smallint,
	branch_id int,
	email varchar(32),
	duty varchar(32),
	rate smallint,
	createdatetime timestamp not null default 0,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='员工档案';

--
-- 表的结构 支付方式
--
create table t_payment (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	paytype tinyint not null default 0,
	isvalid tinyint not null default 0,
	isdefault tinyint not null default 0,
	account tinyint not null default 0,
	description varchar(255),
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='员工档案';

--
-- 表的结构 商品档案
--
create table t_item (
	id int auto_increment,
	itemno varchar(32) not null,
	barcode varchar(32) not null,
	name varchar(32) not null,
	itemclass_id smallint not null default 0,
	unit varchar(10),
	brand_id int not null default 0,
	property_id int not null default 0,
	sup_id int not null default 0,
	purchaseprice double not null default 0,
	retailprice double  not null default 0,
	wholesaleprice double not null default 0,
	isbranch tinyint not null default 0,
	state tinyint not null default 0,
	createdatetime timestamp not null default 0,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='商品档案';

--
-- 表的结构 商品分类
--
create table t_item_class (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	pid smallint,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='商品分类';

--
-- 表的结构 商品品牌	
--
create table t_item_brand (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='商品品牌';

--
-- 表的结构 商品品牌	
--
create table t_item_property (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='商品属性';


--
-- 表的结构 货商档案
--
create table t_sup_info (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	supclass_id smallint not null default 0,
	linkman varchar(32),
	tel varchar(32),
	address varchar(255),
	phone varchar(32),
	bank varchar(32),
	account varchar(32),
	email varchar(32),
	createdatetime timestamp not null default 0,
	state tinyint not null default 0,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='货商档案';

--
-- 表的结构 货商分类
--
create table t_sup_class (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='货商分类';


--
-- 表的结构 客户档案
--
create table t_cus_info (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	cusclass_id smallint not null default 0,
	linkman varchar(32),
	tel varchar(32),
	address varchar(255),
	phone varchar(32),
	bank varchar(32),
	account varchar(32),
	email varchar(32),
	createdatetime timestamp not null default 0,
	state tinyint not null default 0,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='客户档案';

--
-- 表的结构 客户分类
--
create table t_cus_class (
	id smallint auto_increment,
	code varchar(32) not null,
	name varchar(32) not null,
	primary key (id)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='客户分类';

