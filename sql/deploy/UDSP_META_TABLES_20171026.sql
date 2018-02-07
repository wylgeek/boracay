set define off
spool UDSP_CREATE_TABLES.log

prompt
prompt Creating table T_GF_APPLICATION
prompt ===============================
prompt
create table T_GF_APPLICATION
(
  app_code    VARCHAR2(32) not null,
  app_name    VARCHAR2(256),
  app_comment VARCHAR2(512),
  app_status  INTEGER
)
;
alter table T_GF_APPLICATION
  add primary key (APP_CODE);

prompt
prompt Creating table T_GF_AUTH_RIGHT
prompt ==============================
prompt
create table T_GF_AUTH_RIGHT
(
  id        VARCHAR2(32) not null,
  auth_id   VARCHAR2(32),
  user_id   VARCHAR2(32),
  auth_type VARCHAR2(32),
  app_id    VARCHAR2(32)
)
;
alter table T_GF_AUTH_RIGHT
  add primary key (ID);

prompt
prompt Creating table T_GF_DICT
prompt ========================
prompt
create table T_GF_DICT
(
  dict_type_id VARCHAR2(64) not null,
  dict_id      VARCHAR2(64) not null,
  dict_name    VARCHAR2(512),
  status       INTEGER,
  sort_no      INTEGER,
  parent_id    VARCHAR2(64),
  seqno        VARCHAR2(256),
  appid        VARCHAR2(32) not null,
  filter       VARCHAR2(512)
)
;
alter table T_GF_DICT
  add primary key (DICT_TYPE_ID, DICT_ID);

prompt
prompt Creating table T_GF_DICT_TYPE
prompt =============================
prompt
create table T_GF_DICT_TYPE
(
  dict_type_id   VARCHAR2(64) not null,
  dict_type_name VARCHAR2(64),
  appid          VARCHAR2(32) not null
)
;
alter table T_GF_DICT_TYPE
  add primary key (DICT_TYPE_ID);

prompt
prompt Creating table T_GF_EMPLOYEE
prompt ============================
prompt
create table T_GF_EMPLOYEE
(
  emp_id      VARCHAR2(32) not null,
  job_id      VARCHAR2(32) not null,
  user_name   VARCHAR2(128),
  sex         INTEGER,
  birthday    VARCHAR2(10),
  status      VARCHAR2(32),
  card_no     VARCHAR2(10),
  card_type   VARCHAR2(6),
  indate      VARCHAR2(10),
  outdate     VARCHAR2(10),
  otel        VARCHAR2(32),
  mobile_no   VARCHAR2(20),
  htel        VARCHAR2(32),
  haddress    VARCHAR2(256),
  hzipcode    VARCHAR2(6),
  pemail      VARCHAR2(32),
  create_date DATE,
  app_id      VARCHAR2(32),
  orgid       VARCHAR2(32),
  emp_comment VARCHAR2(256),
  oemail      VARCHAR2(32),
  managerid   VARCHAR2(32),
  managername VARCHAR2(128)
)
;
alter table T_GF_EMPLOYEE
  add primary key (EMP_ID);

prompt
prompt Creating table T_GF_FUNCATION
prompt =============================
prompt
create table T_GF_FUNCATION
(
  func_id        VARCHAR2(32) not null,
  func_code      VARCHAR2(32) not null,
  func_name      VARCHAR2(64),
  is_func        VARCHAR2(32),
  displayorder   INTEGER,
  url_acction    VARCHAR2(128),
  parent_func_id VARCHAR2(32),
  appid          VARCHAR2(32) not null
)
;
alter table T_GF_FUNCATION
  add primary key (FUNC_ID);

prompt
prompt Creating table T_GF_LOG
prompt =======================
prompt
create table T_GF_LOG
(
  log_id          VARCHAR2(32) not null,
  action_type     VARCHAR2(32),
  action_url      VARCHAR2(256),
  create_user_id  VARCHAR2(32),
  is_func         VARCHAR2(32),
  app_id          VARCHAR2(32) not null,
  create_date     DATE not null,
  create_username VARCHAR2(32),
  log_body        VARCHAR2(4000)
)
;
alter table T_GF_LOG
  add primary key (LOG_ID);

prompt
prompt Creating table T_GF_LOGINUSER
prompt =============================
prompt
create table T_GF_LOGINUSER
(
  id              VARCHAR2(32) not null,
  emp_id          VARCHAR2(32) not null,
  user_id         VARCHAR2(32) not null,
  user_name       VARCHAR2(128),
  password        VARCHAR2(32),
  status          VARCHAR2(32),
  menu_type       VARCHAR2(256),
  create_date     DATE,
  update_userid   VARCHAR2(32),
  app_id          VARCHAR2(32),
  user_comment    VARCHAR2(256),
  valid_startdate VARCHAR2(10),
  valid_enddate   VARCHAR2(10)
)
;
alter table T_GF_LOGINUSER
  add primary key (ID);

prompt
prompt Creating table T_GF_MENU
prompt ========================
prompt
create table T_GF_MENU
(
  menuid       VARCHAR2(32) not null,
  menuname     VARCHAR2(60) not null,
  menulabel    VARCHAR2(80),
  menucode     VARCHAR2(120),
  isleaf       CHAR(1),
  parameter    VARCHAR2(256),
  displayorder INTEGER,
  app_id       VARCHAR2(64) not null,
  menu_action  VARCHAR2(256),
  parentmenuid VARCHAR2(40),
  menu_icon    VARCHAR2(120)
)
;
alter table T_GF_MENU
  add primary key (MENUID);

prompt
prompt Creating table T_GF_NEXTID
prompt ==========================
prompt
create table T_GF_NEXTID
(
  seq_type  VARCHAR2(64) not null,
  next_id   NUMBER(19),
  last_time DATE
)
;
alter table T_GF_NEXTID
  add primary key (SEQ_TYPE);

prompt
prompt Creating table T_GF_ORG
prompt =======================
prompt
create table T_GF_ORG
(
  orgid         VARCHAR2(32) not null,
  orgname       VARCHAR2(128),
  orgcode       VARCHAR2(256),
  org_level     INTEGER,
  org_seq       VARCHAR2(256) not null,
  org_type      VARCHAR2(32),
  org_address   VARCHAR2(256),
  zipcode       VARCHAR2(6),
  linkman       VARCHAR2(32),
  linktel       VARCHAR2(32),
  create_date   DATE,
  update_date   DATE,
  display_order INTEGER,
  org_comment   VARCHAR2(256),
  app_id        VARCHAR2(32),
  parent_orgid  VARCHAR2(32)
)
;
alter table T_GF_ORG
  add primary key (ORGID);

prompt
prompt Creating table T_GF_QUARTZ
prompt ==========================
prompt
create table T_GF_QUARTZ
(
  pk_id           VARCHAR2(32) not null,
  class_name      VARCHAR2(256),
  method          VARCHAR2(64),
  start_time      TIMESTAMP(6) default sysdate,
  end_time        TIMESTAMP(6),
  has_end_time    CHAR(1),
  task_name       VARCHAR2(512),
  status          CHAR(1) default 0,
  interval_time   NUMBER,
  exec_num        NUMBER,
  trigger_model   CHAR(1),
  trigger_time    VARCHAR2(8),
  circulate_model CHAR(1),
  week_model      VARCHAR2(16),
  month           VARCHAR2(2),
  day             VARCHAR2(2),
  period          CHAR(1),
  week            CHAR(1),
  period_model    CHAR(1),
  owner_server    VARCHAR2(32)
)
;
alter table T_GF_QUARTZ
  add primary key (PK_ID);

prompt
prompt Creating table T_GF_RES_AUTH
prompt ============================
prompt
create table T_GF_RES_AUTH
(
  id        VARCHAR2(32) not null,
  auth_id   VARCHAR2(32),
  auth_type VARCHAR2(32),
  res_id    VARCHAR2(32),
  app_id    VARCHAR2(32),
  res_type  VARCHAR2(32)
)
;
alter table T_GF_RES_AUTH
  add primary key (ID);

prompt
prompt Creating table T_GF_ROLE
prompt ========================
prompt
create table T_GF_ROLE
(
  roleid    VARCHAR2(32) not null,
  app_id    VARCHAR2(32) not null,
  rolename  VARCHAR2(64) not null,
  role_desc VARCHAR2(256)
)
;
alter table T_GF_ROLE
  add primary key (ROLEID, APP_ID);

prompt
prompt Creating table T_GF_SCHD_JOB
prompt ============================
prompt
create table T_GF_SCHD_JOB
(
  job_id            VARCHAR2(32) not null,
  job_name          VARCHAR2(64) not null,
  job_type          VARCHAR2(64) not null,
  command           VARCHAR2(512) not null,
  args              VARCHAR2(4000),
  schedule_type     VARCHAR2(32) not null,
  trigger_time      VARCHAR2(128) not null,
  enabled           CHAR(1) default '1' not null,
  running           CHAR(1) default '0',
  last_elapsed_time NUMBER(18),
  error_count       INTEGER default 0,
  last_error        VARCHAR2(1024),
  owner_server      VARCHAR2(64),
  last_run_time     DATE,
  active_time       DATE,
  last_run_server   VARCHAR2(128),
  update_time       DATE not null,
  update_user       VARCHAR2(32) not null,
  start_date        TIMESTAMP(6),
  end_date          TIMESTAMP(6)
)
;
alter table T_GF_SCHD_JOB
  add primary key (JOB_ID);

prompt
prompt Creating table T_GF_TASK_POOL
prompt =============================
prompt
create table T_GF_TASK_POOL
(
  id                  VARCHAR2(32) not null,
  name                VARCHAR2(128) not null,
  max_active          INTEGER not null,
  min_idle            INTEGER not null,
  idle_timeout        INTEGER not null,
  init_count          INTEGER not null,
  daemon              INTEGER not null,
  thread_priority     INTEGER not null,
  task_producer_name  VARCHAR2(128) not null,
  fetch_batch_size    INTEGER not null,
  sleep_on_idle       INTEGER not null,
  sleep_on_work_after INTEGER not null
)
;
create unique index IX_GF_TASK_POOL_NAME on T_GF_TASK_POOL (NAME);
alter table T_GF_TASK_POOL
  add primary key (ID);

prompt
prompt Creating table T_GF_USER_SESSION
prompt ================================
prompt
create table T_GF_USER_SESSION
(
  user_id    VARCHAR2(32) not null,
  client_ip  VARCHAR2(16),
  login_time TIMESTAMP(6)
)
;
alter table T_GF_USER_SESSION
  add primary key (USER_ID);


spool off