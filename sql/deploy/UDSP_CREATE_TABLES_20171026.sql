-------------------------------------------
-- Export file for user UDSP@BIGDATA     --
-- Created by PC on 2017/10/26, 16:33:01 --
-------------------------------------------

set define off
spool UDSP_CREATE_TABLES.log

prompt
prompt Creating table COM_DATASOURCE
prompt =============================
prompt
create table COM_DATASOURCE
(
  pk_id      VARCHAR2(32) not null,
  name       VARCHAR2(64) not null,
  describe   VARCHAR2(256) not null,
  type       VARCHAR2(32) not null,
  note       VARCHAR2(4000),
  del_flg    CHAR(1) not null,
  crt_user   VARCHAR2(32) not null,
  crt_time   VARCHAR2(32) not null,
  upt_user   VARCHAR2(32) not null,
  upt_time   VARCHAR2(32) not null,
  impl_class VARCHAR2(256),
  model      VARCHAR2(32) not null
)
;
comment on column COM_DATASOURCE.pk_id
  is '主键';
comment on column COM_DATASOURCE.name
  is '名称((唯一、英文)';
comment on column COM_DATASOURCE.describe
  is '说明';
comment on column COM_DATASOURCE.type
  is '数据源类型';
comment on column COM_DATASOURCE.note
  is '备注';
comment on column COM_DATASOURCE.del_flg
  is '删除标志（0：未删除，1：删除';
comment on column COM_DATASOURCE.crt_user
  is '创建者';
comment on column COM_DATASOURCE.crt_time
  is '创建时间';
comment on column COM_DATASOURCE.upt_user
  is '更新者';
comment on column COM_DATASOURCE.upt_time
  is '更新时间';
comment on column COM_DATASOURCE.impl_class
  is '接口实现类';
comment on column COM_DATASOURCE.model
  is '所属模块（IQ、OLQ、RTS、IM）';
create index IDX_COM_DS_DELFLG on COM_DATASOURCE (DEL_FLG);
create index IDX_COM_DS_DELFLG_MODEL_NAME on COM_DATASOURCE (DEL_FLG, MODEL, NAME);
alter table COM_DATASOURCE
  add constraint PK_COM_DATASOURCE primary key (PK_ID);

prompt
prompt Creating table COM_OPERATION_LOG
prompt ================================
prompt
create table COM_OPERATION_LOG
(
  pk_id          VARCHAR2(32) not null,
  action_type    CHAR(1) not null,
  action_url     VARCHAR2(256) not null,
  action_user    VARCHAR2(32) not null,
  action_time    VARCHAR2(32) not null,
  action_content VARCHAR2(1024)
)
;
comment on column COM_OPERATION_LOG.pk_id
  is '主键';
comment on column COM_OPERATION_LOG.action_type
  is '操作类型（1添加 2更新 3删除 4查询）';
comment on column COM_OPERATION_LOG.action_url
  is '操作URL';
comment on column COM_OPERATION_LOG.action_user
  is '操作用户';
comment on column COM_OPERATION_LOG.action_time
  is '操作时间';
comment on column COM_OPERATION_LOG.action_content
  is '操作信息';
alter table COM_OPERATION_LOG
  add constraint PK_MC_PROPERTIES_LOG primary key (PK_ID);

prompt
prompt Creating table COM_PROPERTIES
prompt =============================
prompt
create table COM_PROPERTIES
(
  pk_id    VARCHAR2(32) not null,
  fk_id    VARCHAR2(32) not null,
  name     VARCHAR2(256) not null,
  value    VARCHAR2(4000) not null,
  describe VARCHAR2(4000)
)
;
comment on column COM_PROPERTIES.pk_id
  is '主键';
comment on column COM_PROPERTIES.fk_id
  is '外键ID';
comment on column COM_PROPERTIES.name
  is '名称';
comment on column COM_PROPERTIES.value
  is '数值';
comment on column COM_PROPERTIES.describe
  is '说明';
alter table COM_PROPERTIES
  add constraint PK_PROPERTIES primary key (PK_ID);

prompt
prompt Creating table IM_METADATA
prompt ==========================
prompt
create table IM_METADATA
(
  pk_id    VARCHAR2(32) not null,
  ds_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null,
  tb_name  VARCHAR2(64) not null,
  status   CHAR(1) not null,
  type     CHAR(1) not null
)
;
comment on table IM_METADATA
  is '交互建模-元数据配置';
comment on column IM_METADATA.pk_id
  is '主键';
comment on column IM_METADATA.ds_id
  is '数据源ID';
comment on column IM_METADATA.name
  is '名称/表名((唯一、英文)';
comment on column IM_METADATA.describe
  is '说明/表说明';
comment on column IM_METADATA.note
  is '备注';
comment on column IM_METADATA.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column IM_METADATA.crt_user
  is '创建者';
comment on column IM_METADATA.crt_time
  is '创建时间';
comment on column IM_METADATA.upt_user
  is '更新者';
comment on column IM_METADATA.upt_time
  is '更新时间';
comment on column IM_METADATA.tb_name
  is '库表';
comment on column IM_METADATA.status
  is '状态（1：未建，2：已建）';
comment on column IM_METADATA.type
  is '类型（1：内表，2：外表）';
alter table IM_METADATA
  add constraint PK_IM_MATEDATA primary key (PK_ID);

prompt
prompt Creating table IM_METADATA_COLUMN
prompt =================================
prompt
create table IM_METADATA_COLUMN
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     VARCHAR2(32) not null,
  length   VARCHAR2(32),
  note     VARCHAR2(4000),
  indexed  CHAR(1) not null,
  primary  CHAR(1) not null,
  stored   CHAR(1) not null
)
;
comment on table IM_METADATA_COLUMN
  is '交互建模-元数据字段';
comment on column IM_METADATA_COLUMN.pk_id
  is '主键';
comment on column IM_METADATA_COLUMN.md_id
  is '元数据ID';
comment on column IM_METADATA_COLUMN.seq
  is '序号';
comment on column IM_METADATA_COLUMN.name
  is '名称（唯一、英文）';
comment on column IM_METADATA_COLUMN.describe
  is '说明';
comment on column IM_METADATA_COLUMN.type
  is '类型';
comment on column IM_METADATA_COLUMN.length
  is '长度';
comment on column IM_METADATA_COLUMN.note
  is '备注';
comment on column IM_METADATA_COLUMN.indexed
  is '索引（0：是；1：否）';
comment on column IM_METADATA_COLUMN.primary
  is '主键（0：是；1：否）';
comment on column IM_METADATA_COLUMN.stored
  is '存储（0：是；1：否）';
alter table IM_METADATA_COLUMN
  add constraint PK_IM_MATEDATA_COL primary key (PK_ID);

prompt
prompt Creating table IM_MODEL
prompt =======================
prompt
create table IM_MODEL
(
  pk_id       VARCHAR2(32) not null,
  name        VARCHAR2(64) not null,
  describe    VARCHAR2(256) not null,
  s_ds_id     VARCHAR2(32) not null,
  t_md_id     VARCHAR2(32) not null,
  note        VARCHAR2(4000),
  del_flg     CHAR(1) not null,
  crt_user    VARCHAR2(32) not null,
  crt_time    VARCHAR2(32) not null,
  upt_user    VARCHAR2(32) not null,
  upt_time    VARCHAR2(32) not null,
  type        CHAR(1) not null,
  build_mode  CHAR(1),
  update_mode CHAR(1),
  e_ds_id     VARCHAR2(32),
  status      CHAR(1) not null
)
;
comment on table IM_MODEL
  is '交互建模-模型配置';
comment on column IM_MODEL.pk_id
  is '主键';
comment on column IM_MODEL.name
  is '名称';
comment on column IM_MODEL.describe
  is '说明';
comment on column IM_MODEL.s_ds_id
  is '源数据源ID';
comment on column IM_MODEL.t_md_id
  is '目标元数据ID';
comment on column IM_MODEL.note
  is '备注';
comment on column IM_MODEL.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column IM_MODEL.crt_user
  is '创建者';
comment on column IM_MODEL.crt_time
  is '创建时间';
comment on column IM_MODEL.upt_user
  is '更新者';
comment on column IM_MODEL.upt_time
  is '更新时间';
comment on column IM_MODEL.type
  is '类型（1：批量 2：实时）';
comment on column IM_MODEL.build_mode
  is '构建策略（1：增量，2：全量）';
comment on column IM_MODEL.update_mode
  is '更新策略（1、匹配更新 2、更新、插入 3、增量插入，默认：2）';
comment on column IM_MODEL.e_ds_id
  is '引擎数据源ID';
comment on column IM_MODEL.status
  is '状态（1：未建，2：已建）';
alter table IM_MODEL
  add constraint PK_IM_MODEL primary key (PK_ID);

prompt
prompt Creating table IM_MODEL_FILTER_COLUMN
prompt =====================================
prompt
create table IM_MODEL_FILTER_COLUMN
(
  pk_id       VARCHAR2(32) not null,
  model_id    VARCHAR2(32) not null,
  seq         NUMBER(3) not null,
  name        VARCHAR2(64) not null,
  describe    VARCHAR2(256) not null,
  type        VARCHAR2(32) not null,
  length      VARCHAR2(32),
  is_need     CHAR(1) not null,
  default_val VARCHAR2(64),
  operator    VARCHAR2(32) not null,
  label       VARCHAR2(64) not null
)
;
comment on table IM_MODEL_FILTER_COLUMN
  is '交互模型-模型过滤字段';
comment on column IM_MODEL_FILTER_COLUMN.pk_id
  is '主键';
comment on column IM_MODEL_FILTER_COLUMN.model_id
  is '模型ID';
comment on column IM_MODEL_FILTER_COLUMN.seq
  is '序号';
comment on column IM_MODEL_FILTER_COLUMN.name
  is '名称((英文)';
comment on column IM_MODEL_FILTER_COLUMN.describe
  is '说明';
comment on column IM_MODEL_FILTER_COLUMN.type
  is '类型';
comment on column IM_MODEL_FILTER_COLUMN.length
  is '长度';
comment on column IM_MODEL_FILTER_COLUMN.is_need
  is '是否必输（0：是；1：否）';
comment on column IM_MODEL_FILTER_COLUMN.default_val
  is '默认值';
comment on column IM_MODEL_FILTER_COLUMN.operator
  is '操作符';
comment on column IM_MODEL_FILTER_COLUMN.label
  is '别名';
alter table IM_MODEL_FILTER_COLUMN
  add constraint PK_IM_MODEL_FILTER_COL primary key (PK_ID);

prompt
prompt Creating table IM_MODEL_MAPPING
prompt ===============================
prompt
create table IM_MODEL_MAPPING
(
  pk_id    VARCHAR2(32) not null,
  model_id VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  col_id   VARCHAR2(32) not null,
  note     VARCHAR2(4000),
  type     VARCHAR2(32) not null,
  length   VARCHAR2(32),
  describe VARCHAR2(256) not null,
  indexed  CHAR(1) not null,
  primary  CHAR(1),
  stored   CHAR(1) not null
)
;
comment on table IM_MODEL_MAPPING
  is '交互建模-模型字段映射';
comment on column IM_MODEL_MAPPING.pk_id
  is '主键';
comment on column IM_MODEL_MAPPING.model_id
  is '模型ID';
comment on column IM_MODEL_MAPPING.seq
  is '序号';
comment on column IM_MODEL_MAPPING.name
  is '源元数据字段名称';
comment on column IM_MODEL_MAPPING.col_id
  is '目标元数据字段ID';
comment on column IM_MODEL_MAPPING.note
  is '备注';
comment on column IM_MODEL_MAPPING.type
  is '源元数据字段类型';
comment on column IM_MODEL_MAPPING.length
  is '源元数据字段长度';
comment on column IM_MODEL_MAPPING.describe
  is '源元数据字段说明';
comment on column IM_MODEL_MAPPING.indexed
  is '源元数据字段索引（0：是；1：否）';
comment on column IM_MODEL_MAPPING.primary
  is '源元数据字段主键（0：是；1：否）';
comment on column IM_MODEL_MAPPING.stored
  is '源元数据字段存储（0：是；1：否）';
alter table IM_MODEL_MAPPING
  add constraint PK_IM_MODEL_MAPPING primary key (PK_ID);

prompt
prompt Creating table IM_MODEL_UPDATE_KEY
prompt ==================================
prompt
create table IM_MODEL_UPDATE_KEY
(
  pk_id    VARCHAR2(32) not null,
  col_id   VARCHAR2(32) not null,
  model_id VARCHAR2(32) not null
)
;
comment on table IM_MODEL_UPDATE_KEY
  is '交互建模-模型更新键值';
comment on column IM_MODEL_UPDATE_KEY.pk_id
  is '主键';
comment on column IM_MODEL_UPDATE_KEY.col_id
  is '目标元数据字段ID';
comment on column IM_MODEL_UPDATE_KEY.model_id
  is '模型ID';
alter table IM_MODEL_UPDATE_KEY
  add constraint PK_IM_MODEL_UPDATE_KEY primary key (PK_ID);

prompt
prompt Creating table IQ_APPLICATION
prompt =============================
prompt
create table IQ_APPLICATION
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  note     VARCHAR2(4000),
  max_num  NUMBER(10),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null
)
;
comment on table IQ_APPLICATION
  is '交互查询-应用表';
comment on column IQ_APPLICATION.pk_id
  is '主键';
comment on column IQ_APPLICATION.md_id
  is '元数据ID';
comment on column IQ_APPLICATION.name
  is '名称(唯一、英文)';
comment on column IQ_APPLICATION.describe
  is '说明';
comment on column IQ_APPLICATION.note
  is '备注';
comment on column IQ_APPLICATION.max_num
  is '最大返回数';
comment on column IQ_APPLICATION.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column IQ_APPLICATION.crt_user
  is '创建者';
comment on column IQ_APPLICATION.crt_time
  is '创建时间';
comment on column IQ_APPLICATION.upt_user
  is '更新者';
comment on column IQ_APPLICATION.upt_time
  is '更新时间';
create index IDX_IQ_APP_DELFLG on IQ_APPLICATION (DEL_FLG);
create index IDX_IQ_APP_DELFLG_MDID on IQ_APPLICATION (DEL_FLG, MD_ID);
create index IDX_IQ_APP_DELFLG_NAME on IQ_APPLICATION (DEL_FLG, NAME);
alter table IQ_APPLICATION
  add constraint PK_IQ_APPLICATION primary key (PK_ID);

prompt
prompt Creating table IQ_APPLICATION_ORDER_COLUMN
prompt ==========================================
prompt
create table IQ_APPLICATION_ORDER_COLUMN
(
  pk_id      VARCHAR2(32) not null,
  app_id     VARCHAR2(32) not null,
  seq        NUMBER(3) not null,
  name       VARCHAR2(64) not null,
  describe   VARCHAR2(256),
  type       VARCHAR2(32) not null,
  order_type VARCHAR2(32) not null,
  note       VARCHAR2(4000)
)
;
comment on table IQ_APPLICATION_ORDER_COLUMN
  is '交互查询-排序参数';
comment on column IQ_APPLICATION_ORDER_COLUMN.pk_id
  is '主键';
comment on column IQ_APPLICATION_ORDER_COLUMN.app_id
  is '应用ID';
comment on column IQ_APPLICATION_ORDER_COLUMN.seq
  is '序号';
comment on column IQ_APPLICATION_ORDER_COLUMN.name
  is '名称((英文)';
comment on column IQ_APPLICATION_ORDER_COLUMN.describe
  is '说明';
comment on column IQ_APPLICATION_ORDER_COLUMN.type
  is '类型';
comment on column IQ_APPLICATION_ORDER_COLUMN.order_type
  is '排序类型(ASC、DESC)';
comment on column IQ_APPLICATION_ORDER_COLUMN.note
  is '备注';
create index IDX_IQ_APP_ORDER_COL_APPID on IQ_APPLICATION_ORDER_COLUMN (APP_ID);
alter table IQ_APPLICATION_ORDER_COLUMN
  add constraint PK_IQ_APPLICATION_ORDER_COLUMN primary key (PK_ID);

prompt
prompt Creating table IQ_APPLICATION_QUERY_COLUMN
prompt ==========================================
prompt
create table IQ_APPLICATION_QUERY_COLUMN
(
  pk_id        VARCHAR2(32) not null,
  app_id       VARCHAR2(32) not null,
  seq          NUMBER(3) not null,
  name         VARCHAR2(64) not null,
  describe     VARCHAR2(256) not null,
  type         VARCHAR2(32),
  length       VARCHAR2(32),
  is_need      CHAR(1) not null,
  default_val  VARCHAR2(64),
  operator     VARCHAR2(32) not null,
  label        VARCHAR2(64),
  is_offer_out CHAR(1) not null,
  note         VARCHAR2(4000)
)
;
comment on table IQ_APPLICATION_QUERY_COLUMN
  is '交互查询-查询参数表';
comment on column IQ_APPLICATION_QUERY_COLUMN.pk_id
  is '主键';
comment on column IQ_APPLICATION_QUERY_COLUMN.app_id
  is '应用ID';
comment on column IQ_APPLICATION_QUERY_COLUMN.seq
  is '序号';
comment on column IQ_APPLICATION_QUERY_COLUMN.name
  is '名称((英文)';
comment on column IQ_APPLICATION_QUERY_COLUMN.describe
  is '说明';
comment on column IQ_APPLICATION_QUERY_COLUMN.type
  is '类型';
comment on column IQ_APPLICATION_QUERY_COLUMN.length
  is '长度';
comment on column IQ_APPLICATION_QUERY_COLUMN.is_need
  is '是否必输（0：是；1：否）';
comment on column IQ_APPLICATION_QUERY_COLUMN.default_val
  is '默认值';
comment on column IQ_APPLICATION_QUERY_COLUMN.operator
  is '操作符';
comment on column IQ_APPLICATION_QUERY_COLUMN.label
  is '别名';
comment on column IQ_APPLICATION_QUERY_COLUMN.is_offer_out
  is '是否开放(0：是；1：否)';
comment on column IQ_APPLICATION_QUERY_COLUMN.note
  is '备注';
create index IDX_IQ_APP_QUERY_COL_APPID on IQ_APPLICATION_QUERY_COLUMN (APP_ID);
alter table IQ_APPLICATION_QUERY_COLUMN
  add constraint PK_IQ_APPLICATION_QUERY_COLUMN primary key (PK_ID);

prompt
prompt Creating table IQ_APPLICATION_RETURN_COLUMN
prompt ===========================================
prompt
create table IQ_APPLICATION_RETURN_COLUMN
(
  pk_id    VARCHAR2(32) not null,
  app_id   VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     VARCHAR2(32),
  stats    VARCHAR2(32) not null,
  label    VARCHAR2(64),
  seq      NUMBER(3) not null,
  length   VARCHAR2(32),
  note     VARCHAR2(4000)
)
;
comment on table IQ_APPLICATION_RETURN_COLUMN
  is '交互查询-返回参数表';
comment on column IQ_APPLICATION_RETURN_COLUMN.pk_id
  is '主键';
comment on column IQ_APPLICATION_RETURN_COLUMN.app_id
  is '应用ID';
comment on column IQ_APPLICATION_RETURN_COLUMN.name
  is '名称((唯一、英文)';
comment on column IQ_APPLICATION_RETURN_COLUMN.describe
  is '说明';
comment on column IQ_APPLICATION_RETURN_COLUMN.type
  is '类型';
comment on column IQ_APPLICATION_RETURN_COLUMN.stats
  is '统计函数';
comment on column IQ_APPLICATION_RETURN_COLUMN.label
  is '别名';
comment on column IQ_APPLICATION_RETURN_COLUMN.seq
  is '序号';
comment on column IQ_APPLICATION_RETURN_COLUMN.length
  is '长度';
comment on column IQ_APPLICATION_RETURN_COLUMN.note
  is '备注';
create index IDX_IQ_APP_RETURN_COL_APPID on IQ_APPLICATION_RETURN_COLUMN (APP_ID);
alter table IQ_APPLICATION_RETURN_COLUMN
  add constraint PK_IQ_APPLICATION_RETURN_COLUM primary key (PK_ID);

prompt
prompt Creating table IQ_METADATA
prompt ==========================
prompt
create table IQ_METADATA
(
  pk_id    VARCHAR2(32) not null,
  ds_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null,
  tb_name  VARCHAR2(64) not null
)
;
comment on table IQ_METADATA
  is '交互式查询-元数据集';
comment on column IQ_METADATA.pk_id
  is '主键';
comment on column IQ_METADATA.ds_id
  is '数据源ID';
comment on column IQ_METADATA.name
  is '名称/表名((唯一、英文)';
comment on column IQ_METADATA.describe
  is '说明/表说明';
comment on column IQ_METADATA.note
  is '备注';
comment on column IQ_METADATA.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column IQ_METADATA.crt_user
  is '创建者';
comment on column IQ_METADATA.crt_time
  is '创建时间';
comment on column IQ_METADATA.upt_user
  is '更新者';
comment on column IQ_METADATA.upt_time
  is '更新时间';
comment on column IQ_METADATA.tb_name
  is '表名';
create index IDX_IQ_MD_DELFLG on IQ_METADATA (DEL_FLG);
create index IDX_IQ_MD_DELFLG_DSID on IQ_METADATA (DEL_FLG, DS_ID);
create index IDX_IQ_MD_DELFLG_NAME on IQ_METADATA (DEL_FLG, NAME);
alter table IQ_METADATA
  add constraint PK_IQ_MATEDATA primary key (PK_ID);

prompt
prompt Creating table IQ_METADATA_COLUMN
prompt =================================
prompt
create table IQ_METADATA_COLUMN
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     CHAR(1) not null,
  length   VARCHAR2(32),
  note     VARCHAR2(4000),
  col_type VARCHAR2(32)
)
;
comment on table IQ_METADATA_COLUMN
  is '交互查询-元数据字段列表';
comment on column IQ_METADATA_COLUMN.pk_id
  is '主键';
comment on column IQ_METADATA_COLUMN.md_id
  is '元数据ID';
comment on column IQ_METADATA_COLUMN.seq
  is '序号（和类型一起确定唯一）';
comment on column IQ_METADATA_COLUMN.name
  is '名称((唯一、英文)';
comment on column IQ_METADATA_COLUMN.describe
  is '说明';
comment on column IQ_METADATA_COLUMN.type
  is '类型(1、查询字段；2、返回字段)';
comment on column IQ_METADATA_COLUMN.length
  is '长度';
comment on column IQ_METADATA_COLUMN.note
  is '备注';
comment on column IQ_METADATA_COLUMN.col_type
  is '字段类型';
create index IDX_IQ_MD_COL_MDID on IQ_METADATA_COLUMN (MD_ID);
alter table IQ_METADATA_COLUMN
  add constraint PK_IQ_METADATA_COLUMN primary key (PK_ID);

prompt
prompt Creating table MC_CONSUME_LOG
prompt =============================
prompt
create table MC_CONSUME_LOG
(
  pk_id              VARCHAR2(68) not null,
  user_name          VARCHAR2(32) not null,
  service_name       VARCHAR2(64),
  request_content    VARCHAR2(4000) not null,
  request_start_time VARCHAR2(32) not null,
  request_end_time   VARCHAR2(32) not null,
  run_start_time     VARCHAR2(32),
  run_end_time       VARCHAR2(32),
  response_content   VARCHAR2(512),
  status             CHAR(1) not null,
  error_code         VARCHAR2(32),
  app_type           VARCHAR2(32),
  request_type       CHAR(1) not null,
  app_name           VARCHAR2(64),
  message            VARCHAR2(4000),
  sync_type          VARCHAR2(32)
)
;
comment on table MC_CONSUME_LOG
  is '监控中心-消费日志';
comment on column MC_CONSUME_LOG.pk_id
  is '主键';
comment on column MC_CONSUME_LOG.user_name
  is '用户名';
comment on column MC_CONSUME_LOG.service_name
  is '服务名';
comment on column MC_CONSUME_LOG.request_content
  is '请求内容';
comment on column MC_CONSUME_LOG.request_start_time
  is '请求开始时间';
comment on column MC_CONSUME_LOG.request_end_time
  is '请求结束时间';
comment on column MC_CONSUME_LOG.run_start_time
  is '执行开始时间';
comment on column MC_CONSUME_LOG.run_end_time
  is '执行结束时间';
comment on column MC_CONSUME_LOG.response_content
  is '响应内容（文件路径、少量的消息信息）';
comment on column MC_CONSUME_LOG.status
  is '结果状态(0：成功1：失败)';
comment on column MC_CONSUME_LOG.error_code
  is '错误编码，与UDSP错误编码相同';
comment on column MC_CONSUME_LOG.app_type
  is '应用类型（IQ、OLQ、MM、RTS_PRODUCER、RTS_CONSUMER）';
comment on column MC_CONSUME_LOG.request_type
  is '请求类型（0：内部请求 1：外部请求）';
comment on column MC_CONSUME_LOG.app_name
  is '应用名';
comment on column MC_CONSUME_LOG.message
  is '错误信息或其他消息提示';
comment on column MC_CONSUME_LOG.sync_type
  is '同步/异步';
alter table MC_CONSUME_LOG
  add constraint PK_MC_CONSUME_LOG primary key (PK_ID);

prompt
prompt Creating table MM_APPLICATION
prompt =============================
prompt
create table MM_APPLICATION
(
  pk_id    VARCHAR2(32) not null,
  model_id VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  max_num  NUMBER(10),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null,
  note     VARCHAR2(4000)
)
;
comment on table MM_APPLICATION
  is '模型管理-应用表';
comment on column MM_APPLICATION.pk_id
  is '主键';
comment on column MM_APPLICATION.model_id
  is '模型ID';
comment on column MM_APPLICATION.name
  is '名称((英文、唯一)';
comment on column MM_APPLICATION.describe
  is '说明';
comment on column MM_APPLICATION.max_num
  is '最大返回数';
comment on column MM_APPLICATION.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column MM_APPLICATION.crt_user
  is '创建者';
comment on column MM_APPLICATION.crt_time
  is '创建时间';
comment on column MM_APPLICATION.upt_user
  is '更新者';
comment on column MM_APPLICATION.upt_time
  is '更新时间';
comment on column MM_APPLICATION.note
  is '备注';
create index IDX_MM_APP_DELFLG on MM_APPLICATION (DEL_FLG);
alter table MM_APPLICATION
  add constraint PK_MM_APPLICATION primary key (PK_ID);

prompt
prompt Creating table MM_APPLICATION_EXECUTE_PARAM
prompt ===========================================
prompt
create table MM_APPLICATION_EXECUTE_PARAM
(
  pk_id       VARCHAR2(32) not null,
  seq         NUMBER(3) not null,
  name        VARCHAR2(64) not null,
  describe    VARCHAR2(256) not null,
  is_need     CHAR(1) not null,
  default_val VARCHAR2(256),
  app_id      VARCHAR2(32)
)
;
comment on table MM_APPLICATION_EXECUTE_PARAM
  is '模型管理-执行参数表';
comment on column MM_APPLICATION_EXECUTE_PARAM.pk_id
  is '主键';
comment on column MM_APPLICATION_EXECUTE_PARAM.seq
  is '序号';
comment on column MM_APPLICATION_EXECUTE_PARAM.name
  is '名称((英文)';
comment on column MM_APPLICATION_EXECUTE_PARAM.describe
  is '说明';
comment on column MM_APPLICATION_EXECUTE_PARAM.is_need
  is '是否必输（0:是；1：否）';
comment on column MM_APPLICATION_EXECUTE_PARAM.default_val
  is '默认值';
comment on column MM_APPLICATION_EXECUTE_PARAM.app_id
  is '模型应用ID';
create index IDX_MM_APP_EXE_APPID on MM_APPLICATION_EXECUTE_PARAM (APP_ID);
alter table MM_APPLICATION_EXECUTE_PARAM
  add constraint PK_MM_APPLICATION_EXECUTE_PARA primary key (PK_ID);

prompt
prompt Creating table MM_APPLICATION_RETURN_PARAM
prompt ==========================================
prompt
create table MM_APPLICATION_RETURN_PARAM
(
  pk_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  app_id   VARCHAR2(32)
)
;
comment on table MM_APPLICATION_RETURN_PARAM
  is '模型管理-返回参数表';
comment on column MM_APPLICATION_RETURN_PARAM.pk_id
  is '主键';
comment on column MM_APPLICATION_RETURN_PARAM.seq
  is '序号';
comment on column MM_APPLICATION_RETURN_PARAM.name
  is '名称((英文)';
comment on column MM_APPLICATION_RETURN_PARAM.describe
  is '说明';
comment on column MM_APPLICATION_RETURN_PARAM.app_id
  is '模型应用ID';
create index IDX_MM_APP_RET_APPID on MM_APPLICATION_RETURN_PARAM (APP_ID);
alter table MM_APPLICATION_RETURN_PARAM
  add constraint PK_MM_APPLICATION_RETURN_PARAM primary key (PK_ID);

prompt
prompt Creating table MM_CONTRACTOR
prompt ============================
prompt
create table MM_CONTRACTOR
(
  pk_id         VARCHAR2(32) not null,
  name          VARCHAR2(128) not null,
  http_url      VARCHAR2(256) not null,
  principal     VARCHAR2(32) not null,
  mobile        VARCHAR2(32) not null,
  note          VARCHAR2(4000),
  del_flg       CHAR(1) not null,
  crt_user      VARCHAR2(32) not null,
  crt_time      VARCHAR2(32) not null,
  upt_user      VARCHAR2(32) not null,
  upt_time      VARCHAR2(32) not null,
  extend_field1 VARCHAR2(128),
  extend_field2 VARCHAR2(128),
  cn_name       VARCHAR2(128),
  ftp_password  VARCHAR2(60)
)
;
comment on table MM_CONTRACTOR
  is '模型管理-厂商管理';
comment on column MM_CONTRACTOR.pk_id
  is '主键';
comment on column MM_CONTRACTOR.name
  is '英文名称';
comment on column MM_CONTRACTOR.http_url
  is '远程连接';
comment on column MM_CONTRACTOR.principal
  is '负责人';
comment on column MM_CONTRACTOR.mobile
  is '负责电话';
comment on column MM_CONTRACTOR.note
  is '备注';
comment on column MM_CONTRACTOR.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column MM_CONTRACTOR.crt_user
  is '创建者';
comment on column MM_CONTRACTOR.crt_time
  is '创建时间';
comment on column MM_CONTRACTOR.upt_user
  is '更新者';
comment on column MM_CONTRACTOR.upt_time
  is '更新时间';
comment on column MM_CONTRACTOR.extend_field1
  is '预留字段1';
comment on column MM_CONTRACTOR.extend_field2
  is '预留字段2';
comment on column MM_CONTRACTOR.cn_name
  is '中文名称';
comment on column MM_CONTRACTOR.ftp_password
  is 'ftp登陆密码';
create index IDX_MM_CONT_DELFLG on MM_CONTRACTOR (DEL_FLG);
create index IDX_MM_CONT_DELFLG_NAME on MM_CONTRACTOR (DEL_FLG, NAME);
alter table MM_CONTRACTOR
  add constraint MM_CONTRACTOR_ID primary key (PK_ID);

prompt
prompt Creating table MM_MODEL_FILE
prompt ============================
prompt
create table MM_MODEL_FILE
(
  pk_id      VARCHAR2(32) not null,
  model_id   VARCHAR2(32) not null,
  name       VARCHAR2(64) not null,
  describe   VARCHAR2(256),
  ver_num    VARCHAR2(32) not null,
  ver_note   VARCHAR2(256),
  path       VARCHAR2(256) not null,
  content    BLOB,
  start_time VARCHAR2(32) not null,
  end_time   VARCHAR2(32) not null,
  operation  CHAR(1) not null
)
;
comment on table MM_MODEL_FILE
  is '模型管理-文件管理';
comment on column MM_MODEL_FILE.pk_id
  is '主键';
comment on column MM_MODEL_FILE.model_id
  is '模型ID';
comment on column MM_MODEL_FILE.name
  is '名称';
comment on column MM_MODEL_FILE.describe
  is '说明';
comment on column MM_MODEL_FILE.ver_num
  is '版本号';
comment on column MM_MODEL_FILE.ver_note
  is '版本注释';
comment on column MM_MODEL_FILE.path
  is '路径';
comment on column MM_MODEL_FILE.content
  is '内容';
comment on column MM_MODEL_FILE.start_time
  is '拉链表，开始时间';
comment on column MM_MODEL_FILE.end_time
  is '拉链表，结束时间';
comment on column MM_MODEL_FILE.operation
  is '操作（1：添加、更新；2：删除）';
create index IDX_MM_MF_MMID_STIME_ETIME on MM_MODEL_FILE (MODEL_ID, START_TIME, END_TIME);
create index IDX_MM_MF_STIME_ETIME on MM_MODEL_FILE (START_TIME, END_TIME);
alter table MM_MODEL_FILE
  add constraint PK_MM_MODEL_FILE primary key (PK_ID);

prompt
prompt Creating table MM_MODEL_INFO
prompt ============================
prompt
create table MM_MODEL_INFO
(
  pk_id      VARCHAR2(32) not null,
  name       VARCHAR2(64) not null,
  describe   VARCHAR2(256) not null,
  ver_note   VARCHAR2(4000),
  ver_num    VARCHAR2(32),
  status     CHAR(1),
  note       VARCHAR2(4000),
  del_flg    CHAR(1) not null,
  crt_user   VARCHAR2(32) not null,
  crt_time   VARCHAR2(32) not null,
  upt_user   VARCHAR2(32) not null,
  upt_time   VARCHAR2(32) not null,
  contractor VARCHAR2(32) not null,
  model_type VARCHAR2(32) not null
)
;
comment on table MM_MODEL_INFO
  is '模型管理-模型信息表';
comment on column MM_MODEL_INFO.pk_id
  is '主键';
comment on column MM_MODEL_INFO.name
  is '名称((英文、唯一)';
comment on column MM_MODEL_INFO.describe
  is '说明';
comment on column MM_MODEL_INFO.ver_note
  is '版本注释';
comment on column MM_MODEL_INFO.ver_num
  is '版本号';
comment on column MM_MODEL_INFO.status
  is '发布状态(1：待发布；2：已发布；3：归档)';
comment on column MM_MODEL_INFO.note
  is '备注';
comment on column MM_MODEL_INFO.del_flg
  is '删除标志(0：未删除，1：删除)';
comment on column MM_MODEL_INFO.crt_user
  is '创建者';
comment on column MM_MODEL_INFO.crt_time
  is '创建时间';
comment on column MM_MODEL_INFO.upt_user
  is '更新者';
comment on column MM_MODEL_INFO.upt_time
  is '更新时间';
comment on column MM_MODEL_INFO.contractor
  is '模型开发厂商ID';
comment on column MM_MODEL_INFO.model_type
  is '1：同步；2：异步；3：批处理，多个用逗号分隔';
create index IDX_MM_MODEL_INFO_DELFLG on MM_MODEL_INFO (DEL_FLG);
create index IDX_MM_MODEL_INFO_DELFLG_CONID on MM_MODEL_INFO (DEL_FLG, CONTRACTOR);
create index IDX_MM_MODEL_INFO_DELFLG_NAME on MM_MODEL_INFO (DEL_FLG, NAME);
alter table MM_MODEL_INFO
  add constraint PK_MM_MODEL_INFO primary key (PK_ID);

prompt
prompt Creating table MM_MODEL_PARAM
prompt =============================
prompt
create table MM_MODEL_PARAM
(
  pk_id    VARCHAR2(32) not null,
  mm_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     CHAR(1) not null,
  length   VARCHAR2(32),
  note     VARCHAR2(4000),
  col_type VARCHAR2(32)
)
;
comment on table MM_MODEL_PARAM
  is '模型管理-模型字段列表';
comment on column MM_MODEL_PARAM.pk_id
  is '主键';
comment on column MM_MODEL_PARAM.mm_id
  is '模型ID';
comment on column MM_MODEL_PARAM.seq
  is '序号（和类型一起确定唯一）';
comment on column MM_MODEL_PARAM.name
  is '名称((唯一、英文)';
comment on column MM_MODEL_PARAM.describe
  is '说明';
comment on column MM_MODEL_PARAM.type
  is '类型(1、查询字段；2、返回字段)';
comment on column MM_MODEL_PARAM.length
  is '长度';
comment on column MM_MODEL_PARAM.note
  is '备注';
comment on column MM_MODEL_PARAM.col_type
  is '字段类型';
create index IDX_MM_MODEL_PARAM_MMID on MM_MODEL_PARAM (MM_ID);
alter table MM_MODEL_PARAM
  add constraint PK_MM_MODEL_PARAM primary key (PK_ID);

prompt
prompt Creating table MM_MODEL_VER
prompt ===========================
prompt
create table MM_MODEL_VER
(
  pk_id      VARCHAR2(32) not null,
  model_name VARCHAR2(256) not null,
  ver_num    NUMBER(10) not null,
  ver_note   VARCHAR2(4000),
  crt_time   VARCHAR2(20) not null,
  ver_name   VARCHAR2(256)
)
;
comment on column MM_MODEL_VER.pk_id
  is '主键';
comment on column MM_MODEL_VER.model_name
  is '模型名称';
comment on column MM_MODEL_VER.ver_num
  is '版本号';
comment on column MM_MODEL_VER.ver_note
  is '版本备注';
comment on column MM_MODEL_VER.crt_time
  is '创建时间';
comment on column MM_MODEL_VER.ver_name
  is '版本名称';
alter table MM_MODEL_VER
  add constraint PK_MM_MODEL_VER primary key (PK_ID);

prompt
prompt Creating table OLQ_APPLICATION
prompt ==============================
prompt
create table OLQ_APPLICATION
(
  pk_id     VARCHAR2(32) not null,
  olq_ds_id VARCHAR2(32) not null,
  max_num   NUMBER(10),
  del_flg   CHAR(1) not null,
  crt_user  VARCHAR2(32) not null,
  crt_time  VARCHAR2(32) not null,
  upt_user  VARCHAR2(32) not null,
  upt_time  VARCHAR2(32) not null,
  note      VARCHAR2(4000),
  name      VARCHAR2(128) not null,
  describe  VARCHAR2(128) not null,
  olq_sql   CLOB not null
)
;
comment on column OLQ_APPLICATION.pk_id
  is '主键';
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
comment on column OLQ_APPLICATION.olq_sql
  is '应用配置的SQL语句';
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
alter table OLQ_APPLICATION_PARAM
  add constraint OLQ_APPLICATION_PARAM_PKID primary key (PK_ID);

prompt
prompt Creating table RC_SERVICE
prompt =========================
prompt
create table RC_SERVICE
(
  pk_id    VARCHAR2(32) not null,
  name     VARCHAR2(64),
  describe VARCHAR2(256),
  type     VARCHAR2(32),
  app_id   VARCHAR2(32),
  del_flg  CHAR(1),
  crt_user VARCHAR2(32),
  crt_time VARCHAR2(32),
  upt_user VARCHAR2(32),
  upt_time VARCHAR2(32),
  status   CHAR(1) default 0 not null
)
;
comment on table RC_SERVICE
  is '注册中心-服务注册表';
comment on column RC_SERVICE.pk_id
  is '主键';
comment on column RC_SERVICE.name
  is '名称';
comment on column RC_SERVICE.describe
  is '说明';
comment on column RC_SERVICE.type
  is '类型（IQ：交互查询；MM：模型管理；OLQ：联机查询；RTS-CUS：实时流消费者；RTS-PRO：实时流生产者）';
comment on column RC_SERVICE.app_id
  is '应用ID';
comment on column RC_SERVICE.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RC_SERVICE.crt_user
  is '创建者';
comment on column RC_SERVICE.crt_time
  is '创建时间';
comment on column RC_SERVICE.upt_user
  is '更新者';
comment on column RC_SERVICE.upt_time
  is '更新时间';
comment on column RC_SERVICE.status
  is '启停标志（0：启动，1：停用）';
create index IDX_RC_SERVICE_DF on RC_SERVICE (DEL_FLG);
create index IDX_RC_SERVICE_DF_NAME on RC_SERVICE (DEL_FLG, NAME);
create index IDX_RC_SERVICE_DF_TYPE on RC_SERVICE (DEL_FLG, TYPE);
create index IDX_RC_SERVICE_DF_TYPE_APPID on RC_SERVICE (DEL_FLG, TYPE, APP_ID);
alter table RC_SERVICE
  add constraint PK_RC_SERVICE primary key (PK_ID);

prompt
prompt Creating table RC_USER_SERVICE
prompt ==============================
prompt
create table RC_USER_SERVICE
(
  pk_id                     VARCHAR2(32) not null,
  user_id                   VARCHAR2(32) not null,
  service_id                VARCHAR2(32) not null,
  ip_section                VARCHAR2(512),
  max_sync_num              NUMBER(10) not null,
  max_async_num             NUMBER(10) not null,
  del_flg                   CHAR(1) not null,
  crt_user                  VARCHAR2(32) not null,
  crt_time                  VARCHAR2(32) not null,
  upt_user                  VARCHAR2(32) not null,
  upt_time                  VARCHAR2(32) not null,
  max_sync_wait_num         NUMBER(10) default 0 not null,
  max_async_wait_num        NUMBER(10) default 0 not null,
  max_sync_wait_timeout     NUMBER(10) default 3000 not null,
  max_async_wait_timeout    NUMBER(10) default 600000 not null,
  max_sync_execute_timeout  NUMBER(10) default 3000 not null,
  max_async_execute_timeout NUMBER(10) default 600000 not null,
  status                    CHAR(1) default 0 not null
)
;
comment on column RC_USER_SERVICE.pk_id
  is '主键';
comment on column RC_USER_SERVICE.user_id
  is '用户ID';
comment on column RC_USER_SERVICE.service_id
  is '服务ID';
comment on column RC_USER_SERVICE.ip_section
  is 'IP段';
comment on column RC_USER_SERVICE.max_sync_num
  is '最大同步并发数';
comment on column RC_USER_SERVICE.max_async_num
  is '最大异步并发数';
comment on column RC_USER_SERVICE.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RC_USER_SERVICE.crt_user
  is '创建者';
comment on column RC_USER_SERVICE.crt_time
  is '创建时间';
comment on column RC_USER_SERVICE.upt_user
  is '更新者';
comment on column RC_USER_SERVICE.upt_time
  is '更新时间';
comment on column RC_USER_SERVICE.max_sync_wait_num
  is '最大同步队列等待数';
comment on column RC_USER_SERVICE.max_async_wait_num
  is '最大异步队列等待数';
comment on column RC_USER_SERVICE.max_sync_wait_timeout
  is '同步最大等待超时时间';
comment on column RC_USER_SERVICE.max_async_wait_timeout
  is '异步最大等待超时时间';
comment on column RC_USER_SERVICE.max_sync_execute_timeout
  is '同步最大执行超时时间';
comment on column RC_USER_SERVICE.max_async_execute_timeout
  is '异步最大执行超时时间';
comment on column RC_USER_SERVICE.status
  is '启停标志（0：启动，1：停用）';
create index IDX_RC_USER_SERVICE_DF on RC_USER_SERVICE (DEL_FLG);
create index IDX_RC_USER_SERVICE_DF_SID on RC_USER_SERVICE (DEL_FLG, SERVICE_ID);
create index IDX_RC_USER_SERVICE_DF_SID_UID on RC_USER_SERVICE (DEL_FLG, SERVICE_ID, USER_ID);
alter table RC_USER_SERVICE
  add constraint PK_RC_USER_SERVICE primary key (PK_ID);

prompt
prompt Creating table RTS_CUSTOMER_CONFIG
prompt ==================================
prompt
create table RTS_CUSTOMER_CONFIG
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  group_id VARCHAR2(64) not null,
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null
)
;
comment on table RTS_CUSTOMER_CONFIG
  is '实时流-消费者配置';
comment on column RTS_CUSTOMER_CONFIG.pk_id
  is '主键';
comment on column RTS_CUSTOMER_CONFIG.md_id
  is '元数据ID';
comment on column RTS_CUSTOMER_CONFIG.name
  is '名称';
comment on column RTS_CUSTOMER_CONFIG.describe
  is '说明';
comment on column RTS_CUSTOMER_CONFIG.group_id
  is '组';
comment on column RTS_CUSTOMER_CONFIG.note
  is '备注';
comment on column RTS_CUSTOMER_CONFIG.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RTS_CUSTOMER_CONFIG.crt_user
  is '创建者';
comment on column RTS_CUSTOMER_CONFIG.crt_time
  is '创建时间';
comment on column RTS_CUSTOMER_CONFIG.upt_user
  is '更新者';
comment on column RTS_CUSTOMER_CONFIG.upt_time
  is '更新时间';
create index IDX_RTS_CER_DELFLG on RTS_CUSTOMER_CONFIG (DEL_FLG);
create index IDX_RTS_CER_DELFLG_MDID on RTS_CUSTOMER_CONFIG (DEL_FLG, MD_ID);
create index IDX_RTS_CER_DELFLG_NAME on RTS_CUSTOMER_CONFIG (DEL_FLG, NAME);
alter table RTS_CUSTOMER_CONFIG
  add constraint PK_RTS_CUSTOMER_CONFIG primary key (PK_ID);

prompt
prompt Creating table RTS_METADATA
prompt ===========================
prompt
create table RTS_METADATA
(
  pk_id    VARCHAR2(32) not null,
  ds_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256),
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null,
  topic    VARCHAR2(256) not null
)
;
comment on table RTS_METADATA
  is '实时流-元数据配置';
comment on column RTS_METADATA.pk_id
  is '主键';
comment on column RTS_METADATA.ds_id
  is '数据源ID';
comment on column RTS_METADATA.name
  is '名称(英文)';
comment on column RTS_METADATA.describe
  is '说明';
comment on column RTS_METADATA.note
  is '备注';
comment on column RTS_METADATA.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RTS_METADATA.crt_user
  is '创建者';
comment on column RTS_METADATA.crt_time
  is '创建时间';
comment on column RTS_METADATA.upt_user
  is '更新者';
comment on column RTS_METADATA.upt_time
  is '更新时间';
comment on column RTS_METADATA.topic
  is '主题';
create index IDX_RTS_MD_DELFLG on RTS_METADATA (DEL_FLG);
create index IDX_RTS_MD_DELFLG_DSID on RTS_METADATA (DEL_FLG, DS_ID);
create index IDX_RTS_MD_DELFLG_NAME on RTS_METADATA (DEL_FLG, NAME);
alter table RTS_METADATA
  add constraint PK_RTS_METADATA primary key (PK_ID);

prompt
prompt Creating table RTS_METADATA_COLUMN
prompt ==================================
prompt
create table RTS_METADATA_COLUMN
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  seq      NUMBER(3) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  type     VARCHAR2(32) not null
)
;
comment on table RTS_METADATA_COLUMN
  is '实时流-元数据字段表';
comment on column RTS_METADATA_COLUMN.pk_id
  is '主键';
comment on column RTS_METADATA_COLUMN.md_id
  is '元数据ID';
comment on column RTS_METADATA_COLUMN.seq
  is '序号';
comment on column RTS_METADATA_COLUMN.name
  is '名称((唯一、英文)';
comment on column RTS_METADATA_COLUMN.describe
  is '说明';
comment on column RTS_METADATA_COLUMN.type
  is '类型';
create index IDX_RTS_MD_COL_MDID on RTS_METADATA_COLUMN (MD_ID);
alter table RTS_METADATA_COLUMN
  add constraint PK_RTS_METADATA_COLUMN primary key (PK_ID);

prompt
prompt Creating table RTS_PRODUCRER_CONFIG
prompt ===================================
prompt
create table RTS_PRODUCRER_CONFIG
(
  pk_id    VARCHAR2(32) not null,
  md_id    VARCHAR2(32) not null,
  name     VARCHAR2(64) not null,
  describe VARCHAR2(256) not null,
  note     VARCHAR2(4000),
  del_flg  CHAR(1) not null,
  crt_user VARCHAR2(32) not null,
  crt_time VARCHAR2(32) not null,
  upt_user VARCHAR2(32) not null,
  upt_time VARCHAR2(32) not null
)
;
comment on table RTS_PRODUCRER_CONFIG
  is '实时流-生产者配置';
comment on column RTS_PRODUCRER_CONFIG.pk_id
  is '主键';
comment on column RTS_PRODUCRER_CONFIG.md_id
  is '元数据ID';
comment on column RTS_PRODUCRER_CONFIG.name
  is '名称（英文）';
comment on column RTS_PRODUCRER_CONFIG.describe
  is '说明';
comment on column RTS_PRODUCRER_CONFIG.note
  is '备注';
comment on column RTS_PRODUCRER_CONFIG.del_flg
  is '删除标志（0：未删除，1：删除）';
comment on column RTS_PRODUCRER_CONFIG.crt_user
  is '创建者';
comment on column RTS_PRODUCRER_CONFIG.crt_time
  is '创建时间';
comment on column RTS_PRODUCRER_CONFIG.upt_user
  is '更新者';
comment on column RTS_PRODUCRER_CONFIG.upt_time
  is '更新时间';
create index IDX_RTS_PER_DELFLG on RTS_PRODUCRER_CONFIG (DEL_FLG);
create index IDX_RTS_PER_DELFLG_MDID on RTS_PRODUCRER_CONFIG (DEL_FLG, MD_ID);
create index IDX_RTS_PER_DELFLG_NAME on RTS_PRODUCRER_CONFIG (DEL_FLG, NAME);
alter table RTS_PRODUCRER_CONFIG
  add constraint PK_RTS_PRODUCRER_CONFIG primary key (PK_ID);

spool off
