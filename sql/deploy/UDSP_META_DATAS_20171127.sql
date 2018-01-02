prompt PL/SQL Developer import file
prompt Created on 2017年11月27日 by JunjieM
set feedback off
set define off
prompt Loading T_GF_APPLICATION...
insert into T_GF_APPLICATION (app_code, app_name, app_comment, app_status)
values ('default', '系统管理平台', '系统管理平台', 1);
commit;
prompt 1 records loaded
prompt Loading T_GF_AUTH_RIGHT...
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('11', 'USER', '20170523', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('21', 'USER', '20170315', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('31', 'USER', '2017100801', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('41', 'USER', '2017032101', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('51', 'USER', '2017032102', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('1011', 'ADMIN', 'tomnic', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('2011', 'OLQ', 'ERS', 'role', 'default');
insert into T_GF_AUTH_RIGHT (id, auth_id, user_id, auth_type, app_id)
values ('1', 'ADMIN', 'admin', 'role', 'default');
commit;
prompt 8 records loaded
prompt Loading T_GF_DICT...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'VARCHAR', 'VARCHAR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'CHAR', 'CHAR', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'BIGINT', 'BIGINT', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'BOOLEAN', 'BOOLEAN', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'TINYINT', 'TINYINT', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'STRING', 'STRING', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'DECIMAL', 'DECIMAL', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'INT', 'INT', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'SMALLINT', 'SMALLINT', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'FLOAT', 'FLOAT', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_COMMON_DATA_TYPE', 'TIMESTAMP', 'TIMESTAMP', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'driver.class', '驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'jdbc.url', 'JDBC URL，如：无密码jdbc:hive2://${ip}:${port}/;auth=noSasl  有密码jdbc:hive2://${ip}:${port}/', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_REQUEST_TYPE', '1', 'HTTP', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '500', '500ms', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', 'right like', 'like右查询', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.family.name', 'hbase族名', null, 5, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.family.name', 'hbase列族名字', null, 4, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.qulifier.name', 'hbase列名', null, 6, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.qulifier.name', 'hbase列名', null, 5, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.fqSep', '字段分隔符，如：|、||、\\007、\\t、\\036', null, 6, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'OLQ_APP', '联机查询应用', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '1', '待发布', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '3', '归档', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'jdbc.url', 'JDBC URL，如：jdbc:hive2://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'password', '密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'jdbc.url', 'JDBC URL，如：jdbc:mysql://${ip}:${port}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'password', '密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'username', '用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'driver.class', '驱动类', null, 1, null, null, 'default', 'oracle.jdbc.OracleDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'jdbc.url', 'JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'password', '密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'username', '用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'REDIS', 'REDIS', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'REDIS', 'com.hex.bigdata.udsp.iq.provider.impl.RedisProvider', null, 4, null, null, 'default', '交互查询Redis接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.ip', 'redis连接ip地址', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.password', 'redis连接密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.wait', 'redis连接最长等待时间', null, 7, null, null, 'default', '1000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.test.on.brrow', 'redis连接是否检查连通性', null, 9, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.user', 'redis连接用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'VARCHAR', 'VARCHAR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'CHAR', 'CHAR', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'BIGINT', 'BIGINT', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'BOOLEAN', 'BOOLEAN', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'TINYINT', 'TINYINT', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SYNC_TYPE', 'SYNC', '同步', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_SOURCE_BATCH_TYPE', 'ORACLE', 'ORACLE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_SOURCE_BATCH_TYPE', 'MYSQL', 'MYSQL', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_BATCH_TYPE', 'HBASE', 'HBASE', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_BATCH_TYPE', 'HIVE', 'HIVE', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_BATCH_TYPE', 'MYSQL', 'MYSQL', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_REALTIME_TYPE', 'SOLR', 'SOLR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_REALTIME_TYPE', 'ORACLE', 'ORACLE', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_REALTIME_TYPE', 'MYSQL', 'MYSQL', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.split.policy', 'HBase 分区策略类', null, 4, null, null, 'default', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.family', 'HBase 族名', null, 5, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR', 'solr.shards', 'Solr 分片数', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR', 'solr.replicas', 'Solr 副本数', null, 2, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR', 'solr.max.shards.per.node', 'Solr 单节点最大分片数', null, 3, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'solr.max.shards.per.node', 'Solr 单节点最大分片数', null, 3, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'solr.replicas', 'Solr 副本数', null, 2, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'solr.shards', 'Solr 分片数', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.compression', 'HBase 压缩格式', null, 5, null, null, 'default', 'snappy');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.family', 'HBase 族名', null, 8, null, null, 'default', 'f');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.method', 'HBase 方法', null, 6, null, null, 'default', 'table_att');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.qualifier', 'HBase 列名', null, 9, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.region.num', 'HBase Region数量', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.split.policy', 'HBase 分区策略类', null, 7, null, null, 'default', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'MYSQL', 'com.hex.bigdata.udsp.im.provider.impl.MysqlProvider', null, 1, null, null, 'default', '交互建模的Mysql接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效', null, 10, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.fq.dsv.seprator', '结果数据分隔符，如：|、||、\007、\t、\036', null, 8, null, null, 'default', '\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_SOLR', 'solr.collection.name', 'collection名称', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效', null, 7, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_TYPE', '0', '内表', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.fq.dsv.seprator', '结果数据分隔符，如：|、||、\007、\t、\036', null, 11, null, null, 'default', '\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'ORACLE', 'com.hex.bigdata.udsp.im.provider.impl.OracleProvider', null, 2, null, null, 'default', '交互建模的Oracle接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_BUILD_TYPE', '1', '增量', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_BUILD_TYPE', '2', '全量', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_UPDATE_TYPE', '1', '匹配更新', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_UPDATE_TYPE', '2', '更新插入', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_UPDATE_TYPE', '3', '增量插入', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'ORACLE', 'ORACLE', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'MYSQL', 'MYSQL', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_SOURCE_BATCH_TYPE', 'HIVE', 'HIVE', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'driver.class', 'oracle 驱动类', null, 1, null, null, 'default', 'oracle.jdbc.OracleDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_SOURCE_BATCH_TYPE', 'SOLR', 'SOLR', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_SOURCE_REALTIME_TYPE', 'KAFKA', 'KAFKA', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_BATCH_TYPE', 'SOLR', 'SOLR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_BATCH_TYPE', 'ORACLE', 'ORACLE', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_REALTIME_TYPE', 'HBASE', 'HBASE', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_REALTIME_TYPE', 'SOLR_HBASE', 'SOLR_HBASE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'HIVE', 'com.hex.bigdata.udsp.im.provider.impl.HiveProvider', null, 3, null, null, 'default', '交互建模的Hive接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', '==', '等于', null, 1, null, null, 'default', null);
commit;
prompt 100 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'password', 'oracle 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'username', 'oracle 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', '<', '小于', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1 from dual');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'jdbc.url', 'mysql JDBC URL，如：jdbc:mysql://${ip}:${port}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'password', 'mysql 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'username', 'mysql 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_TYPE', '1', '外表', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_MYSQL', 'driver.class', 'mysql 驱动类', null, 1, null, null, 'default', 'com.mysql.jdbc.Driver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', '>=', '大于等于', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'zookeeper.connection.timeout.ms', '户端连接zookeeper的最大超时时间', null, 4, null, null, 'default', '6000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'zookeeper.sync.time.ms', 'zookeeper同步时间', null, 5, null, null, 'default', '2000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'auto.commit.enable', '如果true,consumer定期地往zookeeper写入每个分区的offset', null, 6, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'auto.commit.interval.ms', '消费者向zookeeper发送offset的时间', null, 7, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'rebalance.retries.max', 'rebalance时的最大尝试次数', null, 8, null, null, 'default', '10');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'zookeeper.session.timeout.ms', '连接zookeeper的session超时时间', null, 3, null, null, 'default', '5000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'rebalance.backoff.ms', '平衡补偿重试间隔时间', null, 9, null, null, 'default', '2000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'auto.offset.reset', 'offset初始化或者达到上线时的处理方式', null, 10, null, null, 'default', 'largest');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'zookeeper.connect', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', '<=', '小于等于', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', '!=', '不等于', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_TYPE', '1', '批量', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_TYPE', '2', '实时', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'SOLR', 'com.hex.bigdata.udsp.im.provider.impl.SolrProvider', null, 4, null, null, 'default', '交互建模的Solr接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR', 'solr.url', 'zookeeper地址、端口和目录，多个地址用逗号分隔，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'solr.url', 'Solr的zookeeper地址、端口和目录，多个地址用逗号分隔，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'SOLR_HBASE', 'com.hex.bigdata.udsp.im.provider.impl.SolrHBaseProvider', null, 7, null, null, 'default', '交互建模的Solr+HBase接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'HBASE', 'com.hex.bigdata.udsp.im.provider.impl.HBaseProvider', null, 5, null, null, 'default', '交互建模的HBase接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_IMPL_CLASS', 'KAFKA', 'com.hex.bigdata.udsp.im.provider.impl.KafkaProvider', null, 6, null, null, 'default', '交互建模的Kafka接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_ENGINE_TYPE', 'HIVE', 'hive类型数据源', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_SERVICE_STATUS', '0', '启用', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_KAFKA', 'topic', '主题', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_TYPE', 'SOLR_HBASE', 'SOLR_HBASE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_SERVICE_STATUS', '1', '停用', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_KAFKA', 'consumer.timeout.ms', '消费超时时间（毫秒）', null, 3, null, null, 'default', '1000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_KAFKA', 'consumer.cron.expression', '消费计划任务表达式', null, 4, null, null, 'default', '0/2 * * * * ?');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.rpc.timeout', '一次RPC请求的超时时间（毫秒）', null, 3, null, null, 'default', '5000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.client.retries.number', '客户端重试最大次数', null, 4, null, null, 'default', '3');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.client.pause', '重试的休眠时间（毫秒）', null, 5, null, null, 'default', '100');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'zookeeper.recovery.retry', 'zookeeper的重试次数（zk的重试总次数是：hbase.client.retries.number * zookeeper.recovery.retry）', null, 6, null, null, 'default', '3');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'zookeeper.recovery.retry.intervalmill', 'zookeeper重试的休眠时间（毫秒）', null, 7, null, null, 'default', '200');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.client.operation.timeout', 'HBase客户端发起一次数据操作直至得到响应之间总的超时时间，数据操作类型包括get、append、increment、delete、put等（毫秒）', null, 8, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.regionserver.lease.period', 'scan操作超时时间（毫秒）', null, 9, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000001', '权限不足', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000003', '运行队列已满', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000005', '参数解析失败', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000008', '没有授权服务', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000009', '必输参数为空', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000099', '其他错误', null, 99, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200001', '模型接口无响应', null, 200001, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000011', '当前消费id不存在', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '100', '100ms', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'MYSQL', 'MYSQL', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'DB2', 'DB2', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'STRING', 'STRING', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', 'like', '模糊匹配', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', '>', '大于', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_TYPE', 'SOLR', 'SOLR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_TYPE', 'HBASE', 'HBASE', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_BATCH_TYPE', 'SOLR_HBASE', 'SOLR_HBASE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_TYPE', 'HIVE', 'HIVE', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_TYPE', 'ORACLE', 'ORACLE', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TARGET_TYPE', 'MYSQL', 'MYSQL', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.region.num', 'HBase Region数量', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.compression', 'HBase 压缩格式', null, 2, null, null, 'default', 'snappy');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.method', 'HBase 方法', null, 3, null, null, 'default', 'table_att');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.qualifier', 'HBase 列名', null, 6, null, null, 'default', 'q');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'jdbc.url', 'oracle JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'INT', 'INT', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'SMALLINT', 'SMALLINT', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'FLOAT', 'FLOAT', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'TIMESTAMP', 'TIMESTAMP', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.port', 'redis连接端口号', null, 2, null, null, 'default', null);
commit;
prompt 200 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'SOLR', 'SOLR', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR', 'solr.servers', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.zk.quorum', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'SOLR_HBASE', 'SOLR_HBASE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.zk.quorum', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'solr.servers', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'zookeeper.connection.timeout.ms', '户端连接zookeeper的最大超时时间', null, 3, null, null, 'default', '6000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'DECIMAL', 'DECIMAL', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'HBASE', 'com.hex.bigdata.udsp.iq.provider.impl.HBaseProvider', null, 2, null, null, 'default', '交互查询的HBase接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'zookeeper.sync.time.ms', 'zookeeper同步时间', null, 4, null, null, 'default', '2000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'consumer.timeout.ms', '消费者超时时间', null, 5, null, null, 'default', '-1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'auto.commit.enable', '如果true,consumer定期地往zookeeper写入每个分区的offset', null, 6, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'auto.commit.interval.ms', '消费者向zookeeper发送offset的时间', null, 7, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'rebalance.retries.max', 'rebalance时的最大尝试次数', null, 8, null, null, 'default', '10');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '==', '等于', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', 'like', '模糊匹配', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '>', '大于', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '<', '小于', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '>=', '大于等于', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '<=', '小于等于', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '!=', '不等于', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'IMPALA', 'IMPALA', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'SOLR_HBASE', 'com.hex.bigdata.udsp.iq.provider.impl.SolrHBaseProvider', null, 3, null, null, 'default', '交互查询的Solr+HBase接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'HBASE', 'HBASE', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_DS_TYPE', 'KAFKA', 'KAFKA', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_TYPE', '1', '查询字段', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_DS_PROPS_KAFKA', 'metadata.broker.list', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_IMPL_CLASS', 'KAFKA', 'com.hex.bigdata.udsp.rts.provider.impl.KafkaProvider', null, 1, null, null, 'default', '实时流的Kafka接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_CARDTYPE', '1', '身份证', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_CARDTYPE', '2', '军官证', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_EMPSTATUS', '1', '正常', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_EMPSTATUS', '2', '离职', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_GENDER', '1', '男', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_GENDER', '2', '女', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_GENDER', '3', '未知', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_ORGTYPE', '1', '总公司', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_ORGTYPE', '2', '总公司部门', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_ORGTYPE', '3', '分公司', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_ORGTYPE', '4', '分公司部门', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_USERSTATUS', '1', '启用', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_USERSTATUS', '2 ', '停用', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_YESORNO', '1', '是', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('COF_YESORNO', '2', '否', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('EXPE_DIR', '1', '正向指标', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('EXPE_DIR', '2', '反向指标', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('GF_STRATEGIC_DIMENSIONS', 'C', '客户', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('GF_STRATEGIC_DIMENSIONS', 'F', '财务', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('GF_STRATEGIC_DIMENSIONS', 'L', '创新发展', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('GF_STRATEGIC_DIMENSIONS', 'P', '内部运营', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('KF_LEVEL', '1', '一级指标', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('KF_LEVEL', '2', '二级指标', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('NUM_PROP', '1', '时期值', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('NUM_PROP', '2', '时点值', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('SCHEDULE_TYPE', 'quartz', 'QUARTZ', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('SCHEDULE_TYPE', 'cron4j', 'CRON4J', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('APP_SERVER', 'serverA', 'A服务器', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('APP_SERVER', 'serverB', 'B服务器', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('LOGIN_CONF', 'USE_USERSESSION', '0', null, null, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_TYPE', '2', '返回字段', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'SOLR', 'com.hex.bigdata.udsp.iq.provider.impl.SolrProvider', null, 1, null, null, 'default', '交互查询的Solr接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'none', 'none', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'count', 'count', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'sum', 'sum', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'avg', 'avg', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'max', 'max', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'min', 'min', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_RETURN_COL_STATS', 'concat', 'concat', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_PRODUCER_PROPS_KAFKA', 'key.serializer.class', 'key对象的serializer类', null, 3, null, null, 'default', 'kafka.serializer.StringEncoder');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_PRODUCER_PROPS_KAFKA', 'request.required.acks', '请求确认模式', null, 4, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'zookeeper.session.timeout.ms', '连接zookeeper的session超时时间', null, 2, null, null, 'default', '5000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'rebalance.backoff.ms', '平衡补偿重试间隔时间', null, 9, null, null, 'default', '2000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'auto.offset.reset', 'offset初始化或者达到上线时的处理方式', null, 10, null, null, 'default', 'largest');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.idle', 'redis连接最大空闲数', null, 5, null, null, 'default', '10000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.timeOut', 'redis连接最大超时时间', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.total', 'redis连接池最大连接数', null, 8, null, null, 'default', '20000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_PRODUCER_PROPS_KAFKA', 'serializer.class', '序列化类', null, 2, null, null, 'default', 'kafka.serializer.StringEncoder');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR_HBASE', 'max.data.size', '最大返回数', null, 7, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_PRODUCER_PROPS_KAFKA', 'metadata.broker.list', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.seprator', '结果数据分隔符', null, 10, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.max.data.size', '最大返回数', null, 3, null, null, 'default', '65536');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.fq.dsv.seprator', '结果数据分隔符，如：|、||、\\007、\\t、\\036', null, 8, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_SOLR', 'solr.max.data.size', '最大返回数', null, 2, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_PROPS_KAFKA', 'zookeeper.connect', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.max.data.size', 'redis查询返回数据最大条数', null, 11, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', 'in', 'in查询', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'VARCHAR', 'VARCHAR', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'STRING', 'STRING', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'DECIMAL', 'DECIMAL', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'CHAR', 'CHAR', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'INT', 'INT', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'BIGINT', 'BIGINT', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'SMALLINT', 'SMALLINT', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'BOOLEAN', 'BOOLEAN', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'DOUBLE', 'DOUBLE', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'FLOAT', 'FLOAT', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'TINYINT', 'TINYINT', null, 11, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_MD_COL_DATA_TYPE', 'TIMESTAMP', 'TIMESTAMP', null, 12, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_BATCH_JOB_STATUS', 'BUILDING', '正在构建', null, 2, null, null, 'default', null);
commit;
prompt 300 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_BATCH_JOB_STATUS', 'BUILD_FAIL', '构建失败', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'STARTING', '开始启动', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_ORDER_COL_TYPE', 'DESC', 'DESC', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'IQ', '交互查询', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'OLQ', '联机查询', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'MM', '模型管理', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'RTS_CONSUMER', '实时流-消费者', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'RTS_PRODUCER', '实时流-生产者', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_CONSUME_LOG_STATUS', '0', '成功', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_CONSUME_LOG_STATUS', '1', '失败', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'MYSQL', 'com.hex.bigdata.udsp.olq.provider.impl.MysqlProvider', null, 4, null, null, 'default', '联机查询的Mysql接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'DB2', 'com.hex.bigdata.udsp.olq.provider.impl.Db2Provider', null, 6, null, null, 'default', '联机查询的Db2接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '1', '添加', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '2', '更新', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '3', '删除', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MC_OPERATION_LOG_TYPE', '4', '查询', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'username', '用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'IMPALA', 'com.hex.bigdata.udsp.olq.provider.impl.ImpalaProvider', null, 1, null, null, 'default', '联机查询的Impala接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'HIVE', 'com.hex.bigdata.udsp.olq.provider.impl.HiveProvider', null, 2, null, null, 'default', '联机查询的Hive接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '2', '已发布', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '0', '待上传', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'driver.class', '驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'username', '用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_REQUEST_TYPE', '1', '外部请求', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SYNC_TYPE', 'ASYNC', '异步', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '200', '200ms', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1 from dual');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'HIVE', 'HIVE', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_ORDER_COL_TYPE', 'ASC', 'ASC', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1 from sysibm.sysdummy1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'HIVE', 'HIVE', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'SOLR', 'SOLR', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'HBASE', 'HBASE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'KAFKA', 'KAFKA', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_STATUS', '2', '已建', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'driver.class', 'Hive 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
commit;
prompt 400 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'jdbc.url', 'Hive JDBC URL，如：jdbc:hive2://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'password', 'Hive 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'username', 'Hive 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HBASE', 'hbase.zk.quorum', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR', 'solr.servers', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.zk.quorum', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'solr.servers', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_TYPE', 'SOLR_HBASE', 'SOLR_HBASE', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_STATUS', '1', '未建', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'password', '密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_REQUEST_TYPE', '0', '内部请求', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000002', '用户名密码错误', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '1000', '1000ms', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'PGSQL', 'com.hex.bigdata.udsp.olq.provider.impl.PgsqlProvider', null, 5, null, null, 'default', '联机查询的Pgsql接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000004', '没有注册服务', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000006', '请求IP不在允许的IP段内', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000007', '程序内部异常', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000010', '调用类型或者ENTITY设置错误', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200002', '模型不支持该接口调用类型', null, 200002, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000012', '查询消费状态过于频繁', null, 12, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '2000', '2000ms', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'ORACLE', 'com.hex.bigdata.udsp.olq.provider.impl.OracleProvider', null, 3, null, null, 'default', '联机查询的Oracle接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'driver.class', '驱动类', null, 1, null, null, 'default', 'org.postgresql.Driver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'ORACLE', 'ORACLE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'PGSQL', 'PGSQL', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'KYLIN', 'KYLIN', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'KYLIN', 'com.hex.bigdata.udsp.olq.provider.impl.KylinProvider', null, 7, null, null, 'default', '联机查询的Kylin接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'jdbc.url', 'JDBC URL，如：jdbc:postgresql://${ip}:${port}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'password', '密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'username', '用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'driver.class', '驱动类', null, 1, null, null, 'default', 'com.ibm.db2.jcc.DB2Driver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'jdbc.url', 'JDBC URL，如:jdbc:db2://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'password', '密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'username', '用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'driver.class', '驱动类', null, 1, null, null, 'default', 'org.apache.kylin.jdbc.Driver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'jdbc.url', 'JDBC URL，如：' || chr(9) || ' jdbc:kylin://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'password', '密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'username', '用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'driver.class', '驱动类', null, 1, null, null, 'default', 'com.mysql.jdbc.Driver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.client.retries.number', '客户端重试最大次数', null, 4, null, null, 'default', '3');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'zookeeper.recovery.retry', 'zookeeper的重试次数（zk的重试总次数是：hbase.client.retries.number * zookeeper.recovery.retry）', null, 6, null, null, 'default', '3');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.client.operation.timeout', 'HBase客户端发起一次数据操作直至得到响应之间总的超时时间，数据操作类型包括get、append、increment、delete、put等（毫秒）', null, 8, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_KAFKA', 'metadata.broker.list', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_ORACLE', 'database.name', '库名', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_ORACLE', 'select.sql', 'SQL语句', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_MYSQL', 'database.name', '库名', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_MYSQL', 'select.sql', 'SQL语句', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_ELASTICSEARCH', 'elasticsearch.servers', 'elasticsearch集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9200,10.1.97.2:9200,10.1.97.3:9200', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'ELASTICSEARCH', 'ELASTICSEARCH', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_KAFKA', 'group.id', '消费组ID', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.rpc.timeout', '一次RPC请求的超时时间（毫秒）', null, 3, null, null, 'default', '5000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.client.pause', '重试的休眠时间（毫秒）', null, 5, null, null, 'default', '100');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'zookeeper.recovery.retry.intervalmill', 'zookeeper重试的休眠时间（毫秒）', null, 7, null, null, 'default', '200');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_SOLR_HBASE', 'hbase.regionserver.lease.period', 'scan操作超时时间（毫秒）', null, 9, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_ELASTICSEARCH', 'elasticsearch.max.data.size', '最大返回数', null, 2, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效', null, 7, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000018', '运行队列已满且未开启等待队列', null, 18, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'INCEPTOR', 'INCEPTOR', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_TYPE', 'MAIL', '发送邮件', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.host', 'SMTP服务器地址（必填）', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.port', 'SMTP服务器端口（必填）', null, 2, 'RC_ALARM_PROPS_MAIL', null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.ssl.check.server.identity', '是否检查服务的身份', null, 4, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.password', '密码', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.cc', '抄送', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.subject', '主题', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_TYPE', 'NONE', '不告警', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000013', '调用参数异常', null, 13, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000015', '执行超时', null, 15, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000017', '服务停用', null, 17, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200004', '模型调用其他异常', null, 200004, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'START_FAIL', '启动失败', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'READY_STOP', '准备停止', null, 5, null, null, 'default', null);
commit;
prompt 500 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'STOP_SUCCESS', '停止成功', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'STOP_FAIL', '停止失败', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('AUTO_REFRESH_TIME', '2000', '2秒', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('AUTO_REFRESH_TIME', '5000', '5秒', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_SOLR', 'violence.query', '是否暴力查询（true/false）', null, 2, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_ORACLE', 'violence.query', '是否暴力查询（true/false）', null, 4, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_HIVE', 'database.name', '库名', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_HIVE', 'table.name', '表名', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_HIVE', 'select.sql', 'SQL语句', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_ORACLE', 'table.name', '表名', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_MYSQL', 'table.name', '表名', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_STATUS', '1', '未建', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_STATUS', '2', '已建', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_HIVE', 'violence.query', '是否暴力查询（true/false）', null, 4, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'ELASTICSEARCH', 'com.hex.bigdata.udsp.iq.provider.impl.ElasticSearchProvider', null, 5, null, null, 'default', '交互查询ElasticSearch接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_MYSQL', 'violence.query', '是否暴力查询（true/false）', null, 4, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('AUTO_REFRESH_TIME', '10000', '10秒', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('AUTO_REFRESH_TIME', '15000', '15秒', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('AUTO_REFRESH_TIME', '30000', '30秒', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('AUTO_REFRESH_TIME', '60000', '60秒', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'IM', '交互建模', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_ORACLE', 'remarks.reporting', '是否允许获取表、字段注释', null, 17, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', 'in', 'in查询', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_BATCH_JOB_STATUS', 'READY_BUILD', '准备构建', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_BATCH_JOB_STATUS', 'BUILD_SUCCESS', '构建成功', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'READY_START', '准备启动', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'RUNNING', '正在运行', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'STOPING', '开始停止', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_REALTIME_JOB_STATUS', 'RUN_FAIL', '运行失败', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', 'right like', 'like右查询', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'INCEPTOR', 'com.hex.bigdata.udsp.olq.provider.impl.InceptorProvider', null, 8, null, null, 'default', '联机查询的Inceptor接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200003', '模型参数错误', null, 200003, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000014', '等待超时', null, 14, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000016', '等待队列已满', null, 16, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_IMPL_CLASS', 'MAIL', 'com.hex.bigdata.udsp.rc.alarm.impl.MailAlarm', null, 1, null, null, 'default', '注册中心的MAIL接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.from', '发件人（必填）', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.username', '用户名', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.to', '收件人（必填）', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RC_ALARM_PROPS_MAIL', 'mail.smtp.bcc', '密送', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'driver.class', '驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'jdbc.url', 'JDBC URL，如：jdbc:hive2://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'min.evictable.idle.time.millis', '空闲连接N毫秒中后释放', null, 13, null, null, 'default', '60000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'password', '密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'test.on.borrow', '是否从池中取出链接前进行检验', null, 15, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'username', '用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1 from system.dual');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_INCEPTOR', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
commit;
prompt 556 records loaded
prompt Loading T_GF_DICT_TYPE...
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RC_ALARM_PROPS_MAIL', '注册中心-警报参数-MAIL（电子邮件）', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RC_ALARM_TYPE', '注册中心-警报类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RC_IMPL_CLASS', '注册中心-接口实现类', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_REALTIME_JOB_STATUS', '交互建模-实时作业状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('AUTO_REFRESH_TIME', '自动刷新时间', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_STATUS', '交互建模-交互建模状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_PROPS_HIVE', '交互建模-模型参数-HIVE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_PROPS_ORACLE', '交互建模-模型参数-ORACLE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_PROPS_MYSQL', '交互建模-模型参数-MYSQL', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_IMPL_CLASS', '交互建模-接口实现类', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_MYSQL', '交互建模-数据源配置-MYSQL', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_BUILD_TYPE', '交互建模-构建模型策略', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_UPDATE_TYPE', '交互建模-更新策略', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_TYPE', '交互建模-源数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_TARGET_BATCH_TYPE', '交互建模-数据源类型（目标、批量）', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_TARGET_TYPE', '交互建模-数据源类型（目标）', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_TARGET_REALTIME_TYPE', '交互建模-数据源类型（目标、实时）', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_PROPS_SOLR', '交互建模-元数据参数-SOLR', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_PROPS_HBASE', '交互建模-元数据参数-HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_PROPS_SOLR_HBASE', '交互建模-元数据参数-SOLR+HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RC_SERVICE_STATUS', '注册中心-服务启停状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_SOLR_HBASE', '交互建模-数据源配置-SOLR_HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_KAFKA', '交互建模-数据源配置-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_TYPE', '交互建模-数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_TYPE', '交互建模-元数据类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MD_STATUS', '交互建模-元数据状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_IMPALA', '联机查询-数据源配置-IMPALA参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_CONSUME_ERROR_CODE', 'UDSP消费接口错误编码', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_CONSUMER_TIMEOUT', '消费者消费超时时间', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MM_REQUEST_TYPE', '模型应用程序请求类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OlQ_APP_COL_DATA_TYPE', '联机查应用数据列类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_TYPE', '交互查询-数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_HBASE', '交互查询-数据源配置-HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_SOLR', '交互查询-数据源配置-SOLR', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_REDIS', '交互查询redis查询', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_SOLR_HBASE', '交互查询-数据源配置-SOLR_HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_APP_ORDER_COL_TYPE', '交互查询-应用配置-排序字段-排序类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_DS_TYPE', '实时流-数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_MD_COL_TYPE', '交互查询-元数据配置-字段信息-所属类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_CONSUMER_PROPS_KAFKA', '实时流-消费者配置-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_IMPL_CLASS', '实时流-接口实现类', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('COF_CARDTYPE', '证件类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('COF_EMPSTATUS', '入职状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('SCHEDULE_TYPE', '调度类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('APP_SERVER', '应用服务器', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('DICT_SERVER', '字典服务', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('LOGIN_CONF', '登录配置', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_APP_QUERY_COL_OPERATOR', '交互查询-应用配置-查询字段-操作符', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_APP_RETURN_COL_STATS', '交互查询-应用配置-返回字段-统计函数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_IMPL_CLASS', '交互查询-接口实现类', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_DS_PROPS_KAFKA', '实时流-数据源配置-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('RTS_PRODUCER_PROPS_KAFKA', '实时流-生产者配置-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_MD_COL_DATA_TYPE', '交互查询-元数据配置-字段数据类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_TYPE', '联机查询-数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MC_CONSUME_LOG_STATUS', '监控中心-消费日志-结果状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MC_OPERATION_LOG_TYPE', '监控中心-操作日志-操作类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('MM_MODEL_STATUS', '模型状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_HIVE', '联机查询-数据源配置-HIVE参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_SYNC_TYPE', '同步/异步', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_SERVICE_TYPE', '应用类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_IMPL_CLASS', '联机查询-接口实现类', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_REQUEST_TYPE', '请求类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_ORACLE', '联机查询-数据源配置-ORACLE参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_MYSQL', '联机查询-数据源配置-MYSQL参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_KYLIN', '联机查询-数据源配置-KYLIN参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_DB2', '联机查询-数据源配置-DB2参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_PGSQL', '联机查询-数据源配置-PGSQL参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('UDSP_COMMON_DATA_TYPE', 'UDSP数据类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_HIVE', '交互建模-数据源配置-HIVE参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_HBASE', '交互建模-数据源配置-HBASE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_SOLR', '交互建模-数据源配置-SOLR', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IQ_DS_PROPS_ELASTICSEARCH', '交互查询-数据源配置-ELASTICSEARCH', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_PROPS_ORACLE', '交互建模-数据源配置-ORACLE', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_SOURCE_BATCH_TYPE', '交互建模-数据源类型（源）', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_DS_SOURCE_REALTIME_TYPE', '交互建模-数据源类型（源、实时）', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_FILTER_TYPE', '交互建模-过滤字段操作类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_PROPS_SOLR', '交互建模-模型参数-SOLR', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_PROPS_KAFKA', '交互建模-模型参数-KAFKA', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_MODEL_ENGINE_TYPE', '交互建模-引擎数据源类型', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('IM_BATCH_JOB_STATUS', '交互建模-批量作业状态', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_INCEPTOR', '联机查询-数据源配置-INCEPTOR配置', 'default');
commit;
prompt 81 records loaded
prompt Loading T_GF_EMPLOYEE...
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7011', '1111', '111', 1, null, '1', null, '1', '2017-08-03', null, null, null, null, null, null, null, to_date('03-08-2017 16:18:01', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1031', '2017032102', '王吉', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('21-03-2017 09:20:44', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7021', '2222', '2222', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('03-08-2017 16:19:49', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1`', '0002', 'core', 1, '1991-11-22', '1', null, '1', '2014-11-17', null, '15905696810', '15905696810', null, null, null, 'core@groupwith.com', to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, 'core@groupwith.com', null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('2011', '10705055', '王应利', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('23-05-2017 11:00:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4011', 'IFE', '综合前端系统', 3, null, '1', null, null, null, null, '骆超', '18806557203', null, null, null, null, to_date('09-08-2017', 'dd-mm-yyyy'), 'default', '1', '综合前端系统', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4021', 'CRM', '客户管理系统', 3, null, '1', null, null, null, null, '沈祖伦', '13586026616', null, null, null, null, to_date('09-08-2017', 'dd-mm-yyyy'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4031', 'MP', '移动工作站', 3, null, '1', null, null, null, null, '王辉', '13586026616', null, null, null, null, to_date('09-08-2017', 'dd-mm-yyyy'), 'default', '1', '移动工作站', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4041', 'SMS', '短信系统', 3, null, '1', null, null, null, null, '谢健', '13750642995', null, null, null, null, to_date('09-08-2017', 'dd-mm-yyyy'), 'default', '1', '短信系统', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('5011', 'CORE', '核心系统', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('09-08-2017', 'dd-mm-yyyy'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1', '00001', 'admin', 1, '2014-11-22', '1', null, '1', '2014-11-17', null, '111', '111111', null, null, null, 'admin@grouwith.com', to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, 'admin@grouwith.com', null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1021', '2017032101', '王大明', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('21-03-2017 09:20:05', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('6011', 'S07', '村镇银行', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('11-07-2017 15:42:14', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7041', 'CPSP', '支付路由系统', 3, null, '1', null, null, null, null, '何宜雪', '13586080825', null, null, null, null, to_date('15-08-2017 16:49:32', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('8011', 'ERS', '信贷档案系统', 3, null, '1', null, null, null, null, '潘学志', '15305760733', null, null, null, null, to_date('22-08-2017 17:26:57', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('3011', '00000011', 'test', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('31-05-2017 19:18:35', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('3021', 'test1', 'test1', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('31-05-2017 19:20:51', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7031', 'AR', '回单系统', 3, null, '1', null, null, null, null, '谢健', '13750642995', null, null, null, null, to_date('15-08-2017', 'dd-mm-yyyy'), 'default', '1', null, null, null, null);
commit;
prompt 18 records loaded
prompt Loading T_GF_FUNCATION...
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('20011', 'IM.cm.model.list.create', '交互建模>模型配置>创建', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('17011', 'IM.cm.ds.list.add', '交互建模>数据源配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('17021', 'IM.cm.ds.list.edit', '交互建模>数据源配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('17031', 'IM.cm.ds.list.remove', '交互建模>数据源配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('18011', 'IM.cm.md.list.add', '交互建模>元数据配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('18021', 'IM.cm.md.list.edit', '交互建模>元数据配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('18031', 'IM.cm.md.list.remove', '交互建模>元数据配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('8011', 'RC.service.list.add', '注册中心>用户注册>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('8021', 'RC.service.list.remove', '注册中心>服务注册>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('8031', 'RC.service.list.edit', '注册中心>服务注册>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('10011', 'MM.model.list.add', '模型管理>模型配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('10021', 'MM.model.list.edit', '模型管理>模型配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('10031', 'MM.model.list.remove', '模型管理>模型配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('11011', 'MM.app.list.add', '模型管理>应用配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('11021', 'MM.app.list.edit', '模型管理>应用配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('11031', 'MM.app.list.remove', '模型管理>应用配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14131', 'RC.service.list.auth', '注册中心>服务注册>授权', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('16011', 'OLQ.qm.testapp.search', '联机查询>OLQ应用测试>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('16021', 'OLQ.qm.testapp.download', '联机查询>OLQ应用测试>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('9011', 'RC.userService.list.add', '注册中心>用户注册>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('9021', 'RC.userService.list.edit', '注册中心>用户注册>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('9031', 'RC.userService.listremove', '注册中心>用户注册>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13011', 'IQ.cm.md.list.add', '交互查询>配置管理>元数据配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13021', 'IQ.cm.md.list.edit', '交互查询>配置管理>元数据配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13031', 'IQ.cm.md.list.remove', '交互查询>配置管理>元数据配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13041', 'IQ.cm.app.list.add', '交互查询>配置管理>应用配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13051', 'IQ.cm.app.list.edit', '交互查询>配置管理>应用配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('13061', 'IQ.cm.app.list.remove', '交互查询>配置管理>应用配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('12011', 'MM.contractor.list.add', '模型管理>厂商管理>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('12021', 'MM.contractor.list.edit', '模型管理>厂商管理>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('12031', 'MM.contractor.list.remove', '模型管理>厂商管理>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14011', 'IQ.qm.test.search', '交互查询>应用测试>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14141', 'RTS.qm.producer.test', '实时流>应用测试>生产者测试', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14031', 'IQ.qm.test.download', '交互查询>应用测试>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14041', 'IQ.qm.app.search', '交互查询>应用实例>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14151', 'RTS.qm.consumer.test', '实时流>应用测试>消费者测试', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14061', 'IQ.qm.app.download', '交互查询>应用实例>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14071', 'OLQ.qm.test.search', '联机查询>应用测试>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14091', 'OLQ.qm.test.download', '联机查询>应用测试>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14101', 'OLQ.qm.app.search', '联机查询>应用实例>查询', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14121', 'OLQ.qm.app.download', '联机查询>应用实例>下载', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('1011', 'RTS.cm.ds.list.add', '实时流>配置管理>数据源配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('1021', 'RTS.cm.ds.list.edit', '实时流>配置管理>数据源配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('1031', 'RTS.cm.ds.list.remove', '实时流>配置管理>数据源配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5011', 'RTS.cm.producer.list.add', '实时流>配置管理>生产者配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5021', 'RTS.cm.producer.list.edit', '实时流>配置管理>生产者配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5031', 'RTS.cm.producer.list.remove', '实时流>配置管理>生产者配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5041', 'RTS.cm.consumer.list.add', '实时流>配置管理>消费配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5051', 'RTS.cm.consumer.list.edit', '实时流>配置管理>消费者配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('5061', 'RTS.cm.consumer.list.remove', '实时流>配置管理>消费者配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('51', 'IQ.cm.ds.list.remove', '交互查询>配置管理>数据源配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('4021', 'RTS.cm.md.list.add', '实时流>配置管理>元数据配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('31', 'IQ.cm.ds.list.add', '交互查询>配置管理>数据源配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('41', 'IQ.cm.ds.list.edit', '交互查询>配置管理>数据源配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('4031', 'RTS.cm.md.list.edit', '实时流>配置管理>元数据配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('4041', 'RTS.cm.md.list.remove', '实时流>配置管理>元数据配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('7011', 'OLQ.cm.ds.list.add', '联机查询>配置管理>数据源配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('7021', 'OLQ.cm.ds.list.edit', '联机查询>配置管理>数据源配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('7031', 'OLQ.cm.ds.list.remove', '联机查询>配置管理>数据源配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('14161', 'MM.qm.test.search', '模型管理>模型测试>执行', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('15011', 'OLQ.cm.app.list.add', '联机查询>应用配置>新增', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('15021', 'OLQ.cm.app.list.edit', '联机查询>应用配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('15031', 'OLQ.cm.app.list.remove', '联机查询>应用配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('21011', 'RC.service.list.start', '注册中心>服务注册>启动', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('21021', 'RC.service.list.stop', '注册中心>服务注册>停用', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('19011', 'IM.cm.model.list.add', '交互建模>模型配置>添加', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('19021', 'IM.cm.model.list.edit', '交互建模>模型配置>编辑', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('19031', 'IM.cm.model.list.remove', '交互建模>模型配置>删除', null, null, null, null, 'default');
insert into T_GF_FUNCATION (func_id, func_code, func_name, is_func, displayorder, url_acction, parent_func_id, appid)
values ('17041', 'IQ.cm.md.link', '交互查询>元数据配置>关联目标元数据', null, null, null, null, 'default');
commit;
prompt 69 records loaded
prompt Loading T_GF_LOGINUSER...
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('10061', '7031', 'AR', '回单系统', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('15-08-2017 11:02:53', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('10031', '7021', '2222', '2222', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('03-08-2017 16:19:49', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5021', '4011', 'IFE', '综合前端系统', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:14:40', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, '2017-06-08', null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('2011', '2011', 'tomnic', '王应利', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('23-05-2017 11:00:20', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5031', '4021', 'CRM', '客户管理系统', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:15:28', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5041', '4031', 'MP', '移动工作站', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:16:32', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5051', '4041', 'SMS', '短信系统', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:18:53', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('1', '1', 'admin', 'admin', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('11011', '8011', 'ERS', '信贷档案系统', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('22-08-2017 17:26:57', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('10071', '7041', 'CPSP', '支付路由系统', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('15-08-2017 16:49:32', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('10041', '5011', 'S07', '核心系统', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('09-08-2017 10:01:31', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('7011', '5011', 'S01', '核心系统', '96e79218965eb72c92a549dd5a330112', '1', null, to_date('04-07-2017 10:15:41', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
commit;
prompt 12 records loaded
prompt Loading T_GF_MENU...
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115001', '作业监控', null, 'mc.job', '2', null, 30, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115011', '批量作业', null, 'mc.job.batch', '1', null, 1, 'default', 'mc.job.batch.list', '115001', 'fa fa-tasks');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115021', '实时作业', null, 'mc.job.realtime', '1', null, 2, 'default', 'mc.job.realtime.list', '115001', 'fa fa-clock-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('113001', '交互建模', null, 'im.core', '2', null, 110, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('113011', 'IM数据源配置', null, 'im.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=IM', '113001', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('113021', 'IM元数据配置', null, 'im.cm.md', '1', null, 2, 'default', 'im.cm.md.list', '113001', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('109011', '角色授权', null, 'goframe.auth.roleauth', '1', null, 70, 'default', 'goframe.auth.role_auth', '1101', 'fa fa-key');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('107001', '前置银联明细查询应用', null, 'cupatrxjnl_app', '1', null, 2, 'default', 'iq.qm.app?name=soa_cupatrxjnl_solr_hbase_app', '100001', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('110001', 'OLQ应用配置', null, 'olq.application.config', '1', null, 20, 'default', 'olq.cm.app.list', '100081', 'fa fa-list');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('106001', 'MM厂商管理', null, 'mm.contractor.list', '1', null, 1, 'default', 'mm.cm.contractor.list', '100101', 'fa fa-group');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('111011', 'OLQ应用测试', null, 'olq.qm.app', '1', null, 30, 'default', 'olq.qm.olqApps', '100011', 'fa fa-dashboard');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100301', '服务授权', null, 'rc.auth', '1', null, 20, 'default', 'rc.auth.list', '100211', 'fa fa-key');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100311', 'RTS元数据配置', null, 'rts.cm.md', '1', null, 2, 'default', 'rts.cm.md.list', '100121', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('101011', '图表统计', null, 'mc.stats.charts', '1', null, 1, 'default', 'mc.stats.charts', '100251', 'fa fa-bar-chart-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('108001', 'Impala大数据开发环境数据库查询', null, 'tzb_dev_impala', '1', null, 1, 'default', 'olq.qm.app?name=tzb_dev_impala', '100011', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1101', '后台管理', '后台管理', 'goframe.core', '2', null, 1001, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1102', '用户管理', null, 'goframe.user.list', '1', null, 20, 'default', 'goframe.user.list', '1101', 'glyphicon glyphicon-user');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1103', '角色管理', null, 'goframe.role.index', '1', null, 30, 'default', 'goframe.role.index', '1101', 'fa fa-users');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1104', '组织机构', null, 'goframe.org.index', '1', null, 10, 'default', 'goframe.org.tree', '1101', 'fa fa-tree');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1105', '菜单管理', null, 'goframe.menu.manage', '1', null, 40, 'default', 'goframe.menu.manage', '1101', 'glyphicon glyphicon-menu-hamburger');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('1107', '数据字典', null, 'goframe.dict.index', '1', null, 50, 'default', 'goframe.dict.index', '1101', 'fa fa-table');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('101001', '任务调度', null, 'goframe.schedule.list', '1', null, 80, 'default', 'goframe.schedule.list', '1101', 'fa fa-tasks');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('101002', '功能管理', null, 'goframe.function.index', '1', null, 60, 'default', 'goframe.function.index', '1101', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100001', '交互查询', null, 'iq.core', '2', null, 101, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100011', '联机查询', null, 'olq.core', '2', null, 201, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100021', '模型管理', null, 'mm.core', '2', null, 301, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100031', '实时流', null, 'rts.core', '2', null, 401, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100041', 'IQ配置管理', null, 'iq.cm', '2', null, 10, 'default', null, '100001', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100051', 'IQ应用测试', null, 'iq.qm', '1', null, 20, 'default', 'iq.qm.test', '100001', 'fa fa-dashboard');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100061', 'IQ数据源配置', null, 'iq.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=IQ', '100041', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100071', 'IQ元数据配置', null, 'iq.cm.md', '1', null, 2, 'default', 'iq.cm.md.list', '100041', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100091', 'OLQ数据源测试', null, 'olq.qm.ds', '1', null, 20, 'default', 'olq.qm.test', '100011', 'fa fa-dashboard');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100111', 'MM应用测试', null, 'mm.qm', '1', null, 20, 'default', 'mm.qm.test', '100021', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100121', 'RTS配置管理', null, 'rts.cm', '2', null, 10, 'default', null, '100031', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100141', 'MM模型配置', null, 'mm.cm.model', '1', null, 2, 'default', 'mm.cm.model.list', '100101', 'fa fa-cubes');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100161', 'IQ应用配置', null, 'iq.cm.app', '1', null, 3, 'default', 'iq.cm.app.list', '100041', 'fa fa-list');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100171', 'OLQ数据源配置', null, 'olq.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=OLQ', '100081', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100191', 'RTS生产者配置', null, 'rts.cm.producer', '1', null, 3, 'default', 'rts.cm.producer.list', '100121', 'fa fa-sign-in');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100211', '注册中心', null, 'rc.core', '2', null, 501, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100231', '日志监控', null, 'mc.log', '2', null, 10, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100261', '操作日志', null, 'mc.log.operation', '1', null, 1, 'default', 'mc.log.operation.list', '100231', 'fa fa-file-text-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100291', '服务注册', null, 'rc.service', '1', null, 10, 'default', 'rc.service.list', '100211', 'fa fa-shield');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100081', 'OLQ配置管理', null, 'olq.cm', '2', null, 10, 'default', null, '100011', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100101', 'MM配置管理', null, 'mm.cm', '2', null, 10, 'default', null, '100021', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100131', 'RTS应用测试', null, 'rts.qm', '1', null, 20, 'default', 'rts.qm.test', '100031', 'fa fa-dashboard');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100151', 'MM应用配置', null, 'mm.cm.app', '1', null, 3, 'default', 'mm.cm.app.list', '100101', 'fa fa-list');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100181', 'RTS数据源配置', null, 'rts.cm.ds', '1', null, 1, 'default', 'com.ds.list?model=RTS', '100121', 'fa fa-database');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100201', 'RTS消费者配置', null, 'rts.cm.consumer', '1', null, 4, 'default', 'rts.cm.consumer.list', '100121', 'fa fa-sign-out');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100221', '监控中心', null, 'mc.core', '2', null, 601, 'default', null, 'root', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100251', '统计监控', null, 'mc.stats', '2', null, 40, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('100271', '消费日志', null, 'mc.log.consume', '1', null, 2, 'default', 'mc.log.consume.list', '100231', 'fa fa-file-text');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('112001', 'Impala联机查询应用', null, 'tzb_olq_app', '1', null, 2, 'default', 'olq.qm.olqApp?name=olq_app3', '100011', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('105001', '存贷比查询应用', null, 'cdb_app', '1', null, 1, 'default', 'iq.qm.app?name=soa_cdb_solr_hbase_app2', '100001', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('113031', 'IM模型配置', null, 'im.cm.model', '1', null, 3, 'default', 'im.cm.model.list', '113001', 'fa fa-list');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115031', '队列监控', null, 'mc.queue', '2', null, 20, 'default', null, '100221', null);
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115041', '运行队列', null, 'mc.queue.run', '1', null, 1, 'default', 'mc.current.list', '115031', 'fa fa-align-justify');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('115051', '等待队列', null, 'mc.queue.wait', '1', null, 2, 'default', 'mc.wait.list', '115031', 'fa fa-align-center');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('114001', '用户维度统计', null, 'mc.stats.user.userChart', '1', null, 2, 'default', 'mc.stats.user.userChart', '100251', 'fa fa-bar-chart-o');
insert into T_GF_MENU (menuid, menuname, menulabel, menucode, isleaf, parameter, displayorder, app_id, menu_action, parentmenuid, menu_icon)
values ('114011', '服务维度统计', null, 'mc.stats.service.serviceChart', '1', null, 3, 'default', 'mc.stats.service.serviceChart', '100251', 'fa fa-bar-chart-o');
commit;
prompt 59 records loaded
prompt Loading T_GF_NEXTID...
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFUser', 1201, to_date('22-08-2017 17:26:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFMENU', 11500, to_date('29-09-2017 19:58:08', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GF_RES_AUTH', 14400, to_date('26-10-2017 10:04:06', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('LOG_ID', 259100, to_date('23-11-2017 19:48:22', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFFuncation', 1801, to_date('26-10-2017 09:56:31', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFEMPLOYEE', 901, to_date('22-08-2017 17:26:57', 'dd-mm-yyyy hh24:mi:ss'));
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('GFAUTHRIGHT', 301, to_date('17-10-2017 16:47:52', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 7 records loaded
prompt Loading T_GF_ORG...
insert into T_GF_ORG (orgid, orgname, orgcode, org_level, org_seq, org_type, org_address, zipcode, linkman, linktel, create_date, update_date, display_order, org_comment, app_id, parent_orgid)
values ('1', '台州银行', '0000', 1, '.1.', 'undefined', '台州', null, null, null, null, null, null, null, 'default', '0');
commit;
prompt 1 records loaded
prompt Loading T_GF_QUARTZ...
prompt Table is empty
prompt Loading T_GF_RES_AUTH...
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('141021', 'OLQ', 'role', '100091', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125001', 'USER', 'role', '105001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125011', 'USER', 'role', '107001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125021', 'USER', 'role', '108001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('141081', 'OLQ', 'role', 'OLQ.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142691', 'ADMIN', 'role', 'IM.cm.model.list.create', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142701', 'ADMIN', 'role', 'IM.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142711', 'ADMIN', 'role', 'IM.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142721', 'ADMIN', 'role', 'IM.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142731', 'ADMIN', 'role', 'IM.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142741', 'ADMIN', 'role', 'IM.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142751', 'ADMIN', 'role', 'IM.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142761', 'ADMIN', 'role', 'RC.service.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142771', 'ADMIN', 'role', 'RC.service.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142781', 'ADMIN', 'role', 'RC.service.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142791', 'ADMIN', 'role', 'MM.model.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142801', 'ADMIN', 'role', 'MM.model.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142811', 'ADMIN', 'role', 'MM.model.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142821', 'ADMIN', 'role', 'MM.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142831', 'ADMIN', 'role', 'MM.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142841', 'ADMIN', 'role', 'MM.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142851', 'ADMIN', 'role', 'RC.service.list.auth', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142861', 'ADMIN', 'role', 'OLQ.qm.testapp.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142871', 'ADMIN', 'role', 'OLQ.qm.testapp.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142881', 'ADMIN', 'role', 'RC.userService.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142891', 'ADMIN', 'role', 'RC.userService.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142901', 'ADMIN', 'role', 'RC.userService.listremove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142911', 'ADMIN', 'role', 'IQ.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142921', 'ADMIN', 'role', 'IQ.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142931', 'ADMIN', 'role', 'IQ.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142941', 'ADMIN', 'role', 'IQ.cm.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142951', 'ADMIN', 'role', 'IQ.cm.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142961', 'ADMIN', 'role', 'IQ.cm.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142971', 'ADMIN', 'role', 'MM.contractor.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142981', 'ADMIN', 'role', 'MM.contractor.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('142991', 'ADMIN', 'role', 'MM.contractor.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143001', 'ADMIN', 'role', 'IQ.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143011', 'ADMIN', 'role', 'RTS.qm.producer.test', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143021', 'ADMIN', 'role', 'IQ.qm.test.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143031', 'ADMIN', 'role', 'IQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143041', 'ADMIN', 'role', 'RTS.qm.consumer.test', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143051', 'ADMIN', 'role', 'IQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143061', 'ADMIN', 'role', 'OLQ.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143071', 'ADMIN', 'role', 'OLQ.qm.test.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143081', 'ADMIN', 'role', 'OLQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140001', 'ADMIN', 'role', '105001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140011', 'ADMIN', 'role', '107001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140021', 'ADMIN', 'role', '100051', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140031', 'ADMIN', 'role', '100061', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140041', 'ADMIN', 'role', '100071', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140051', 'ADMIN', 'role', '100161', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140061', 'ADMIN', 'role', '113011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140071', 'ADMIN', 'role', '113021', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140081', 'ADMIN', 'role', '113031', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140091', 'ADMIN', 'role', '108001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140101', 'ADMIN', 'role', '112001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140111', 'ADMIN', 'role', '100091', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140121', 'ADMIN', 'role', '111011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140131', 'ADMIN', 'role', '100171', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140141', 'ADMIN', 'role', '110001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140151', 'ADMIN', 'role', '100111', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140161', 'ADMIN', 'role', '106001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140171', 'ADMIN', 'role', '100141', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140181', 'ADMIN', 'role', '100151', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140191', 'ADMIN', 'role', '100131', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140201', 'ADMIN', 'role', '100181', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140211', 'ADMIN', 'role', '100311', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140221', 'ADMIN', 'role', '100191', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140231', 'ADMIN', 'role', '100201', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140241', 'ADMIN', 'role', '100291', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140251', 'ADMIN', 'role', '100301', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140261', 'ADMIN', 'role', '100261', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140271', 'ADMIN', 'role', '100271', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140281', 'ADMIN', 'role', '115041', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140291', 'ADMIN', 'role', '115051', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140301', 'ADMIN', 'role', '115011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140311', 'ADMIN', 'role', '115021', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140321', 'ADMIN', 'role', '101011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140331', 'ADMIN', 'role', '114001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140341', 'ADMIN', 'role', '114011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140351', 'ADMIN', 'role', '1104', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140361', 'ADMIN', 'role', '1102', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140371', 'ADMIN', 'role', '1103', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140381', 'ADMIN', 'role', '1105', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140391', 'ADMIN', 'role', '1107', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140401', 'ADMIN', 'role', '101002', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('140411', 'ADMIN', 'role', '109011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129431', 'USER', 'role', 'IQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129441', 'USER', 'role', 'IQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129451', 'USER', 'role', 'OLQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129461', 'USER', 'role', 'OLQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143091', 'ADMIN', 'role', 'OLQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143101', 'ADMIN', 'role', 'RTS.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143111', 'ADMIN', 'role', 'RTS.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143121', 'ADMIN', 'role', 'RTS.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143131', 'ADMIN', 'role', 'RTS.cm.producer.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143141', 'ADMIN', 'role', 'RTS.cm.producer.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143151', 'ADMIN', 'role', 'RTS.cm.producer.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143161', 'ADMIN', 'role', 'RTS.cm.consumer.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143171', 'ADMIN', 'role', 'RTS.cm.consumer.list.edit', 'default', 'func');
commit;
prompt 100 records committed...
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143181', 'ADMIN', 'role', 'RTS.cm.consumer.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143191', 'ADMIN', 'role', 'IQ.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143201', 'ADMIN', 'role', 'RTS.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143211', 'ADMIN', 'role', 'IQ.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143221', 'ADMIN', 'role', 'IQ.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143231', 'ADMIN', 'role', 'RTS.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143241', 'ADMIN', 'role', 'RTS.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143251', 'ADMIN', 'role', 'OLQ.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143261', 'ADMIN', 'role', 'OLQ.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143271', 'ADMIN', 'role', 'OLQ.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143281', 'ADMIN', 'role', 'MM.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143291', 'ADMIN', 'role', 'OLQ.cm.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143301', 'ADMIN', 'role', 'OLQ.cm.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143311', 'ADMIN', 'role', 'OLQ.cm.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143321', 'ADMIN', 'role', 'RC.service.list.start', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143331', 'ADMIN', 'role', 'RC.service.list.stop', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143341', 'ADMIN', 'role', 'IM.cm.model.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143351', 'ADMIN', 'role', 'IM.cm.model.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143361', 'ADMIN', 'role', 'IM.cm.model.list.remove', 'default', 'func');
commit;
prompt 119 records loaded
prompt Loading T_GF_ROLE...
insert into T_GF_ROLE (roleid, app_id, rolename, role_desc)
values ('USER', 'default', '普通用户', null);
insert into T_GF_ROLE (roleid, app_id, rolename, role_desc)
values ('OLQ', 'default', '联机查询用户', '联机查询用户');
insert into T_GF_ROLE (roleid, app_id, rolename, role_desc)
values ('ADMIN', 'default', '系统管理员', null);
commit;
prompt 3 records loaded
prompt Loading T_GF_SCHD_JOB...
prompt Table is empty
prompt Loading T_GF_TASK_POOL...
prompt Table is empty
set feedback on
set define on
prompt Done.
