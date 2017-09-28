--联机查询应用
prompt Creating table OLQ_APPLICATION
prompt ==============================
prompt
create table OLQ_APPLICATION
(
  pk_id     VARCHAR2(32) not null,
  olq_sql   VARCHAR2(1024) not null,
  olq_ds_id VARCHAR2(32) not null,
  max_num   NUMBER(10),
  del_flg   CHAR(1) not null,
  crt_user  VARCHAR2(32) not null,
  crt_time  VARCHAR2(32) not null,
  upt_user  VARCHAR2(32) not null,
  upt_time  VARCHAR2(32) not null,
  note      VARCHAR2(4000),
  name      VARCHAR2(128) not null,
  describe  VARCHAR2(128) not null
)
;
comment on column OLQ_APPLICATION.pk_id
  is '主键';
comment on column OLQ_APPLICATION.olq_sql
  is '应用配置的SQL语句';
comment on column OLQ_APPLICATION.olq_ds_id
  is '联机查询数据源ID';
comment on column OLQ_APPLICATION.max_num
  is '最大返回数';
comment on column OLQ_APPLICATION.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column OLQ_APPLICATION.crt_user
  is '创建者';
comment on column OLQ_APPLICATION.crt_time
  is '创建时间';
comment on column OLQ_APPLICATION.upt_user
  is '更新者';
comment on column OLQ_APPLICATION.upt_time
  is '更新时间';
comment on column OLQ_APPLICATION.note
  is '备注';
comment on column OLQ_APPLICATION.name
  is '名称';
comment on column OLQ_APPLICATION.describe
  is '说明';
alter table OLQ_APPLICATION
  add constraint OLQ_APPLICATION_PKID primary key (PK_ID);

prompt
prompt Creating table OLQ_APPLICATION_PARAM
prompt ====================================
prompt
create table OLQ_APPLICATION_PARAM
(
  pk_id         VARCHAR2(32) not null,
  param_name    VARCHAR2(128) not null,
  param_desc    VARCHAR2(256) not null,
  default_value VARCHAR2(64),
  is_need       CHAR(1) not null,
  olq_app_id    VARCHAR2(32) not null,
  seq           NUMBER(3)
)
;
comment on column OLQ_APPLICATION_PARAM.pk_id
  is '主键';
comment on column OLQ_APPLICATION_PARAM.param_name
  is '参数名称';
comment on column OLQ_APPLICATION_PARAM.param_desc
  is '参数说明';
comment on column OLQ_APPLICATION_PARAM.default_value
  is '默认值';
comment on column OLQ_APPLICATION_PARAM.is_need
  is '是否必输，0：必输，1：不必输';
comment on column OLQ_APPLICATION_PARAM.olq_app_id
  is '应用ID';
comment on column OLQ_APPLICATION_PARAM.seq
  is '序号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table OLQ_APPLICATION_PARAM
  add constraint OLQ_APPLICATION_PARAM_PKID primary key (PK_ID);


-- 20170719 实时流新增字段类型   
-- Add/modify columns 
alter table RTS_METADATA_COLUMN add type varchar2(32) not null;
-- Add comments to the columns 
comment on column RTS_METADATA_COLUMN.type
  is '类型';

-- 20170918 缓冲队列控制
-- Add/modify columns
alter table RC_USER_SERVICE add max_sync_wait_num NUMBER(10) default 10 not null;
alter table RC_USER_SERVICE add max_async_wait_num NUMBER(10) default 10 not null;
alter table RC_USER_SERVICE add max_wait_timeout NUMBER(10) default 3000 not null;
alter table RC_USER_SERVICE add max_execute_timeout NUMBER(10) default 3000 not null;
-- Add comments to the columns
comment on column RC_USER_SERVICE.max_sync_wait_num
  is '最大同步队列等待数';
comment on column RC_USER_SERVICE.max_async_wait_num
  is '最大异步队列等待数';
comment on column RC_USER_SERVICE.max_wait_timeout
  is '最大等待超时时间';
comment on column RC_USER_SERVICE.max_execute_timeout
  is '最大执行超时时间';
-- 20170918 缓冲队列控制
-- Drop columns
alter table RC_USER_SERVICE drop column max_wait_timeout;
alter table RC_USER_SERVICE drop column max_execute_timeout;
-- Add/modify columns
alter table RC_USER_SERVICE add max_sync_wait_timeout NUMBER(10) default 3000 not null;
alter table RC_USER_SERVICE add max_async_wait_timeout NUMBER(10) default 3000 not null;
alter table RC_USER_SERVICE add max_sync_execute_timeout NUMBER(10) default 3000 not null;
alter table RC_USER_SERVICE add max_async_execute_timeout NUMBER(10) default 3000 not null;
-- Add comments to the columns
comment on column RC_USER_SERVICE.max_sync_wait_timeout
  is '同步最大等待超时时间';
comment on column RC_USER_SERVICE.max_async_wait_timeout
  is '异步最大等待超时时间';
comment on column RC_USER_SERVICE.max_sync_execute_timeout
  is '同步最大执行超时时间';
comment on column RC_USER_SERVICE.max_async_execute_timeout
  is '异步最大执行超时时间';