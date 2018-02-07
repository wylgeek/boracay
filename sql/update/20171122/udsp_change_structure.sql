
-- 20171122 添加告警方式
-- Add/modify columns
alter table RC_USER_SERVICE add ALARM_TYPE VARCHAR2(32);
-- Add comments to the columns
comment on column RC_USER_SERVICE.ALARM_TYPE
  is '告警方式（NONE、MAIL、...）';
