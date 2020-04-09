/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/9 16:21:01                            */
/*==============================================================*/


drop table if exists manager;

drop table if exists sys_operation_log;

/*==============================================================*/
/* Table: manager                                               */
/*==============================================================*/
create table manager
(
   id                   bigint not null auto_increment,
   username             varchar(64) not null,
   password             varchar(64) not null,
   email                varchar(50) not null default '',
   phone                varchar(16) not null default '',
   status               int not null default 0 comment '用户状态（0：启动； 1：禁用）默认0启用',
   err_num              int not null default 0 comment '登陆错误次数；后面限制错误登陆次数扩展才用',
   create_millis        bigint not null default 0,
   last_update_millis   bigint not null default 0,
   extra                varchar(128) not null default '',
   primary key (id)
);

alter table manager comment '后台用户';

/*==============================================================*/
/* Table: sys_operation_log                                     */
/*==============================================================*/
create table sys_operation_log
(
   id                   bigint not null auto_increment,
   username             varchar(64) not null,
   ip                   varchar(16) not null default '',
   url                  varchar(128) not null default '',
   method               varchar(64) not null default '',
   parameters           varchar(1600) not null default '' comment '传入参数列表',
   execution_time       bigint not null default 0 comment '执行的时长（毫秒）',
   visit_millis         bigint not null default 0 comment '访问时间戳',
   create_millis        bigint not null default 0,
   last_update_millis   bigint not null default 0,
   extra                varchar(128) not null default '',
   primary key (id)
);

alter table sys_operation_log comment '系统操作日志';

