-- 字典表添加数据
insert into T_GF_DICT_TYPE (DICT_TYPE_ID,DICT_TYPE_NAME,APPID) values ('ED_INTERFACE_TYPE','服务类型','default');
insert into T_GF_DICT_TYPE (DICT_TYPE_ID,DICT_TYPE_NAME,APPID) values ('ED_INTERFACE_COMPANY','服务厂商','default');
insert into T_GF_DICT_TYPE (DICT_TYPE_ID,DICT_TYPE_NAME,APPID) values ('ED_CACHE_TIME','缓存时间','default');

insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_INTERFACE_TYPE','1','缓存服务','1','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_INTERFACE_TYPE','2','实时服务','2','default');

insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_INTERFACE_COMPANY','1','同盾数据','1','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_INTERFACE_COMPANY','2','百融数据','2','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_INTERFACE_COMPANY','3','义务数据','3','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_INTERFACE_COMPANY','4','芝麻信用','4','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_INTERFACE_COMPANY','5','国政通','5','default');

insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_CACHE_TIME','360','6小时','5','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_CACHE_TIME','720','12小时','9','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_CACHE_TIME','1440','1天','12','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_CACHE_TIME','2880','2天','15','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_CACHE_TIME','7200','5天','20','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_CACHE_TIME','10080','7天','30','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_CACHE_TIME','43200','30天','40','default');
insert into T_GF_DICT (DICT_TYPE_ID,DICT_ID,DICT_NAME,SORT_NO,APPID) values ('ED_CACHE_TIME','129600','90天','50','default');


-- 新建接口信息表
create table ED_INTERFACE_INFO
(
  pk_id             VARCHAR2(32) not null,
  interface_code    VARCHAR2(128) not null,
  interface_name    VARCHAR2(128) not null,
  valid_time        NUMBER(11) not null,
  req_url           VARCHAR2(256) not null,
  interface_desc    VARCHAR2(4000),
  interface_type    VARCHAR2(2) not null,
  interface_company VARCHAR2(2) not null,
  crt_user          VARCHAR2(32) not null,
  crt_time          TIMESTAMP(6) not null,
  update_user       VARCHAR2(32),
  update_time       TIMESTAMP(6),
  is_del            CHAR(1) default 0 not null
);
-- Add comments to the columns
comment on column ED_INTERFACE_INFO.pk_id
  is '主键ID';
comment on column ED_INTERFACE_INFO.interface_code
  is '接口编码';
comment on column ED_INTERFACE_INFO.interface_name
  is '接口名';
comment on column ED_INTERFACE_INFO.valid_time
  is '缓存时间';
comment on column ED_INTERFACE_INFO.req_url
  is '请求地址';
comment on column ED_INTERFACE_INFO.interface_desc
  is '接口描述';
comment on column ED_INTERFACE_INFO.interface_type
  is '接口类型';
comment on column ED_INTERFACE_INFO.interface_company
  is '接口厂商';
comment on column ED_INTERFACE_INFO.crt_user
  is '创建人';
comment on column ED_INTERFACE_INFO.crt_time
  is '创建时间';
comment on column ED_INTERFACE_INFO.update_user
  is '修改人';
comment on column ED_INTERFACE_INFO.update_time
  is '修改时间';
comment on column ED_INTERFACE_INFO.is_del
  is '是否删除，0未删除；1删除';
-- Create/Recreate indexes
create index IDX_SERVICE_INFO on ED_INTERFACE_INFO (INTERFACE_CODE);
-- Create/Recreate primary, unique and foreign key constraints
alter table ED_INTERFACE_INFO
  add constraint PK_SERVICE_INFO primary key (PK_ID);


-- 新建接口点击次数统计表
-- Create table
create table ED_INTERFACE_COUNT
(
  pk_id     VARCHAR2(32) not null,
  app_id    VARCHAR2(32) not null,
  req_user  VARCHAR2(32),
  req_time  TIMESTAMP(6) not null,
  req_param VARCHAR2(2000)
);
-- Add comments to the columns
comment on column ED_INTERFACE_COUNT.pk_id
  is '主键ID';
comment on column ED_INTERFACE_COUNT.app_id
  is '应用ID';
comment on column ED_INTERFACE_COUNT.req_user
  is '请求人';
comment on column ED_INTERFACE_COUNT.req_time
  is '请求发起时间';
comment on column ED_INTERFACE_COUNT.req_param
  is '请求参数（json字符串形式）';
-- Create/Recreate primary, unique and foreign key constraints
alter table ED_INTERFACE_COUNT
  add constraint PK_SERVICE_COUNT primary key (PK_ID);


-- 新建模型参数列表表1
-- Create table
create table ED_INTERFACE_PARAM
(
  pk_id    VARCHAR2(32) not null,
  interface_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     CHAR(1) not null,
  note     VARCHAR2(4000),
  col_type VARCHAR2(32)
);
-- Add comments to the table
comment on table ED_INTERFACE_PARAM
  is '接口管理-接口字段列表';
-- Add comments to the columns
comment on column ED_INTERFACE_PARAM.pk_id
  is '主键';
comment on column ED_INTERFACE_PARAM.interface_id
  is '接口ID';
comment on column ED_INTERFACE_PARAM.seq
  is '序号（和类型一起确定唯一）';
comment on column ED_INTERFACE_PARAM.name
  is '名称((唯一、英文)';
comment on column ED_INTERFACE_PARAM.describe
  is '说明';
comment on column ED_INTERFACE_PARAM.type
  is '类型(1、查询字段；2、返回字段)';
comment on column ED_INTERFACE_PARAM.note
  is '备注';
comment on column ED_INTERFACE_PARAM.col_type
  is '字段类型';
-- Create/Recreate indexes
create index IDX_ED_INTERFACE_PARAM on ED_INTERFACE_PARAM (interface_id);
-- Create/Recreate primary, unique and foreign key constraints
alter table ED_INTERFACE_PARAM
  add constraint PK_ED_INTERFACE_PARAM primary key (PK_ID);


-- 接口管理-应用表
-- Create table
create table ED_APPLICATION
(
  pk_id        VARCHAR2(32) not null,
  interface_id VARCHAR2(32) not null,
  name         VARCHAR2(64) not null,
  describe     VARCHAR2(256) not null,
  max_num      NUMBER(10),
  del_flg      CHAR(1) not null,
  crt_user     VARCHAR2(32) not null,
  crt_time     VARCHAR2(32) not null,
  upt_user     VARCHAR2(32),
  upt_time     VARCHAR2(32),
  note         VARCHAR2(4000),
  is_check     CHAR(1)
);
-- Add comments to the table
comment on table ED_APPLICATION
  is '接口管理-应用表';
-- Add comments to the columns
comment on column ED_APPLICATION.pk_id
  is '主键';
comment on column ED_APPLICATION.interface_id
  is '接口ID';
comment on column ED_APPLICATION.name
  is '名称((英文、唯一)';
comment on column ED_APPLICATION.describe
  is '说明';
comment on column ED_APPLICATION.max_num
  is '最大返回数';
comment on column ED_APPLICATION.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column ED_APPLICATION.crt_user
  is '创建者';
comment on column ED_APPLICATION.crt_time
  is '创建时间';
comment on column ED_APPLICATION.upt_user
  is '更新者';
comment on column ED_APPLICATION.upt_time
  is '更新时间';
comment on column ED_APPLICATION.note
  is '备注';
comment on column ED_APPLICATION.is_check
  is '是否校验输入参数(0：不校验，1：校验)';
-- Create/Recreate indexes
create index IDX_ED_APP_DELFLG on ED_APPLICATION (DEL_FLG);
-- Create/Recreate primary, unique and foreign key constraints
alter table ED_APPLICATION
  add constraint PK_ED_APPLICATION primary key (PK_ID);



-- 接口管理-输入参数
-- Create table
create table ED_APPLICATION_REQUEST_PARAM
(
  pk_id       VARCHAR2(32) not null,
  seq         NUMBER(3) not null,
  name        VARCHAR2(64) not null,
  describe    VARCHAR2(256) not null,
  is_need     CHAR(1) not null,
  default_val VARCHAR2(256),
  app_id      VARCHAR2(32)
);
-- Add comments to the table
comment on table ED_APPLICATION_REQUEST_PARAM
  is '接口管理-输入参数';
-- Add comments to the columns
comment on column ED_APPLICATION_REQUEST_PARAM.pk_id
  is '主键';
comment on column ED_APPLICATION_REQUEST_PARAM.seq
  is '序号';
comment on column ED_APPLICATION_REQUEST_PARAM.name
  is '名称((英文)';
comment on column ED_APPLICATION_REQUEST_PARAM.describe
  is '说明';
comment on column ED_APPLICATION_REQUEST_PARAM.is_need
  is '是否必输（0:是；1：否）';
comment on column ED_APPLICATION_REQUEST_PARAM.default_val
  is '默认值';
comment on column ED_APPLICATION_REQUEST_PARAM.app_id
  is '接口应用ID';
-- Create/Recreate indexes
create index IDX_ED_APP_EXE_APPID on ED_APPLICATION_REQUEST_PARAM (APP_ID);
-- Create/Recreate primary, unique and foreign key constraints
alter table ED_APPLICATION_REQUEST_PARAM
  add constraint PK_ED_APPLICATION_REQUEST_PARA primary key (PK_ID);


-- 接口管理返回参数表
-- Create table
create table ED_APPLICATION_RESPONSE_PARAM
(
  pk_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  app_id   VARCHAR2(32)
);
-- Add comments to the table
comment on table ED_APPLICATION_RESPONSE_PARAM
  is '接口管理返回参数表';
-- Add comments to the columns
comment on column ED_APPLICATION_RESPONSE_PARAM.pk_id
  is '主键';
comment on column ED_APPLICATION_RESPONSE_PARAM.seq
  is '序号';
comment on column ED_APPLICATION_RESPONSE_PARAM.name
  is '名称((英文)';
comment on column ED_APPLICATION_RESPONSE_PARAM.describe
  is '说明';
comment on column ED_APPLICATION_RESPONSE_PARAM.app_id
  is '接口应用ID';
-- Create/Recreate indexes
create index IDX_ED_APP_RES_APPID on ED_APPLICATION_RESPONSE_PARAM (APP_ID);
-- Create/Recreate primary, unique and foreign key constraints
alter table ED_APPLICATION_RESPONSE_PARAM
  add constraint PK_ED_APPLICATION_RES_PARAM primary key (PK_ID);