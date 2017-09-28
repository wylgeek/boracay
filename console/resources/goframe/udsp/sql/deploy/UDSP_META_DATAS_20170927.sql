prompt PL/SQL Developer import file
prompt Created on 2017年9月27日 by JunjieM
set feedback off
set define off
prompt Loading COM_DATASOURCE...
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('3e14287aa8424e2ba3f81ba3e392b828', 'hj_test', 'Ad', 'HBASE', 'zzzz', '1', 'admin', '2017-09-05 17:11:45.180', 'admin', '2017-09-05 17:11:45.180', 'zzzz', 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('e3ef337e3153453b9b98f1736195a35b', 'test_oracle', '测试oracle', 'ORACLE', null, '0', 'admin', '2017-09-08 10:34:28.619', 'admin', '2017-09-08 10:34:28.619', null, 'OLQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('0a8268ebb73a47bb8e500b4577434313', 'im_hex_hive2', '致宇大数据-HIVE数据源', 'HIVE', null, '0', 'admin', '2017-09-20 09:42:46.573', 'admin', '2017-09-26 20:19:35.903', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('e55f961071f34909a76bdcc580bfae5a', 'HJ_HIVE', 'HJ_HIVE', 'HIVE', null, '0', 'admin', '2017-09-07 14:20:01.340', 'admin', '2017-09-14 10:18:37.500', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('8300a81d356443f29a01d12b0abaee54', 'hex_dev_elasticsearch', '致宇大数据开发环境ELASTICSEARCH数据源', 'ELASTICSEARCH', null, '0', 'admin', '2017-09-26 15:43:59.476', 'admin', '2017-09-26 15:43:59.476', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('cf2ce388877644d0848194fd13152285', 'hex_dev_redis', '致宇大数据redis交互查询', 'REDIS', null, '0', 'admin', '2017-07-31 22:08:05.024', 'admin', '2017-07-31 22:08:05.024', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('e4680c30fba940698beb19a217a891e5', 'hex_dev_hbase_for_hostname', '致宇大数据开发环境HBASE使用主机名方式', 'HBASE', null, '0', 'admin', '2017-07-31 22:08:07.886', 'admin', '2017-07-31 22:08:07.886', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('c23ffa34de214b769fc57e7da423cf16', 'hex_dev_hbase_for_proxy', '致宇大数据开发环境HBASE使用代理方式', 'HBASE', null, '0', 'admin', '2017-07-31 22:08:09.969', 'admin', '2017-07-31 22:08:09.969', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('f0e8932771c8499ab39be729ae007101', 'hex_dev_solr_hbase_for_proxy', '致宇大数据开发环境SOLR+HBASE使用代理方式', 'SOLR_HBASE', null, '0', 'admin', '2017-07-31 22:08:11.025', 'admin', '2017-07-31 22:08:11.025', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('4f81658c49ea4ffaab4197e77dc3b820', 'hex_impala', '致宇大数据-Impala数据源', 'IMPALA', null, '0', 'admin', '2017-07-31 22:08:52.716', 'admin', '2017-09-02 07:58:52.263', null, 'OLQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('39dc538bfbe2463da58469242af48ab0', 'hex_dev_kafka', '致宇公司开发环境', 'KAFKA', null, '0', 'admin', '2017-08-17 09:03:30.800', 'admin', '2017-08-17 09:03:30.800', null, 'RTS');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('53dbd410f3664ad697fb33eacd77853c', 'hex_dev_hbase', '致宇大数据开发环境HBASE', 'HBASE', null, '0', 'admin', '2017-07-31 22:08:06.838', 'admin', '2017-07-31 22:08:06.838', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('56221da424da45688b005243caa7bacf', 'hex_dev_solr_hbase_for_hostname', '致宇大数据开发环境SOLR+HBASE使用主机名方式', 'SOLR_HBASE', null, '0', 'admin', '2017-07-31 22:08:08.924', 'admin', '2017-07-31 22:08:08.924', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('b8f83b642df642b8af5d853d49de9c0a', 'hex_dev_solr_hbase', '致宇大数据开发环境SOLR+HBASE', 'SOLR_HBASE', null, '0', 'admin', '2017-07-31 22:08:12.069', 'admin', '2017-07-31 22:08:12.069', null, 'IQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('31bb8f04f37e4b2da5b10999d05a6f9c', 'hex_oracle', '本地oracle', 'ORACLE', null, '0', 'admin', '2017-08-01 10:06:40.373', 'admin', '2017-08-01 10:06:40.373', null, 'OLQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('f0500012f0d244af9f8a29300f724a72', 'UDSP_ORACLE', 'UDSP_ORACLE', 'ORACLE', null, '0', 'admin', '2017-09-06 17:20:06.313', 'admin', '2017-09-06 17:40:59.514', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('b790018e2e194520ba367668fd8411f9', 'HJ_SOLR', 'HJ_SOLR', 'SOLR', 'HJ_SOLR', '0', 'admin', '2017-09-11 15:14:59.503', 'admin', '2017-09-11 19:18:26.874', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('aaabe7f7a0cf412cbbe8aa0f670ee5ee', 'HJ_HBASE', 'HJ_HBASE', 'HBASE', 'HJ_HBASE', '0', 'admin', '2017-09-12 09:41:04.161', 'admin', '2017-09-12 09:41:04.161', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('a684e3e668f0482da99f23914c5dfc32', 'SOLR_HBASE', 'SOLR_HBASE', 'SOLR_HBASE', null, '0', 'admin', '2017-09-14 16:53:31.189', 'admin', '2017-09-14 16:53:31.189', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('68da2d155cb449fd9891bc4da064bc04', 'im_hex_impala', '致宇大数据-Impala数据源', 'IMPALA', null, '1', 'admin', '2017-09-20 09:41:41.778', 'admin', '2017-09-20 09:41:41.778', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('15c2d37df5e6434f9183b7c0e1f20da0', 'jin_mysql', '本地mysql—jintian', 'MYSQL', null, '0', 'admin', '2017-09-21 10:41:19.747', 'admin', '2017-09-25 15:57:30.568', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('9f5edf70d8074bae9d001dbacf313556', 'HJ_MYSQL', 'HJ_MYSQL', 'MYSQL', '123', '0', 'admin', '2017-09-06 11:16:14.060', 'admin', '2017-09-07 22:38:27.582', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('caff9907ab8e4e909b67ed4af252a81a', 'dev_mysql', 'tomnic的本地mysql数据库', 'MYSQL', null, '0', 'admin', '2017-09-12 09:18:35.817', 'admin', '2017-09-12 09:18:35.817', null, 'OLQ');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('4de1700c917940b0ba2f11681ae658c3', 'hex_solr_hbase', 'hex_solr_hbase', 'SOLR_HBASE', null, '0', 'admin', '2017-09-19 15:49:10.415', 'admin', '2017-09-20 15:25:22.282', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('ff0ea269fb034813b1c8db5bc39174ac', 'hex_hive', 'hex_hive', 'HIVE', null, '0', 'admin', '2017-09-19 15:50:20.868', 'admin', '2017-09-19 15:50:20.868', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('IM_1', 'hex_dev_hbase', '致宇大数据开发环境HBASE', 'HBASE', null, '1', 'admin', '2017-07-31 22:08:06.838', 'admin', '2017-07-31 22:08:06.838', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('IM_2', 'hex_dev_hbase_for_hostname', '致宇大数据开发环境HBASE使用主机名方式', 'HBASE', null, '1', 'admin', '2017-07-31 22:08:07.886', 'admin', '2017-07-31 22:08:07.886', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('IM_3', 'hex_dev_hbase_for_proxy', '致宇大数据开发环境HBASE使用代理方式', 'HBASE', null, '1', 'admin', '2017-07-31 22:08:09.969', 'admin', '2017-07-31 22:08:09.969', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('IM_4', 'hex_dev_redis', '致宇大数据redis交互查询', 'REDIS', null, '1', 'admin', '2017-07-31 22:08:05.024', 'admin', '2017-07-31 22:08:05.024', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('IM_5', 'hex_dev_solr_hbase', '致宇大数据开发环境SOLR+HBASE', 'SOLR_HBASE', null, '1', 'admin', '2017-07-31 22:08:12.069', 'admin', '2017-07-31 22:08:12.069', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('IM_6', 'hex_dev_solr_hbase_for_hostname', '致宇大数据开发环境SOLR+HBASE使用主机名方式', 'SOLR_HBASE', null, '1', 'admin', '2017-07-31 22:08:08.924', 'admin', '2017-07-31 22:08:08.924', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('IM_7', 'hex_dev_solr_hbase_for_proxy', '致宇大数据开发环境SOLR+HBASE使用代理方式', 'SOLR_HBASE', null, '1', 'admin', '2017-07-31 22:08:11.025', 'admin', '2017-07-31 22:08:11.025', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('6968dda4247f4342ad3bad09442670f9', 'SOLR_HBASE', 'SOLR_HBASE', 'SOLR_HBASE', null, '1', 'admin', '2017-09-12 14:21:37.006', 'admin', '2017-09-12 14:21:37.006', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('e15a7e21200948c5ba2cc92fe3d4e339', 'hex_hbase', 'hex_hbase', 'HBASE', null, '0', 'admin', '2017-09-18 09:10:12.175', 'admin', '2017-09-19 15:40:00.358', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('ce5c958b77294337baec27e418e5075e', 'hex_solr', 'hex_solr', 'SOLR', null, '0', 'admin', '2017-09-18 14:09:23.510', 'admin', '2017-09-20 15:28:20.131', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('0573547746164d8babab2edfcdfa4015', 'hex_mysql', 'hex_mysql', 'MYSQL', null, '0', 'admin', '2017-09-18 16:51:21.959', 'admin', '2017-09-18 16:51:21.959', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('040b53fddcc248578c49ec1abd253404', 'hex_oracle', 'hex_oracle', 'ORACLE', null, '0', 'admin', '2017-09-18 16:52:17.012', 'admin', '2017-09-18 16:52:17.012', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('2f9f1efe3814428890b8eeb1f952b616', 'hex_kafka', 'hex_kafka', 'KAFKA', null, '0', 'admin', '2017-09-19 16:05:16.911', 'admin', '2017-09-19 16:05:16.911', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('e55951ed203341ea83e079314e825d3d', 'jin_hive', '公司hive，引擎数据源', 'HIVE', null, '0', 'admin', '2017-09-21 10:53:51.393', 'admin', '2017-09-21 10:53:51.393', null, 'IM');
insert into COM_DATASOURCE (pk_id, name, describe, type, note, del_flg, crt_user, crt_time, upt_user, upt_time, impl_class, model)
values ('1522f3b690f0408f9eb94925c50f6392', 'jin_mysql_localhosh', '本地mysql', 'MYSQL', '本地mysql；ip地址为localhost', '0', 'admin', '2017-09-26 09:11:07.857', 'admin', '2017-09-26 09:11:07.857', null, 'IM');
commit;
prompt 40 records loaded
prompt Loading COM_PROPERTIES...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('08a4e56f6868416e827a602ce8035bd0', '0d6fd07b94d64dc49eea0f5b98e7893e', 'solr.shards', '1', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('85bc6299ecb84bfc80a63932563801ae', '0d6fd07b94d64dc49eea0f5b98e7893e', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('bb92e9e2e3e4462db4c1c21baf356801', '0d6fd07b94d64dc49eea0f5b98e7893e', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5ab00005ece64beaa16d8f2042033ec9', '0d6fd07b94d64dc49eea0f5b98e7893e', 'hbase.region.num', '1', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e11439750d0c4dd59e9650675a253fde', '0d6fd07b94d64dc49eea0f5b98e7893e', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a604a874e06b46599beb20a222f394c7', '0d6fd07b94d64dc49eea0f5b98e7893e', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e1f69210f2a943ffbaeda4052382a134', '0d6fd07b94d64dc49eea0f5b98e7893e', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b0c6c08365834e2da1a21d810bfb72e9', '0d6fd07b94d64dc49eea0f5b98e7893e', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5760b9a5f4ec4a81acc652e20aa29726', '0d6fd07b94d64dc49eea0f5b98e7893e', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c38d6fa526e2431ca6b776a511108275', '0d6fd07b94d64dc49eea0f5b98e7893e', 'hbase.fq.sep', '\\007', 'hbase 字段分隔符');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3e2db92391e5434190770f33529ae2f5', '0d6fd07b94d64dc49eea0f5b98e7893e', 'hbase.rowkey.sep', '|', 'hbase 索引分隔符');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a32ec79906844d0192e74841065d8356', '3e14287aa8424e2ba3f81ba3e392b828', 'hbase.zk.quorum', '1', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7135e3ad7e444cd3ad8c05b967f66cc7', '3e14287aa8424e2ba3f81ba3e392b828', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5e549745ea054bf4a82ada300a79fc9c', '63b04680378840de9e09b18a78d26e70', 'solr.shards', '1', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('954a4cba26ec4a1b8e0f04c733bbfa4a', '63b04680378840de9e09b18a78d26e70', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e32e1e66c66341fa907205f1abdcc40a', '63b04680378840de9e09b18a78d26e70', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7e29b8d41c9a4c8b83664d944b63adeb', '63b04680378840de9e09b18a78d26e70', 'hbase.region.num', '1', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1019000263484f23b82f6c94f90d018a', '63b04680378840de9e09b18a78d26e70', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cdd7fca877a84996a71d012307d615cc', '63b04680378840de9e09b18a78d26e70', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b6e2fe88eed249c48c03f0c7efbc7786', '63b04680378840de9e09b18a78d26e70', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fd82e05780ce4d4e9f6013d699740bc1', '63b04680378840de9e09b18a78d26e70', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('370d3323a00748cbb2dfcee878ffcee1', '63b04680378840de9e09b18a78d26e70', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('517ff72e98154bedb88119f5570a8b8b', '63b04680378840de9e09b18a78d26e70', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('622b5945df094819a9853214fdb58fba', '63b04680378840de9e09b18a78d26e70', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1a1ebc0c9083408a9cd0b4547f2e6d0a', '7fde339cd6ae45f3a7988144d0eefcb8', 'oracle.table.name', 'Oracle 表名', '测试表名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a33b5d024a134426996e0250fb70b04c', '7fde339cd6ae45f3a7988144d0eefcb8', 'oracle.database.name', 'Oracle 库名', '测试库名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('df4b0edacf344e5ea222de62daff90fe', 'b5fac00d6b2a4461a3a3c90fbb83a0c1', 'topic', 'rts_jyls_jylx_udsp', '主题');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('aaf4c196d1b54e65a40f37318a7ca9f8', 'b5fac00d6b2a4461a3a3c90fbb83a0c1', 'consumer.timeout.ms', '1000', '消费超时时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f0b8e68a02ec4d03af684ed2724775e4', 'b5fac00d6b2a4461a3a3c90fbb83a0c1', 'consumer.cron.expression', '*/3 * * * * ?', '消费计划任务表达式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e23e3e83d03e40beb70b00fa2584a0a2', 'b5fac00d6b2a4461a3a3c90fbb83a0c1', 'group.id', 'qw', '消费组ID');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('036320821c9d4bf8b410b684192a3f7a', 'e3ef337e3153453b9b98f1736195a35b', 'jdbc.url', 'jdbc:oracle:thin:@dev.goupwith.com:1521/zctggl2', 'oracle JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6e97eb896d3a433882811ddb0a88a034', 'e3ef337e3153453b9b98f1736195a35b', 'username', 'udsp', 'oracle 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('46eeb2b6805949f98c0f2f806cd8f508', 'e3ef337e3153453b9b98f1736195a35b', 'password', 'Udsp159357', 'oracle 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('bf92ff65f7a04cfe8ce1bc24a7c9765c', '7fde339cd6ae45f3a7988144d0eefcb8', 'oracle.sql', 'Oracle SQL语句', 'select 1 from dual');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d1dc38f2a7aa4378b5a548c92c47837e', '4be8486377cc4548adbb52997eda57b6', 'a', 'd', 'f');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('41efa5119b504b1281f429d420ae1a92', '9f5edf70d8074bae9d001dbacf313556', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f4765c2a76c54de28c865a386a92b7af', '9f5edf70d8074bae9d001dbacf313556', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('72fd0d760d7949b1be100aa96db72482', '9f5edf70d8074bae9d001dbacf313556', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b5843590a0354d5ab525b378daa67657', '9f5edf70d8074bae9d001dbacf313556', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7c5c86c3a7394f6c99901b150f6bb356', '9f5edf70d8074bae9d001dbacf313556', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a7e90693876d4919bd095afbff5b3d52', '9f5edf70d8074bae9d001dbacf313556', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('faeec06abec84238a555b6a327439efb', '9f5edf70d8074bae9d001dbacf313556', 'driver.class', 'com.mysql.jdbc.Driver', 'mysql 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c77d84e46b3e463980d6ab82054464fc', '9f5edf70d8074bae9d001dbacf313556', 'jdbc.url', 'jdbc:mysql://localhost:3306', 'mysql JDBC URL，如：jdbc:mysql://${ip}:${port}');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('05ae07e025f24a3e8f5075635a7d4a7f', '9f5edf70d8074bae9d001dbacf313556', 'username', 'root', 'mysql 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('90f9a7075be248c9bd23aaa917190194', '9f5edf70d8074bae9d001dbacf313556', 'password', '1234', 'mysql 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('95768bfca78b45afa9dd51de87069c33', '9f5edf70d8074bae9d001dbacf313556', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cc9d2a625b9a4205a1cfb96050d6142f', '9f5edf70d8074bae9d001dbacf313556', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c5d72e164aca4f70b74cfe9fd7ad5676', '9f5edf70d8074bae9d001dbacf313556', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c565e792a78d49f986b5a6d8b577777e', '9f5edf70d8074bae9d001dbacf313556', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b314b033b1fb4291baaab033bf9a5c8d', '9f5edf70d8074bae9d001dbacf313556', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('81f5df606f1646b4ade5312332d4f292', '9f5edf70d8074bae9d001dbacf313556', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d9f478bb16964ba7baa1352e6d2184c3', 'e3ef337e3153453b9b98f1736195a35b', 'driver.class', 'oracle.jdbc.OracleDriver', 'oracle 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('041bd58c46524bc68816cf4c5f6e8669', 'e3ef337e3153453b9b98f1736195a35b', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b0ada689fd4b4c3892646fe08c45fb27', 'e3ef337e3153453b9b98f1736195a35b', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ccf776e2aaad4a01b95be88d1060e53f', 'e3ef337e3153453b9b98f1736195a35b', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('16382dce9d3c445fa944e03756c2efed', 'e3ef337e3153453b9b98f1736195a35b', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('50990fc7c9ef44dd85d53225ed58c380', 'e3ef337e3153453b9b98f1736195a35b', 'validation.query', 'select 1 from dual', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2d432752cd9047f484ae9076fa5a1038', 'e3ef337e3153453b9b98f1736195a35b', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a3bbaee23b7c4fd3b56579bd80cd85f8', 'e3ef337e3153453b9b98f1736195a35b', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1c9587bec3894c7684c40ff90cf650d7', 'e3ef337e3153453b9b98f1736195a35b', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b6b9aa0bda4f4f1ba5dc4741cbe6f493', 'f0500012f0d244af9f8a29300f724a72', 'driver.class', 'oracle.jdbc.OracleDriver', 'oracle 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d4ad6ca862184ce6b702a00f6aa341dd', 'f0500012f0d244af9f8a29300f724a72', 'jdbc.url', 'jdbc:oracle:thin:@dev.goupwith.com:1521/zctggl2', 'oracle JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('be6d755744054397a070c56752a049af', 'f0500012f0d244af9f8a29300f724a72', 'username', 'udsp', 'oracle 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ce3e559bd87840ab89e37d8bc473daf2', 'f0500012f0d244af9f8a29300f724a72', 'password', 'Udsp159357', 'oracle 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('99fa93b24d79472ea61043b25aff2a53', 'f0500012f0d244af9f8a29300f724a72', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('56076397b3d34fd9bf340e8651cc57c7', 'f0500012f0d244af9f8a29300f724a72', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('437fa7db767845e9adc2b9b6722c3299', 'f0500012f0d244af9f8a29300f724a72', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3ecad9de3db445968c8d25c6eb50ee8f', 'f0500012f0d244af9f8a29300f724a72', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('35405bd90f7844b38284b784c70bb691', 'f0500012f0d244af9f8a29300f724a72', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0ad5624a4b314f17ac32b0fc6485fdeb', 'f0500012f0d244af9f8a29300f724a72', 'validation.query', 'select 1 from dual', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('54d4684a29fb4605b70234c3aeb9701d', 'f0500012f0d244af9f8a29300f724a72', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d555d62f3bc14cac9f4de5129780d3ea', 'f0500012f0d244af9f8a29300f724a72', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f577649ddaf14c30b24dd4f90f0bc5d2', 'f0500012f0d244af9f8a29300f724a72', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9bd9b175325a486e9742c8f0c6fee207', 'f0500012f0d244af9f8a29300f724a72', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c8b2a8b35a0b4531b095e15c21317abc', 'f0500012f0d244af9f8a29300f724a72', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2d4ad710c0494398b889261ac70918c4', 'f0500012f0d244af9f8a29300f724a72', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3c7502cfee97444e86bc95ee61957021', '91da97dd62e54c5cb3022f6a76dbb8a3', 'asd', '1', '1');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9ec228598d9440f1836798e98ca515d6', 'e3ef337e3153453b9b98f1736195a35b', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('379303cb62d34201bc7e7447d670f11e', 'e3ef337e3153453b9b98f1736195a35b', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('23cb6402514d4f8cb9c82a099635bf4b', 'e3ef337e3153453b9b98f1736195a35b', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('36a0035ba5b24a278b3bf6ecca393388', 'e3ef337e3153453b9b98f1736195a35b', 'max.data.size', '65535', '最大数据返回条数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('14e7b8bbf904413ea57189eb44542e82', 'c9e58e1611b648709b4d504f919715cb', 'w', '121', 'dsdwdw');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5abc8de3f5ad461b85da94102ec0db11', '92c08521fbb140b496eabb160bc161ee', 'wew', 'wefw', '2f23');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('40d2512977884096840a0045e9bbaacf', 'f8c1e30594b44261ba297f9814347924', 'wew', 'wewe', 'ewe');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('56c8512b05f245ab8a01972e8d27c5c8', '6facbd0870da4920964a389c5014f0d7', 'wef', 'we', 'wefw');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e214e9c39c744e4fa8e24c631d05e55f', '5823eab478f44b218fd4a8e47cc1d151', 'wew', 'wew', 'ewe');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8e8eeb9d7cca4eb8b4bd77004b213e20', 'a4ee2d3d8d834e538c41fb51e7416baa', 'wefw', 'ef', 'gwe');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f811139fb5ac40819878efccc9a4b4b7', '171cdb6096334424af129615770d5ab2', 'sdfsd', 'fwe', 'wegwe');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5207adb838884d5d88cc7a985365cc9b', 'd667818d3df4450f8706903997ff4822', 'sdfsd', 'fwe', 'wegwe');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('63e7ebea61dd4c59adc88ec0d79df011', '912e2352cab1468ab679f505f978887f', 'sdfsd', 'fwe', 'wegwe');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('96ba1d6c0f5a4ec29d15cc3facfc6a56', 'e3ef337e3153453b9b98f1736195a35b', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e911b6c435b04dab9986e64b8c700a63', 'd2efe036b6aa4a32a56408ebf84c2ebd', 'sdf', 'df', 'fwew');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a680bcc6be3f4dada9189c40d877a491', '777084b1de1f4d939d58b8213fab36fb', 'wefwe', 'wef', 'wefw');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6c30c29396754fb88ee78088d2e876f5', '3e02b44b993e4b8a8624a73ecd9a45b2', 'sd', 'ds', 'sd');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('86d39e496dda4e779246a6a891a1c55a', '379b34d6e471495c95159ca4b3a5ef0c', 'asdq', 'asd', 'qwd');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('95bbd62d21154c2ebc258a825921e76c', '576340e60dcb4b98b014aa99abe510df', 'asda', 'asdqw', 'qww');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ab019b5b85584e829ed4c384b19de612', 'a9a68430a54f4820b67906e72d7b14ec', 'sdf', 'asdq', 'wdqw');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b31d4083106142419bb4acf3d788d2c7', 'b790018e2e194520ba367668fd8411f9', 'solr.servers', 'hadoop01:8983,hadoop02:8983,hadoop03:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7dc6c3d3268d425a84567f3d4b3a002c', 'caff9907ab8e4e909b67ed4af252a81a', 'driver.class', 'com.mysql.jdbc.Driver', 'mysql 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('143cd53b17ff4491b7a117d4a4546af4', 'caff9907ab8e4e909b67ed4af252a81a', 'jdbc.url', 'jdbc:mysql://localhost:3306', 'mysql JDBC URL，如：jdbc:mysql://${ip}:${port}');
commit;
prompt 100 records committed...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('769f70004ab24739814129c8be2e1250', 'b790018e2e194520ba367668fd8411f9', 'solr.url', 'hadoop02:2181,hadoop03:2181', 'zookeeper地址、端口和目录，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a3d1b3c51df44f95b4a68a7d56498e3d', 'caff9907ab8e4e909b67ed4af252a81a', 'username', 'root', 'mysql 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4c074c457f994a9baad2bcfb300a7681', 'caff9907ab8e4e909b67ed4af252a81a', 'password', 'root', 'mysql 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ab90971436bd4678a727c5df946ce8c1', 'caff9907ab8e4e909b67ed4af252a81a', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('12ce2ddbda714a039e2b2e534ba199dd', 'caff9907ab8e4e909b67ed4af252a81a', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f6ec68710613466996a2d1d885e0c6bd', 'caff9907ab8e4e909b67ed4af252a81a', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('23ccc8347ccd46479a904d766a01059a', 'caff9907ab8e4e909b67ed4af252a81a', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cd1d5db43e804f9cb2a50766f3f722c4', 'caff9907ab8e4e909b67ed4af252a81a', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9b96f186c1e94501a3b614e69d894016', 'caff9907ab8e4e909b67ed4af252a81a', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9e0691b7977c4f128ac5d6d9fb03b886', 'caff9907ab8e4e909b67ed4af252a81a', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ffffb83e5680477a8e6fd3e68934e1dd', 'caff9907ab8e4e909b67ed4af252a81a', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('172ad5a3617c4f2b88febbbfd1b9b428', '53dbd410f3664ad697fb33eacd77853c', 'hbase.zk.quorum', '192.168.1.61,192.168.1.62,192.168.1.63', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d456ca9855ad4dc28e38a69317b155b1', '53dbd410f3664ad697fb33eacd77853c', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f0cb3833ca1a46f7b027bf4cd5ce5174', '56221da424da45688b005243caa7bacf', 'hbase.zk.quorum', 'node1,node2,node3', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6eb26b585364478383669aca338fc8b2', '56221da424da45688b005243caa7bacf', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('92378f650c1b4bb1bd9f0ccf1c6b606a', '56221da424da45688b005243caa7bacf', 'solr.servers', 'node1:8983,node2:8983,node3:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('55e6c0c0085f4ffb869fcd06b2355c9f', 'b8f83b642df642b8af5d853d49de9c0a', 'hbase.zk.quorum', '192.168.1.61,192.168.1.62,192.168.1.63', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5bc7a632d20a4b67a60a27c1bf000999', 'b8f83b642df642b8af5d853d49de9c0a', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('aa8ae4046b144b978b5265ceb57d8185', 'b8f83b642df642b8af5d853d49de9c0a', 'solr.servers', '192.168.1.61:8983,192.168.1.62:8983,192.168.1.63:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('12bc97d5c6214291a3782dbece326f9d', '4f81658c49ea4ffaab4197e77dc3b820', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'Impala 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('91aaa379df85422d8a2dbccfeb7818d1', '4f81658c49ea4ffaab4197e77dc3b820', 'jdbc.url', 'jdbc:hive2://192.168.1.61:21050', 'Impala JDBC URL，如：无密码jdbc:hive2://${ip}:${port}/;auth=noSasl  有密码jdbc:hive2://${ip}:${port}/');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4bcf802a33494c5397e25f6ca5c06ba7', '4f81658c49ea4ffaab4197e77dc3b820', 'username', 'hive', 'Impala 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7ace7b0a751742dd90f44b83527c3919', '4f81658c49ea4ffaab4197e77dc3b820', 'password', '111111', 'Impala 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b38c760602c94f67a05db27485497ba5', '4f81658c49ea4ffaab4197e77dc3b820', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('48dcdd3bc5684ea6a5d6a10120ae2e66', '4f81658c49ea4ffaab4197e77dc3b820', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('60f7d6e6ea6546709de81392d73dc984', '4f81658c49ea4ffaab4197e77dc3b820', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ad5997505d4f4d688eeeae00529b6938', '4f81658c49ea4ffaab4197e77dc3b820', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('194ce2316ff74a6daf70d4a4c9c3dd3e', '4f81658c49ea4ffaab4197e77dc3b820', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5fc4dec7d0264788a981a949910689f9', '4f81658c49ea4ffaab4197e77dc3b820', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('868c52b716e34b3ebd85e0cf7d236ab1', '4f81658c49ea4ffaab4197e77dc3b820', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9602cef9b5f54af59247246da49fe99f', '4f81658c49ea4ffaab4197e77dc3b820', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ef49394207a14a4fa88a0ca4fb991a34', '4f81658c49ea4ffaab4197e77dc3b820', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('26cd988c1355417f821fdbb8721621e0', '4f81658c49ea4ffaab4197e77dc3b820', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('410a79c2b02c453e82cd5904846ca6a5', '4f81658c49ea4ffaab4197e77dc3b820', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('de3d517e3eb34ef681534279c5acebaf', '4f81658c49ea4ffaab4197e77dc3b820', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cd26f6af7a364259aef9da967687b93a', '4f81658c49ea4ffaab4197e77dc3b820', 'max.connected.size', '2000', null);
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ae7fd42dea2e495c92ae47376ae20594', '3b9c3e875f854ec2bf36a23f2c444e8d', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6c7900878d3644b58c6bc655e7ab63b9', '3b9c3e875f854ec2bf36a23f2c444e8d', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('968c26ba1ba74d1d9edb4da9e2a9a94b', '3b9c3e875f854ec2bf36a23f2c444e8d', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('82e533bd208d4e72b93b33a3173dc80a', '3b9c3e875f854ec2bf36a23f2c444e8d', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9750e3b5f90c460d9db823a8da2c2ee4', '3b9c3e875f854ec2bf36a23f2c444e8d', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7948434f022646f5919b61abf64bf314', '3b9c3e875f854ec2bf36a23f2c444e8d', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c6ef45a80b614001a58a629a80c60a0a', '3b9c3e875f854ec2bf36a23f2c444e8d', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('51a7ab484d164a3aac5f11a14d4132e7', '3b9c3e875f854ec2bf36a23f2c444e8d', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('240cfa390553461bb0b39070419411fa', '3b9c3e875f854ec2bf36a23f2c444e8d', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2a7c2acfa780476a995dbac554c48429', '3b9c3e875f854ec2bf36a23f2c444e8d', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6c50b4a6ee2041309f56286963554fb4', '2e3b08b5009348f6a1511334710d46c1', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3678c201c1b5418493c5562b0880bb61', '2e3b08b5009348f6a1511334710d46c1', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('26f4204ed5ef41b7ae41d1d5dc0dfa5f', '2e3b08b5009348f6a1511334710d46c1', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('541b98f345da47a5a0449c55fce53967', '2e3b08b5009348f6a1511334710d46c1', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('98dce2ed5d5148e5977a1250dd706b26', '39dc538bfbe2463da58469242af48ab0', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6dd02455d99841d784799c07805e3517', '3275cbea78534218b8e5495a77e285e4', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.21:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('65a2acb88f56486c925ee097ef49a78d', '3275cbea78534218b8e5495a77e285e4', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0799891d478e405a88c4162340624abe', '3275cbea78534218b8e5495a77e285e4', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('97f9c2a25e584f4492d9eaac52622d20', '3275cbea78534218b8e5495a77e285e4', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fc900abddca74aa29807acbd600d8a3a', '60bebb6656a6460c80d95e382ee7fd9e', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('66a2c5802cae462d8cd7c65df5b791fa', '60bebb6656a6460c80d95e382ee7fd9e', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4cf52fc3aaf6433ba60204a0b975426e', '60bebb6656a6460c80d95e382ee7fd9e', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ce0fa0c5e04041d68c96b98b27c32e1d', '60bebb6656a6460c80d95e382ee7fd9e', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('73ca365a3a804e11a66c20b2600d5624', '60bebb6656a6460c80d95e382ee7fd9e', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b51fcfc0ec3e420990ae68637671ddc5', '60bebb6656a6460c80d95e382ee7fd9e', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('34044191abcf4831b3b2d302af836611', '60bebb6656a6460c80d95e382ee7fd9e', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('764e19b08f534dd98f8e19cb7b5afdcc', '60bebb6656a6460c80d95e382ee7fd9e', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('51e1cdcc0e4a4e709180f46912ddbc2e', '60bebb6656a6460c80d95e382ee7fd9e', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d0991af1af23428aa0d4dfad94f9069f', '60bebb6656a6460c80d95e382ee7fd9e', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('69f7b1829df9459ea95f41b81b62cd30', '1ec11dfb5beb4e00902d8048acf04320', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('38c65ffc99414c6aae5f91e1aa9fd0b0', '1ec11dfb5beb4e00902d8048acf04320', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fbf57de98e8543179c0e9e0d984e9694', '1ec11dfb5beb4e00902d8048acf04320', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b8cc18c4e43647219a1c79c2b3d11cb8', '1ec11dfb5beb4e00902d8048acf04320', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ba2935dbc9df41269078c4a291cc3ba8', '1ec11dfb5beb4e00902d8048acf04320', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('659c052dabf04b1a8e1308d5beb998b8', '1ec11dfb5beb4e00902d8048acf04320', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e7e8118d5fef4e21b85389503b624551', '1ec11dfb5beb4e00902d8048acf04320', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5b4feac5a9c94d94b5afccd9e98e8231', '1ec11dfb5beb4e00902d8048acf04320', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('800ffc6fd566421c977bd85e17f3fa05', '1ec11dfb5beb4e00902d8048acf04320', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8b117522eaee4c23867626a242bee1bd', '1ec11dfb5beb4e00902d8048acf04320', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('225b64ee971b45bca3ec59abb54e6949', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('29f798ad38da4b78a1694eb25da22390', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('78265a9786cb4c0b859bffbd45ca8188', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d62828ccfc3a43e7a3ec1de229ff06f6', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('77ec8ec182dd4fa2937d35361d5ec4af', '8f8a9c78d75943d9b62374fa12a3e879', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7cbe7e30c9d449cd95ed17f0c64c9439', '8f8a9c78d75943d9b62374fa12a3e879', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9dff1eeb22ab43e09e772e3b574eaf1f', '8f8a9c78d75943d9b62374fa12a3e879', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('42a2adbd827c41089c86d831f609a978', '8f8a9c78d75943d9b62374fa12a3e879', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('948f3ef0757642b4b3f12fb92cf765f3', 'cf2ce388877644d0848194fd13152285', 'redis.connection.port', '6379', 'redis连接端口号');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b901000f1665464dafd99cfd2cf49685', 'cf2ce388877644d0848194fd13152285', 'redis.max.idle', '10000', 'redis连接最大空闲数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('691f0c8820bc4c36b4080d74036a8ea0', 'cf2ce388877644d0848194fd13152285', 'redis.max.wait', '1000', 'redis连接最长等待时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1dafce4b11214b32882d50c10bf16866', 'cf2ce388877644d0848194fd13152285', 'redis.max.timeOut', '1000', 'redis连接最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9729565bad65482fa3f65590a976f026', 'cf2ce388877644d0848194fd13152285', 'redis.test.on.brrow', 'true', 'redis连接是否检查连通性');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('037118408b564163aa04f7a8804b0ab7', 'cf2ce388877644d0848194fd13152285', 'redis.max.total', '20000', 'redis连接池最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e2949336952a4957ac1ec85b9d9e5a0f', 'cf2ce388877644d0848194fd13152285', 'redis.connection.ip', '192.168.1.61', 'redis连接ip地址');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7771a0f385dd44c1b41d1ddf5e3922ec', 'cf2ce388877644d0848194fd13152285', 'redis.seprator', '\\007', '结果数据分隔符');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('08263318a3ca43d8adef8965c166037e', 'e4680c30fba940698beb19a217a891e5', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3cf504146a2d41558f0ea4fb2b5f763a', 'e4680c30fba940698beb19a217a891e5', 'hbase.zk.quorum', 'node1,node2,node3', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8d9305e52fdb4bad990e71289457608b', 'c23ffa34de214b769fc57e7da423cf16', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e1fdf4bb75844619842ae523f01ffc04', 'c23ffa34de214b769fc57e7da423cf16', 'hbase.zk.quorum', '192.168.1.61', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4745c4133841493daddc364515952be6', 'f0e8932771c8499ab39be729ae007101', 'solr.servers', '192.168.1.61:8983,192.168.1.62:8983,192.168.1.63:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8f307628f5df4298969bb50e9563fe07', 'f0e8932771c8499ab39be729ae007101', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('663063bc230048178e3f78d0daf9b1bb', 'f0e8932771c8499ab39be729ae007101', 'hbase.zk.quorum', '192.168.1.61', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('98a68c0b56ad411588887daaf46e75cf', '31bb8f04f37e4b2da5b10999d05a6f9c', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'oracle 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('439cb03c127344e296ab0f9316811501', '31bb8f04f37e4b2da5b10999d05a6f9c', 'jdbc.url', 'jdbc:oracle:thin:@192.168.1.61:1521/orcl', 'oracle JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}');
commit;
prompt 200 records committed...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f32136d3839341dfbcb2403783a0ddba', '31bb8f04f37e4b2da5b10999d05a6f9c', 'username', 'udsp', 'oracle 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('48221d9bd82d44eba7806676d5190865', '31bb8f04f37e4b2da5b10999d05a6f9c', 'password', 'udsp', 'oracle 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8502e920f17e4499a061397afd38e57d', '31bb8f04f37e4b2da5b10999d05a6f9c', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('37485d4b1cfb4e5389a863ad18a2953f', '31bb8f04f37e4b2da5b10999d05a6f9c', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('840442046c6e42b3a49596405dea3fb5', '31bb8f04f37e4b2da5b10999d05a6f9c', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('221c1b079ac94c658ca4c3babe1b99ec', '31bb8f04f37e4b2da5b10999d05a6f9c', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('05873260db274164a2b477f09f47c1cf', '31bb8f04f37e4b2da5b10999d05a6f9c', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9808b5c035be4b738e635285733282ef', '31bb8f04f37e4b2da5b10999d05a6f9c', 'validation.query', 'select 1 from dual', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c340b31353514ee788ac2b61d420b5f1', '31bb8f04f37e4b2da5b10999d05a6f9c', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('09b0b26e501b4a339ccf4cd892fbe4e4', '31bb8f04f37e4b2da5b10999d05a6f9c', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4b88f7645d5e470bbd7df831167684eb', '31bb8f04f37e4b2da5b10999d05a6f9c', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d110e3c915dc42df956b05e9384107f8', '31bb8f04f37e4b2da5b10999d05a6f9c', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cef26964294544f8a7f0c4704f83f029', '31bb8f04f37e4b2da5b10999d05a6f9c', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('26239700450a49d9b64fb57b206ca9fd', '31bb8f04f37e4b2da5b10999d05a6f9c', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2019278def2d411db2b4ad3e52f9c9f9', '31bb8f04f37e4b2da5b10999d05a6f9c', 'max.data.size', '65535', '最大数据返回条数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fc39878e36cf41448a50281215222165', '38ebdd9795a84d2aae51cd753859e325', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('26a86b60ca464ea8b60232eabc4151aa', '38ebdd9795a84d2aae51cd753859e325', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6f3a92889b3a4b2197c9a679d5b2fa23', '38ebdd9795a84d2aae51cd753859e325', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1ca60f14e36142a78caedddae0b66d26', '38ebdd9795a84d2aae51cd753859e325', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b2a833d8613c495f9da90955c86b35ff', '38ebdd9795a84d2aae51cd753859e325', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('56b25ef9c2fe4fbcb2f691a3b3931862', '38ebdd9795a84d2aae51cd753859e325', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('15ec68981dd2495a8e1018833934b2ce', '38ebdd9795a84d2aae51cd753859e325', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b019e6c13d094de1a0d8fa60da2be11d', '38ebdd9795a84d2aae51cd753859e325', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('24c90ae641ee44b084c022df62cbe89c', '38ebdd9795a84d2aae51cd753859e325', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3e237892db80458e897ef33d22a71550', '38ebdd9795a84d2aae51cd753859e325', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e43c1f4c74284704ac1f990ab66ad821', '44aebab24d4741eb9d240fd198e15490', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5f7938484d174b51b4d726ec092ae506', '44aebab24d4741eb9d240fd198e15490', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('82e0bb7a4b574a94946c063bbbee4663', '44aebab24d4741eb9d240fd198e15490', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2a37e1eaf1fc49c0a4108816271b25f9', '44aebab24d4741eb9d240fd198e15490', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('dd11091b9f1a499088777194334d8f8c', '44aebab24d4741eb9d240fd198e15490', 'consumer.timeout.ms', '-1', '消费者超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b9f750a5a6964423b4ad510e1907a536', '44aebab24d4741eb9d240fd198e15490', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1c88d9ad72e140e3949d31041122c71e', '44aebab24d4741eb9d240fd198e15490', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ab6b429af762442788d35df35ca57b26', '44aebab24d4741eb9d240fd198e15490', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5a20683204bf48b799ba50a5345e2865', '44aebab24d4741eb9d240fd198e15490', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ade8cf0e91d841a7953a1e3f99b93f8e', '44aebab24d4741eb9d240fd198e15490', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('540afda11856413b9e60fcb049cd3590', '82f6a904cd6546c3b1c1cd6bdcf6214b', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ebc99be608a145b9b5402720cb8c4fd6', '82f6a904cd6546c3b1c1cd6bdcf6214b', 'serializer.class', 'kafka.serializer.StringEncoder', '序列化类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('76fc6c4e38544e379deb8f76e9c009f7', '82f6a904cd6546c3b1c1cd6bdcf6214b', 'key.serializer.class', 'kafka.serializer.StringEncoder', 'key对象的serializer类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4ee09817437c4f18905fb968c4098521', '82f6a904cd6546c3b1c1cd6bdcf6214b', 'request.required.acks', '0', '请求确认模式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('73068db44120487fa8eb2f8f2a4d715c', '9b241f431ba744e59c656fe8d2f0ca39', 'solr.shards', '1', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d72fd57556114317ad3dd39463770d6d', '9b241f431ba744e59c656fe8d2f0ca39', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c0c408c7516d451db3e3f45da48043e5', '9b241f431ba744e59c656fe8d2f0ca39', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e7f023e9a55d4f37a21e606c653804a7', '9b241f431ba744e59c656fe8d2f0ca39', 'hbase.region.num', '3', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4f24190546f0489cb9ff55390107e3dc', '9b241f431ba744e59c656fe8d2f0ca39', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d134ac57ee9049c6a2c2d896213c38a1', '9b241f431ba744e59c656fe8d2f0ca39', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ccb699fc7c4144bc80f815241c9dc42b', '9b241f431ba744e59c656fe8d2f0ca39', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8fdd46ec7a9f417ba9e84d25b58f839b', '9b241f431ba744e59c656fe8d2f0ca39', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3ade245497ed4752a54966b9a8d33844', '9b241f431ba744e59c656fe8d2f0ca39', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b8961086324a48709551539d69dfe949', '9b241f431ba744e59c656fe8d2f0ca39', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8f20ea5452bf413fbf3bf59d487545cb', '9b241f431ba744e59c656fe8d2f0ca39', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f4ba69dfa27549b9acd71e133e4bf8d2', 'c61052cc291440998f22b2d980c6a1a1', 'solr.shards', '1', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c6dfa84eb6974e8fb79bf16b37539549', 'c61052cc291440998f22b2d980c6a1a1', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a7833bdb21af45d8bc23f4094286d454', 'c61052cc291440998f22b2d980c6a1a1', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e75f7f97b0c64da89cf44f6bba0e2a55', 'c61052cc291440998f22b2d980c6a1a1', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0574016e38aa40edae26802618eea721', 'c61052cc291440998f22b2d980c6a1a1', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cb1e31283f1445ef88103fb91160667d', 'c61052cc291440998f22b2d980c6a1a1', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('eb7f9f030bac48068ab626c9a4b1ebab', 'c61052cc291440998f22b2d980c6a1a1', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('202704a13c164cada17f4d8e806c3fad', 'c61052cc291440998f22b2d980c6a1a1', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('215ffc9040694aef9e3fb7f2e6285964', 'c61052cc291440998f22b2d980c6a1a1', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('43e737b04bf3420fbb0fc2f0999c4c10', 'c61052cc291440998f22b2d980c6a1a1', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3784c7575c894f85987e7e27bfe2ce68', 'c61052cc291440998f22b2d980c6a1a1', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e457c6199c4844e28b9a6ba484ca0cee', 'aaabe7f7a0cf412cbbe8aa0f670ee5ee', 'hbase.zk.quorum', 'hadoop02,hadoop03', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('98c075331fd14fb5b470c3ab73a48a8a', 'aaabe7f7a0cf412cbbe8aa0f670ee5ee', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('827949fa16fe4bb18c52e42399d6d13d', 'e55f961071f34909a76bdcc580bfae5a', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'Hive 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6b1033dbfd414e91b5085a8b31fd712b', 'e55f961071f34909a76bdcc580bfae5a', 'jdbc.url', 'jdbc:hive2://192.168.31.100:10000/default', 'Hive JDBC URL，如：jdbc:hive2://${ip}:${port}/default');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('801c5c13a84545829888a961affbe00f', 'e55f961071f34909a76bdcc580bfae5a', 'username', 'root', 'Hive 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7b14f5bc7c5d423cbb1cafdb9f8c289a', 'e55f961071f34909a76bdcc580bfae5a', 'password', '1234', 'Hive 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8fa1c19b5dd946d2a74eab8aca9efe35', 'e55f961071f34909a76bdcc580bfae5a', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8a0087376fee453c9ffea601d70be6b3', 'e55f961071f34909a76bdcc580bfae5a', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0870824570454447846d0b1e8ed997be', 'e55f961071f34909a76bdcc580bfae5a', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ffa535032a334cebb1112dfc1a6c5852', 'e55f961071f34909a76bdcc580bfae5a', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fd2640fd4ab346329c64bfe23cee7268', 'e55f961071f34909a76bdcc580bfae5a', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('659408eba481461eb182f1f2fa1ee531', 'e55f961071f34909a76bdcc580bfae5a', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('315a80b902e44332a00345ec61ec9a12', 'e55f961071f34909a76bdcc580bfae5a', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d5a0e8807e304506bd96b6d415667ee8', 'e55f961071f34909a76bdcc580bfae5a', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d47047a782f545ecb7fc0a8e2db39787', 'e55f961071f34909a76bdcc580bfae5a', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('eee6d9ab96be46e289473324d4af026b', 'e55f961071f34909a76bdcc580bfae5a', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('16b34efefa30494cb913e47fd9c676ea', 'e55f961071f34909a76bdcc580bfae5a', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3d313885d6264bd3bdee08a4a509d052', 'e55f961071f34909a76bdcc580bfae5a', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7e030dba46164953ab586fafe884c4e2', '68da2d155cb449fd9891bc4da064bc04', 'jdbc.url', 'jdbc:hive2://192.168.1.61:21050', 'Impala JDBC URL，如：无密码jdbc:hive2://${ip}:${port}/;auth=noSasl  有密码jdbc:hive2://${ip}:${port}/');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('21d01c2931d74dd5a0523713906b5e58', '68da2d155cb449fd9891bc4da064bc04', 'username', 'hive', 'Impala 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a3010a3aa6cd485eaa367378d83ac9bc', '68da2d155cb449fd9891bc4da064bc04', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'Impala 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ce1de10910dd4d3d9517dabb2e2647b6', '68da2d155cb449fd9891bc4da064bc04', 'password', '111111', 'Impala 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b6f9b027f63047f6b3bb8d28d819def6', '694548b88ea548e48fb42e56f8b13b62', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4d36827782ad4656a96ab417b67c9f36', '694548b88ea548e48fb42e56f8b13b62', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7965453848fc48f58007a46686e89fe7', 'b47f6a09b6484260a3edbd3cc270434f', 'hbase.region.num', '1', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2e8e222c767f4440b9ba024e463bf9d2', 'b47f6a09b6484260a3edbd3cc270434f', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2881820ef6cb47f381d28fd10c82b907', 'b47f6a09b6484260a3edbd3cc270434f', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9647fa5e94974ce18f52dc39fe5400d7', 'b47f6a09b6484260a3edbd3cc270434f', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('72bf3c90795f49e99d77c8ab3d9982bf', 'b47f6a09b6484260a3edbd3cc270434f', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fee58ee9c368455fa90d4558f7ed3a3e', 'b47f6a09b6484260a3edbd3cc270434f', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ec546dd6d6ae44188f7f84396d923a26', 'b47f6a09b6484260a3edbd3cc270434f', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3f29c6d85a6e4bdba6fc1b6aeaabc0b7', 'b47f6a09b6484260a3edbd3cc270434f', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b1d2c8a9ba7c4762a300c63e948e077f', '6e0ea73a30104632ae0fcaf012fb6812', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('75b08d19e5fb4102aa884a0f6cb8fa99', '6e0ea73a30104632ae0fcaf012fb6812', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('65429dca3ef54cf78f33a65e49bfffe5', '6e0ea73a30104632ae0fcaf012fb6812', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cba9550261764dd0a6d298f86ebc7644', '694548b88ea548e48fb42e56f8b13b62', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7ce61481add14a609338117976e5eaa3', '694548b88ea548e48fb42e56f8b13b62', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5db1fdc6bec64e1e8b5a954539536887', '694548b88ea548e48fb42e56f8b13b62', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3e4b13bceecb42b0aa5fe0414d57e437', '694548b88ea548e48fb42e56f8b13b62', 'hbase.method', 'table_att', 'HBase 方法');
commit;
prompt 300 records committed...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2abf18e16f96455ebe755b7318f9f79b', '694548b88ea548e48fb42e56f8b13b62', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9f5b6b91b46b4b7eb1e612b746993cbb', '694548b88ea548e48fb42e56f8b13b62', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('af875e2563aa45ad81316eca32f5c09e', '6e0ea73a30104632ae0fcaf012fb6812', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('668903c22f464740bbcbbbcd2e26115d', '6e0ea73a30104632ae0fcaf012fb6812', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('270ae6d0447c4bac9ede008f6ff59e14', '6e0ea73a30104632ae0fcaf012fb6812', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7380232f909046a996a7108631c8da90', '6e0ea73a30104632ae0fcaf012fb6812', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8d40555731ab44d0812dcc934341202a', '6e0ea73a30104632ae0fcaf012fb6812', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e9c67dbef0d3406aa4e22a3ecd398c1c', '694548b88ea548e48fb42e56f8b13b62', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fcdb8c4b4efc4f2f9dcd00ac866be2a2', '694548b88ea548e48fb42e56f8b13b62', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('eafc143f6be94e6ebd0a5ee8ae21d0a7', '694548b88ea548e48fb42e56f8b13b62', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f15dc900f683440eb45698c2fbabada0', '6e0ea73a30104632ae0fcaf012fb6812', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cdb83a81a94a47daa9528cb0d650ad25', '6e0ea73a30104632ae0fcaf012fb6812', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c23b78d68a9e479da1e6bfa389bc8bd1', '6e0ea73a30104632ae0fcaf012fb6812', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6508997511c744e681511a81cd475151', '68da2d155cb449fd9891bc4da064bc04', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2fd69a4f32e94ac1b9f48d055a3641e0', '68da2d155cb449fd9891bc4da064bc04', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b9e6601e87f84fe89daf4534e9661d3e', '4c6b37a854ef466d959e4bb3248877ca', 'oracle.table.name', '测试表名', 'Oracle 表名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('11bbf859bfd84da8a5374a79efba0b2e', '4c6b37a854ef466d959e4bb3248877ca', 'oracle.database.name', '测试库名', 'Oracle 库名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6a59d71ddf0f426ebaf901f714340e90', '4c6b37a854ef466d959e4bb3248877ca', 'oracle.sql', 'select 1 from dual', 'Oracle SQL语句');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('772e9cf3ceaf48a69e684cd05d67f8a3', '1820799ba8b54ff4a0563f019ec0c436', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('40488cf54ee64e2880c717c2600e9d1b', '1820799ba8b54ff4a0563f019ec0c436', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('680fb387fb0e44a69cdc2764b85bb2b0', 'a684e3e668f0482da99f23914c5dfc32', 'hbase.zk.quorum', 'hadoop01,hadoop02,hadoop03', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('840b1126c0a74b82819eb658301f8318', '1820799ba8b54ff4a0563f019ec0c436', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2aafdabab1ae4c468144da34631c75f8', 'a684e3e668f0482da99f23914c5dfc32', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9ef509dbdac6453ab64dd72fd8b25035', 'a684e3e668f0482da99f23914c5dfc32', 'solr.servers', 'hadoop01:8983,hadoop02:8983,hadoop03:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('50e61c3d4c9e4de7a153b36dbb3844ef', 'a684e3e668f0482da99f23914c5dfc32', 'solr.url', 'hadoop01:2181,hadoop02:2181,hadoop03:2181', 'zookeeper地址、端口和目录，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ea0c8e2259d9487f8d7900fc441e097c', '8566fc8364b84a5baef71a50fc9788e1', 'abc', 'abc', 'abc');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('356ff1a9f7eb42eabe29f31ebdd06623', '1820799ba8b54ff4a0563f019ec0c436', 'hbase.region.num', '20', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('930f7d07be014267b2221ae8ac5766bc', '1820799ba8b54ff4a0563f019ec0c436', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ac78b2840ee245379e8836c37fa7b8c7', '1820799ba8b54ff4a0563f019ec0c436', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('738e269aaca84da1bcd7a159c4dddc6b', '1820799ba8b54ff4a0563f019ec0c436', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8123f8e67cbb407391f344821bd1e0d2', '1820799ba8b54ff4a0563f019ec0c436', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('369d6fed35b24048874d98239d8b12bf', '2775352589d84c4ea35c8db31df6271b', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('455305e623f94ba68805420a70c28315', '2775352589d84c4ea35c8db31df6271b', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c9c082ea85504a888a68de47f30b5f75', '94a9cff85f424b5883fcf066d3a48649', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2d660a48980b40fb85595a6aafd44f4b', '4fc8560df2c441509e347eb529fdae88', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('342eca036b5e4488bdfc91257971b072', '4fc8560df2c441509e347eb529fdae88', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ac8d0b2e730140158b0c69949c409361', '4fc8560df2c441509e347eb529fdae88', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('944ea6671e804a6a8ed1a225f9dda554', '7f79de21f3a44c2a9c66e79302f3f3c7', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ddaf03fedd5c40e6ad9a8d8d55ffc659', '7f79de21f3a44c2a9c66e79302f3f3c7', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b67dbccfcc18460ba8904e51e829adce', '7f79de21f3a44c2a9c66e79302f3f3c7', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('206bc7559b5f4ca69777ff2fe414e664', '2775352589d84c4ea35c8db31df6271b', 'solr.shards', '5', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3d4fd1cb8b714dbd9324bfa00f38c1e3', '94a9cff85f424b5883fcf066d3a48649', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('24229e09e5354a16bbe42ffb5dd3595a', '94a9cff85f424b5883fcf066d3a48649', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6316c19c6e7a447a9ce39e4c55d18e5f', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hbase.zk.quorum', '192.168.1.61,192.168.1.62,192.168.1.63', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('220b69c40fc54d199721c81c2cc27ac9', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c792574b992646e4a07803625749f357', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hbase.rpc.timeout', '5000', '一次RPC请求的超时时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3dd679a454e94a64b5892e85acca7d18', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hbase.client.retries.number', '3', '客户端重试最大次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('155e4b5cfb6c4866bfb16de87a980971', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hbase.client.pause', '100', '重试的休眠时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('10034a6f1ae9489ea6b0ff3c67ad78dd', 'e15a7e21200948c5ba2cc92fe3d4e339', 'zookeeper.recovery.retry', '3', 'zookeeper的重试次数（zk的重试总次数是：hbase.client.retries.number * zookeeper.recovery.retry）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f79b34c92f0c454a8b2f56d5446d2ffc', 'e15a7e21200948c5ba2cc92fe3d4e339', 'zookeeper.recovery.retry.intervalmill', '200', 'zookeeper重试的休眠时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1e34ea53e7b8481ea2a23d82b0268df4', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hbase.client.operation.timeout', '30000', 'HBase客户端发起一次数据操作直至得到响应之间总的超时时间，数据操作类型包括get、append、increment、delete、put等（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c1801fbf76b647688385a2bed35681c1', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hbase.regionserver.lease.period', '60000', 'scan操作超时时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('59591620fb084fd9bf894c3133642c10', '2f9f1efe3814428890b8eeb1f952b616', 'metadata.broker.list', '192.168.1.61:9092,192.168.1.62:9092,192.168.1.63:9092', 'Kafka集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9092,10.1.97.2:9092,10.1.97.3:9092');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ad787aabb8ab43b99c463531f915c99e', '2f9f1efe3814428890b8eeb1f952b616', 'zookeeper.connect', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181', 'zookeeper集群的IP和端口地址，多个地址用逗号分隔，如，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('885727453f37471580b1a8526f0cfe03', '2f9f1efe3814428890b8eeb1f952b616', 'zookeeper.session.timeout.ms', '5000', '连接zookeeper的session超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4ccc2b1020ea4335b6a132ffa3a7e2fb', '2f9f1efe3814428890b8eeb1f952b616', 'zookeeper.connection.timeout.ms', '6000', '户端连接zookeeper的最大超时时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('302c012be5af40cc95ca9a494ded4be9', '2f9f1efe3814428890b8eeb1f952b616', 'zookeeper.sync.time.ms', '2000', 'zookeeper同步时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('103ae599e48041d99a146e0e7252ce1b', '2f9f1efe3814428890b8eeb1f952b616', 'auto.commit.enable', 'true', '如果true,consumer定期地往zookeeper写入每个分区的offset');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3f4911465fd04ddf9dfaff3e658e8d6d', '2f9f1efe3814428890b8eeb1f952b616', 'auto.commit.interval.ms', '60000', '消费者向zookeeper发送offset的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d38499a30fdc4130a630e541b2d9857a', '2f9f1efe3814428890b8eeb1f952b616', 'rebalance.retries.max', '10', 'rebalance时的最大尝试次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('123e5b28d88248859a69b03715c81283', '2f9f1efe3814428890b8eeb1f952b616', 'rebalance.backoff.ms', '2000', '平衡补偿重试间隔时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a4c9c8280b1542349076040f18f15b65', '2f9f1efe3814428890b8eeb1f952b616', 'auto.offset.reset', 'largest', 'offset初始化或者达到上线时的处理方式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4a7654c425964c818c9d4b82a1b42bab', '0573547746164d8babab2edfcdfa4015', 'jdbc.url', 'jdbc:mysql://goupwith.mysql.rds.aliyuncs.com:3306/edh', 'mysql JDBC URL，如：jdbc:mysql://${ip}:${port}');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6c0a90917c5042c784808d58fa56e739', '0573547746164d8babab2edfcdfa4015', 'username', 'edh', 'mysql 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('498b55a45fef4ac0ba7c62cf8d4f46eb', '0573547746164d8babab2edfcdfa4015', 'password', 'edh159357', 'mysql 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e924741fcab04577ac04feb87e3c0519', '0573547746164d8babab2edfcdfa4015', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('069ff5db3b3549deb90b32125d3cb6dc', '0573547746164d8babab2edfcdfa4015', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e8161f9909544b61abab58e9c15a9596', '0573547746164d8babab2edfcdfa4015', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0c2b15fffa6841bb86a6d503c7d4270c', '0573547746164d8babab2edfcdfa4015', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b725c9d48b764967ba8dc6d280ae2177', '0573547746164d8babab2edfcdfa4015', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('199a30310d104e8b8b05da399e8ed46a', '6968dda4247f4342ad3bad09442670f9', 'hbase.zk.quorum', 'hadoop02,hadoop03', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a2e1db2c33c14aaab3921fcc36fb4100', '6968dda4247f4342ad3bad09442670f9', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5bf85fabe1c746ee9bd9985501ddec4e', '6968dda4247f4342ad3bad09442670f9', 'solr.servers', 'hadoop01:8983,hadoop02:8983,hadoop03:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e20c31b6576447a9b806570b3e546a53', '6968dda4247f4342ad3bad09442670f9', 'solr.url', 'hadoop02:2181,hadoop03:2181', 'zookeeper地址、端口和目录，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d6d38050dec4416a8ea9046257ef34bb', '0573547746164d8babab2edfcdfa4015', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8e2957dca57c455b9b081b35c69943d2', '0573547746164d8babab2edfcdfa4015', 'driver.class', 'com.mysql.jdbc.Driver', 'mysql 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b56c42bf982c4842ba2dfad9a8825772', '0573547746164d8babab2edfcdfa4015', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d19e75ad7995477098b40bc6d76842a0', '0573547746164d8babab2edfcdfa4015', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('35cce73d691f48f7bd77c87597d2bc5c', '0573547746164d8babab2edfcdfa4015', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('81b39128c30a4bcb8a8e9b68267b2215', '0573547746164d8babab2edfcdfa4015', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6eee782b270d44afbbbff5678666758a', '0573547746164d8babab2edfcdfa4015', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2ac27dcb68544a9fa8a78e07af84184b', '0573547746164d8babab2edfcdfa4015', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6664f81074fb461999438f8fa476c77b', '040b53fddcc248578c49ec1abd253404', 'driver.class', 'oracle.jdbc.OracleDriver', 'oracle 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cee6a28931494e37b109c4a84123b9d6', '040b53fddcc248578c49ec1abd253404', 'jdbc.url', 'jdbc:oracle:thin:@dev.goupwith.com:1521/zctggl', 'oracle JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c04b55cfa0e0458fb4720fa34089b86a', '040b53fddcc248578c49ec1abd253404', 'username', 'dcp', 'oracle 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0273e77c38674388887e02bc232a88a5', '040b53fddcc248578c49ec1abd253404', 'password', 'shhex20170314', 'oracle 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b44bd5f58b5a4dd8a34902022a9c17c0', '040b53fddcc248578c49ec1abd253404', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c51fb7de2db94191be2ec81d3193c6eb', '040b53fddcc248578c49ec1abd253404', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c641f679817f4a96bd0bac5016dbb65b', '040b53fddcc248578c49ec1abd253404', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f3364883987840e0b9bf93eccbc5aef1', '040b53fddcc248578c49ec1abd253404', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a9f62b25649e4f62a6eab02d0a18ad4f', '040b53fddcc248578c49ec1abd253404', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6b8b082c5d6c497a9827dfbd1dc51bba', '040b53fddcc248578c49ec1abd253404', 'validation.query', 'select 1 from dual', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('00351b1ae3654759968ea25ce43a94f2', '040b53fddcc248578c49ec1abd253404', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f20a5d926e964dc1829a01390dd4e4af', '040b53fddcc248578c49ec1abd253404', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cfec367a6d414f808e2d8e77537d38c7', '040b53fddcc248578c49ec1abd253404', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1cc0b6853cb846ba8e45f05a22e32f0e', '040b53fddcc248578c49ec1abd253404', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('59d175b3fb7c45e9bc12ac318253fb92', '040b53fddcc248578c49ec1abd253404', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6ffaf72f6a8e4c17b23a072bfb51c3be', '040b53fddcc248578c49ec1abd253404', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('81f8d732e0bc46e09f7a0f628ec4bfaa', '9bc6fbcc659746609aff55ef3555590b', 'hbase.region.num', '10', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('384ef0fa5b744be39aacb5fce752ac81', '9bc6fbcc659746609aff55ef3555590b', 'hbase.compression', 'snappy', 'HBase 压缩格式');
commit;
prompt 400 records committed...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d3a59534162d4013a174f6d60f457b3b', '9bc6fbcc659746609aff55ef3555590b', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('003aa13f64ba4309a06b84d49978dc55', '9bc6fbcc659746609aff55ef3555590b', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('70c9dea60d8246e6a3b45ada97ac605a', '9bc6fbcc659746609aff55ef3555590b', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a7ab6eadb1854a3a968a5f5ca56001d2', '9bc6fbcc659746609aff55ef3555590b', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e533e61ab2b2400ca4e409a3e7bcdc84', '9bc6fbcc659746609aff55ef3555590b', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('41d05d846c8e47e2b5512e16516bf525', '9bc6fbcc659746609aff55ef3555590b', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1edcc02c55974683bc8d55b77cbf642e', '68da2d155cb449fd9891bc4da064bc04', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c30d153367304993885983e172c12d20', '68da2d155cb449fd9891bc4da064bc04', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cf107cfb7be540168bef3887f26aec25', '68da2d155cb449fd9891bc4da064bc04', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ba9f7bef7ddc4095bd151d23c8847af5', '68da2d155cb449fd9891bc4da064bc04', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d933aa2b9fce40929c1747a1083f07e1', '68da2d155cb449fd9891bc4da064bc04', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8ff2f2e11f864c078cfe0c7b0b0761b0', '68da2d155cb449fd9891bc4da064bc04', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('205ac9a47aa64d7eac3b4bfa06f38d43', '68da2d155cb449fd9891bc4da064bc04', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('09a9fcd4f3384d2dbc26bdd202523be2', '68da2d155cb449fd9891bc4da064bc04', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8256d30b93e24bc691233ad2065d7045', '68da2d155cb449fd9891bc4da064bc04', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c76dbef56de04ee0a12154405e9f9116', '68da2d155cb449fd9891bc4da064bc04', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('789b7b7bf9eb433aa7495ca6126b7ae4', '68da2d155cb449fd9891bc4da064bc04', 'max.connected.size', '2000', null);
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6b8c173827da4ba097c8beca82721842', 'b495f039aea74c419e450c1af797959f', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ac99367c46f7422fb857bfcc3d83b002', 'b495f039aea74c419e450c1af797959f', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ea1de71998ba4a5895a49c9c5b04eceb', '4ba6c197b4314b7e9e403fde09f75482', 'oracle.table.name', 'Oracle 表名', '测试表名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1df9adf9e55b47368ea000254ab901bc', '4ba6c197b4314b7e9e403fde09f75482', 'oracle.database.name', 'Oracle 库名', '测试库名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2600791642d14fbb88489f2d4a7cc2c2', '4ba6c197b4314b7e9e403fde09f75482', 'oracle.sql', 'Oracle SQL语句', 'select 1 from dual');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4df7b61abaa94a62b45733bbd6f029d9', 'b495f039aea74c419e450c1af797959f', 'solr.shards', '3', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ad2ebc81a15b4e4f9f19aafb47348fff', '4de1700c917940b0ba2f11681ae658c3', 'hbase.rpc.timeout', '5000', '一次RPC请求的超时时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('31d0df6989714099a7b4a70cc4304496', '4de1700c917940b0ba2f11681ae658c3', 'hbase.client.retries.number', '3', '客户端重试最大次数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c58e848ce9654d48934b30b1d66b962b', '4de1700c917940b0ba2f11681ae658c3', 'hbase.client.pause', '100', '重试的休眠时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0108a5303c1e4b1288e70e507a27919c', '4de1700c917940b0ba2f11681ae658c3', 'zookeeper.recovery.retry', '3', 'zookeeper的重试次数（zk的重试总次数是：hbase.client.retries.number * zookeeper.recovery.retry）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2435dfb928524a71b6d042bd84774153', '4de1700c917940b0ba2f11681ae658c3', 'zookeeper.recovery.retry.intervalmill', '200', 'zookeeper重试的休眠时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('360d5b642d194410be19446d065bd8ca', '4de1700c917940b0ba2f11681ae658c3', 'hbase.client.operation.timeout', '30000', 'HBase客户端发起一次数据操作直至得到响应之间总的超时时间，数据操作类型包括get、append、increment、delete、put等（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('05f4d4a4f67143ff878a34cbf1c80e95', '4de1700c917940b0ba2f11681ae658c3', 'hbase.regionserver.lease.period', '60000', 'scan操作超时时间（毫秒）');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9310f4bf406b44539979e2c710e4e17f', '4de1700c917940b0ba2f11681ae658c3', 'solr.servers', '192.168.1.61:8983,192.168.1.62:8983,192.168.1.63:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('bcabce15dd374edeabef56b6467255d5', '4de1700c917940b0ba2f11681ae658c3', 'solr.url', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181/solr', 'Solr的zookeeper地址、端口和目录，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8c5a006e244f443193ecc4c6b76ace28', '4de1700c917940b0ba2f11681ae658c3', 'hbase.zk.quorum', '192.168.1.61,192.168.1.62,192.168.1.63', 'HBase的Zookeeper的集群IP，多个IP用逗号分隔，如：10.1.97.1,10.1.97.2,10.1.97.3');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a438a744d4bf467dbd800e277bd9ae4e', 'ce5c958b77294337baec27e418e5075e', 'solr.servers', '192.168.1.61:8983,192.168.1.62:8983,192.168.1.63:8983', 'Solr集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:8983,10.1.97.2:8983,10.1.97.3:8983');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('04104ec1822849d1825a450456fc4c59', '493008fd55294957a8d7c3edb0ba503a', 'solr.shards', '3', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('097882ba2f20438eb9732ed717d17a17', '493008fd55294957a8d7c3edb0ba503a', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ed14c98f96784d2e8a0eaa6ea3c3c796', '493008fd55294957a8d7c3edb0ba503a', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('05f1c1de99074932abd880b8256ea1d7', '493008fd55294957a8d7c3edb0ba503a', 'hbase.region.num', '100', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4a206add3edb41738fa36b55fd2d0d12', '493008fd55294957a8d7c3edb0ba503a', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('76fd308753d245d0ac7bda5df5fca416', '493008fd55294957a8d7c3edb0ba503a', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c23fd2197f62484685cb7b8ba208c7d4', '493008fd55294957a8d7c3edb0ba503a', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('33696220b7764d9e93d714874714a690', '493008fd55294957a8d7c3edb0ba503a', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('567de4f62e074ca880e0887885689e5e', '493008fd55294957a8d7c3edb0ba503a', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('bf609275f97946d2970774cb9a850b76', '493008fd55294957a8d7c3edb0ba503a', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('452ba9a3948a45e8b94cb5042b8579e5', '493008fd55294957a8d7c3edb0ba503a', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('08ced7a2c06848a88f4dfb0bdff58c26', '39616843d72645deac179aa8d728fe8d', 'solr.shards', '3', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f2e2db6daf3049d6a3849cf322d2619c', '39616843d72645deac179aa8d728fe8d', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fbde50d78a42441d821215bc8dfb07b8', '39616843d72645deac179aa8d728fe8d', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3aaf4601f4b242098147a78c5691c2e7', '4de1700c917940b0ba2f11681ae658c3', 'hbase.zk.port', '2181', 'HBase的Zookeeper的端口，如：2181');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9fcf39ab3fbf47a69b70a4b7ca9e44d3', 'ce5c958b77294337baec27e418e5075e', 'solr.url', '192.168.1.61:2181,192.168.1.62:2181,192.168.1.63:2181/solr', 'zookeeper地址、端口和目录，如：10.1.97.1:2181,10.1.97.2:2181,10.1.97.3:2181/solr');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7ec82c84f4d94dbfa6453597e453f13d', 'd6301dd94c934c6e84912623f86362a5', 'hive.database.name', 'sdsd', 'Hvie 库名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a4e79f89983840c68498123fed3a620a', 'd6301dd94c934c6e84912623f86362a5', 'hive.table.name', 'weq', 'Hive 表名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('361e451d372c40f2a17d7bf7b08b85d2', 'd6301dd94c934c6e84912623f86362a5', 'hive.sql', 'sds', 'Hive SQL语句');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8796e26333704f71bc9b35bf5ce1d194', 'caff9907ab8e4e909b67ed4af252a81a', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0b6e128e8c844e00bfa402e1520b4bf0', 'caff9907ab8e4e909b67ed4af252a81a', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('98befc73382e47c6bb3b8d2484030965', 'caff9907ab8e4e909b67ed4af252a81a', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a3e175e330034060950c9af3fd5be2d1', 'caff9907ab8e4e909b67ed4af252a81a', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1eca7ac05a2740cfa546c51a2a795f14', 'caff9907ab8e4e909b67ed4af252a81a', 'max.data.size', '65535', '最大数据返回条数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('82ad53c602b24c44ba04b2c60ad77773', '9ba52403cd154f8e8546c80312d48570', 'solr.shards', '3', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fc7dc79f37e34a4d936285b4f49c5868', '9ba52403cd154f8e8546c80312d48570', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ba6400cb5aa0430b9eb177593aa106f5', '9ba52403cd154f8e8546c80312d48570', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('76c29552c68d42e58348ff8791f65eb1', 'a9ca00a444fe4e6d97cef4f702c09afa', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4e8b9c17b04e4d30b30f330ca08de860', 'a9ca00a444fe4e6d97cef4f702c09afa', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('778dcdda43ee4fb3af4ada1fe646dc72', 'a9ca00a444fe4e6d97cef4f702c09afa', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9b9ba86d562246e4a29a1f0a0dc9742a', '15c2d37df5e6434f9183b7c0e1f20da0', 'driver.class', 'com.mysql.jdbc.Driver', 'mysql 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5fa3dfd848f14d739185e8bd1c4eecf8', '15c2d37df5e6434f9183b7c0e1f20da0', 'jdbc.url', 'jdbc:mysql://192.168.1.151:3306', 'mysql JDBC URL，如：jdbc:mysql://${ip}:${port}');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4f4d7c64870743aea3d40a311c94eb6f', '15c2d37df5e6434f9183b7c0e1f20da0', 'username', 'root', 'mysql 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9bd3fa4fbc604efe8cb6cad094084cd0', '15c2d37df5e6434f9183b7c0e1f20da0', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('eeb80464faf04c1ea4c6423d62641c85', '15c2d37df5e6434f9183b7c0e1f20da0', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2ee4b5ad9fc64b1b926e27f138b527a2', '15c2d37df5e6434f9183b7c0e1f20da0', 'password', '123456', 'mysql 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6cb7e09eb35b4d0881a3264ed08c73d1', '15c2d37df5e6434f9183b7c0e1f20da0', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9e216fac22e74caa8891aa72f7529802', 'e55951ed203341ea83e079314e825d3d', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'Hive 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d5d951014780414e8587cb66774fac88', 'e55951ed203341ea83e079314e825d3d', 'jdbc.url', 'jdbc:hive2://192.168.1.61:21050', 'Hive JDBC URL，如：jdbc:hive2://${ip}:${port}/default');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6ceafd78b2aa48dab06fabeab2972c61', 'e55951ed203341ea83e079314e825d3d', 'username', 'hive', 'Hive 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('52c10d840bc84076bd927b37af03c357', 'e55951ed203341ea83e079314e825d3d', 'password', '111111', 'Hive 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('190b50ebfa254399a1d773311df9095c', 'e55951ed203341ea83e079314e825d3d', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2ea1d4a8ac5d4d1fa2b3d2d639ad39b7', 'e55951ed203341ea83e079314e825d3d', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('eeed07f5f33a439794f6a7954d8235ed', 'e55951ed203341ea83e079314e825d3d', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('22438155f0af49319afd30a9517032d9', 'e55951ed203341ea83e079314e825d3d', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4c1abe4940374903b281b44de8fa27eb', 'e55951ed203341ea83e079314e825d3d', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('adba1b7b14e4453aaa47499d313c36c0', 'e55951ed203341ea83e079314e825d3d', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8c49528bd44a406499a458d61afda97e', 'e55951ed203341ea83e079314e825d3d', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('51bfbc0b10104efd9a0a3e44c486e477', 'e55951ed203341ea83e079314e825d3d', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('eb4e72ada5fe480392867191c7a660a4', 'e55951ed203341ea83e079314e825d3d', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d2e4cd29fd5a4103a994bb49e9a52709', 'e55951ed203341ea83e079314e825d3d', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e073b9bab6df446b8c885f8604d89a59', 'e55951ed203341ea83e079314e825d3d', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c76e9eb1b3c84a3691d2a012756921a2', 'e55951ed203341ea83e079314e825d3d', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('afe4ca49f04e406185437c48f1abe8ac', 'e4962a47cb9644afac76cf90c263052b', 'mysql.database.name', 'practise', 'Mysql 库名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0c0d064dcbbf45aab75f3646317c5461', 'e4962a47cb9644afac76cf90c263052b', 'mysql.table.name', 'person', 'Mysql 表名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e04f2decaac241cb9787b1b89eab35c0', '15c2d37df5e6434f9183b7c0e1f20da0', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9f251e8100c648579dbab2a4b6b45bef', '15c2d37df5e6434f9183b7c0e1f20da0', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e56bd7747fe14c20a06aeb36b14f7fdc', '15c2d37df5e6434f9183b7c0e1f20da0', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('18d83574c96c47919fc9a3e38bc4d535', '15c2d37df5e6434f9183b7c0e1f20da0', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a26e731b3b98434ebfac00e1c1861093', '15c2d37df5e6434f9183b7c0e1f20da0', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ada774690b134ba0a4c520d575bd2889', '15c2d37df5e6434f9183b7c0e1f20da0', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a5a4466a6e6f4fd5b40e3a3e360df2e6', '15c2d37df5e6434f9183b7c0e1f20da0', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1cbe2e2a84724a699e0e7267e6550a59', '15c2d37df5e6434f9183b7c0e1f20da0', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b34e4f3d49a845f6ba07585f1d5b3a82', '15c2d37df5e6434f9183b7c0e1f20da0', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3809a8e30d6543c2bc08d8f2ece3cc3c', '1522f3b690f0408f9eb94925c50f6392', 'driver.class', 'com.mysql.jdbc.Driver', 'mysql 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3d3b02c8dd9c48e8841e0a36bd779b37', '1522f3b690f0408f9eb94925c50f6392', 'jdbc.url', 'jdbc:mysql://localhost:3306', 'mysql JDBC URL，如：jdbc:mysql://${ip}:${port}');
commit;
prompt 500 records committed...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('07ccdf4dd8f04885a0642fc463380df9', '1522f3b690f0408f9eb94925c50f6392', 'username', 'root', 'mysql 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6a551f27444b4a5fbec4a04160470ee1', '1522f3b690f0408f9eb94925c50f6392', 'password', '123456', 'mysql 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('88ccc24bc6094d2dab964c70f3509fb2', '1522f3b690f0408f9eb94925c50f6392', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f86a21bc4bd142d59cc5cd77cf85b342', '1522f3b690f0408f9eb94925c50f6392', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9801fe0360ea427aba67332191bc3b8b', '1522f3b690f0408f9eb94925c50f6392', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c73f1c9e6e9846ebb25553bfc7beb197', '1522f3b690f0408f9eb94925c50f6392', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2d53c7e2c8a840d49e1c809833801fb0', '1522f3b690f0408f9eb94925c50f6392', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c0f6498c9f9c4cbc9d5e425f967728e9', '1522f3b690f0408f9eb94925c50f6392', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0abc3875905740a6a3db4bac3708af22', '1522f3b690f0408f9eb94925c50f6392', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6b2da037ed0a4ec5b51001f70ca354fe', '1522f3b690f0408f9eb94925c50f6392', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9c30ef1b25064c4caf5fac8485f037f5', '1522f3b690f0408f9eb94925c50f6392', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a5ca87f8ae224c8f98e194ff2393da01', '1522f3b690f0408f9eb94925c50f6392', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('829cf08c846f442283300a4d2f2a7340', '1522f3b690f0408f9eb94925c50f6392', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('598a2e002d67440694c7cf38f62c86a7', '1522f3b690f0408f9eb94925c50f6392', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ceb1a8d94dab41f3b4b8c5997e1475e4', '7d056baacde14047927f7067d96823cb', 'mysql.database.name', 'practise', 'Mysql 库名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1ee16f37f2d24eaf880921347e518879', '7d056baacde14047927f7067d96823cb', 'mysql.table.name', 'person', 'Mysql 表名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9e7fb9dcdbee48359ed6ed0b3ecb15a2', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a31ad9b173d640d0b3f00ea183011f33', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1f0f895171e8480e9a693864aaf75a49', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6a54b0bd898e4ebfa0a52b3943d01d12', 'fbe6a87eb7764a7b83c3f741bce853c8', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c5b6de03424b48a6a3d01617af3d0cb2', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('84c8bda00f4445aba6b6ef69b61c552f', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('376a35be0b93484bb190c2f132d71537', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('91b199080abb4b0d88ed7f97f560b6a2', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9638824357744d0293fe9f4369334e04', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9868c10a92e4426dbaf44c5fa6eb2e92', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('027f14686e4b40d3b850016f9264ed95', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('bed4978001fd43b9b9c6ad4baee920f6', 'd45fb860e55c4a10b08bb7a2744b1d7d', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4f3a1351b89c4208aaf9ded32de3350d', 'fbe6a87eb7764a7b83c3f741bce853c8', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a41052a791a846b09ae2ef11a903f242', 'fbe6a87eb7764a7b83c3f741bce853c8', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6e0991dd95e241368deb1f0927031c8d', 'fbe6a87eb7764a7b83c3f741bce853c8', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f8631ba3c98e4495ba24b0b1f4c3a089', 'fbe6a87eb7764a7b83c3f741bce853c8', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('44adc7e5dd08401f8a877d344541d7e2', 'fbe6a87eb7764a7b83c3f741bce853c8', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('97f2076e9d1644d49187f1cd2a5dbb19', 'fbe6a87eb7764a7b83c3f741bce853c8', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c6491cdfa36e41c78132d03e71800a2d', 'fbe6a87eb7764a7b83c3f741bce853c8', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('679e622c7991474fb9c42442bb49f7fc', 'fbe6a87eb7764a7b83c3f741bce853c8', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('37d8e93096824def8bee42db7b5802c5', 'fbe6a87eb7764a7b83c3f741bce853c8', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5b8a549c997c491dbaa8fc3c9ec119a9', 'fbe6a87eb7764a7b83c3f741bce853c8', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a2d6618a136d484e8f84280fbadf8916', 'dd2a72729f6642a3a247af5d2cea7345', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fb64cf1dbddd4437a6b753c89d4a5a75', 'dd2a72729f6642a3a247af5d2cea7345', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('48f74a58a9de4a54a4ef1d347191590d', '8300a81d356443f29a01d12b0abaee54', 'elasticsearch.servers', '192.168.125.101:9200,192.168.125.102:9200,192.168.125.103:9200', 'elasticsearch集群的IP和端口地址，多个地址用逗号分隔，如：10.1.97.1:9200,10.1.97.2:9200,10.1.97.3:9200');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fd12ff170ee848648a43a17672961881', '8300a81d356443f29a01d12b0abaee54', 'solr.max.data.size', '65535', '最大返回数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b015b615ce0b43a6a3aebfe76ef25bbd', '9bf3e555bba9413f9e494bbf5c22e071', 'database.name', 'default', '库名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a38712d231aa4a5db1312d2171110036', '9bf3e555bba9413f9e494bbf5c22e071', 'table.name', 'solr_handler_test1', '表名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('eadce47b965f456284c6def4ec5e21b2', 'f60ddab56f56487eacaed6f3c6d02709', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5c24d981e5ce49889304ee877c6c18ce', 'f60ddab56f56487eacaed6f3c6d02709', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1d2bc4aa45944c8eb83d7a196ec0589d', 'f60ddab56f56487eacaed6f3c6d02709', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('eb903da1cf4743348b471f2ca5d99af4', 'f60ddab56f56487eacaed6f3c6d02709', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2046e2860b704fdd8576ab0142359a56', 'f60ddab56f56487eacaed6f3c6d02709', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fff923098ed340f5a177e8ae34cf0764', 'f60ddab56f56487eacaed6f3c6d02709', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5e931dc7aa7d4e81a88c0bd0650805c5', 'f60ddab56f56487eacaed6f3c6d02709', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f74b222949ed44618e9a5c5ebaaab250', 'f60ddab56f56487eacaed6f3c6d02709', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d1aea55511ec48cd8a54bb2bdb77735d', 'f60ddab56f56487eacaed6f3c6d02709', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b17c6ec13e934ec2988cdabdcb774830', 'f60ddab56f56487eacaed6f3c6d02709', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9d26ffd9616645a3a5b70092f8ab0a89', 'f60ddab56f56487eacaed6f3c6d02709', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ea677dc8e4054dd081715294201e21a1', 'eff3494b2b1d4232aea90d006ebb664e', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('947d2501a8ce4e57b2f6a96d2156782a', 'eff3494b2b1d4232aea90d006ebb664e', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('45e3a4d8742444a9bf5fd1405fa48baf', 'eff3494b2b1d4232aea90d006ebb664e', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0081ee1b692d4ec49df12ec14b5dc50c', 'eff3494b2b1d4232aea90d006ebb664e', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('46a3b1042e6f4d6499983b2ad28382ba', 'eff3494b2b1d4232aea90d006ebb664e', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('6086c5f176f64ea6970ba6996894d227', 'eff3494b2b1d4232aea90d006ebb664e', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8eb4850dc8cd40ecb7dd2bcfe639b137', 'eff3494b2b1d4232aea90d006ebb664e', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('52f7c296252c4d4198d05104847b2030', 'eff3494b2b1d4232aea90d006ebb664e', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b22f963236d4447da22968ddc9aec207', 'eff3494b2b1d4232aea90d006ebb664e', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ca88b336dc4547b88055b54a62f8831f', 'eff3494b2b1d4232aea90d006ebb664e', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('56fd0dbd439b414d8ee64e9c4008a7cf', 'eff3494b2b1d4232aea90d006ebb664e', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('42fb4bc78bf34b238331c1af1213ed70', 'dd2a72729f6642a3a247af5d2cea7345', 'solr.shards', '3', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('abae61a5b89a4c9f982e4c83ede2e9df', '04b039b92408452b8cb7f567cc4def73', 'solr.shards', '1', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('767180d5bcbc41afb1b28e3a73f53f97', '04b039b92408452b8cb7f567cc4def73', 'solr.replicas', '1', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cf6c8c04a65f4cde8c0b836bf51d481a', '04b039b92408452b8cb7f567cc4def73', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('dc0f981d91c347378f7273cf5275e69b', '04b039b92408452b8cb7f567cc4def73', 'hbase.region.num', '1', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ca65b346fe794fb5abacb4d7f09da4a6', '04b039b92408452b8cb7f567cc4def73', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4340b2a7a4414096a3a3c6f2d4fd531d', '04b039b92408452b8cb7f567cc4def73', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e48b4f219acf4a339f1e8290e59454c8', '04b039b92408452b8cb7f567cc4def73', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d871e3ca45cf41709b3999506c95ebf3', '04b039b92408452b8cb7f567cc4def73', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1af16cfaa7104d6183faba7aaf05e6e1', '04b039b92408452b8cb7f567cc4def73', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('682e1e90f4124e7caf87ffe1c2a960c5', '04b039b92408452b8cb7f567cc4def73', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b99f858f240946ddb8a39aed808bb815', '04b039b92408452b8cb7f567cc4def73', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('aa321d863617423780552580b14f63ec', '52f1454e2078459b805db48f9dc97408', 'abc', 'abc', 'abc');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0e88d7761f564b4d9377206499255658', '0a8268ebb73a47bb8e500b4577434313', 'jdbc.url', 'jdbc:hive2://192.168.1.61:10000', 'Impala JDBC URL，如：无密码jdbc:hive2://${ip}:${port}/;auth=noSasl  有密码jdbc:hive2://${ip}:${port}/');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4b9dec48145e43648a1258b61181dd45', '0a8268ebb73a47bb8e500b4577434313', 'username', 'hive', 'Impala 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5ef8c886dfcc49d7ac05712de00cfe6e', '0a8268ebb73a47bb8e500b4577434313', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'Impala 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d41c7754e7fb400d8a8d232b632fb1f4', '0a8268ebb73a47bb8e500b4577434313', 'password', '111111', 'Impala 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3586f1bd01cc41088d13dae3047879a4', '3cc9eb59343e4a39893774df6519951a', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2025e55e39ec4428bcaf56e9c812b0ea', '3cc9eb59343e4a39893774df6519951a', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('abee5747d8984ace85122bdabdee96f7', '3cc9eb59343e4a39893774df6519951a', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f7a3c72729e247c5bdf9a3447b50b03a', '3cc9eb59343e4a39893774df6519951a', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('741b36081f2a45dbb688d5a7fd55db59', '3cc9eb59343e4a39893774df6519951a', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('156f71d3174f403398b820af370ab1fc', '3cc9eb59343e4a39893774df6519951a', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ca54d9666636454289b6430001a17862', '3cc9eb59343e4a39893774df6519951a', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('da1c9ebe44674aabaad8ad44e06fc59b', '3cc9eb59343e4a39893774df6519951a', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('59043aa6f0064a05b196640b4ad1a8f2', '3cc9eb59343e4a39893774df6519951a', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1312ee1d233e48f5a601618f1eb3093c', '3cc9eb59343e4a39893774df6519951a', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cc93629428504c65bf0154a945f9b22e', '3cc9eb59343e4a39893774df6519951a', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8e2280ff05a44038b7932f2ab30a564d', '0a8268ebb73a47bb8e500b4577434313', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ec0f2c96987e43e5bfadf0262bded86e', '0a8268ebb73a47bb8e500b4577434313', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('59747d5e3de4462b888d7be66668c766', '0a8268ebb73a47bb8e500b4577434313', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('7fc5766b51b6464181bd74a9dfc0ca81', '0a8268ebb73a47bb8e500b4577434313', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('fdca97d99db54887b747ad04424e8ade', '0a8268ebb73a47bb8e500b4577434313', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('86c432ebaac44d5db125e96a66a15c64', '0a8268ebb73a47bb8e500b4577434313', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
commit;
prompt 600 records committed...
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3129ffc3a3a14642a7e01ecbfbaca705', '0a8268ebb73a47bb8e500b4577434313', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9f420c16e3cc4ec99fe0a36abe87ad6a', '0a8268ebb73a47bb8e500b4577434313', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5e1b3f6b724b4a0b8542e2842af98435', '0a8268ebb73a47bb8e500b4577434313', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ef690e5b57284a8f983d551302942426', '0a8268ebb73a47bb8e500b4577434313', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e8eac067f1db47cf853c07b359e9ac8e', '0a8268ebb73a47bb8e500b4577434313', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('272ac99e9b29424cbbc7a5839413be87', '0a8268ebb73a47bb8e500b4577434313', 'test.on.return', 'false', '是否在归还到池中前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('894b23a46dab48dea1f5ce7fe37e6e78', '0a8268ebb73a47bb8e500b4577434313', 'max.connected.size', '2000', null);
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('1c1d36ce7f824d3da09fff33242a6ea3', '869689bda8ab45d79c25dcee0a98d244', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('86ed0144a3cf45259eab54b032f9e952', '869689bda8ab45d79c25dcee0a98d244', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('8df92edc492440889cf60a16553cfc69', '869689bda8ab45d79c25dcee0a98d244', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0e86e1e7408b44d89fd6cf695f8d02c5', '99adc198c8274ad8b6c27dfd87dbd209', 'solr.shards', '2', 'Solr 分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9a160c466e84478bb2f5b197b6936566', '99adc198c8274ad8b6c27dfd87dbd209', 'solr.replicas', '2', 'Solr 副本数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('2aa7fee03b8745df9e8635d2a21d1312', '99adc198c8274ad8b6c27dfd87dbd209', 'solr.max.shards.per.node', '2', 'Solr 单节点最大分片数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('f7c0513ea6c54f1a81fbe97428d89d16', 'ff0ea269fb034813b1c8db5bc39174ac', 'driver.class', 'org.apache.hive.jdbc.HiveDriver', 'Hive 驱动类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4014e50d444b44899b0c8f48c17c8238', '99adc198c8274ad8b6c27dfd87dbd209', 'hbase.region.num', '2', 'HBase Region数量');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('07a6df90d47d45d3852caf676ee0c120', '99adc198c8274ad8b6c27dfd87dbd209', 'hbase.compression', 'snappy', 'HBase 压缩格式');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('0755e05d26434a839371adc8c2b6668d', '99adc198c8274ad8b6c27dfd87dbd209', 'hbase.method', 'table_att', 'HBase 方法');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('9ec38555507b4bd6bb35e0283ed4c32c', '99adc198c8274ad8b6c27dfd87dbd209', 'hbase.split.policy', 'org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy', 'HBase 分区策略类');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('528d12037bc543e49f7d6ae6ed4550d8', '99adc198c8274ad8b6c27dfd87dbd209', 'hbase.family', 'f', 'HBase 族名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('72daf9970bc744dfbb1b3056709480ad', '99adc198c8274ad8b6c27dfd87dbd209', 'hbase.qualifier', 'q', 'HBase 列名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c87e2d5d36a241f4aed165914a20168c', '99adc198c8274ad8b6c27dfd87dbd209', 'hbase.fq.data.type', 'dsv', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('d7db0e64a4d2484887e2d22927b24f2f', '99adc198c8274ad8b6c27dfd87dbd209', 'hbase.fq.dsv.seprator', '\\007', '结果数据分隔符，如：|、||、\\007、\\t、\\036');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('61ff8da958604a70ab2124b76f44ff35', 'ff0ea269fb034813b1c8db5bc39174ac', 'jdbc.url', 'jdbc:hive2://192.168.1.61:10000/default', 'Hive JDBC URL，如：jdbc:hive2://${ip}:${port}/default');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('e8b475c845fb40aab26a471af0f3e610', 'ff0ea269fb034813b1c8db5bc39174ac', 'username', 'hive', 'Hive 用户名');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5a49823b3d404af49ff19bc919e37f23', 'ff0ea269fb034813b1c8db5bc39174ac', 'password', '111111', 'Hive 密码');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('bcc44ec4fc9b408197669e1b23f139e3', 'ff0ea269fb034813b1c8db5bc39174ac', 'initial.size', '5', '初始连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('b87614d203f245e3bfdc7d6235233080', 'ff0ea269fb034813b1c8db5bc39174ac', 'min.idle', '2', '最小空闲连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('4a042789d46241779630c65fa8b38a0a', 'ff0ea269fb034813b1c8db5bc39174ac', 'max.idle', '50', '最大连接数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3e4c6ff8f2214d16ae28c26453b9e9b4', 'ff0ea269fb034813b1c8db5bc39174ac', 'max.active', '25', '最大并发数');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('a7e4164480d848d1a6e4887ec1651615', 'ff0ea269fb034813b1c8db5bc39174ac', 'max.wait', '3000', '最长等待时间，单位毫秒');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('5be1587ed6cf4f8880d4a1cdc4b3cb29', 'ff0ea269fb034813b1c8db5bc39174ac', 'validation.query', 'select 1', '验证链接的SQL语句，必须能返回一行及以上数据');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('393ea771fc474e89bb10728095bb950e', 'ff0ea269fb034813b1c8db5bc39174ac', 'validation.query.timeout', '0', '自动验证连接的时间');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('c2fc8f3a0662464984fe50eab499568d', 'ff0ea269fb034813b1c8db5bc39174ac', 'time.between.eviction.runs.millis', '30000', 'N毫秒检测一次是否有死掉的线程');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('3c1e0545fc6c4202896e46046d1a2ffa', 'ff0ea269fb034813b1c8db5bc39174ac', 'min.evictable.idle.time.millis', '60000', '空闲连接N毫秒中后释放');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('89cd3e992e334a01ac8d65d7ae9a7093', 'ff0ea269fb034813b1c8db5bc39174ac', 'test.while.idle', 'true', '是否被空闲链接回收器进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('ccd27485147f4c3aa57eacfcd05842b9', 'ff0ea269fb034813b1c8db5bc39174ac', 'test.on.borrow', 'false', '是否从池中取出链接前进行检验');
insert into COM_PROPERTIES (pk_id, fk_id, name, value, describe)
values ('cb7f98e5c0ae44b9bcd293fedaf684c7', 'ff0ea269fb034813b1c8db5bc39174ac', 'test.on.return', 'false', '是否在归还到池中前进行检验');
commit;
prompt 637 records loaded
prompt Loading IM_METADATA...
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('9bc6fbcc659746609aff55ef3555590b', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hex_hbase_test01', 'hbase_test01', null, '0', 'admin', '2017-09-18 08:57:56.192', 'admin', '2017-09-20 09:58:53.850', 'test01', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('8fd91c3409bf47ecaaac67a31ffe7219', '0573547746164d8babab2edfcdfa4015', 'hex_mysql_test01', 'hex_mysql_test01', null, '0', 'admin', '2017-09-20 11:09:10.615', 'admin', '2017-09-20 11:10:09.474', 'test01', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('3685735665ef4be5ad750b2259416f14', 'f0500012f0d244af9f8a29300f724a72', 'Oracle', 'Oracle', 'Oracle', '0', 'admin', '2017-09-08 10:01:33.199', 'admin', '2017-09-18 10:33:38.998', 't1', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('dd2a72729f6642a3a247af5d2cea7345', 'ce5c958b77294337baec27e418e5075e', 'hex_solr_test01', 'hex_solr_test01', null, '0', 'admin', '2017-09-18 14:12:31.812', 'admin', '2017-09-20 15:29:01.356', 'test01', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('9432c331b7f341ff8bb021c5588ecfdf', '040b53fddcc248578c49ec1abd253404', 'hex_oracle_test01', 'hex_oracle_test01', null, '0', 'admin', '2017-09-20 11:14:02.092', 'admin', '2017-09-20 15:59:28.621', 'test01', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('504fe1e335e349229275b3ced01edaf0', '9f5edf70d8074bae9d001dbacf313556', 'mysql_0911', 'mysql_0911', 'asd', '0', 'admin', '2017-09-11 10:04:27.236', 'admin', '2017-09-15 16:28:23.868', 'cboard.sales1', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('34583191b3044530af41d349e06cded1', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', '123', '1', 'admin', '2017-09-18 14:32:10.594', 'admin', '2017-09-18 14:37:46.090', 'cboard.test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('dca13797de434942830f71421d5a1176', 'ff0ea269fb034813b1c8db5bc39174ac', 'hex_hive_test01', 'hex_hive_test01', null, '0', 'admin', '2017-09-20 15:36:29.526', 'admin', '2017-09-20 16:13:36.946', 'sdata.test01', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('4be8486377cc4548adbb52997eda57b6', '9f5edf70d8074bae9d001dbacf313556', 'test', 'test', 'test', '1', 'admin', '2017-09-05 18:51:23.710', 'admin', '2017-09-18 10:32:27.560', 'cboard.t4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('af4021fcb1524cf5be72dda8989e8110', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 15:22:53.900', 'admin', '2017-09-18 15:22:53.900', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('2092b6e77c3542f5aba02070c41fbd9e', 'f0500012f0d244af9f8a29300f724a72', 'oracle', 'oracle', 'oracle', '0', 'admin', '2017-09-07 12:59:36.348', 'admin', '2017-09-07 12:59:36.348', 'test.aa', '1', '1');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('5d2cba179a4b41e8a173c29caa5118b6', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:08:05.342', 'admin', '2017-09-18 17:08:05.342', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('576340e60dcb4b98b014aa99abe510df', '9f5edf70d8074bae9d001dbacf313556', 'asa', 'qw', 'asdsa', '1', 'admin', '2017-09-08 16:57:28.919', 'admin', '2017-09-08 16:57:28.919', 'asda', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('a9a68430a54f4820b67906e72d7b14ec', '9f5edf70d8074bae9d001dbacf313556', 'dwew', 'asa', 'qwd', '1', 'admin', '2017-09-08 16:59:41.805', 'admin', '2017-09-08 16:59:41.805', 'asf', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('b495f039aea74c419e450c1af797959f', 'ce5c958b77294337baec27e418e5075e', 'test111111', 'test111111', null, '1', 'admin', '2017-09-20 10:50:00.334', 'admin', '2017-09-20 10:55:30.010', 'test01', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('9ba52403cd154f8e8546c80312d48570', 'ce5c958b77294337baec27e418e5075e', 'hex_solr_cupatrxjnl', 'hex_solr_cupatrxjnl', null, '0', 'admin', '2017-09-22 09:17:06.553', 'admin', '2017-09-22 09:17:33.176', 'cupatrxjnl', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('da043e150fea428aaa9e8556a42b2513', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'cboard.test1', '1', 'admin', '2017-09-18 14:28:35.643', 'admin', '2017-09-18 14:29:36.086', 'cboard.test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('b5dcea66fbee445cb772ea17cd9b5407', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 14:38:54.688', 'admin', '2017-09-18 14:38:54.688', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('7f79de21f3a44c2a9c66e79302f3f3c7', 'b790018e2e194520ba367668fd8411f9', 'testCheck', 'testCheck', 'testCheck', '1', 'admin', '2017-09-18 16:45:20.932', 'admin', '2017-09-18 16:45:20.932', 'testCheck', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('2775352589d84c4ea35c8db31df6271b', 'b790018e2e194520ba367668fd8411f9', 'testCheck1', 'testCheck', 'testCheck', '1', 'admin', '2017-09-18 16:45:45.612', 'admin', '2017-09-18 16:45:45.612', 'testCheck', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('94a9cff85f424b5883fcf066d3a48649', 'b790018e2e194520ba367668fd8411f9', 'testCheck2', 'testCheck', 'testCheck2', '1', 'admin', '2017-09-18 16:46:26.237', 'admin', '2017-09-18 16:46:29.851', 'testCheck', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('90e0ead7b41249359b30e097518cdf4b', '0573547746164d8babab2edfcdfa4015', 'test3', 'test3', 'asd', '1', 'admin', '2017-09-18 17:01:31.189', 'admin', '2017-09-18 17:02:24.140', 'test3', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('b47f6a09b6484260a3edbd3cc270434f', 'aaabe7f7a0cf412cbbe8aa0f670ee5ee', 'hbase', 'hbase', null, '0', 'admin', '2017-09-12 09:45:45.504', 'admin', '2017-09-14 16:25:41.247', 'hbase', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('fa4df15376474a159421cc6b1722e291', '9f5edf70d8074bae9d001dbacf313556', 'test3', 'test3', 'test3', '1', 'admin', '2017-09-18 17:03:19.173', 'admin', '2017-09-18 17:03:19.173', 'test3', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('3dc9577c2879430a8f84f93911bef299', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', null, '1', 'admin', '2017-09-18 17:05:52.294', 'admin', '2017-09-18 17:05:52.294', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('56516049fd9b4884920c53b9eeb6f4fc', 'e15a7e21200948c5ba2cc92fe3d4e339', 'hex_hbase_test01_2', 'hex_hbase_test01_2', null, '1', 'admin', '2017-09-20 10:00:39.418', 'admin', '2017-09-20 10:00:39.418', 'test01', '2', '1');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('d45fb860e55c4a10b08bb7a2744b1d7d', 'a684e3e668f0482da99f23914c5dfc32', 'hello1', 'hello1', null, '0', 'admin', '2017-09-14 17:45:02.842', 'admin', '2017-09-14 17:57:14.204', 'hello1', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('28b74e1ba9c74ccf95009b4a012b8ed2', 'e55f961071f34909a76bdcc580bfae5a', 'Hive', 'Hive', 'Hive', '0', 'admin', '2017-09-08 15:25:59.716', 'admin', '2017-09-14 14:17:43.435', 'default.Hive', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('0a41ae9e77844d63ad754a6a54babad9', '9f5edf70d8074bae9d001dbacf313556', 'test3', 'test1', 'test1', '1', 'admin', '2017-09-18 15:24:04.842', 'admin', '2017-09-18 15:24:04.842', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('379b34d6e471495c95159ca4b3a5ef0c', '9f5edf70d8074bae9d001dbacf313556', 'sds', 'wew', 'wefw', '1', 'admin', '2017-09-08 16:54:47.941', 'admin', '2017-09-08 16:54:47.941', 'dsfwe', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('18515fe397bf4f5e9be30556eff03651', 'e55f961071f34909a76bdcc580bfae5a', 'hive_0911', 'hive_0911', '123123', '0', 'admin', '2017-09-11 11:04:45.722', 'admin', '2017-09-18 11:02:27.821', 'default.t2', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('4fc8560df2c441509e347eb529fdae88', 'b790018e2e194520ba367668fd8411f9', 'HJ_SOLR', 'HJ_SOLR', null, '0', 'admin', '2017-09-11 16:17:32.716', 'admin', '2017-09-18 15:34:13.319', 'hello', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('04b039b92408452b8cb7f567cc4def73', '6968dda4247f4342ad3bad09442670f9', 'SOLR_HBASE', 'SOLR_HBASE', null, '0', 'admin', '2017-09-12 14:50:01.634', 'admin', '2017-09-12 14:50:11.524', 'solr_hbase', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('a08e85b9212d4527ab3646a3d5467d12', 'f0500012f0d244af9f8a29300f724a72', 'test2', 'test', null, '0', 'admin', '2017-09-13 11:47:55.284', 'admin', '2017-09-14 16:35:16.849', 't2', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('ca15758eebb94f629d3973f648cde9fc', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:09:34.157', 'admin', '2017-09-18 17:09:34.157', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('52f1454e2078459b805db48f9dc97408', '9f5edf70d8074bae9d001dbacf313556', 'testUpload', 'testUpload', null, '1', 'admin', '2017-09-15 14:43:08.945', 'admin', '2017-09-15 14:43:08.945', 'testUpload', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('4699cd7c1031431b99ee9b3f9d70b065', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:10:40.519', 'admin', '2017-09-18 17:10:40.519', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('c2387e1c0a354baca67bcd9a23d54805', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:10:03.483', 'admin', '2017-09-18 17:10:03.483', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('18b2e2838d0e4412b528c33464402919', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:11:34.980', 'admin', '2017-09-18 17:11:34.980', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('31dcc5809db94ca0988469fa33df131f', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:11:36.862', 'admin', '2017-09-18 17:11:36.862', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('d2417147182a43ce839c645be20c347d', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:11:37.474', 'admin', '2017-09-18 17:11:37.474', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('a87c9a0e241e4e538015f6439d8524fa', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:11:38.213', 'admin', '2017-09-18 17:11:38.213', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('4f4bfd360093466ba34ff039a092fd8f', '9f5edf70d8074bae9d001dbacf313556', 'test4', 'test4', 'test4', '1', 'admin', '2017-09-18 17:11:38.899', 'admin', '2017-09-18 17:11:38.899', 'test4', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('e5da741df8164f24931bb989922d6b70', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 17:22:53.982', 'admin', '2017-09-18 17:22:53.982', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('52f93d3fda96487396dfb229a3840213', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 17:22:55.778', 'admin', '2017-09-18 17:22:55.778', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('7810313efcc2433896103af2c85b119c', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 17:22:56.325', 'admin', '2017-09-18 17:22:56.325', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('8bcb1076b20b47a08d7855e0ca5b053c', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 17:22:56.825', 'admin', '2017-09-18 17:22:56.825', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('5935083cad074e1f9fe968309a61a62d', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 17:22:57.586', 'admin', '2017-09-18 17:22:57.586', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('4714fe82ffcf497ab2067957417bf13c', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 17:22:58.224', 'admin', '2017-09-18 17:22:58.224', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('8741e8e7ccbb47d1aa3dbfda209cdf32', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '1', 'admin', '2017-09-18 17:23:00.952', 'admin', '2017-09-18 17:23:00.952', 'test1', '1', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('2a3f1e36c4704316a31ade4d387b3f0a', '9f5edf70d8074bae9d001dbacf313556', 'test1', 'test1', 'test1', '0', 'admin', '2017-09-18 17:24:18.105', 'admin', '2017-09-18 17:24:18.681', 'cboard.test1', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('3710b71ee02f434bbadaabb07db9e074', '040b53fddcc248578c49ec1abd253404', 'hex_oracle', 'DCP_ETL_FILE_COL_INFO', null, '0', 'admin', '2017-09-19 10:37:53.503', 'admin', '2017-09-19 10:37:53.503', 'DCP_ETL_FILE_COL_INFO', '2', '1');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('e2b12d6ac89c4424ba9ca5241c154f15', 'ce5c958b77294337baec27e418e5075e', 'hex_solr_handler_test1', 'hex_solr_handler_test1', null, '0', 'admin', '2017-09-20 09:57:18.209', 'admin', '2017-09-20 09:57:18.209', 'solr_handler_test1', '2', '1');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('493008fd55294957a8d7c3edb0ba503a', '4de1700c917940b0ba2f11681ae658c3', 'hex_solr_hbase_test02', 'hex_solr_hbase_test02', null, '0', 'admin', '2017-09-20 11:18:04.916', 'admin', '2017-09-20 15:26:09.456', 'test02', '2', '0');
insert into IM_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name, status, type)
values ('39616843d72645deac179aa8d728fe8d', 'ce5c958b77294337baec27e418e5075e', 'hex_solr_test02', 'hex_solr_test02', null, '1', 'admin', '2017-09-20 14:53:57.513', 'admin', '2017-09-20 15:20:35.307', 'test02', '1', '0');
commit;
prompt 55 records loaded
prompt Loading IM_METADATA_COLUMN...
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8705c5ca944b4201b5c242ec2d2d9d0a', '8fd91c3409bf47ecaaac67a31ffe7219', 1, 'name', '名称', 'VARCHAR', '256', null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('b7f4989ee6b441a5b743f0beb7eb9191', '8fd91c3409bf47ecaaac67a31ffe7219', 2, 'id', '主键', 'VARCHAR', '32', null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('689533ea59c445bead176489eb338087', '9432c331b7f341ff8bb021c5588ecfdf', 1, 'id', 'id', 'VARCHAR', '32', null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('b5a9702487504a4688f30cc7f50c6044', '4be8486377cc4548adbb52997eda57b6', 1, 'ID', 'hahahah', 'INT', '10', null, '1', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('e806ab72317f4fdc968a0bd1a1423374', '4be8486377cc4548adbb52997eda57b6', 2, 'LOGO', 'asczxc', 'VARCHAR', '255', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('b82af4787e54408fa4d9443e32d01f35', '3685735665ef4be5ad750b2259416f14', 2, 'b', 'b', 'VARCHAR', '5', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ed6bf1cae6644a0cbc9a7a7cabdb7f48', '9432c331b7f341ff8bb021c5588ecfdf', 2, 'name', 'name', 'VARCHAR', '256', null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('7dd6ba6fa4834b1c9f7fa8e04804daaa', '34583191b3044530af41d349e06cded1', 1, 'test1', 'test1', 'STRING', '10', null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('d9329b63db234f3ca0476b6de177d961', '04b039b92408452b8cb7f567cc4def73', 2, 'category', 'category', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('a8db9444d436499cb6b1c0af14eaa1a1', 'a08e85b9212d4527ab3646a3d5467d12', 2, 'c', '2', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8c5c3006824447749622fbd8f9690ea3', 'a08e85b9212d4527ab3646a3d5467d12', 1, 'a', '1', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('5e9283aa4b1f4f0ea918136c58d592b8', 'a08e85b9212d4527ab3646a3d5467d12', 3, 'b', '3', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('6e5a3d954a4f4cb18275ffada989d6ed', '3685735665ef4be5ad750b2259416f14', 1, 'a', 'a', 'VARCHAR', '10', null, '1', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('2e7c847f8dfc4dc697bdad66d8a6fe10', '04b039b92408452b8cb7f567cc4def73', 1, 'cat', 'cat', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('973344a2fa79453db9994135aed59821', '576340e60dcb4b98b014aa99abe510df', 1, 'qwq', 'asda', 'STRING', 'sad', 'asd', '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('0fb069060461475ca7ee8cb9ce42c045', '5d2cba179a4b41e8a173c29caa5118b6', 1, 'test4', 'test4', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('64c913f738d3499f92846d0c6d926f8c', '2092b6e77c3542f5aba02070c41fbd9e', 1, 'F', 'oracle', 'DECIMAL', '10,3', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8ebc7719cf71487b84193f5fc72295b2', '2092b6e77c3542f5aba02070c41fbd9e', 2, 'E', 'oracle', 'STRING', '0', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8e5259bcdad54c48816fa23867442dab', '2092b6e77c3542f5aba02070c41fbd9e', 3, 'G', 'oracle', 'TIMESTAMP', '11', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('a684040c9c6b458e9dcf0a3906d060b1', '2092b6e77c3542f5aba02070c41fbd9e', 4, 'B', 'oracle', 'STRING', '4000', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('e0196f026afe43288abedf8d04d2a160', '2092b6e77c3542f5aba02070c41fbd9e', 5, 'K', 'oracle', 'DOUBLE', '8', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('48e00a5d381343ea86904290e77a29d6', '2092b6e77c3542f5aba02070c41fbd9e', 6, 'J', 'oracle', 'FLOAT', '4', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('afb29f25c51045ebbb1473db73b32c4e', '2092b6e77c3542f5aba02070c41fbd9e', 7, 'H', 'oracle', 'VARCHAR', '1', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('58f36838db3f4d3eab088b42c1e5683a', '2092b6e77c3542f5aba02070c41fbd9e', 8, 'D', 'oracle', 'TIMESTAMP', '7', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('e3ef057546ef4fc2a2b72171b4d22760', '2092b6e77c3542f5aba02070c41fbd9e', 9, 'I', 'oracle', 'DECIMAL', '22', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('0542ddc0d47046e3aec62db5334976d1', '2092b6e77c3542f5aba02070c41fbd9e', 10, 'A', 'oracle', 'STRING', '4000', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('854735f0a453462ab74806ab50159506', '2092b6e77c3542f5aba02070c41fbd9e', 11, 'C', 'oracle', 'CHAR', '1', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('e3e04be934a74613a60ee627dd95afc8', 'dd2a72729f6642a3a247af5d2cea7345', 1, 'id', '主键', 'STRING', null, null, '1', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('6bc52b215073411e8cb897e0d3b58f47', 'a9a68430a54f4820b67906e72d7b14ec', 1, 'adf', 'asd', 'STRING', '11', 'asd', '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('114fe847793e48c48ccfbac834d06b7f', 'dd2a72729f6642a3a247af5d2cea7345', 2, 'khh', '客户号', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('5e17545e72af4abc860f0861e52dfbf7', 'dd2a72729f6642a3a247af5d2cea7345', 3, 'dt', '日期', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8dc46dff5890416297703958a12b2ece', 'dd2a72729f6642a3a247af5d2cea7345', 4, 'amt', '金额', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('7a7f159a327d4fef87edac9b9472b00f', 'd45fb860e55c4a10b08bb7a2744b1d7d', 1, 'author', 'author', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ce77b792cb824fce8ec2b41fefe3b47c', '9ba52403cd154f8e8546c80312d48570', 1, 'id', 'id', 'STRING', null, null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('c5335b20af154812a3cdab1480e4a07c', 'd45fb860e55c4a10b08bb7a2744b1d7d', 2, 'cat', 'cat', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('7bb4ef55aab24e478cbdf8d95a4684dd', 'd45fb860e55c4a10b08bb7a2744b1d7d', 3, 'category', 'category', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('891134df97c54faf9949ab1c0e24d650', '9ba52403cd154f8e8546c80312d48570', 2, 'ccy', 'ccy', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('a64f1d23f08740d388a25f6ca3cafa56', '9ba52403cd154f8e8546c80312d48570', 3, 'amt', 'amt', 'DOUBLE', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('66d3e8212e664624ab2b66e2765a6bc1', 'da043e150fea428aaa9e8556a42b2513', 1, 'a', 'b', 'STRING', '10', null, '1', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8a9bfb42b3be4a8893f8e5d68173d43b', 'af4021fcb1524cf5be72dda8989e8110', 1, 'a', 'a', 'STRING', '10', '10', '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ae1e456877654754b0de919a1d18e7a9', '9ba52403cd154f8e8546c80312d48570', 4, 'auth_code', 'auth_code', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('c69f3aa0dd7643c397e79fe55e159254', '9ba52403cd154f8e8546c80312d48570', 5, 'bu_ret_code', 'bu_ret_code', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('090aba4398b94f87aac80206fd0bc0c8', '9ba52403cd154f8e8546c80312d48570', 6, 'bu_settlement_date', 'bu_settlement_date', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('3f90db5ce7164f159430527a2d99540d', '9ba52403cd154f8e8546c80312d48570', 7, 'bu_tran_code', 'bu_tran_code', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('33cfa3711b894ee48e02838eb08af2b9', '9ba52403cd154f8e8546c80312d48570', 8, 'card_no', 'card_no', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('b8768ca9b9dc4944ad12a96aa6e328f1', '9ba52403cd154f8e8546c80312d48570', 9, 'channel_type', 'channel_type', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('5a33169e0ade4428a3b18e4a49bea75f', '9ba52403cd154f8e8546c80312d48570', 10, 'current_num', 'current_num', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8317df7f6f1d46d99d78dfd5b41a0a83', '9ba52403cd154f8e8546c80312d48570', 11, 'medium_type', 'medium_type', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('fac26626b178480fa9556742b3907847', '9ba52403cd154f8e8546c80312d48570', 12, 'message_type', 'message_type', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('93612e2e52b840a5bd7962c0a0b7faae', '9ba52403cd154f8e8546c80312d48570', 13, 'paymen_date', 'paymen_date', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('9c7a07139bbb42d09cd697e1f938459d', '9ba52403cd154f8e8546c80312d48570', 14, 'dt', 'dt', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('35dd7ab7384c45a9bd6a5c54b27bc15c', '9ba52403cd154f8e8546c80312d48570', 15, 'retr_ref_no', 'retr_ref_no', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('caf7105ce869432bb525222582ed86c5', '9ba52403cd154f8e8546c80312d48570', 16, 'system_tracr_no', 'system_tracr_no', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('6e0daced58d34773a38fcc62b0b477d8', '9ba52403cd154f8e8546c80312d48570', 17, 'teller_no', 'teller_no', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('009dd25c429e4914b67d71a5ae6c9ef1', '9ba52403cd154f8e8546c80312d48570', 18, 'term_seq_no', 'term_seq_no', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('127a64c82c4345d6a504f28d1fa737a5', '9ba52403cd154f8e8546c80312d48570', 19, 'total_num', 'total_num', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('9fc27d0e289547399e4b9abfdb26b1ef', '9ba52403cd154f8e8546c80312d48570', 20, 'tran_in_acct_no', 'tran_in_acct_no', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('558c6955ff8d421693ed332c0612042d', '9ba52403cd154f8e8546c80312d48570', 21, 'tran_status', 'tran_status', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('9ed3bf66bc0048e6b1729cb1bd1a6293', '9ba52403cd154f8e8546c80312d48570', 22, 'uni_message_type', 'uni_message_type', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('81ae7cdc7f6149198a91ca4e1c8417a6', 'b5dcea66fbee445cb772ea17cd9b5407', 1, 'a', 'a', 'STRING', '10', 'test1', '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ab36128bc14042f9a4f4393b454475ac', '94a9cff85f424b5883fcf066d3a48649', 1, 'testCheck', 'testCheck', 'STRING', '10', null, '1', '0', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('d7cc7177664c452d85bde7e6dd677a52', '90e0ead7b41249359b30e097518cdf4b', 1, 'a', 'a', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('b6d9719124fd4ecab56503b376fbf42b', 'fa4df15376474a159421cc6b1722e291', 1, 'test3', 'test3', 'STRING', '10', 'test3', '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('a9dbef1736fe4b749bb140d6801f7415', '4fc8560df2c441509e347eb529fdae88', 1, 'author', 'author', 'STRING', null, null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('e1dadcd824254c0d991006479d0ed1b5', 'b47f6a09b6484260a3edbd3cc270434f', 1, 'a', 'a', 'STRING', null, null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('272dd02e0dca46cdbf5ac44ec9a8b330', '4fc8560df2c441509e347eb529fdae88', 2, 'cat', 'cat', 'STRING', null, null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('bdfb508830874cdba337c445f619ce36', '4fc8560df2c441509e347eb529fdae88', 3, 'category', 'category', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('bf436e164f444b5d8d50c4b5f494a872', '7f79de21f3a44c2a9c66e79302f3f3c7', 1, 'testCheck', 'testCheck', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('af50f9c7c9414a8dace5575567689a93', '2775352589d84c4ea35c8db31df6271b', 1, 'testCheck', 'testCheck', 'STRING', '10', null, '1', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('d1356ab95a9e4422895c16e6e0dba1f8', 'b47f6a09b6484260a3edbd3cc270434f', 2, 'b', 'b', 'STRING', null, null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('7a7631aadef0437e8e274dd5e55c6b80', '3dc9577c2879430a8f84f93911bef299', 1, 'test4', 'test4', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('05d9cddd9bf94eeda2dec171c2ed2eb3', 'b47f6a09b6484260a3edbd3cc270434f', 3, 'c', 'c', 'STRING', null, null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('93675f3878b64e62bbd9bcc939997f8a', '56516049fd9b4884920c53b9eeb6f4fc', 1, 'key', 'key', 'STRING', null, null, '1', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ab08fb43ca8b435fa7a708b9c9ea6601', '56516049fd9b4884920c53b9eeb6f4fc', 2, 'val', 'value', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('22a480610b4f468e8c4563bd4e07f0ff', 'b495f039aea74c419e450c1af797959f', 1, 'amt', 'amt', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('0affbec4ac3f4b61b5c5f85b13e0755a', 'b495f039aea74c419e450c1af797959f', 2, 'dt', 'dt', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('e75371a480a048608d1c8cf07bd7c3cd', 'b495f039aea74c419e450c1af797959f', 3, 'id', 'id', 'STRING', null, null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8e4c43f8c92c4e908383f2846b321f04', 'b495f039aea74c419e450c1af797959f', 4, 'khh', 'khh', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('36a73cf1a89149f4a56f06ed217bb285', '9bc6fbcc659746609aff55ef3555590b', 2, 'dt', '日期', 'TIMESTAMP', '10', null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('37e055df825d43709c5c9c9cfc79b835', '0a41ae9e77844d63ad754a6a54babad9', 1, 'a', 'a', 'STRING', '10', '10', '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ccab89f39873462987b65fd2ba322d13', '9bc6fbcc659746609aff55ef3555590b', 3, 'amt', '金额', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('9e1c15f1dc4b47f6bdd8a9eaa71dfd2e', '9bc6fbcc659746609aff55ef3555590b', 1, 'khh', '客户号', 'STRING', '10', null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8c4fdc0ae7d146a18b6da81bafb28a75', '379b34d6e471495c95159ca4b3a5ef0c', 1, 'w', 'we', 'STRING', '1', 'wqw', '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('008a1312cb8143ba8e3e0a8bfb9feac9', '4699cd7c1031431b99ee9b3f9d70b065', 1, 'test4', 'test4', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ecb922c741554e1289a7b247f3f42db7', '18b2e2838d0e4412b528c33464402919', 1, 'a', 'a', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('c714d249fcaf40b4b8fc34ef72370e2a', '18515fe397bf4f5e9be30556eff03651', 1, 'a', 'df', 'TINYINT', '3', null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('3318794cea974093a252b996664c636d', '18515fe397bf4f5e9be30556eff03651', 2, 'b', 'aaa', 'SMALLINT', '5', null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('6120dcc0d02b43cab8d4bc9d5d3b0c10', '31dcc5809db94ca0988469fa33df131f', 1, 'a', 'a', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('e66f710786ec40b7a235c15923032ae3', '504fe1e335e349229275b3ced01edaf0', 1, 'ID', 'hahahah', 'INT', '10', null, '1', '0', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ebb6f83794ce4263bd8ffb73a3bb9a5f', '504fe1e335e349229275b3ced01edaf0', 2, 'LOGO', 'asczxc', 'VARCHAR', '255', null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('0d2402591b474e699fb7aa5dd234dea1', '493008fd55294957a8d7c3edb0ba503a', 1, 'id', '主键', 'STRING', null, null, '1', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('f9eac205e8434c1186623fe2fed7c16d', '493008fd55294957a8d7c3edb0ba503a', 2, 'khh', '客户号', 'STRING', '10', null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('cbb6a41b8fb54af4af193ca1055405df', '18515fe397bf4f5e9be30556eff03651', 3, 'c', 'e', 'FLOAT', '7,7', null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('173ec37366ae4894b7bd105e9ad51f6c', '18515fe397bf4f5e9be30556eff03651', 4, 'd', 'q', 'DOUBLE', '15,15', null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('7a4b224b1de44108af4d854745b0c90c', '18515fe397bf4f5e9be30556eff03651', 5, 'j', 'q', 'TIMESTAMP', '29,9', null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('19f2c4590537418d93f03b1c4de2ab06', '18515fe397bf4f5e9be30556eff03651', 6, 'k', 'q', 'DECIMAL', '10', null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('46939a1dd7434a14ad92bf7e1fac191b', '18515fe397bf4f5e9be30556eff03651', 7, 'm', 'q', 'TIMESTAMP', '10', null, '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ca0c6a8bd00c4cb9904c0cfde972164d', '493008fd55294957a8d7c3edb0ba503a', 3, 'dt', '日期', 'TIMESTAMP', '8', null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('35788e3033d8439d9afb378d03c61d38', 'e2b12d6ac89c4424ba9ca5241c154f15', 1, 'amt', 'amt', 'DOUBLE', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('304268c965844ae89dd14ba7a56bb21d', 'e2b12d6ac89c4424ba9ca5241c154f15', 2, 'card_no', 'card_no', 'STRING', null, null, '0', '1', '0');
commit;
prompt 100 records committed...
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('4bd57ead60b64dd9b5b7050781e1b3a6', 'e2b12d6ac89c4424ba9ca5241c154f15', 3, 'channel_type', 'channel_type', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ef58f019449a4131a65695d1f3e2ef24', '28b74e1ba9c74ccf95009b4a012b8ed2', 1, 'a', 'aa', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('b95d154a16254a39a3811559d6237367', '28b74e1ba9c74ccf95009b4a012b8ed2', 2, 'b', 'b', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('927205ba2da2465e902800cd3a61ed0d', '52f1454e2078459b805db48f9dc97408', 1, 'a', 'a', 'string', '10', 'a', '1', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('62e2fdf643e34a609673f8f52e4a1c7c', 'e2b12d6ac89c4424ba9ca5241c154f15', 4, 'dt', 'dt', 'STRING', null, null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('005cfc6afba84464b254e4a9c7b13ca8', 'ca15758eebb94f629d3973f648cde9fc', 1, 'test4', 'test4', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('88f0c786fbb04f5191be7b63d1c71f8a', 'c2387e1c0a354baca67bcd9a23d54805', 1, 'test4', 'test4', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('3552886545db4b0d85ff0467933beb58', 'd2417147182a43ce839c645be20c347d', 1, 'a', 'a', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('2b86283fb09348969f5a537ceb7295e3', 'a87c9a0e241e4e538015f6439d8524fa', 1, 'a', 'a', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('4481cd5691e14931a32599e2991925f0', '4f4bfd360093466ba34ff039a092fd8f', 1, 'a', 'a', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('782263463bc5418bbf9e5cfcadae599b', 'e5da741df8164f24931bb989922d6b70', 1, 'test1', 'test1', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('cc69c8ea9eaf456e913e8b7626a16e94', '52f93d3fda96487396dfb229a3840213', 1, 'test1', 'test1', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('7aa9b152fb384a7a8fa094ba78f9aba5', '7810313efcc2433896103af2c85b119c', 1, 'test1', 'test1', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('703397eb27544945aa1c21bf51c91470', '8bcb1076b20b47a08d7855e0ca5b053c', 1, 'test1', 'test1', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('85a166165f2f44daa6853ab90edff7a9', '5935083cad074e1f9fe968309a61a62d', 1, 'test1', 'test1', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('5a3d4fa221d24da1955422f2ac31be27', '4714fe82ffcf497ab2067957417bf13c', 1, 'test1', 'test1', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('da8bca85e05e454aa2ed7c45dcac7a63', '8741e8e7ccbb47d1aa3dbfda209cdf32', 1, 'test1', 'test1', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('76e8c940332345dd85191c4a723f671d', '2a3f1e36c4704316a31ade4d387b3f0a', 1, 'test1', 'test1', 'STRING', '10', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('1cf1f02d7f3546498d18dab29ff6f198', '3710b71ee02f434bbadaabb07db9e074', 1, 'PK_ID', '主键', 'VARCHAR', '32', null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8efb8dea0a224f1b83728aa11e00c101', '3710b71ee02f434bbadaabb07db9e074', 2, 'BIZ_CODE', '业务编码', 'VARCHAR', '256', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('607ecd7647a7462b9f7bbfa7a95483ec', '3710b71ee02f434bbadaabb07db9e074', 3, 'COL_NAME', '字段名', 'VARCHAR', '256', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('b3d31c342463423e9b0a038eb3ac33fd', '3710b71ee02f434bbadaabb07db9e074', 4, 'COL_LENGTH', '字段长度', 'VARCHAR', '20', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('9105b2cfeda94861af1e2a7fd7ddb452', '3710b71ee02f434bbadaabb07db9e074', 5, 'COL_POS', '字段位置', 'DECIMAL', '3', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('846b28201e0847658e2d9d6d68d4b0ac', '3710b71ee02f434bbadaabb07db9e074', 6, 'COL_TYPE', '字段类型', 'VARCHAR', '32', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('edc55475299942e89dcd392b1b559df1', '3710b71ee02f434bbadaabb07db9e074', 7, 'IS_ALLOW_NULL', '是否为空', 'VARCHAR', '2', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('65bd61e0f62b448ca54e72d01ebd1ac9', '3710b71ee02f434bbadaabb07db9e074', 8, 'COL_CN_NAME', '字段注释', 'VARCHAR', '1024', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('eefc2361a3904dc39f54ede0462042ec', 'dca13797de434942830f71421d5a1176', 2, 'khh', '客户号', 'VARCHAR', '10', null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('e4f70734c9634ffbb006fe9fe775f1a9', 'e2b12d6ac89c4424ba9ca5241c154f15', 5, 'id', 'id', 'STRING', null, null, '0', '0', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('4e9bd7f814b542e3828d4769da0bab40', '493008fd55294957a8d7c3edb0ba503a', 4, 'amt', '金额', 'DECIMAL', '22,2', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('09b042934bfd492fbee5e9fe3f1e653c', 'dca13797de434942830f71421d5a1176', 3, 'dt', '日期', 'VARCHAR', '8', null, '0', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('fa6f9db9dd3344f48e6696a6eed79bf6', 'dca13797de434942830f71421d5a1176', 4, 'amt', '金额', 'DECIMAL', '22,2', null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('2e8607b7c5724ea799fc96f56421756e', '39616843d72645deac179aa8d728fe8d', 1, 'id', 'id', 'STRING', null, null, '1', '0', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('ad3c7fe7f8484ded96f4591f9dfba808', '39616843d72645deac179aa8d728fe8d', 2, 'khh', '客户号', 'STRING', null, null, '0', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('8d0b02a387254582bb9e2319d4003f3b', '39616843d72645deac179aa8d728fe8d', 3, 'dt', '日期', 'STRING', null, null, '0', '1', '1');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('0c1013d44987401fa7f66701f8bded59', '39616843d72645deac179aa8d728fe8d', 4, 'amt', '金额', 'STRING', null, null, '1', '1', '0');
insert into IM_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, indexed, primary, stored)
values ('9564c15e7ec848b6870d36233601abde', 'dca13797de434942830f71421d5a1176', 1, 'id', '主键', 'STRING', null, null, '1', '0', '0');
commit;
prompt 136 records loaded
prompt Loading IM_MODEL...
insert into IM_MODEL (pk_id, name, describe, s_ds_id, t_md_id, note, del_flg, crt_user, crt_time, upt_user, upt_time, type, build_mode, update_mode, e_ds_id, status)
values ('b5fac00d6b2a4461a3a3c90fbb83a0c1', 'test_1', '测试实时-更新主键', '2f9f1efe3814428890b8eeb1f952b616', 'a08e85b9212d4527ab3646a3d5467d12', null, '0', 'admin', '2017-09-12 15:26:23.778', 'admin', '2017-09-25 16:03:30.969', '2', null, '2', null, '1');
insert into IM_MODEL (pk_id, name, describe, s_ds_id, t_md_id, note, del_flg, crt_user, crt_time, upt_user, upt_time, type, build_mode, update_mode, e_ds_id, status)
values ('d6301dd94c934c6e84912623f86362a5', 'test', '测试', 'e55f961071f34909a76bdcc580bfae5a', '2092b6e77c3542f5aba02070c41fbd9e', null, '0', 'admin', '2017-09-08 15:40:46.941', 'admin', '2017-09-21 09:24:32.981', '1', '1', null, 'e55f961071f34909a76bdcc580bfae5a', '2');
insert into IM_MODEL (pk_id, name, describe, s_ds_id, t_md_id, note, del_flg, crt_user, crt_time, upt_user, upt_time, type, build_mode, update_mode, e_ds_id, status)
values ('4c6b37a854ef466d959e4bb3248877ca', 'ceshi1', '完整流程测试', 'f0500012f0d244af9f8a29300f724a72', '2092b6e77c3542f5aba02070c41fbd9e', null, '1', 'admin', '2017-09-14 10:53:32.608', 'admin', '2017-09-14 10:53:32.608', '1', '2', null, 'e55f961071f34909a76bdcc580bfae5a', '1');
insert into IM_MODEL (pk_id, name, describe, s_ds_id, t_md_id, note, del_flg, crt_user, crt_time, upt_user, upt_time, type, build_mode, update_mode, e_ds_id, status)
values ('4ba6c197b4314b7e9e403fde09f75482', 'ceshi2222', '完整流程测试', 'f0500012f0d244af9f8a29300f724a72', '2092b6e77c3542f5aba02070c41fbd9e', null, '0', 'admin', '2017-09-14 14:56:54.029', 'admin', '2017-09-27 14:31:40.782', '1', '2', null, 'e55f961071f34909a76bdcc580bfae5a', '1');
insert into IM_MODEL (pk_id, name, describe, s_ds_id, t_md_id, note, del_flg, crt_user, crt_time, upt_user, upt_time, type, build_mode, update_mode, e_ds_id, status)
values ('7fde339cd6ae45f3a7988144d0eefcb8', 'ceshi22221112', '完整流程测试', 'f0500012f0d244af9f8a29300f724a72', '2092b6e77c3542f5aba02070c41fbd9e', null, '0', 'admin', '2017-09-14 15:08:10.576', 'admin', '2017-09-20 09:35:20.366', '1', '2', null, 'e55f961071f34909a76bdcc580bfae5a', '1');
insert into IM_MODEL (pk_id, name, describe, s_ds_id, t_md_id, note, del_flg, crt_user, crt_time, upt_user, upt_time, type, build_mode, update_mode, e_ds_id, status)
values ('e4962a47cb9644afac76cf90c263052b', 'buildModel_Batch', '批量建模测试', '15c2d37df5e6434f9183b7c0e1f20da0', '493008fd55294957a8d7c3edb0ba503a', null, '0', 'admin', '2017-09-21 11:05:00.194', 'admin', '2017-09-21 14:39:29.840', '1', '2', null, 'e55951ed203341ea83e079314e825d3d', '1');
insert into IM_MODEL (pk_id, name, describe, s_ds_id, t_md_id, note, del_flg, crt_user, crt_time, upt_user, upt_time, type, build_mode, update_mode, e_ds_id, status)
values ('7d056baacde14047927f7067d96823cb', 'buildlocal_batch', 'localMysqlBatch', '1522f3b690f0408f9eb94925c50f6392', '493008fd55294957a8d7c3edb0ba503a', '利用本地mysql作为源数据源，测试某些bug', '0', 'admin', '2017-09-26 09:17:01.218', 'admin', '2017-09-27 14:07:26.793', '1', '1', null, '0a8268ebb73a47bb8e500b4577434313', '1');
insert into IM_MODEL (pk_id, name, describe, s_ds_id, t_md_id, note, del_flg, crt_user, crt_time, upt_user, upt_time, type, build_mode, update_mode, e_ds_id, status)
values ('9bf3e555bba9413f9e494bbf5c22e071', 'hex_hive_handler_test1_to_test01', 'hex_hive_handler_test1_to_test01', 'ff0ea269fb034813b1c8db5bc39174ac', 'dca13797de434942830f71421d5a1176', null, '0', 'admin', '2017-09-27 09:01:37.927', 'admin', '2017-09-27 14:00:17.743', '1', '1', null, '0a8268ebb73a47bb8e500b4577434313', '1');
commit;
prompt 8 records loaded
prompt Loading IM_MODEL_FILTER_COLUMN...
insert into IM_MODEL_FILTER_COLUMN (pk_id, model_id, seq, name, describe, type, length, is_need, default_val, operator, label)
values ('8d71e3da48974a59ba5c1f6058a6a95f', 'd6301dd94c934c6e84912623f86362a5', 1, 'sd', 'werwr', 'CHAR', 'sdf', '0', null, '<', 'wefe');
insert into IM_MODEL_FILTER_COLUMN (pk_id, model_id, seq, name, describe, type, length, is_need, default_val, operator, label)
values ('9d833411f792490b92bc35c0fc0bd23f', '4ba6c197b4314b7e9e403fde09f75482', 1, 'sdw', 'sdw', 'INT', '11', '0', '12', '<', 'sdf');
insert into IM_MODEL_FILTER_COLUMN (pk_id, model_id, seq, name, describe, type, length, is_need, default_val, operator, label)
values ('4ca3df5a1c844f80ab40b90675eb4f65', '9bf3e555bba9413f9e494bbf5c22e071', 1, 'dt', '日期', 'STRING', null, '1', null, '==', 'DT');
insert into IM_MODEL_FILTER_COLUMN (pk_id, model_id, seq, name, describe, type, length, is_need, default_val, operator, label)
values ('922eb716ca7e4b18b22369e576528d6b', '4c6b37a854ef466d959e4bb3248877ca', 1, 'sdw', 'sdw', 'INT', '11', '0', null, '<', 'sdf');
insert into IM_MODEL_FILTER_COLUMN (pk_id, model_id, seq, name, describe, type, length, is_need, default_val, operator, label)
values ('ea7d0a7f234a41d480f7d877c1353962', '7fde339cd6ae45f3a7988144d0eefcb8', 1, 'sdw', 'sdw', 'INT', '11', '0', '12', '<', 'sdf');
insert into IM_MODEL_FILTER_COLUMN (pk_id, model_id, seq, name, describe, type, length, is_need, default_val, operator, label)
values ('50d97b69a6594bc4b7299c0e304d96b6', 'b5fac00d6b2a4461a3a3c90fbb83a0c1', 1, 'sds', '测试内容', 'DECIMAL', '13', '0', null, '>=', 'sds');
insert into IM_MODEL_FILTER_COLUMN (pk_id, model_id, seq, name, describe, type, length, is_need, default_val, operator, label)
values ('2f15ca8689704bcaa4e07f607093396a', 'e4962a47cb9644afac76cf90c263052b', 1, 'id', '测试过滤字段', 'INT', '4', '0', '1', '==', 'id');
insert into IM_MODEL_FILTER_COLUMN (pk_id, model_id, seq, name, describe, type, length, is_need, default_val, operator, label)
values ('ed9e47abaed443029e58c886ffa46ac6', '7d056baacde14047927f7067d96823cb', 1, 'name', '名称', 'VARCHAR', '30', '0', null, 'like', 'name');
commit;
prompt 8 records loaded
prompt Loading IM_MODEL_MAPPING...
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('f8ff62d845174e4ebf93ca7aa9f77f15', 'd6301dd94c934c6e84912623f86362a5', 1, 'cds', '64c913f738d3499f92846d0c6d926f8c', 'sdfqefww', 'CHAR', '11', 'sd');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('fae5b0475c7444ac8f19e58d1f78cce3', '4ba6c197b4314b7e9e403fde09f75482', 1, 'sdc', 'e0196f026afe43288abedf8d04d2a160', null, 'SMALLINT', 'e', 'qwq');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('2a3692bd36f143f0aa181ba8dfc702df', '9bf3e555bba9413f9e494bbf5c22e071', 1, 'id', '9564c15e7ec848b6870d36233601abde', null, 'STRING', '2147483647', 'from deserializer');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('959e7a903cdd4a0188dd2cce646c56fd', '9bf3e555bba9413f9e494bbf5c22e071', 2, 'dt', '09b042934bfd492fbee5e9fe3f1e653c', null, 'STRING', '2147483647', 'from deserializer');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('3d09e83c5f8f451cadf526e6f8ceeed2', '4c6b37a854ef466d959e4bb3248877ca', 1, 'sdc', 'a684040c9c6b458e9dcf0a3906d060b1', null, 'SMALLINT', 'e', 'qwq');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('f344d5d19dec47f8ba660f9f576693f6', '9bf3e555bba9413f9e494bbf5c22e071', 3, 'ccy', 'eefc2361a3904dc39f54ede0462042ec', null, 'STRING', '2147483647', 'from deserializer');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('079782087c2d44cebe1b25eb861cc969', '9bf3e555bba9413f9e494bbf5c22e071', 4, 'amt', 'fa6f9db9dd3344f48e6696a6eed79bf6', null, 'DECIMAL', '38,18', 'from deserializer');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('4555b227a6f64b0aac1d955ad3af6832', '7fde339cd6ae45f3a7988144d0eefcb8', 1, 'sdc', 'a684040c9c6b458e9dcf0a3906d060b1', null, 'SMALLINT', '11', 'qwq');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('4a591e3048074012a1b2af86ba814a1d', 'e4962a47cb9644afac76cf90c263052b', 2, 'name', 'f9eac205e8434c1186623fe2fed7c16d', null, 'VARCHAR', '60', '测试名称');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('342cc1e9352642b7bcc8f2cdc68ef3fd', 'e4962a47cb9644afac76cf90c263052b', 1, 'id', '0d2402591b474e699fb7aa5dd234dea1', null, 'INT', '10', '测试id');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('9e109c21111c481b8a2dec388c4ce945', 'e4962a47cb9644afac76cf90c263052b', 3, 'remarks', 'ca0c6a8bd00c4cb9904c0cfde972164d', null, 'VARCHAR', '255', '测试备注');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('1d38869d8dec468da6d940f09cffd19a', 'b5fac00d6b2a4461a3a3c90fbb83a0c1', 1, 'cd', '8c5c3006824447749622fbd8f9690ea3', '测试', 'STRING', '300', '测试字段');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('5ed08f07d5e042a4864c179bd5936349', '7d056baacde14047927f7067d96823cb', 1, 'id', '0d2402591b474e699fb7aa5dd234dea1', null, 'INT', '10', '测试映射');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('7c24edf012b6489a8ee83760a83b01ff', '7d056baacde14047927f7067d96823cb', 2, 'name', 'f9eac205e8434c1186623fe2fed7c16d', null, 'VARCHAR', '60', '测试映射1');
insert into IM_MODEL_MAPPING (pk_id, model_id, seq, name, col_id, note, type, length, describe)
values ('f1fc5f6c4b4149788c1f65ca749afa7d', '7d056baacde14047927f7067d96823cb', 3, 'remarks', 'ca0c6a8bd00c4cb9904c0cfde972164d', null, 'VARCHAR', '255', '测试映射2');
commit;
prompt 15 records loaded
prompt Loading IM_MODEL_UPDATE_KEY...
insert into IM_MODEL_UPDATE_KEY (pk_id, col_id, model_id)
values ('1cf5864ee19b4ed5915460a176beb18b', 'ef58f019449a4131a65695d1f3e2ef24', 'b5fac00d6b2a4461a3a3c90fbb83a0c1');
insert into IM_MODEL_UPDATE_KEY (pk_id, col_id, model_id)
values ('cf3d4890e80440258cfc65ed49a088f6', 'b95d154a16254a39a3811559d6237367', 'b5fac00d6b2a4461a3a3c90fbb83a0c1');
commit;
prompt 2 records loaded
prompt Loading IQ_APPLICATION...
insert into IQ_APPLICATION (pk_id, md_id, name, describe, note, max_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('1b82f3a00fa34de7a4116372d4f0d8ca', '7ffb213154444588900d239b7f5e3131', 'test_soa_cupatrxjnl_solr_hbase_app', '前置银联明细查询', null, 60000, '1', 'admin', '2017-09-12 16:36:11.357', 'admin', '2017-09-12 16:36:11.357');
insert into IQ_APPLICATION (pk_id, md_id, name, describe, note, max_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('5f7693ccf3f449fdb575cdd5e9d32f32', '7ffb213154444588900d239b7f5e3131', 'soa_cupatrxjnl_solr_hbase_app', '前置银联明细查询', null, 60000, '0', 'admin', '2017-07-31 22:08:37.597', 'admin', '2017-07-31 22:08:37.597');
insert into IQ_APPLICATION (pk_id, md_id, name, describe, note, max_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('170ebfd104e740ae99359d08b37f60ae', '045b391ae3d349e5a503ce98b093ee4e', 'soa_megacorp_employee_app1', 'ES交互查询测试专用应用1', null, 1024, '0', 'admin', '2017-09-26 19:26:59.931', 'admin', '2017-09-26 19:26:59.931');
insert into IQ_APPLICATION (pk_id, md_id, name, describe, note, max_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('ac1ecb0e542d41a5b8953dedf74c70fb', '045b391ae3d349e5a503ce98b093ee4e', 'soa_megacorp_employee_app2', 'ES交互查询测试专用应用1', null, 1025, '0', 'admin', '2017-09-27 16:42:22.172', 'admin', '2017-09-27 16:42:22.172');
insert into IQ_APPLICATION (pk_id, md_id, name, describe, note, max_num, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('debf02e42fe54a07a51f598f71ad7873', '7ffb213154444588900d239b7f5e3131', 'test_soa_cupatrxjnl_solr_hbase_app', '前置银联明细查询', null, 60000, '1', 'admin', '2017-09-12 16:37:45.690', 'admin', '2017-09-12 16:37:45.690');
commit;
prompt 5 records loaded
prompt Loading IQ_APPLICATION_ORDER_COLUMN...
insert into IQ_APPLICATION_ORDER_COLUMN (pk_id, app_id, seq, name, describe, type, order_type)
values ('a9fdee7b81744b45bd5b66e7f1197578', '170ebfd104e740ae99359d08b37f60ae', 1, 'acct_no', '账户号', 'STRING', 'ASC');
insert into IQ_APPLICATION_ORDER_COLUMN (pk_id, app_id, seq, name, describe, type, order_type)
values ('2970f0d3c4dc4c02bea39813cfdd817e', 'ac1ecb0e542d41a5b8953dedf74c70fb', 1, 'acct_zcrq', '账户注册日期', 'STRING', 'ASC');
commit;
prompt 2 records loaded
prompt Loading IQ_APPLICATION_QUERY_COLUMN...
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('515329c6dace48bdb7e9edeb27e76bc1', '1b82f3a00fa34de7a4116372d4f0d8ca', 1, 'system_tracr_no', '系统跟踪号', null, null, '1', null, '==', 'system_tracr_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('911429049db44b8fa24b9c825e07f48b', '1b82f3a00fa34de7a4116372d4f0d8ca', 2, 'card_no', '卡号', null, null, '1', null, '==', 'card_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('cec7f56a90724d7a80cf00b7a0724626', '1b82f3a00fa34de7a4116372d4f0d8ca', 3, 'tran_in_acct_no', '转入账号', null, null, '1', null, '==', 'tran_in_acct_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('726a2224e2404c8ab09366edea10bed4', '1b82f3a00fa34de7a4116372d4f0d8ca', 4, 'channel_type', '渠道类型', 'STRING', null, '1', null, '==', 'channel_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('215b7870821b4c4ab3fe6c74e28dd065', '1b82f3a00fa34de7a4116372d4f0d8ca', 5, 'amt', '交易金额(金额)', 'STRING', null, '1', null, '==', 'amt', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('97991cc8ebc94c9480a04bb6a286b8fd', '1b82f3a00fa34de7a4116372d4f0d8ca', 6, 'auth_code', '授权码', 'STRING', null, '1', null, '==', 'auth_code', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('0b844defa51349efa9c8e7f919b34849', '1b82f3a00fa34de7a4116372d4f0d8ca', 7, 'bu_settlement_date', '银联清算日期', 'STRING', null, '1', null, '==', 'bu_settlement_date', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('73ce4c6fc0334ddba1650d7c3e28b5b3', '1b82f3a00fa34de7a4116372d4f0d8ca', 8, 'teller_no', '柜员号', 'STRING', null, '1', null, '==', 'teller_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('48a0b5072f414387831204464a35f1d9', '1b82f3a00fa34de7a4116372d4f0d8ca', 9, 'bu_tran_code', '银联交易码', 'STRING', null, '1', null, '==', 'bu_tran_code', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('f6011b3f821647cf96bd4066b1492cb2', '1b82f3a00fa34de7a4116372d4f0d8ca', 10, 'ccy', '币种', 'STRING', null, '1', null, '==', 'ccy', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('cc76f9fb0b8f4398a31905af9a9bd8f5', '1b82f3a00fa34de7a4116372d4f0d8ca', 11, 'message_type', '报文类型', 'STRING', null, '1', null, '==', 'message_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('ced7da00fc4f4bcda16c64aaef6f1ff3', '1b82f3a00fa34de7a4116372d4f0d8ca', 12, 'tran_status', '交易状态', 'STRING', null, '1', null, '==', 'tran_status', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('260f5cd6449d42c08bd4bb5e4d2398e1', '1b82f3a00fa34de7a4116372d4f0d8ca', 13, 'term_seq_no', '终端流水号', 'STRING', null, '1', null, '==', 'term_seq_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('7e1e18ab1a3546c0be9287749d6f497f', '1b82f3a00fa34de7a4116372d4f0d8ca', 14, 'paymen_date', '支付日期', 'STRING', null, '1', null, '==', 'paymen_date', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('e9eb6376322445889427d943e6afbe98', '1b82f3a00fa34de7a4116372d4f0d8ca', 15, 'medium_type', '介质类型', 'STRING', null, '1', null, '==', 'medium_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('7ff53fdb90d64615b9c74f5730086e01', '1b82f3a00fa34de7a4116372d4f0d8ca', 16, 'retr_ref_no', '检索参考号', 'STRING', null, '1', null, '==', 'retr_ref_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('a041b1cd48c0487485aaa72d48ef026e', '1b82f3a00fa34de7a4116372d4f0d8ca', 17, 'uni_message_type', '统一报文类型', 'STRING', null, '1', null, '==', 'uni_message_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('97a09f7d1b694e1889ad9a4347f553c4', '1b82f3a00fa34de7a4116372d4f0d8ca', 18, 'bu_ret_code', '银联标准返回代码', 'STRING', null, '1', null, '==', 'bu_ret_code', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('0657f3bf3d1f446c9076834f35387a43', '5f7693ccf3f449fdb575cdd5e9d32f32', 1, 'system_tracr_no', '系统跟踪号', 'STRING', null, '1', null, '==', 'system_tracr_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('52cec670e39744be95dfa91c29b8f48a', '5f7693ccf3f449fdb575cdd5e9d32f32', 2, 'card_no', '卡号', 'STRING', null, '1', null, '==', 'card_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('5085801b88c646ec92010e701145a9b7', '5f7693ccf3f449fdb575cdd5e9d32f32', 3, 'tran_in_acct_no', '转入账号', 'STRING', null, '1', null, '==', 'tran_in_acct_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('411431a461ba4cd58456d6943bcc3b67', '5f7693ccf3f449fdb575cdd5e9d32f32', 4, 'channel_type', '渠道类型', 'STRING', null, '1', null, '==', 'channel_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('c3eae89dc347436fbc6944b47c418593', '5f7693ccf3f449fdb575cdd5e9d32f32', 5, 'amt', '交易金额(金额)', 'STRING', null, '1', null, '==', 'amt', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('5fd9b758a2e545999e6ae5074030c5f5', '5f7693ccf3f449fdb575cdd5e9d32f32', 6, 'auth_code', '授权码', 'STRING', null, '1', null, '==', 'auth_code', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('af29c3610ac445d6b39e4ae672e4f201', '5f7693ccf3f449fdb575cdd5e9d32f32', 7, 'bu_settlement_date', '银联清算日期', 'STRING', null, '1', null, '==', 'bu_settlement_date', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('be52291625334199a143c724f0e71344', '5f7693ccf3f449fdb575cdd5e9d32f32', 8, 'teller_no', '柜员号', 'STRING', null, '1', null, '==', 'teller_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('1c4a7acdab2145678a93e4b590002d92', '5f7693ccf3f449fdb575cdd5e9d32f32', 9, 'bu_tran_code', '银联交易码', 'STRING', null, '1', null, '==', 'bu_tran_code', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('4bd52868739844a38da3ccef2f5fc5a5', '5f7693ccf3f449fdb575cdd5e9d32f32', 10, 'ccy', '币种', 'STRING', null, '1', null, '==', 'ccy', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('3cfa42ed7b864df889093c2e18366520', '5f7693ccf3f449fdb575cdd5e9d32f32', 11, 'message_type', '报文类型', 'STRING', null, '1', null, '==', 'message_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('b53eb1dbddb34b77b96317a61d668c7c', '5f7693ccf3f449fdb575cdd5e9d32f32', 12, 'tran_status', '交易状态', 'STRING', null, '1', null, '==', 'tran_status', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('173f3962a408416d9b6e3c0e40014428', '5f7693ccf3f449fdb575cdd5e9d32f32', 13, 'term_seq_no', '终端流水号', 'STRING', null, '1', null, '==', 'term_seq_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('797a6d9f271a484ba3709be32a759bbe', '5f7693ccf3f449fdb575cdd5e9d32f32', 14, 'paymen_date', '支付日期', 'STRING', null, '1', null, '==', 'paymen_date', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('fa5532a6f0fe4b57ae50d83434e1e821', '5f7693ccf3f449fdb575cdd5e9d32f32', 15, 'medium_type', '介质类型', 'STRING', null, '1', null, '==', 'medium_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('a255e61893264ff1a6f492f9bf4e2d8d', '5f7693ccf3f449fdb575cdd5e9d32f32', 16, 'retr_ref_no', '检索参考号', 'STRING', null, '1', null, '==', 'retr_ref_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('b303197ee1ae455ea2f2010d5ebab63b', '5f7693ccf3f449fdb575cdd5e9d32f32', 17, 'uni_message_type', '统一报文类型', 'STRING', null, '1', null, '==', 'uni_message_type', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('41d58a471cb44a5b8cf606874b17ad17', '5f7693ccf3f449fdb575cdd5e9d32f32', 18, 'bu_ret_code', '银联标准返回代码', 'STRING', null, '1', null, '==', 'bu_ret_code', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('ddc86d856eec407686c38ff0502807ac', '170ebfd104e740ae99359d08b37f60ae', 1, 'acct_zcrq', '账户注册日期', 'STRING', null, '0', null, '>=', 'start_zcrq', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('53f52425c4e34c5aa1d19cf8aa3bf581', '170ebfd104e740ae99359d08b37f60ae', 2, 'acct_zcrq', '账户注册日期', 'STRING', null, '0', null, '<=', 'end_zcrq', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('d82f074111a1499ba3c92516da652ca2', '170ebfd104e740ae99359d08b37f60ae', 3, 'acct_no', '账户号', 'STRING', '20', '0', null, '==', 'acct_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('8fa2ddebc8984e2281e0616361a98ef7', '170ebfd104e740ae99359d08b37f60ae', 4, 'acct_name', '账户姓名', 'STRING', '30', '0', null, 'right like', 'acct_name', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('c9ac53f898a947078b317a884d7cf817', '170ebfd104e740ae99359d08b37f60ae', 5, 'age', '账户年龄', 'INT', null, '0', null, '>', 'age', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('9c42857428bd4e05adb6aba2b3099744', 'ac1ecb0e542d41a5b8953dedf74c70fb', 1, 'acct_zcrq', '开始日期', 'STRING', null, '1', null, '>=', 'start_zcrq', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('de66d30e27d64f519536075dd2dd8350', 'ac1ecb0e542d41a5b8953dedf74c70fb', 2, 'acct_zcrq', '结束日期', 'STRING', null, '1', null, '<=', 'end_zcrq', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('38575d93319a452195fe7a34bc607ed5', 'ac1ecb0e542d41a5b8953dedf74c70fb', 3, 'acct_no', '账户号', 'STRING', '20', '1', null, 'like', 'acct_no', '0');
insert into IQ_APPLICATION_QUERY_COLUMN (pk_id, app_id, seq, name, describe, type, length, is_need, default_val, operator, label, is_offer_out)
values ('3c17737871cc4572b0d0fbabf31d9086', 'ac1ecb0e542d41a5b8953dedf74c70fb', 4, 'acct_name', '账户姓名', 'STRING', '30', '1', null, 'right like', 'acct_name', '0');
commit;
prompt 45 records loaded
prompt Loading IQ_APPLICATION_RETURN_COLUMN...
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b9ac3745d7b249a7846e0dfe058dfad3', '1b82f3a00fa34de7a4116372d4f0d8ca', 'accept_branch_id', '受理机构', 'STRING', 'none', 'accept_branch_id', 1, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('79e97bac7c54484e84b48f3f5a6a6e54', '1b82f3a00fa34de7a4116372d4f0d8ca', 'branch_id', '发送方机构ID(机构码)', 'STRING', 'none', 'branch_id', 2, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('90a3b9caff2246c6a57aa2cb86230765', '1b82f3a00fa34de7a4116372d4f0d8ca', 'tran_time', '交易时间', 'STRING', 'none', 'tran_time', 3, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('61169a148e5c44d6a450f97824827693', '1b82f3a00fa34de7a4116372d4f0d8ca', 'system_tracr_no', '系统跟踪号', 'STRING', 'none', 'system_tracr_no', 4, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b602d0c147a64c0e81aa1c051ee33552', '1b82f3a00fa34de7a4116372d4f0d8ca', 'paymen_date', '支付日期', 'STRING', 'none', 'paymen_date', 5, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2b3da7aadece492f8fa7fa103b09c540', '1b82f3a00fa34de7a4116372d4f0d8ca', 'channel_type', '渠道类型', 'STRING', 'none', 'channel_type', 6, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d13b0ea19d7c40eab12e71fa9a4a6222', '1b82f3a00fa34de7a4116372d4f0d8ca', 'uni_message_type', '统一报文类型', 'STRING', 'none', 'uni_message_type', 7, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('4e2c7ec884a14c2c90ec94801d030892', '1b82f3a00fa34de7a4116372d4f0d8ca', 'bu_tran_code', '银联交易码', 'STRING', 'none', 'bu_tran_code', 8, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f96b2c5099eb425aac7823d1ea357d34', '1b82f3a00fa34de7a4116372d4f0d8ca', 'card_no', '卡号', 'STRING', 'none', 'card_no', 9, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('7fddcdc299a846c3a1281dcdea164a54', '1b82f3a00fa34de7a4116372d4f0d8ca', 'ic_trxamt', '交易金额(金额)', 'STRING', 'none', 'ic_trxamt', 10, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f8ee68e48ae84ca6a89db20de55dac54', '1b82f3a00fa34de7a4116372d4f0d8ca', 'accept_date', '受理日期', 'STRING', 'none', 'accept_date', 11, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('9900822f8008446380f5e1c69b2f7fd0', '1b82f3a00fa34de7a4116372d4f0d8ca', 'accept_time', '受理时间', 'STRING', 'none', 'accept_time', 12, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('80ab1b7bc8ae46c7ab49e46d23fa68b1', '1b82f3a00fa34de7a4116372d4f0d8ca', 'bu_settlement_date', '银联清算日期', 'STRING', 'none', 'bu_settlement_date', 13, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('dc8ace5d931c4707bf7762b7c97ae952', '1b82f3a00fa34de7a4116372d4f0d8ca', 'trader_type', '商户类型', 'STRING', 'none', 'trader_type', 14, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1d424d95ba12487fb1d91cd7694c6288', '1b82f3a00fa34de7a4116372d4f0d8ca', 'country_code', '国家代码', 'STRING', 'none', 'country_code', 15, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2616aa34c39e46f6af61b27635f95dae', '1b82f3a00fa34de7a4116372d4f0d8ca', 'acct_input_type', '主账号输入方式', 'STRING', 'none', 'acct_input_type', 16, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('59e9b1f71d8c4cf9ba05dbe4b36823a4', '1b82f3a00fa34de7a4116372d4f0d8ca', 'passwoar_inpuat_type', '密码输入方式', 'STRING', 'none', 'passwoar_inpuat_type', 17, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('4371ca23e9cd4dfeadbc28dd0b108717', '1b82f3a00fa34de7a4116372d4f0d8ca', 'card_serial_no', '卡序号', 'STRING', 'none', 'card_serial_no', 18, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('89441c2bc2864884b4d510c6eaa343d1', '1b82f3a00fa34de7a4116372d4f0d8ca', 'commission', '手续费金额', 'STRING', 'none', 'commission', 19, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('c810d2ce133b4588a3643c311277f11e', '1b82f3a00fa34de7a4116372d4f0d8ca', 'retr_ref_no', '检索参考号', 'STRING', 'none', 'retr_ref_no', 20, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3756ea3be74c43d5a0409c07d05c512e', '1b82f3a00fa34de7a4116372d4f0d8ca', 'auth_code', '授权码', 'STRING', 'none', 'auth_code', 21, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('32b006f61d9941659f407920416752a5', '1b82f3a00fa34de7a4116372d4f0d8ca', 'bu_ret_code', '银联标准返回代码', 'STRING', 'none', 'bu_ret_code', 22, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('31590aa9e065417b857a7f853a82b687', '1b82f3a00fa34de7a4116372d4f0d8ca', 'term_no', '终端号', 'STRING', 'none', 'term_no', 23, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('506d4af7c63743b0b8fb0c13e2af8886', '1b82f3a00fa34de7a4116372d4f0d8ca', 'mech_no', '商户号', 'STRING', 'none', 'mech_no', 24, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('34883a1ddb0f4058af38b447d650769a', '1b82f3a00fa34de7a4116372d4f0d8ca', 'trader_name_addr', '商户名称地址', 'STRING', 'none', 'trader_name_addr', 25, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('6be4b7578baf43d0b53f7c2aba0bd37a', '1b82f3a00fa34de7a4116372d4f0d8ca', 'add_data', '附加数据', 'STRING', 'none', 'add_data', 26, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('5eaea5259a434534bc1b37b242da212b', '1b82f3a00fa34de7a4116372d4f0d8ca', 'bu_ccy', '银联货币代码', 'STRING', 'none', 'bu_ccy', 27, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('6b126f43f38043ceb80db5ef1b19e236', '1b82f3a00fa34de7a4116372d4f0d8ca', 'reason_msg', '原因内容', 'STRING', 'none', 'reason_msg', 28, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('bf7b16c310f44feabb6a093f603df97d', '1b82f3a00fa34de7a4116372d4f0d8ca', 'input_cap_flag', '输入能力标志', 'STRING', 'none', 'input_cap_flag', 29, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('7aa4352af6874069bdb4d76503b3062c', '1b82f3a00fa34de7a4116372d4f0d8ca', 'ws_type', '终端类型', 'STRING', 'none', 'ws_type', 30, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('fb3d4230c3ba43ab9e404b6e9d9a6b4d', '1b82f3a00fa34de7a4116372d4f0d8ca', 'interactive_mode', '交互方式', 'STRING', 'none', 'interactive_mode', 31, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('5e3a993a56114635a035b998415b2c55', '1b82f3a00fa34de7a4116372d4f0d8ca', 'special_fee_type', '特殊费用类型', 'STRING', 'none', 'special_fee_type', 32, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('cf417e36922b4dca92ca9809141ebf1b', '1b82f3a00fa34de7a4116372d4f0d8ca', 'special_fee_level', '特殊费用级别', 'STRING', 'none', 'special_fee_level', 33, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('70dc5fc8805048dc90b3da322dddf56a', '1b82f3a00fa34de7a4116372d4f0d8ca', 'medium_type', '介质类型', 'STRING', 'none', 'medium_type', 34, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('83a6b40fcbde4aa69bbe878736f51f6c', '1b82f3a00fa34de7a4116372d4f0d8ca', 'tran_mode', '交易模式', 'STRING', 'none', 'tran_mode', 35, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('932e7b2249dc40cd9ecf5818966b5f9b', '1b82f3a00fa34de7a4116372d4f0d8ca', 'use_type', '使用类型', 'STRING', 'none', 'use_type', 36, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('70e1860644a54ae586ffa03f9c01dc2c', '1b82f3a00fa34de7a4116372d4f0d8ca', 'acct_type', '账户类型', 'STRING', 'none', 'acct_type', 37, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('352e8378e1674b239fa4efc14673fe12', '1b82f3a00fa34de7a4116372d4f0d8ca', 'user_level', '用户级别', 'STRING', 'none', 'user_level', 38, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('91a2ee8e50b2442e81ce59b3881febac', '1b82f3a00fa34de7a4116372d4f0d8ca', 'product_type', '产品类型', 'STRING', 'none', 'product_type', 39, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('018a20c2acc24dd884c0182127d1b30a', '1b82f3a00fa34de7a4116372d4f0d8ca', 'ori_accept_branch_id', '原受理机构标识码', 'STRING', 'none', 'ori_accept_branch_id', 40, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('7e105811ecc946cf91549e27c8be1764', '1b82f3a00fa34de7a4116372d4f0d8ca', 'old_branch_id', '原发送方机构ID', 'STRING', 'none', 'old_branch_id', 41, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('64e4d07a09e54966939f77498ac03f86', '1b82f3a00fa34de7a4116372d4f0d8ca', 'ori_tran_time', '原交易时间', 'STRING', 'none', 'ori_tran_time', 42, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('c9d94604fc6e4d1b968941f98400614b', '1b82f3a00fa34de7a4116372d4f0d8ca', 'ori_system_tracr_no', '原系统跟踪号', 'STRING', 'none', 'ori_system_tracr_no', 43, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('8687cc03ff3345ffb97fde43b71b793e', '1b82f3a00fa34de7a4116372d4f0d8ca', 'rcv_bank', '接收机构', 'STRING', 'none', 'rcv_bank', 44, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('cc1e226a83824f14b860d4b5e34898a0', '1b82f3a00fa34de7a4116372d4f0d8ca', 'tran_in_acct_no', '转入账号', 'STRING', 'none', 'tran_in_acct_no', 45, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('5bce8fa2e90b492fa657eca8a7921cd4', '1b82f3a00fa34de7a4116372d4f0d8ca', 'trader_discount', '商户折扣率', 'STRING', 'none', 'trader_discount', 46, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('6631fad3ed9a4284b874bf5e5c6772d8', '1b82f3a00fa34de7a4116372d4f0d8ca', 'term_seq_no', '终端流水号', 'STRING', 'none', 'term_seq_no', 47, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e4fd95dadb0849f196b6c1c3ded595d3', '1b82f3a00fa34de7a4116372d4f0d8ca', 'app_bank_id', '经办行行号', 'STRING', 'none', 'app_bank_id', 48, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3bcc9e8b9ef548c5a924d0951cddabdb', '1b82f3a00fa34de7a4116372d4f0d8ca', 'bank_type', '银行类型', 'STRING', 'none', 'bank_type', 49, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('646cbb16b2804354a2751aca230ac4dc', '1b82f3a00fa34de7a4116372d4f0d8ca', 'app_branch_no', '经办机构', 'STRING', 'none', 'app_branch_no', 50, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('5a6b0e4a4e284b01a9f78cbd6b9abd8f', '1b82f3a00fa34de7a4116372d4f0d8ca', 'main_bank_id', '主交易行行号', 'STRING', 'none', 'main_bank_id', 51, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3f59fe7678ca4721ad9a98845ec66ce8', '1b82f3a00fa34de7a4116372d4f0d8ca', 'main_bank_type', '主交易银行类型', 'STRING', 'none', 'main_bank_type', 52, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('de2d00fb4c0c4272a9303e7ed6958f0f', '1b82f3a00fa34de7a4116372d4f0d8ca', 'main_branch_id', '主交易机构码', 'STRING', 'none', 'main_branch_id', 53, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('276ff353066d426dace2cdac35499c7a', '1b82f3a00fa34de7a4116372d4f0d8ca', 'sub_acct_no', '子账号', 'STRING', 'none', 'sub_acct_no', 54, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1528faf662264a828cb47e402017cfe4', '1b82f3a00fa34de7a4116372d4f0d8ca', 'assi_bank_id', '副交易行号', 'STRING', 'none', 'assi_bank_id', 55, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('0efa7b07812f4aa7be5d5b39152dadc0', '1b82f3a00fa34de7a4116372d4f0d8ca', 'assi_bank_type', '副交易银行类型', 'STRING', 'none', 'assi_bank_type', 56, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('68e7fdc86ac24de2b9169a72f9cad5a9', '1b82f3a00fa34de7a4116372d4f0d8ca', 'assi_branch_id', '副交易机构码', 'STRING', 'none', 'assi_branch_id', 57, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2c250c9a9dcd4e7aa10bec879dbc6040', '1b82f3a00fa34de7a4116372d4f0d8ca', 'teller_no', '柜员号', 'STRING', 'none', 'teller_no', 58, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d002b34c9b5a4d93a9616d54ee7270ac', '1b82f3a00fa34de7a4116372d4f0d8ca', 'tran_code', '交易码', 'STRING', 'none', 'tran_code', 59, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('39cdf71e72274b36a4fb9bd48b75d8d4', '5f7693ccf3f449fdb575cdd5e9d32f32', 'accept_branch_id', '受理机构', 'STRING', 'none', 'accept_branch_id', 1, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('29d9636e374444428f92b3883e7766e3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'branch_id', '发送方机构ID(机构码)', 'STRING', 'none', 'branch_id', 2, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b5391d2aa8844df69467fa174d545ece', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_time', '交易时间', 'STRING', 'none', 'tran_time', 3, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('9757535129f148c1893531d3648ae763', '5f7693ccf3f449fdb575cdd5e9d32f32', 'system_tracr_no', '系统跟踪号', 'STRING', 'none', 'system_tracr_no', 4, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('75c6b809c02744f3be340d8c11d13459', '5f7693ccf3f449fdb575cdd5e9d32f32', 'paymen_date', '支付日期', 'STRING', 'none', 'paymen_date', 5, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('42edf515303642c4aada75067c9c47f3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'channel_type', '渠道类型', 'STRING', 'none', 'channel_type', 6, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('ef3d4394feda4ea68854da1f7d870581', '5f7693ccf3f449fdb575cdd5e9d32f32', 'uni_message_type', '统一报文类型', 'STRING', 'none', 'uni_message_type', 7, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f623ef1226fa43939f4fa44545959fa1', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bu_tran_code', '银联交易码', 'STRING', 'none', 'bu_tran_code', 8, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e2032a2147b4433288910267cbe176fe', '5f7693ccf3f449fdb575cdd5e9d32f32', 'card_no', '卡号', 'STRING', 'none', 'card_no', 9, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b6ba3905668d4cd4903e9de1415bc93e', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ic_trxamt', '交易金额(金额)', 'STRING', 'none', 'ic_trxamt', 10, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('fad36dcfb86846eeb48727b43ad79212', '5f7693ccf3f449fdb575cdd5e9d32f32', 'accept_date', '受理日期', 'STRING', 'none', 'accept_date', 11, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('72a89d94f7954b74813ac44948d6e482', '5f7693ccf3f449fdb575cdd5e9d32f32', 'accept_time', '受理时间', 'STRING', 'none', 'accept_time', 12, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('16c6b639262f447c9ab19e3399d8f581', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bu_settlement_date', '银联清算日期', 'STRING', 'none', 'bu_settlement_date', 13, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('a2ab116d09354efbb71bb68a69a4da08', '5f7693ccf3f449fdb575cdd5e9d32f32', 'trader_type', '商户类型', 'STRING', 'none', 'trader_type', 14, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('9ef5d566e912459782b7ecc45af9517f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'country_code', '国家代码', 'STRING', 'none', 'country_code', 15, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2dc55af844124123af6161e2a1b115f4', '5f7693ccf3f449fdb575cdd5e9d32f32', 'acct_input_type', '主账号输入方式', 'STRING', 'none', 'acct_input_type', 16, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('09147b474ed141f296f114fb4e2ee15f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'passwoar_inpuat_type', '密码输入方式', 'STRING', 'none', 'passwoar_inpuat_type', 17, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('dbd01f5bc8be4411a5648749f0ca4be7', '5f7693ccf3f449fdb575cdd5e9d32f32', 'card_serial_no', '卡序号', 'STRING', 'none', 'card_serial_no', 18, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d15bfea6f43f4d67894fe28e88d861ea', '5f7693ccf3f449fdb575cdd5e9d32f32', 'commission', '手续费金额', 'STRING', 'none', 'commission', 19, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f4122e95ef0146d6807f309f2f00ed0b', '5f7693ccf3f449fdb575cdd5e9d32f32', 'retr_ref_no', '检索参考号', 'STRING', 'none', 'retr_ref_no', 20, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('509def8dd1cd420c933dffa8f1ac3e2f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'auth_code', '授权码', 'STRING', 'none', 'auth_code', 21, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('619feaef59e84637b05a495f3733f6be', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bu_ret_code', '银联标准返回代码', 'STRING', 'none', 'bu_ret_code', 22, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('cecbecd745ed4415a357e34d8c1a7715', '5f7693ccf3f449fdb575cdd5e9d32f32', 'term_no', '终端号', 'STRING', 'none', 'term_no', 23, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e0c89bea44524d109589e12df0d41cd5', '5f7693ccf3f449fdb575cdd5e9d32f32', 'mech_no', '商户号', 'STRING', 'none', 'mech_no', 24, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('7a97b2aa82e84aedb149bc738c7f03bc', '5f7693ccf3f449fdb575cdd5e9d32f32', 'trader_name_addr', '商户名称地址', 'STRING', 'none', 'trader_name_addr', 25, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('291fb5bf62494753a19acbb6d237b451', '5f7693ccf3f449fdb575cdd5e9d32f32', 'add_data', '附加数据', 'STRING', 'none', 'add_data', 26, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('52340052cee941bfb9eb66516b9237a3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bu_ccy', '银联货币代码', 'STRING', 'none', 'bu_ccy', 27, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('55f68febb2ab4dc78ab3ef28bf86becb', '5f7693ccf3f449fdb575cdd5e9d32f32', 'reason_msg', '原因内容', 'STRING', 'none', 'reason_msg', 28, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('68b66b1096e44d9d87aaabb6170abb1d', '5f7693ccf3f449fdb575cdd5e9d32f32', 'input_cap_flag', '输入能力标志', 'STRING', 'none', 'input_cap_flag', 29, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('25f5b8bfb973447c8d84d0652a00002a', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ws_type', '终端类型', 'STRING', 'none', 'ws_type', 30, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('fb6c496338184a908c67dad2ed9b9679', '5f7693ccf3f449fdb575cdd5e9d32f32', 'interactive_mode', '交互方式', 'STRING', 'none', 'interactive_mode', 31, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('79be3c20acaf46c7838f5e525c11b64f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'special_fee_type', '特殊费用类型', 'STRING', 'none', 'special_fee_type', 32, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('102412f54a5543da848d4b4b1f25607f', '5f7693ccf3f449fdb575cdd5e9d32f32', 'special_fee_level', '特殊费用级别', 'STRING', 'none', 'special_fee_level', 33, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e5428a48552b49788f6b5355bceb94f7', '5f7693ccf3f449fdb575cdd5e9d32f32', 'medium_type', '介质类型', 'STRING', 'none', 'medium_type', 34, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('87838570283c483abfd061c2a61e0621', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_mode', '交易模式', 'STRING', 'none', 'tran_mode', 35, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3cc11212e87f46bfa67427a979e64f52', '5f7693ccf3f449fdb575cdd5e9d32f32', 'use_type', '使用类型', 'STRING', 'none', 'use_type', 36, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('47d9cab3bd804831aa0252f90eb459ff', '5f7693ccf3f449fdb575cdd5e9d32f32', 'acct_type', '账户类型', 'STRING', 'none', 'acct_type', 37, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('a443af678c5b433ca338482d14f8f398', '5f7693ccf3f449fdb575cdd5e9d32f32', 'user_level', '用户级别', 'STRING', 'none', 'user_level', 38, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('a5e224a2cba34dd8a46a1bf8f6e6a8bd', '5f7693ccf3f449fdb575cdd5e9d32f32', 'product_type', '产品类型', 'STRING', 'none', 'product_type', 39, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d515742b3d014f949f9819855ac49f85', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ori_accept_branch_id', '原受理机构标识码', 'STRING', 'none', 'ori_accept_branch_id', 40, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('35f97ac1547d44a5bf98323d2cc4c282', '5f7693ccf3f449fdb575cdd5e9d32f32', 'old_branch_id', '原发送方机构ID', 'STRING', 'none', 'old_branch_id', 41, null);
commit;
prompt 100 records committed...
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('436aa788f25343189059d5a5a05d8b86', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ori_tran_time', '原交易时间', 'STRING', 'none', 'ori_tran_time', 42, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3931799600d7423ebecc28688ea512cc', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ori_system_tracr_no', '原系统跟踪号', 'STRING', 'none', 'ori_system_tracr_no', 43, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('869e01be74ce4f4e83929f142aded7f3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'rcv_bank', '接收机构', 'STRING', 'none', 'rcv_bank', 44, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2e8653426fb046baa75a7a18f6f0c94b', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_in_acct_no', '转入账号', 'STRING', 'none', 'tran_in_acct_no', 45, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('dd30855b35d647c09b4bcc37cb004d3a', '5f7693ccf3f449fdb575cdd5e9d32f32', 'trader_discount', '商户折扣率', 'STRING', 'none', 'trader_discount', 46, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d0f130062243426a86e4098fe65e1425', '5f7693ccf3f449fdb575cdd5e9d32f32', 'term_seq_no', '终端流水号', 'STRING', 'none', 'term_seq_no', 47, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('cf83bb41f58c4394b83651be0ed146b4', '5f7693ccf3f449fdb575cdd5e9d32f32', 'app_bank_id', '经办行行号', 'STRING', 'none', 'app_bank_id', 48, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1d530f4de92247f7beebb832fb05e904', '5f7693ccf3f449fdb575cdd5e9d32f32', 'bank_type', '银行类型', 'STRING', 'none', 'bank_type', 49, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('600f7cda901845a3b854e7601c511cb6', '5f7693ccf3f449fdb575cdd5e9d32f32', 'app_branch_no', '经办机构', 'STRING', 'none', 'app_branch_no', 50, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('5804200b257e448eb9540d3c1284535e', '5f7693ccf3f449fdb575cdd5e9d32f32', 'main_bank_id', '主交易行行号', 'STRING', 'none', 'main_bank_id', 51, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d4e51ea071c54df6a49f1e3ce3888424', '5f7693ccf3f449fdb575cdd5e9d32f32', 'main_bank_type', '主交易银行类型', 'STRING', 'none', 'main_bank_type', 52, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1a42ca92ba2c4a46b0f47b46d407bcba', '5f7693ccf3f449fdb575cdd5e9d32f32', 'main_branch_id', '主交易机构码', 'STRING', 'none', 'main_branch_id', 53, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f78ac286abfa4d06afca02c905e1e5ca', '5f7693ccf3f449fdb575cdd5e9d32f32', 'sub_acct_no', '子账号', 'STRING', 'none', 'sub_acct_no', 54, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e10f37cc973046fc913d4dd1cdbb1399', '5f7693ccf3f449fdb575cdd5e9d32f32', 'assi_bank_id', '副交易行号', 'STRING', 'none', 'assi_bank_id', 55, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('07821b018a3c49209db9572b72e4e88c', '5f7693ccf3f449fdb575cdd5e9d32f32', 'assi_bank_type', '副交易银行类型', 'STRING', 'none', 'assi_bank_type', 56, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3dbd5711629e45579c720df127618243', '5f7693ccf3f449fdb575cdd5e9d32f32', 'assi_branch_id', '副交易机构码', 'STRING', 'none', 'assi_branch_id', 57, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d28819a832834709a5ead6acc6d42f83', '5f7693ccf3f449fdb575cdd5e9d32f32', 'teller_no', '柜员号', 'STRING', 'none', 'teller_no', 58, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('a2f926dba81644809d8869a44f2459c9', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_code', '交易码', 'STRING', 'none', 'tran_code', 59, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('5f06672ea7b642a88bc0e63aa72bdfa6', '5f7693ccf3f449fdb575cdd5e9d32f32', 'ccy', '币种', 'STRING', 'none', 'ccy', 60, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('85c672fa0df84a908aaa83651217bc6d', '5f7693ccf3f449fdb575cdd5e9d32f32', 'dr_cr_flag', '借贷标志', 'STRING', 'none', 'dr_cr_flag', 61, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('c6be339a27c84c1285e8f3d4ae02ba2e', '5f7693ccf3f449fdb575cdd5e9d32f32', 'amt', '交易金额(金额)', 'STRING', 'none', 'amt', 62, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('ac3377cd3caa47a7a7baddd3d1775a7c', '5f7693ccf3f449fdb575cdd5e9d32f32', 'limit_id', '限额编号', 'STRING', 'none', 'limit_id', 63, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('bba614749f494d7e93e409e52d78263d', '5f7693ccf3f449fdb575cdd5e9d32f32', 'main_card_no', '主卡号', 'STRING', 'none', 'main_card_no', 64, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1147d40acd0a4c1b9b9f51aa1957cafc', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_limit_id', '交易限额号', 'STRING', 'none', 'tran_limit_id', 65, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1cf7d9eb90874e0db6b12ea2a108bbe7', '5f7693ccf3f449fdb575cdd5e9d32f32', 'business_scope', '主经营范围', 'STRING', 'none', 'business_scope', 66, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('db6177f5d57044c6b315e377058e1ff3', '5f7693ccf3f449fdb575cdd5e9d32f32', 'message_type', '报文类型', 'STRING', 'none', 'message_type', 67, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d05e10bf3396482e841922080d2ef648', '5f7693ccf3f449fdb575cdd5e9d32f32', 'tran_status', '交易状态', 'STRING', 'none', 'tran_status', 68, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('ea18d21db0a9458caa6044af50b0d94a', '1b82f3a00fa34de7a4116372d4f0d8ca', 'ccy', '币种', 'STRING', 'none', 'ccy', 60, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('4d6113218014490ea6d5b7280693f728', '1b82f3a00fa34de7a4116372d4f0d8ca', 'dr_cr_flag', '借贷标志', 'STRING', 'none', 'dr_cr_flag', 61, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('7e7fc335769f4164a108fcc92b731a83', '1b82f3a00fa34de7a4116372d4f0d8ca', 'amt', '交易金额(金额)', 'STRING', 'none', 'amt', 62, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f91de2e5336a45ecac3a5c7e450f401c', '1b82f3a00fa34de7a4116372d4f0d8ca', 'limit_id', '限额编号', 'STRING', 'none', 'limit_id', 63, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('97ccabd785b740a08ab04b886875310d', '1b82f3a00fa34de7a4116372d4f0d8ca', 'main_card_no', '主卡号', 'STRING', 'none', 'main_card_no', 64, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('03f785dd3d1049f090dcc6a8a65b0910', '1b82f3a00fa34de7a4116372d4f0d8ca', 'tran_limit_id', '交易限额号', 'STRING', 'none', 'tran_limit_id', 65, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2df7bf975f054fa5a5a90b79a0e40afc', '1b82f3a00fa34de7a4116372d4f0d8ca', 'business_scope', '主经营范围', 'STRING', 'none', 'business_scope', 66, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('564898fd832d42e2a01c263eacb4b751', '1b82f3a00fa34de7a4116372d4f0d8ca', 'message_type', '报文类型', 'STRING', 'none', 'message_type', 67, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d07bec8a80aa46f0852bc18c3fc668e2', '1b82f3a00fa34de7a4116372d4f0d8ca', 'tran_status', '交易状态', 'STRING', 'none', 'tran_status', 68, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1b298635e8c24de5910663d6c4ae517d', 'debf02e42fe54a07a51f598f71ad7873', 'accept_branch_id', '受理机构', 'STRING', 'none', 'accept_branch_id', 1, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3c2d80cc3b9148d8a5e76bcaa3a07bda', 'debf02e42fe54a07a51f598f71ad7873', 'branch_id', '发送方机构ID(机构码)', 'STRING', 'none', 'branch_id', 2, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('175411708562464b825b473c5e6c2d2a', 'debf02e42fe54a07a51f598f71ad7873', 'tran_time', '交易时间', 'STRING', 'none', 'tran_time', 3, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('c9ea0e4ceac1460faa7e950ec991a0e8', 'debf02e42fe54a07a51f598f71ad7873', 'system_tracr_no', '系统跟踪号', 'STRING', 'none', 'system_tracr_no', 4, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d5e68f56a2c5454a949109a5482703ae', 'debf02e42fe54a07a51f598f71ad7873', 'paymen_date', '支付日期', 'STRING', 'none', 'paymen_date', 5, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('55a16c153fff48858b994c568662e4b8', 'debf02e42fe54a07a51f598f71ad7873', 'channel_type', '渠道类型', 'STRING', 'none', 'channel_type', 6, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b38af16ad7384fd5832dbc0b25530f47', 'debf02e42fe54a07a51f598f71ad7873', 'uni_message_type', '统一报文类型', 'STRING', 'none', 'uni_message_type', 7, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('6a718332a3504dda988356606e79e4eb', 'debf02e42fe54a07a51f598f71ad7873', 'bu_tran_code', '银联交易码', 'STRING', 'none', 'bu_tran_code', 8, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('ce2ab7553a1347a69d9adfc53a7cb67a', 'debf02e42fe54a07a51f598f71ad7873', 'card_no', '卡号', 'STRING', 'none', 'card_no', 9, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('c6341e0ae44945248c3ca469d59d4ccd', 'debf02e42fe54a07a51f598f71ad7873', 'ic_trxamt', '交易金额(金额)', 'STRING', 'none', 'ic_trxamt', 10, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('028ecfbe4be7441a9603153c9122dd70', 'debf02e42fe54a07a51f598f71ad7873', 'accept_date', '受理日期', 'STRING', 'none', 'accept_date', 11, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3865793751d7410eae91ef4b7097c41b', 'debf02e42fe54a07a51f598f71ad7873', 'accept_time', '受理时间', 'STRING', 'none', 'accept_time', 12, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('152bf0bb5a2b42e78c119e2bdad960ae', 'debf02e42fe54a07a51f598f71ad7873', 'bu_settlement_date', '银联清算日期', 'STRING', 'none', 'bu_settlement_date', 13, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('af9f73a7d0c74dfb9ddb1377c680283b', 'debf02e42fe54a07a51f598f71ad7873', 'trader_type', '商户类型', 'STRING', 'none', 'trader_type', 14, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('bc8f78a2dc4b4bbe99d8e9cd835507a1', 'debf02e42fe54a07a51f598f71ad7873', 'country_code', '国家代码', 'STRING', 'none', 'country_code', 15, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b9511cd7eb3343e68fc2abaf93d19779', 'debf02e42fe54a07a51f598f71ad7873', 'acct_input_type', '主账号输入方式', 'STRING', 'none', 'acct_input_type', 16, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('545dd83aa8d1470bb837be2cfb107645', 'debf02e42fe54a07a51f598f71ad7873', 'passwoar_inpuat_type', '密码输入方式', 'STRING', 'none', 'passwoar_inpuat_type', 17, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('6a0d91a145e741a496cb2a36c1f449c2', 'debf02e42fe54a07a51f598f71ad7873', 'card_serial_no', '卡序号', 'STRING', 'none', 'card_serial_no', 18, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1fdd58ab49b7421fa48a07c9a84ccc19', 'debf02e42fe54a07a51f598f71ad7873', 'commission', '手续费金额', 'STRING', 'none', 'commission', 19, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d2a51be04bc64d08a8e906fa0d35c942', 'debf02e42fe54a07a51f598f71ad7873', 'retr_ref_no', '检索参考号', 'STRING', 'none', 'retr_ref_no', 20, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('280aca0551d14235afe2db48cd020014', 'debf02e42fe54a07a51f598f71ad7873', 'auth_code', '授权码', 'STRING', 'none', 'auth_code', 21, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('0cc47701371a45a7b927b1dbec2a0c6f', 'debf02e42fe54a07a51f598f71ad7873', 'bu_ret_code', '银联标准返回代码', 'STRING', 'none', 'bu_ret_code', 22, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2021b309e8c045baae584f2508433698', 'debf02e42fe54a07a51f598f71ad7873', 'term_no', '终端号', 'STRING', 'none', 'term_no', 23, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('00248c2bd0cf439c801a230a7669ec96', 'debf02e42fe54a07a51f598f71ad7873', 'mech_no', '商户号', 'STRING', 'none', 'mech_no', 24, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('62761b683c224ea3b10bd04ffe2037f4', 'debf02e42fe54a07a51f598f71ad7873', 'trader_name_addr', '商户名称地址', 'STRING', 'none', 'trader_name_addr', 25, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('90dd260e748b47eebcbf4fcb05242af6', 'debf02e42fe54a07a51f598f71ad7873', 'add_data', '附加数据', 'STRING', 'none', 'add_data', 26, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('047685b7e26441ea9e81fdd95cd17c85', 'debf02e42fe54a07a51f598f71ad7873', 'bu_ccy', '银联货币代码', 'STRING', 'none', 'bu_ccy', 27, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('08f25b95ab544a85bddcf26038160969', 'debf02e42fe54a07a51f598f71ad7873', 'reason_msg', '原因内容', 'STRING', 'none', 'reason_msg', 28, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('56fc1d07a5a94a888b03c46a4f373fd9', 'debf02e42fe54a07a51f598f71ad7873', 'input_cap_flag', '输入能力标志', 'STRING', 'none', 'input_cap_flag', 29, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('fbd18b48eed5469c9a324a93feb408ef', 'debf02e42fe54a07a51f598f71ad7873', 'ws_type', '终端类型', 'STRING', 'none', 'ws_type', 30, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2429c5c688804fe68da7cb6b6b4d1685', 'debf02e42fe54a07a51f598f71ad7873', 'interactive_mode', '交互方式', 'STRING', 'none', 'interactive_mode', 31, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('9553b8e7a33a4981bd16e2dd201f4505', 'debf02e42fe54a07a51f598f71ad7873', 'special_fee_type', '特殊费用类型', 'STRING', 'none', 'special_fee_type', 32, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('086f72ff6c024c4bae2ec5f10d7efb6f', 'debf02e42fe54a07a51f598f71ad7873', 'special_fee_level', '特殊费用级别', 'STRING', 'none', 'special_fee_level', 33, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('6a02120ef3a6418ab4cf744ece486bf9', 'debf02e42fe54a07a51f598f71ad7873', 'medium_type', '介质类型', 'STRING', 'none', 'medium_type', 34, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f630ace53f234da4a394aae868f4de99', 'debf02e42fe54a07a51f598f71ad7873', 'tran_mode', '交易模式', 'STRING', 'none', 'tran_mode', 35, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1ac44293b3e94c41b6767f72ec47fe86', 'debf02e42fe54a07a51f598f71ad7873', 'use_type', '使用类型', 'STRING', 'none', 'use_type', 36, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('7d5f703cc7a5448cb93e0afd0e3a1fb5', 'debf02e42fe54a07a51f598f71ad7873', 'acct_type', '账户类型', 'STRING', 'none', 'acct_type', 37, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('efe7fa22c9cb4246b21bb1c7b72e573e', 'debf02e42fe54a07a51f598f71ad7873', 'user_level', '用户级别', 'STRING', 'none', 'user_level', 38, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b08d06e7f56542bda565fd5dd399e3d1', 'debf02e42fe54a07a51f598f71ad7873', 'product_type', '产品类型', 'STRING', 'none', 'product_type', 39, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b9a9feb3182e4ce2b51147e60cfd339e', 'debf02e42fe54a07a51f598f71ad7873', 'ori_accept_branch_id', '原受理机构标识码', 'STRING', 'none', 'ori_accept_branch_id', 40, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f65f2fea19144d6685d4e6625466b029', 'debf02e42fe54a07a51f598f71ad7873', 'old_branch_id', '原发送方机构ID', 'STRING', 'none', 'old_branch_id', 41, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('44e2d3e95fd748bba96b0669384a9cd5', 'debf02e42fe54a07a51f598f71ad7873', 'ori_tran_time', '原交易时间', 'STRING', 'none', 'ori_tran_time', 42, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e008a1f7a7904f01adc75c81e0bde7a8', 'debf02e42fe54a07a51f598f71ad7873', 'ori_system_tracr_no', '原系统跟踪号', 'STRING', 'none', 'ori_system_tracr_no', 43, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('4bc9215895c24c9b9d23e890ba60665c', 'debf02e42fe54a07a51f598f71ad7873', 'rcv_bank', '接收机构', 'STRING', 'none', 'rcv_bank', 44, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('04e29336ef554bfc804b25349c1f089b', 'debf02e42fe54a07a51f598f71ad7873', 'tran_in_acct_no', '转入账号', 'STRING', 'none', 'tran_in_acct_no', 45, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('54dabc0db1f243ada5ddc804ca21c11c', 'debf02e42fe54a07a51f598f71ad7873', 'trader_discount', '商户折扣率', 'STRING', 'none', 'trader_discount', 46, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('c9f6d8e3bf9c494facd45c86a4ffbbcf', 'debf02e42fe54a07a51f598f71ad7873', 'term_seq_no', '终端流水号', 'STRING', 'none', 'term_seq_no', 47, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('0b8f6dab8b7940b09ac82059cf9d0963', 'debf02e42fe54a07a51f598f71ad7873', 'app_bank_id', '经办行行号', 'STRING', 'none', 'app_bank_id', 48, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('4d21068e6ce045f7b23a8f1c3e9ae054', 'debf02e42fe54a07a51f598f71ad7873', 'bank_type', '银行类型', 'STRING', 'none', 'bank_type', 49, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('74a8f843f0794b04b73a188c6483ab1a', 'debf02e42fe54a07a51f598f71ad7873', 'app_branch_no', '经办机构', 'STRING', 'none', 'app_branch_no', 50, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2c827e1ddec84e2396c10b36c1432563', 'debf02e42fe54a07a51f598f71ad7873', 'main_bank_id', '主交易行行号', 'STRING', 'none', 'main_bank_id', 51, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('8dbbc956324a466baff39b497980748a', 'debf02e42fe54a07a51f598f71ad7873', 'main_bank_type', '主交易银行类型', 'STRING', 'none', 'main_bank_type', 52, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b7c54b9e757b48c4b635de0c8a4bfeab', 'debf02e42fe54a07a51f598f71ad7873', 'main_branch_id', '主交易机构码', 'STRING', 'none', 'main_branch_id', 53, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('8d5337516d1d4958b42d1b412c0cd462', 'debf02e42fe54a07a51f598f71ad7873', 'sub_acct_no', '子账号', 'STRING', 'none', 'sub_acct_no', 54, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('1cad5e367a534844804752791e381f3b', 'debf02e42fe54a07a51f598f71ad7873', 'assi_bank_id', '副交易行号', 'STRING', 'none', 'assi_bank_id', 55, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('375ad82d3b124cc78768ed51a9d8a8fb', 'debf02e42fe54a07a51f598f71ad7873', 'assi_bank_type', '副交易银行类型', 'STRING', 'none', 'assi_bank_type', 56, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f2cf425611f442039866c5cf85ea60c9', 'debf02e42fe54a07a51f598f71ad7873', 'assi_branch_id', '副交易机构码', 'STRING', 'none', 'assi_branch_id', 57, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('785b15986d744108a82d163491fe1020', 'debf02e42fe54a07a51f598f71ad7873', 'teller_no', '柜员号', 'STRING', 'none', 'teller_no', 58, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('903b4247046c4180944ff81dee36bb06', 'debf02e42fe54a07a51f598f71ad7873', 'tran_code', '交易码', 'STRING', 'none', 'tran_code', 59, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d7d2d51735024d63a7ed89a379e47cb8', 'debf02e42fe54a07a51f598f71ad7873', 'ccy', '币种', 'STRING', 'none', 'ccy', 60, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('d9491b03eda84d708b9eb074dfd4dfa7', 'debf02e42fe54a07a51f598f71ad7873', 'dr_cr_flag', '借贷标志', 'STRING', 'none', 'dr_cr_flag', 61, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2d54fb40a8364b0bb7c5fbcc705a3759', 'debf02e42fe54a07a51f598f71ad7873', 'amt', '交易金额(金额)', 'STRING', 'none', 'amt', 62, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('faae3d96cf2f4ec3b948ef1d13a8c27a', 'debf02e42fe54a07a51f598f71ad7873', 'limit_id', '限额编号', 'STRING', 'none', 'limit_id', 63, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('4158a1673be04456b7a3d5578f4a87cd', 'debf02e42fe54a07a51f598f71ad7873', 'main_card_no', '主卡号', 'STRING', 'none', 'main_card_no', 64, null);
commit;
prompt 200 records committed...
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('6a95d1f0b244458d85e8fd4106cc4793', 'debf02e42fe54a07a51f598f71ad7873', 'tran_limit_id', '交易限额号', 'STRING', 'none', 'tran_limit_id', 65, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('13d853339c1142d38cd55014a3d7c31f', 'debf02e42fe54a07a51f598f71ad7873', 'business_scope', '主经营范围', 'STRING', 'none', 'business_scope', 66, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('154b899fd41b44f8b41ed47f12898dce', 'debf02e42fe54a07a51f598f71ad7873', 'message_type', '报文类型', 'STRING', 'none', 'message_type', 67, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('0fcb6eec041c4463926c0d81989fe69a', 'debf02e42fe54a07a51f598f71ad7873', 'tran_status', '交易状态', 'STRING', 'none', 'tran_status', 68, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('e63d82705aa944bbba07569df08c4899', '170ebfd104e740ae99359d08b37f60ae', 'acct_no', '账户号', 'STRING', 'none', 'acct_no', 1, '20');
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('7e70b88abc2e46f8bee995759beb1ee2', '170ebfd104e740ae99359d08b37f60ae', 'acct_name', '账户姓名', 'STRING', 'none', 'acct_name', 2, '30');
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('b6fbd620bd3041cfadd7e52720350b12', '170ebfd104e740ae99359d08b37f60ae', 'age', '账户年龄', 'INT', 'none', 'age', 3, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('6da712c90ec54a679badc01efdff13a6', '170ebfd104e740ae99359d08b37f60ae', 'acct_about', '账户说明', 'STRING', 'none', 'acct_about', 4, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('f9181eea8efb4bf4a764c65d5af24595', '170ebfd104e740ae99359d08b37f60ae', 'acct_zcrq', '账户注册日期', 'STRING', 'none', 'acct_zcrq', 5, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('728f7ffc6525407baf35c1b1f05f9652', '170ebfd104e740ae99359d08b37f60ae', 'acct_zcsj', '账户注册时间', 'STRING', 'none', 'acct_zcsj', 6, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('2b28913aea76443d92e271552cc9ff00', 'ac1ecb0e542d41a5b8953dedf74c70fb', 'acct_no', '账户号', 'STRING', 'none', 'acct_no', 1, '20');
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('668466f58b654fdaa3fab56cc722f164', 'ac1ecb0e542d41a5b8953dedf74c70fb', 'acct_name', '账户姓名', 'STRING', 'none', 'acct_name', 2, '30');
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3c83528a1fa84724984edf01c3b49f32', 'ac1ecb0e542d41a5b8953dedf74c70fb', 'age', '账户年龄', 'INT', 'none', 'age', 3, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('161049ffc57a403e82cbaeea7be1873f', 'ac1ecb0e542d41a5b8953dedf74c70fb', 'acct_about', '账户说明', 'STRING', 'none', 'acct_about', 4, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('3c27c8c9bffb4647baa5fa5e7c42c1a6', 'ac1ecb0e542d41a5b8953dedf74c70fb', 'acct_zcrq', '账户注册日期', 'STRING', 'none', 'acct_zcrq', 5, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('8c827a2d95dc460b99d38f55210c9aa9', 'ac1ecb0e542d41a5b8953dedf74c70fb', 'acct_zcsj', '账户注册时间', 'STRING', 'none', 'acct_zcsj', 6, null);
insert into IQ_APPLICATION_RETURN_COLUMN (pk_id, app_id, name, describe, type, stats, label, seq, length)
values ('4b909366022c4427aceae1945d3f18df', 'ac1ecb0e542d41a5b8953dedf74c70fb', 'acct_interests', '账户兴趣爱好', 'STRING', 'none', 'acct_interests', 7, null);
commit;
prompt 217 records loaded
prompt Loading IQ_METADATA...
insert into IQ_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name)
values ('045b391ae3d349e5a503ce98b093ee4e', '8300a81d356443f29a01d12b0abaee54', 'soa_megacorp_employee', 'ES测试专用元数据', 'ES测试专用元数据', '0', 'admin', '2017-09-26 19:20:25.438', 'admin', '2017-09-26 19:21:20.356', 'megacorp.employee');
insert into IQ_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name)
values ('ac1a651160814a5fafa821b0589eb95b', '53dbd410f3664ad697fb33eacd77853c', 'SOA_HBASE_CUPATRXJNL_HBASE', '前置银联查询', null, '0', 'admin', '2017-08-01 11:16:02.550', 'admin', '2017-08-01 11:16:02.550', 'SOA_HBASE_CUPATRXJNL');
insert into IQ_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, tb_name)
values ('7ffb213154444588900d239b7f5e3131', 'b8f83b642df642b8af5d853d49de9c0a', 'soa_cupatrxjnl_solr_hbase', '前置银联明细查询', null, '0', 'admin', '2017-07-31 22:08:25.086', 'admin', '2017-07-31 22:08:25.086', 'HDS_CUPATRXJNL');
commit;
prompt 3 records loaded
prompt Loading IQ_METADATA_COLUMN...
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8c7c21b6acd54d158bdffc1c0b24937a', 'ac1a651160814a5fafa821b0589eb95b', 1, 'card_no', '卡号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a18f762dbbeb491e90e58f5e1293e4a6', 'ac1a651160814a5fafa821b0589eb95b', 2, 'paymen_date', '支付时间', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2767537ebd9147578c2650581471deb5', 'ac1a651160814a5fafa821b0589eb95b', 1, 'card_no', '卡号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('e3a8467e5e4f4a1fbff5abeca41a208e', 'ac1a651160814a5fafa821b0589eb95b', 2, 'paymen_date', '支付时间', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('3b26a736d09c4cda805e5c9b8607e4a3', '7ffb213154444588900d239b7f5e3131', 1, 'accept_branch_id', '受理机构', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('89236f77a947451ca50d26569e2b3da5', '7ffb213154444588900d239b7f5e3131', 2, 'branch_id', '发送方机构ID(机构码)', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('1282c34a52c844c3ae0d6945b00e857a', '7ffb213154444588900d239b7f5e3131', 3, 'tran_time', '交易时间', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8bf6a5b302d4476eaf20627aeb6e90ce', '7ffb213154444588900d239b7f5e3131', 4, 'system_tracr_no', '系统跟踪号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b190ac9d03094c718acc6cb9829af5ff', '7ffb213154444588900d239b7f5e3131', 5, 'paymen_date', '支付日期', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('56185e2da57e486fba73211c1032981f', '7ffb213154444588900d239b7f5e3131', 6, 'channel_type', '渠道类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('0c51ed0c26ac46de85ac5e384ab32bdb', '7ffb213154444588900d239b7f5e3131', 7, 'uni_message_type', '统一报文类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('004fb88e7674470b89c33046e63e51b7', '7ffb213154444588900d239b7f5e3131', 8, 'bu_tran_code', '银联交易码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2271b6b49d9241628b66f65a2381d358', '7ffb213154444588900d239b7f5e3131', 9, 'card_no', '卡号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('1f05c71080c24f5bb4e1a10c39341933', '7ffb213154444588900d239b7f5e3131', 10, 'ic_trxamt', '交易金额(金额)', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('fe3d985ce3524c049ab465c65731bb35', '7ffb213154444588900d239b7f5e3131', 11, 'accept_date', '受理日期', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('be4e3bedf0b241159d0aec19877199f7', '7ffb213154444588900d239b7f5e3131', 12, 'accept_time', '受理时间', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('9729419773294b4181bd9f2618b93b06', '7ffb213154444588900d239b7f5e3131', 13, 'bu_settlement_date', '银联清算日期', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('906f6997a1d242cd8390baa24c7fd9be', '7ffb213154444588900d239b7f5e3131', 14, 'trader_type', '商户类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('635fa142e5c046a1895fad0c086e3d5b', '7ffb213154444588900d239b7f5e3131', 15, 'country_code', '国家代码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('d66ce9a0427c4baaad449f495efc5324', '7ffb213154444588900d239b7f5e3131', 16, 'acct_input_type', '主账号输入方式', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('5c6930bc2ee94f3fb0dd2b9543cee1f8', '7ffb213154444588900d239b7f5e3131', 17, 'passwoar_inpuat_type', '密码输入方式', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b6b2cfd1615d4c02b19165089a15a067', '7ffb213154444588900d239b7f5e3131', 18, 'card_serial_no', '卡序号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('0f11275aeac74d3fab83e0827e35cf25', '7ffb213154444588900d239b7f5e3131', 19, 'commission', '手续费金额', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('5fcdc5bca0284545a213116e3b6f65c7', '7ffb213154444588900d239b7f5e3131', 20, 'retr_ref_no', '检索参考号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2425ad718fd943079002e7f312219eac', '7ffb213154444588900d239b7f5e3131', 21, 'auth_code', '授权码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7bccd5e2dc5449458b62104ad137f6ee', '7ffb213154444588900d239b7f5e3131', 22, 'bu_ret_code', '银联标准返回代码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7b075797b8fa4b56a22fe985b5110d1d', '7ffb213154444588900d239b7f5e3131', 23, 'term_no', '终端号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a1a086623c044311979665d4967b6a05', '7ffb213154444588900d239b7f5e3131', 24, 'mech_no', '商户号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('ece59236906545a99dfbe1a8b57590c4', '7ffb213154444588900d239b7f5e3131', 25, 'trader_name_addr', '商户名称地址', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('47daf62a403a42ce832784f30744058a', '7ffb213154444588900d239b7f5e3131', 26, 'add_data', '附加数据', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('399d462c1e2a441e8639e9991b522091', '7ffb213154444588900d239b7f5e3131', 27, 'bu_ccy', '银联货币代码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('4acdae2e791344cdbd4b76c14da232bd', '7ffb213154444588900d239b7f5e3131', 28, 'reason_msg', '原因内容', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('818c080eb3cb4fd1a429f762b286672c', '7ffb213154444588900d239b7f5e3131', 29, 'input_cap_flag', '输入能力标志', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('f4083cc2d65e4f8a96a1f82de73fe1ac', '7ffb213154444588900d239b7f5e3131', 30, 'ws_type', '终端类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a98761b1c5064d2c85d7b4b1a3e48b2f', '7ffb213154444588900d239b7f5e3131', 31, 'interactive_mode', '交互方式', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7e2a451e69ee4176b128e3ee5690473d', '7ffb213154444588900d239b7f5e3131', 32, 'special_fee_type', '特殊费用类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('6e4f95f987624d8cbaeecfc8e54b22c6', '7ffb213154444588900d239b7f5e3131', 33, 'special_fee_level', '特殊费用级别', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('ff03161c794142868023448ede2bf89a', '7ffb213154444588900d239b7f5e3131', 34, 'medium_type', '介质类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('64f9023de12049d893bfc7eeb038e6d2', '7ffb213154444588900d239b7f5e3131', 35, 'tran_mode', '交易模式', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a80b4073a6094f25a6c4de787d192280', '7ffb213154444588900d239b7f5e3131', 36, 'use_type', '使用类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('400324b05d0540b7a8bd994a21c01c20', '7ffb213154444588900d239b7f5e3131', 37, 'acct_type', '账户类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8fc6851560af4c9e8d14e720fb940631', '7ffb213154444588900d239b7f5e3131', 38, 'user_level', '用户级别', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('129e5c741d8646e993043677fa9cfcf5', '7ffb213154444588900d239b7f5e3131', 39, 'product_type', '产品类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('286eeef78d824b9f8a7421e68bfa6a07', '7ffb213154444588900d239b7f5e3131', 40, 'ori_accept_branch_id', '原受理机构标识码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('878e929a7c064e1bb7fb13118d28cee3', '7ffb213154444588900d239b7f5e3131', 41, 'old_branch_id', '原发送方机构ID', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('3bbe991703d443d5a3b5335c24145b03', '7ffb213154444588900d239b7f5e3131', 42, 'ori_tran_time', '原交易时间', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('9150df0bca0a4300bf9aba3bfe2bfa5c', '7ffb213154444588900d239b7f5e3131', 43, 'ori_system_tracr_no', '原系统跟踪号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('0b3e4c917e9f47edac6cb0acb340df8f', '7ffb213154444588900d239b7f5e3131', 44, 'rcv_bank', '接收机构', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('6bbb8f1cf7e443d18a97b19cf7abda5b', '7ffb213154444588900d239b7f5e3131', 45, 'tran_in_acct_no', '转入账号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7168fd39000442778d8519c94df9534c', '7ffb213154444588900d239b7f5e3131', 46, 'trader_discount', '商户折扣率', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('922b9eb3638f49578a44f5ed8b1c11f9', '7ffb213154444588900d239b7f5e3131', 47, 'term_seq_no', '终端流水号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('bfb6bd2ff8084f14a2c137e2c35479e3', '7ffb213154444588900d239b7f5e3131', 48, 'app_bank_id', '经办行行号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2028f526fa1d42a1b82fd215e762401c', '7ffb213154444588900d239b7f5e3131', 49, 'bank_type', '银行类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('076c54301cbb4a8d9b9cd9939f92a20d', '7ffb213154444588900d239b7f5e3131', 50, 'app_branch_no', '经办机构', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('12b764f0f295429c890c6bf7ea0e44cd', '7ffb213154444588900d239b7f5e3131', 51, 'main_bank_id', '主交易行行号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('864f881f49324b6288e2cd41e7e6e40a', '7ffb213154444588900d239b7f5e3131', 52, 'main_bank_type', '主交易银行类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('24cd1610e1a84566ba13ad419628e3c1', '7ffb213154444588900d239b7f5e3131', 53, 'main_branch_id', '主交易机构码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2a707745a7e44696818641012436ddc2', '7ffb213154444588900d239b7f5e3131', 54, 'sub_acct_no', '子账号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('5af0f83c5c5c4e878b23a75810df87db', '7ffb213154444588900d239b7f5e3131', 55, 'assi_bank_id', '副交易行号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('4bd9a87892d64d05b587554d483d5268', '7ffb213154444588900d239b7f5e3131', 56, 'assi_bank_type', '副交易银行类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8ba3be135f3a484ca54199df9a54ae3b', '7ffb213154444588900d239b7f5e3131', 57, 'assi_branch_id', '副交易机构码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('8ef99bbba0264f11b75263f9545e139c', '7ffb213154444588900d239b7f5e3131', 58, 'teller_no', '柜员号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('778b68d0360446328bf54ca1829bab8c', '7ffb213154444588900d239b7f5e3131', 59, 'tran_code', '交易码', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b350eecd9b244adaa234a39eb240e937', '7ffb213154444588900d239b7f5e3131', 60, 'ccy', '币种', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a72922465ac744e5b96f03eda66a7dfe', '7ffb213154444588900d239b7f5e3131', 61, 'dr_cr_flag', '借贷标志', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('541db3f58e3c4cc6ac54127404af685a', '7ffb213154444588900d239b7f5e3131', 62, 'amt', '交易金额(金额)', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('af703415c9d34f3b890d52d610741bca', '7ffb213154444588900d239b7f5e3131', 63, 'limit_id', '限额编号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('136a98538d8f4260813553cf36cb4d89', '7ffb213154444588900d239b7f5e3131', 64, 'main_card_no', '主卡号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('c52773f1692b4e1ba63fb0b3ba0698e5', '7ffb213154444588900d239b7f5e3131', 65, 'tran_limit_id', '交易限额号', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('0bde7b69b2214f78867cc77141498a0d', '7ffb213154444588900d239b7f5e3131', 66, 'business_scope', '主经营范围', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('df26e83784a8404699535aa98eb74965', '7ffb213154444588900d239b7f5e3131', 67, 'message_type', '报文类型', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('63bbc4d59baf4b5aafd0feb41a26d790', '7ffb213154444588900d239b7f5e3131', 68, 'tran_status', '交易状态', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('37fbdbbe80fd4185aa8ab16e4bb26776', '7ffb213154444588900d239b7f5e3131', 1, 'system_tracr_no', '系统跟踪号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b03db4867adf48878c343188868de6be', '7ffb213154444588900d239b7f5e3131', 2, 'card_no', '卡号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b5fd29758c00406090d02d11c712fbbb', '7ffb213154444588900d239b7f5e3131', 3, 'tran_in_acct_no', '转入账号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('db78fa2aac46475390e8d2038c756dcc', '7ffb213154444588900d239b7f5e3131', 4, 'channel_type', '渠道类型', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('21df131c30b4477781a937a90f43a0eb', '7ffb213154444588900d239b7f5e3131', 5, 'amt', '交易金额(金额)', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('70484df0ac4e4746b09da3c0343a9a7c', '7ffb213154444588900d239b7f5e3131', 6, 'auth_code', '授权码', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('34c19569476f48ce803bf3363d052770', '7ffb213154444588900d239b7f5e3131', 7, 'bu_settlement_date', '银联清算日期', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('ff5731a0233d471e81c1fb91e284d10c', '7ffb213154444588900d239b7f5e3131', 8, 'teller_no', '柜员号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('4c45981e68be4a2493581ad6d2b7d4e6', '7ffb213154444588900d239b7f5e3131', 9, 'bu_tran_code', '银联交易码', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b5cbfefaf306425cad4856f94c4c0650', '7ffb213154444588900d239b7f5e3131', 10, 'ccy', '币种', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('54e1897323e24cbca8a1672177d367b1', '7ffb213154444588900d239b7f5e3131', 11, 'message_type', '报文类型', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('485a02b0c6c74369b09262f4fcc3fbd7', '7ffb213154444588900d239b7f5e3131', 12, 'tran_status', '交易状态', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('7ae50a5d9fd348579993c6ba29cca915', '7ffb213154444588900d239b7f5e3131', 13, 'term_seq_no', '终端流水号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('1e0a4c620f2f490daced9d669bf5eece', '7ffb213154444588900d239b7f5e3131', 14, 'paymen_date', '支付日期', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b92661c1f7b14d4c9410ec7b1877e318', '7ffb213154444588900d239b7f5e3131', 15, 'medium_type', '介质类型', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('c61425e6f8e9478c870df5491bd88709', '7ffb213154444588900d239b7f5e3131', 16, 'retr_ref_no', '检索参考号', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2347093b16094d83a05075abad953eb2', '7ffb213154444588900d239b7f5e3131', 17, 'uni_message_type', '统一报文类型', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('13c8061a9de54d21987f289c86b14eef', '7ffb213154444588900d239b7f5e3131', 18, 'bu_ret_code', '银联标准返回代码', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('2559ddcbbe034a5584a85f2ab9a0bef4', '045b391ae3d349e5a503ce98b093ee4e', 1, 'acct_zcrq', '账户注册日期', '1', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('07e991109228431fbae4c6142e3f70cd', '045b391ae3d349e5a503ce98b093ee4e', 2, 'acct_no', '账户号', '1', '20', null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('206541753c3c40a59f98dd7dab009f91', '045b391ae3d349e5a503ce98b093ee4e', 3, 'acct_name', '账户姓名', '1', '30', null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('ccb2d87bf4ae43bdbf613ae9dec0545f', '045b391ae3d349e5a503ce98b093ee4e', 4, 'age', '账户年龄', '1', null, null, 'INT');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('4e8393a77767409eb3a86c9e57382a94', '045b391ae3d349e5a503ce98b093ee4e', 1, 'acct_no', '账户号', '2', '20', null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('48be2a02955e49fbbb620fc4e6cd426d', '045b391ae3d349e5a503ce98b093ee4e', 2, 'acct_name', '账户姓名', '2', '30', null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('95dcad0ee5544ad4b09b225f9563d8f1', '045b391ae3d349e5a503ce98b093ee4e', 3, 'age', '账户年龄', '2', null, null, 'INT');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('fd372872e48f47f6b44ea862d81c35a0', '045b391ae3d349e5a503ce98b093ee4e', 4, 'acct_about', '账户说明', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('b9a715577377486b8a1bab85e98d82d2', '045b391ae3d349e5a503ce98b093ee4e', 5, 'acct_zcrq', '账户注册日期', '2', null, null, 'STRING');
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('a83afc5272744f729327159e92f38d1b', '045b391ae3d349e5a503ce98b093ee4e', 6, 'acct_zcsj', '账户注册时间', '2', null, null, 'STRING');
commit;
prompt 100 records committed...
insert into IQ_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type, length, note, col_type)
values ('15174640e4d248efbb8b84fb7e7d80a5', '045b391ae3d349e5a503ce98b093ee4e', 7, 'acct_interests', '账户兴趣爱好', '2', null, null, 'STRING');
commit;
prompt 101 records loaded
prompt Loading MM_APPLICATION...
prompt Table is empty
prompt Loading MM_APPLICATION_EXECUTE_PARAM...
prompt Table is empty
prompt Loading MM_APPLICATION_RETURN_PARAM...
prompt Table is empty
prompt Loading MM_CONTRACTOR...
prompt Table is empty
prompt Loading OLQ_APPLICATION...
insert into OLQ_APPLICATION (pk_id, olq_sql, olq_ds_id, max_num, del_flg, crt_user, crt_time, upt_user, upt_time, note, name, describe)
values ('eacef02a7a504e44bd9c8ba188785098', 'SELECT * FROM hds.hds_cupatrxjnl  ' || chr(10) || 'ORDER BY main_card_no  ' || chr(10) || 'limit ${limit} offset ${offset}', '4f81658c49ea4ffaab4197e77dc3b820', null, '0', 'admin', '2017-08-01 09:56:18.874', 'admin', '2017-09-02 08:11:33.617', null, 'hds_cupatrxjnl', '前置银联查询');
commit;
prompt 1 records loaded
prompt Loading OLQ_APPLICATION_PARAM...
insert into OLQ_APPLICATION_PARAM (pk_id, param_name, param_desc, default_value, is_need, olq_app_id, seq)
values ('1eaf629ef1574c078fdb3a757f98d9f6', 'limit', '查询数', '10', '0', 'eacef02a7a504e44bd9c8ba188785098', 1);
insert into OLQ_APPLICATION_PARAM (pk_id, param_name, param_desc, default_value, is_need, olq_app_id, seq)
values ('3e4d297e99bd494d8949e8090be43e20', 'offset', '开始数', '0', '0', 'eacef02a7a504e44bd9c8ba188785098', 2);
commit;
prompt 2 records loaded
prompt Loading RC_SERVICE...
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('ef0577ce86bd4dea9b26008074cc849a', 'test_oracle_service', null, 'OLQ', 'e3ef337e3153453b9b98f1736195a35b', '1', 'admin', '2017-09-11 14:18:41.066', 'admin', '2017-09-11 14:18:41.066', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('5cfc9cf2667640ca97017b1b07df009d', 'hds_cupatrxjnl', '前置银联SQL查询', 'OLQ_APP', 'eacef02a7a504e44bd9c8ba188785098', '0', 'admin', '2017-08-01 10:18:15.505', 'admin', '2017-08-01 10:18:15.505', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('ce6bcad33e32415cbaa460223824c6b5', 'rts_jyls_data_producer', null, 'RTS_PRODUCER', '3275cbea78534218b8e5495a77e285e4', '0', 'admin', '2017-08-17 09:27:41.287', 'admin', '2017-08-17 09:27:41.287', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('28bfcbec97d44efca2bb75a0b60b4267', 'rts_jyls_total_producer', '实时交易流水-按总量统计-生产者', 'RTS_PRODUCER', '2e3b08b5009348f6a1511334710d46c1', '0', 'admin', '2017-08-17 16:26:01.179', 'admin', '2017-08-17 16:26:01.179', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('dc61bb9ef31541f9acfb9f27adab73e4', 'rts_jyls_branch_consumer', '实时交易流水-按网点统计-消费者', 'RTS_CONSUMER', '60bebb6656a6460c80d95e382ee7fd9e', '0', 'admin', '2017-08-17 16:28:25.737', 'admin', '2017-08-17 16:28:25.737', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('36717f7406934c25aae98cbea2a1a8a7', 'rts_jyls_borough_consumer', '实时交易流水队列-按区域统计-消费者', 'RTS_CONSUMER', '38ebdd9795a84d2aae51cd753859e325', '0', 'admin', '2017-08-17 16:28:58.256', 'admin', '2017-08-17 16:28:58.256', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('b95ace7d0da14ec286d8cfc7b9fa43b6', 'rts_jyls_branch_producer', '实时交易流水-按网点统计-生产者', 'RTS_PRODUCER', '8f8a9c78d75943d9b62374fa12a3e879', '0', 'admin', '2017-08-17 16:24:52.430', 'admin', '2017-08-17 16:24:52.430', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('5b92cc87714a4a9b9bf8b94ebefba056', 'rts_jyls_total_consumer', '实时交易流水-按总量统计-消费者', 'RTS_CONSUMER', '1ec11dfb5beb4e00902d8048acf04320', '0', 'admin', '2017-08-17 16:27:30.548', 'admin', '2017-08-17 16:27:30.548', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('012cf5961471409595dcbd9551d03a4e', 'rts_jyls_jylx_consumer', '实时交易流水-按交易类型统计-消费者', 'RTS_CONSUMER', '44aebab24d4741eb9d240fd198e15490', '0', 'admin', '2017-08-17 16:28:02.377', 'admin', '2017-08-17 16:28:02.377', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('0198091e57434cc5a472aecbcf469515', 'rts_jyls_borough_producer', '实时交易流水队列-按区域统计', 'RTS_PRODUCER', 'ce0c43fdbb1f4aa48dbb1d7b7b938a3b', '0', 'admin', '2017-08-17 16:26:48.379', 'admin', '2017-08-17 16:26:48.379', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('693b80333b304f8285a1a5a6a70cb26f', 'hex_cupatrxjnl_service', null, 'IQ', '5f7693ccf3f449fdb575cdd5e9d32f32', '0', 'admin', '2017-08-08 11:42:26.844', 'admin', '2017-08-08 11:42:26.844', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('6722d8418a8b4bd78e4b4da11584bfed', 'rts_jyls_jylx_producer', '实时交易流水-按交易类型统计-生产者', 'RTS_PRODUCER', '82f6a904cd6546c3b1c1cd6bdcf6214b', '0', 'admin', '2017-08-17 16:25:31.140', 'admin', '2017-08-17 16:25:31.140', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('1e467fcc038a47d38c6ff6fe809efc57', 'tomnic_mysql', 'tomnic的本地mysql数据库', 'OLQ', 'caff9907ab8e4e909b67ed4af252a81a', '1', 'admin', '2017-09-12 09:19:21.151', 'admin', '2017-09-12 09:19:21.151', '1');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('285ed7465daf48e79e7e39c969379328', null, null, 'OLQ', 'e3ef337e3153453b9b98f1736195a35b', '1', 'admin', '2017-09-19 10:16:23.660', 'admin', '2017-09-19 10:16:23.660', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('21aa13bfa220409aa4129a82fbf60a1e', 'dev_oracle', null, 'OLQ', 'e3ef337e3153453b9b98f1736195a35b', '1', 'admin', '2017-09-19 10:54:53.857', 'admin', '2017-09-19 10:54:53.857', '1');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('c1aca78991114634aa1b3f8dc3804158', 'dev_oracle', null, 'OLQ', 'e3ef337e3153453b9b98f1736195a35b', '0', 'admin', '2017-09-19 10:55:32.042', 'admin', '2017-09-19 11:04:58.589', '1');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('e03b15fe5e7c400eb44745a834f90cca', null, null, 'OLQ', 'e3ef337e3153453b9b98f1736195a35b', '1', 'admin', '2017-09-19 10:14:46.502', 'admin', '2017-09-19 10:14:46.502', '0');
insert into RC_SERVICE (pk_id, name, describe, type, app_id, del_flg, crt_user, crt_time, upt_user, upt_time, status)
values ('04b6ba82df2c4e679841494d5415a625', 'dev_oracle', null, 'OLQ', 'e3ef337e3153453b9b98f1736195a35b', '1', 'admin', '2017-09-19 10:16:44.661', 'admin', '2017-09-19 10:16:44.661', '0');
commit;
prompt 18 records loaded
prompt Loading RC_USER_SERVICE...
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('40ea9dd30e824f02b76db4ee8e6f3621', 'tomnic', '1e467fcc038a47d38c6ff6fe809efc57', null, 100, 200, '1', 'admin', '2017-09-12 09:20:28.343', 'admin', '2017-09-15 16:13:21.342', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('b665d634d4b34c3ebadde9387c0c4e2a', 'rtsmap', 'dc61bb9ef31541f9acfb9f27adab73e4', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.351', 'admin', '2017-08-17 16:30:00.351', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('e0e838f332204815910559b7503b2afc', 'admin', '5cfc9cf2667640ca97017b1b07df009d', null, 100, 50, '0', 'admin', '2017-08-08 11:40:00.981', 'admin', '2017-08-08 11:40:00.981', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('a7652dc3654b4d6a8f58116e58f14618', 'rtsmap', 'ce6bcad33e32415cbaa460223824c6b5', null, 100, 100, '0', 'admin', '2017-08-17 09:33:26.887', 'admin', '2017-08-17 09:33:26.887', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('f466bb109c934bec9426273e39ab30f7', 'rtsmap', '5b92cc87714a4a9b9bf8b94ebefba056', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.395', 'admin', '2017-08-17 16:30:00.395', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('a4d5cb1f8c1941a8a51a54bf9b10e7f4', 'rtsmap', '28bfcbec97d44efca2bb75a0b60b4267', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.440', 'admin', '2017-08-17 16:30:00.440', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('93490151691841b097e7dc3e1810314e', 'rtsmap', 'b95ace7d0da14ec286d8cfc7b9fa43b6', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.489', 'admin', '2017-08-17 16:30:00.489', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('b594778e5dc94e9187a927e21363a395', 'rtsmap', '36717f7406934c25aae98cbea2a1a8a7', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.306', 'admin', '2017-08-17 16:30:00.306', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('9cf3db9895274195900246a3389d2c43', 'rtsmap', '012cf5961471409595dcbd9551d03a4e', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.373', 'admin', '2017-08-17 16:30:00.373', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('fa628dac20f44fb2bf6fe459f106732e', 'rtsmap', '0198091e57434cc5a472aecbcf469515', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.418', 'admin', '2017-08-17 16:30:00.418', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('0665d80be99e472dab1f98e77773ac77', 'rtsmap', '6722d8418a8b4bd78e4b4da11584bfed', null, 100, 100, '0', 'admin', '2017-08-17 16:30:00.462', 'admin', '2017-08-17 16:30:00.462', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('b03a851be555486abe0d7941def3ca24', 'admin', '693b80333b304f8285a1a5a6a70cb26f', null, 100, 50, '0', 'admin', '2017-08-08 11:42:41.759', 'admin', '2017-08-08 11:42:41.759', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('7ffe2eaf5d124fdda44956bc65c748c3', 'tomnic', '5cfc9cf2667640ca97017b1b07df009d', null, 10, 10, '0', 'admin', '2017-08-01 10:18:40.296', 'admin', '2017-08-01 10:18:40.296', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('35ba0ea2446b4865a96b4f51c49f6038', 'tomnic', 'ef0577ce86bd4dea9b26008074cc849a', null, 10, 10, '1', 'admin', '2017-09-11 14:19:25.310', 'admin', '2017-09-11 14:19:25.310', 0, 0, 3000, 600000, 3000, 600000);
insert into RC_USER_SERVICE (pk_id, user_id, service_id, ip_section, max_sync_num, max_async_num, del_flg, crt_user, crt_time, upt_user, upt_time, max_sync_wait_num, max_async_wait_num, max_sync_wait_timeout, max_async_wait_timeout, max_sync_execute_timeout, max_async_execute_timeout)
values ('e4e88cf558e7488182c4629ac40e7d03', 'tomnic', '693b80333b304f8285a1a5a6a70cb26f', null, 10, 10, '1', 'admin', '2017-09-18 14:28:46.865', 'admin', '2017-09-18 14:30:49.468', 0, 0, 30000, 600000, 30000, 600000);
commit;
prompt 15 records loaded
prompt Loading RTS_CUSTOMER_CONFIG...
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('3b9c3e875f854ec2bf36a23f2c444e8d', '6afe89b053c14217b5de4b2bb4822bb4', 'rts_jyls_data_consumer', '交易流水测试', 'group2', null, '0', 'admin', '2017-08-17 14:20:29.840', 'admin', '2017-08-17 14:20:29.840');
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('38ebdd9795a84d2aae51cd753859e325', 'c1b08f3657314c23aa527c54dd955948', 'rts_jyls_borough_consumer', '实时交易流水队列-按区域统计-消费者', 'rtsmap', '实时交易流水队列-按区域统计-消费者', '0', 'admin', '2017-08-17 16:05:28.580', 'admin', '2017-08-17 16:05:59.859');
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('60bebb6656a6460c80d95e382ee7fd9e', '66bde8beaf534c82b71bc91dce7d2102', 'rts_jyls_branch_consumer', '实时交易流水-按网点统计-消费者', 'rtsmap', '实时交易流水-按网点统计-消费者', '0', 'admin', '2017-08-17 16:08:36.256', 'admin', '2017-08-17 16:08:36.256');
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('1ec11dfb5beb4e00902d8048acf04320', '3a3dad3c5d0542f4be9f17141bd9a4b3', 'rts_jyls_total_consumer', '实时交易流水-按总量统计-消费者', 'rtsmap', '实时交易流水-按总量统计-消费者', '0', 'admin', '2017-08-17 16:11:04.714', 'admin', '2017-08-17 16:11:04.714');
insert into RTS_CUSTOMER_CONFIG (pk_id, md_id, name, describe, group_id, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('44aebab24d4741eb9d240fd198e15490', '020f4314855a434482867b0243a1a849', 'rts_jyls_jylx_consumer', '实时交易流水-按交易类型统计-消费者', 'rtsmap', '实时交易流水-按交易类型统计-消费者', '0', 'admin', '2017-08-17 16:09:53.984', 'admin', '2017-08-17 16:09:53.984');
commit;
prompt 5 records loaded
prompt Loading RTS_METADATA...
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('6afe89b053c14217b5de4b2bb4822bb4', '39dc538bfbe2463da58469242af48ab0', 'jyls_data', '交易流水原始数据', null, '0', 'admin', '2017-08-17 09:24:29.375', 'admin', '2017-08-17 09:24:29.375', 'rts.jyls.data');
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('c1b08f3657314c23aa527c54dd955948', '39dc538bfbe2463da58469242af48ab0', 'jyls_borough', '实时交易流水队列-按区域统计', '实时交易流水队列-按区域统计', '0', 'admin', '2017-08-17 15:31:59.212', 'admin', '2017-08-17 16:03:25.153', 'rts_jyls_borough_udsp');
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('66bde8beaf534c82b71bc91dce7d2102', '39dc538bfbe2463da58469242af48ab0', 'jyls_branch', '实时交易流水-按网点统计', '实时交易流水-按网点统计', '0', 'admin', '2017-08-17 15:36:29.498', 'admin', '2017-08-17 15:52:26.574', 'rts_jyls_branch_udsp');
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('020f4314855a434482867b0243a1a849', '39dc538bfbe2463da58469242af48ab0', 'jyls_jylx', '实时交易流水-按交易类型统计', '实时交易流水-按交易类型统计', '0', 'admin', '2017-08-17 15:43:42.460', 'admin', '2017-08-17 15:52:13.614', 'rts_jyls_jylx_udsp');
insert into RTS_METADATA (pk_id, ds_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time, topic)
values ('3a3dad3c5d0542f4be9f17141bd9a4b3', '39dc538bfbe2463da58469242af48ab0', 'jyls_total', '实时交易流水-按总量统计', '实时交易流水-按总量统计', '0', 'admin', '2017-08-17 15:51:42.945', 'admin', '2017-08-17 15:51:55.877', 'rts_jyls_total_udsp');
commit;
prompt 5 records loaded
prompt Loading RTS_METADATA_COLUMN...
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('e9ec2e96864e4862889c6135048b516c', 'c1b08f3657314c23aa527c54dd955948', 1, 'boroughId', '区域id', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('ea5c89b8808844219876d783ed1580ce', 'c1b08f3657314c23aa527c54dd955948', 2, 'borough', '区域名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('e3f9f513570048ce9dbafbf3ecf78bfe', 'c1b08f3657314c23aa527c54dd955948', 3, 'incomeSum', '收入', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('38c47cff66fc4ed0aeeed5a962f018cb', 'c1b08f3657314c23aa527c54dd955948', 4, 'expensesSum', '支出', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('15f6a1baf1c945faa8547fb3866827a3', 'c1b08f3657314c23aa527c54dd955948', 5, 'statistime', '统计时间', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b78caf9479064586add6f6ca5b40fe9f', '6afe89b053c14217b5de4b2bb4822bb4', 1, 'jylsh', '交易流水号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('7d72c8e69f00407c889642fec1b3c645', '6afe89b053c14217b5de4b2bb4822bb4', 2, 'jym', '交易码', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('163139b753554391a3c19975b8861fd5', '6afe89b053c14217b5de4b2bb4822bb4', 3, 'jymc', '交易名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('9b2c5373a0dc4c9ebe72c56a660cadee', '6afe89b053c14217b5de4b2bb4822bb4', 4, 'zcbz', '支出标志', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('2e7ccab5b33d4c898b8e2d4f1acd8c36', '6afe89b053c14217b5de4b2bb4822bb4', 5, 'jgm', '机构码', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('12b949c8e96740d7b777b7eb86a7fc73', '6afe89b053c14217b5de4b2bb4822bb4', 6, 'jgmc', '机构名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('7b38812d62034e4ea6c521ff7980c34a', '6afe89b053c14217b5de4b2bb4822bb4', 7, 'boroughId', '区域id', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('5fd1723401214f3197c9fb7ed5de1f41', '6afe89b053c14217b5de4b2bb4822bb4', 8, 'borough', '区域名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('2d0f0081b82c4e81a2080ef8a4c07ddb', '6afe89b053c14217b5de4b2bb4822bb4', 9, 'khh', '客户号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('90f9c217652949928b3220d9e8cbcafc', '6afe89b053c14217b5de4b2bb4822bb4', 10, 'zhkh', '账号卡号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b7473d1f66494409ae708cc7653770bf', '6afe89b053c14217b5de4b2bb4822bb4', 11, 'zhxh', '账号序号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('bf1e1e210db442f5ad835c61c6e32cab', '6afe89b053c14217b5de4b2bb4822bb4', 12, 'khmc', '客户名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('ad5e2f28bebb48fcb1ec91eba6a0b005', '6afe89b053c14217b5de4b2bb4822bb4', 13, 'dfzh', '对方账号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('95d3320c03b44b73a29ceba8934aa902', '6afe89b053c14217b5de4b2bb4822bb4', 14, 'dfxh', '对方序号', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b6ed4717c2e54720ae34df302789e069', '6afe89b053c14217b5de4b2bb4822bb4', 15, 'dfhm', '对方名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('43ca4265de55447daf55ae3947f78ddc', '6afe89b053c14217b5de4b2bb4822bb4', 16, 'jyje', '交易金额', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('2d5cecaec3f14bd19064eb95ce2d1f8b', '6afe89b053c14217b5de4b2bb4822bb4', 17, 'jyrq', '交易日期', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b6834cb6740c492cbcb7d5ec4c73491d', '6afe89b053c14217b5de4b2bb4822bb4', 19, 'zy', '摘要', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('1123rcaec3f14bd19064eb95ce2d1f8b', '6afe89b053c14217b5de4b2bb4822bb4', 18, 'jysj', '交易时间', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('75f819e1aa7b449f91fe6a176eba364d', '020f4314855a434482867b0243a1a849', 1, 'jym', '交易码', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('02a8249bfc4c405db2b5fb9c795a106b', '020f4314855a434482867b0243a1a849', 2, 'jymc', '交易名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('6da12ab9ddd44b4fa99e6f6149bad745', '020f4314855a434482867b0243a1a849', 3, 'incomeSum', '收入', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('f7dff4ee91d640aa8affdc8cb14cdda1', '020f4314855a434482867b0243a1a849', 4, 'expensesSum', '支出', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('4ed4026ca48f4457b5181e418a664746', '020f4314855a434482867b0243a1a849', 5, 'statistime', '统计时间', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('ea3f80f5602d49478e9d4549f21e123b', '66bde8beaf534c82b71bc91dce7d2102', 1, 'branchId', '网点id', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('cf51f5e80d4c4cf6ab1d34df40553e53', '66bde8beaf534c82b71bc91dce7d2102', 2, 'branch', '网点名称', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('8051864ca2cd4eaf8e687286c72fa2a1', '66bde8beaf534c82b71bc91dce7d2102', 3, 'incomeSum', '收入', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('59f584e4f1294e5dabeb1f3675256c28', '66bde8beaf534c82b71bc91dce7d2102', 4, 'expensesSum', '支出', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b811847a188446e1900854781d18f594', '66bde8beaf534c82b71bc91dce7d2102', 5, 'statistime', '统计时间', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('b047436bf64045be82d4bf114cfd851c', '3a3dad3c5d0542f4be9f17141bd9a4b3', 1, 'statisDate', '统计日期', 'STRING');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('0f13c78d65c54714b24686f5bc98a32e', '3a3dad3c5d0542f4be9f17141bd9a4b3', 2, 'incomeSum', '收入', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('817e48a704df42c89302cae5fef6a5c2', '3a3dad3c5d0542f4be9f17141bd9a4b3', 3, 'expensesSum', '支出', 'DECIMAL');
insert into RTS_METADATA_COLUMN (pk_id, md_id, seq, name, describe, type)
values ('44c0867405f14c248ccdce6524b02713', '3a3dad3c5d0542f4be9f17141bd9a4b3', 4, 'statistime', '统计时间', 'STRING');
commit;
prompt 38 records loaded
prompt Loading RTS_PRODUCRER_CONFIG...
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('8f8a9c78d75943d9b62374fa12a3e879', '66bde8beaf534c82b71bc91dce7d2102', 'rts_jyls_branch_producer', '实时交易流水-按网点统计-生产者', null, '0', 'admin', '2017-08-17 15:59:52.284', 'admin', '2017-08-17 16:14:04.440');
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('82f6a904cd6546c3b1c1cd6bdcf6214b', '020f4314855a434482867b0243a1a849', 'rts_jyls_jylx_producer', '实时交易流水-按交易类型统计-生产者', null, '0', 'admin', '2017-08-17 16:01:50.112', 'admin', '2017-08-17 16:13:56.052');
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('2e3b08b5009348f6a1511334710d46c1', '3a3dad3c5d0542f4be9f17141bd9a4b3', 'rts_jyls_total_producer', '实时交易流水-按总量统计-生产者', null, '0', 'admin', '2017-08-17 16:02:44.151', 'admin', '2017-08-17 16:13:47.471');
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('3275cbea78534218b8e5495a77e285e4', '6afe89b053c14217b5de4b2bb4822bb4', 'rts_jyls_data', 'rts_jyls_data', null, '0', 'admin', '2017-08-17 09:26:43.050', 'admin', '2017-08-17 09:26:43.050');
insert into RTS_PRODUCRER_CONFIG (pk_id, md_id, name, describe, note, del_flg, crt_user, crt_time, upt_user, upt_time)
values ('ce0c43fdbb1f4aa48dbb1d7b7b938a3b', 'c1b08f3657314c23aa527c54dd955948', 'rts_jyls_borough_producer', '实时交易流水队列-按区域统计', '实时交易流水队列-按区域统计', '0', 'admin', '2017-08-17 16:03:39.510', 'admin', '2017-08-17 16:13:38.471');
commit;
prompt 5 records loaded
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
values ('1', 'ADMIN', 'admin', 'role', 'default');
commit;
prompt 7 records loaded
prompt Loading T_GF_DICT...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_FILTER_TYPE', 'like', '模糊匹配', null, 2, null, null, 'default', null);
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
values ('IM_MODEL_FILTER_TYPE', '!=', '不等于', null, 7, null, null, 'default', null);
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
values ('UDSP_CONSUME_ERROR_CODE', '000003', '并发数量达到上限', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000005', '参数解析失败', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000008', '没有授权服务', null, 8, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000009', '必输参数为空', null, 9, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000099', '其他错误', null, 99, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200001', '模型接口无响应', null, 13, null, null, 'default', null);
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
values ('OlQ_APP_COL_DATA_TYPE', 'DECIMAL', 'DECIMAL', null, 3, null, null, 'default', null);
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
commit;
prompt 100 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_REDIS', 'redis.connection.port', 'redis连接端口号', null, 2, null, null, 'default', null);
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
values ('IQ_APP_QUERY_COL_OPERATOR', 'like', '模糊匹配', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '>', '大于', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '<', '小于', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '>=', '大于等于', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '<=', '小于等于', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_QUERY_COL_OPERATOR', '!=', '不等于', null, 7, null, null, 'default', null);
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
values ('RTS_IMPL_CLASS', 'KAFKA', 'com.hex.bigdata.udsp.rts.provider.impl.KafkaProvider', null, 1, null, null, 'default', '实时流的KAFKA接口实现类');
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
values ('OLQ_DS_TYPE', 'HIVE', 'HIVE', null, 2, null, null, 'default', null);
commit;
prompt 200 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_APP_ORDER_COL_TYPE', 'ASC', 'ASC', null, 1, null, null, 'default', null);
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
values ('OLQ_IMPL_CLASS', 'MYSQL', 'com.hex.bigdata.udsp.olq.provider.impl.MysqlProvider', null, 4, null, null, 'default', '联机查询的mysql接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'DB2', 'com.hex.bigdata.udsp.olq.provider.impl.Db2Provider', null, 6, null, null, 'default', '联机查询的db2接口实现类');
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
values ('OLQ_DS_PROPS_IMPALA', 'username', 'Impala 用户名', null, 3, null, null, 'default', null);
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
values ('OLQ_DS_PROPS_HIVE', 'driver.class', 'Hive 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'username', 'Hive 用户名', null, 3, null, null, 'default', null);
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
values ('OLQ_DS_PROPS_DB2', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
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
values ('IM_DS_PROPS_HIVE', 'username', 'Hive 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'initial.size', '初始连接数', null, 5, null, null, 'default', '5');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'min.idle', '最小空闲连接数', null, 6, null, null, 'default', '2');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'max.active', '最大并发数', null, 8, null, null, 'default', '25');
commit;
prompt 300 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'validation.query', '验证链接的SQL语句，必须能返回一行及以上数据', null, 10, null, null, 'default', 'select 1');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'time.between.eviction.runs.millis', 'N毫秒检测一次是否有死掉的线程', null, 12, null, null, 'default', '30000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'test.while.idle', '是否被空闲链接回收器进行检验', null, 14, null, null, 'default', 'true');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'test.on.return', '是否在归还到池中前进行检验', null, 16, null, null, 'default', 'false');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_DS_PROPS_HIVE', 'jdbc.url', 'Hive JDBC URL，如：jdbc:hive2://${ip}:${port}/default', null, 2, null, null, 'default', null);
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
values ('IM_DS_PROPS_HBASE', 'hbase.zk.port', 'HBase的Zookeeper的端口，如：2181', null, 2, null, null, 'default', '2181');
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
values ('OLQ_DS_PROPS_IMPALA', 'password', 'Impala 密码', null, 4, null, null, 'default', null);
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
values ('OLQ_IMPL_CLASS', 'PGSQL', 'com.hex.bigdata.udsp.olq.provider.impl.PgsqlProvider', null, 5, null, null, 'default', '联机查询的pgsql接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000004', '没有注册服务', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000006', '请求IP不在允许的IP段内', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000007', '程序内部异常', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000010', '调用类型或者ENTITY设置错误', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '200002', '模型不支持该接口调用类型', null, 14, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_CONSUME_ERROR_CODE', '000012', '查询消费状态过于频繁', null, 12, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('RTS_CONSUMER_TIMEOUT', '2000', '2000ms', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'ORACLE', 'com.hex.bigdata.udsp.olq.provider.impl.OracleProvider', null, 3, null, null, 'default', '联机查询的oracle接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'driver.class', 'pgsql 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'ORACLE', 'ORACLE', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'PGSQL', 'PGSQL', null, 5, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_TYPE', 'KYLIN', 'KYLIN', null, 7, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_IMPL_CLASS', 'KYLIN', 'com.hex.bigdata.udsp.olq.provider.impl.KylinProvider', null, 7, null, null, 'default', '联机查询的kylin接口实现类');
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
values ('OLQ_DS_PROPS_PGSQL', 'jdbc.url', 'pgsql JDBC URL，如：jdbc:postgresql://${ip}:${port}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'password', 'pgsql 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_PGSQL', 'username', 'pgsql 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'driver.class', 'db2 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'jdbc.url', 'db2 JDBC URL，如:jdbc:db2://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'password', 'db2 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_DB2', 'username', 'db2 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'driver.class', 'kylin 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'jdbc.url', 'kylin JDBC URL，如：' || chr(9) || ' jdbc:kylin://${ip}:${port}/${database}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'password', 'kylin 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_KYLIN', 'username', 'kylin 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'driver.class', 'mysql 驱动类', null, 1, null, null, 'default', 'com.mysql.jdbc.Driver');
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
values ('IQ_IMPL_CLASS', 'ELASTICSEARCH', 'com.hex.bigdata.udsp.iq.provider.impl.ELSearchProvider', null, 5, null, null, 'default', '交互查询elasticsearch接口实现类');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'jdbc.url', 'mysql JDBC URL，如：jdbc:mysql://${ip}:${port}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'password', 'mysql 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_MYSQL', 'username', 'mysql 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'driver.class', 'oracle 驱动类', null, 1, null, null, 'default', 'oracle.jdbc.OracleDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'jdbc.url', 'oracle JDBC URL，如：jdbc:oracle:thin:@${ip}:${port}/${model}', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'password', 'oracle 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_ORACLE', 'username', 'oracle 用户名', null, 3, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_TYPE', 'REDIS', 'REDIS', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_IMPL_CLASS', 'REDIS', 'com.hex.bigdata.udsp.iq.provider.impl.RedisProvider', null, 4, null, null, 'default', '交互查询redis接口实现类');
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
commit;
prompt 400 records committed...
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OlQ_APP_COL_DATA_TYPE', 'TINYINT', 'TINYINT', null, 10, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IQ_DS_PROPS_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效', null, 7, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.data.size', '最大数据返回条数', null, 17, null, null, 'default', '65535');
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
values ('OLQ_DS_PROPS_IMPALA', 'driver.class', 'Impala 驱动类', null, 1, null, null, 'default', 'org.apache.hive.jdbc.HiveDriver');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_IMPALA', 'jdbc.url', 'Impala JDBC URL，如：无密码jdbc:hive2://${ip}:${port}/;auth=noSasl  有密码jdbc:hive2://${ip}:${port}/', null, 2, null, null, 'default', null);
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
values ('IQ_DS_PROPS_SOLR_HBASE', 'hbase.fqSep', '字段分隔符', null, 6, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('UDSP_SERVICE_TYPE', 'OLQ_APP', '联机查询应用', null, 6, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '1', '待发布', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('MM_MODEL_STATUS', '3', '归档', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'jdbc.url', 'Hive JDBC URL，如：jdbc:hive2://${ip}:${port}/default', null, 2, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'password', 'Hive 密码', null, 4, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.idle', '最大连接数', null, 7, null, null, 'default', '50');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'max.wait', '最长等待时间，单位毫秒', null, 9, null, null, 'default', '3000');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('OLQ_DS_PROPS_HIVE', 'validation.query.timeout', '自动验证连接的时间', null, 11, null, null, 'default', '0');
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
values ('IM_MD_PROPS_HBASE', 'hbase.fq.dsv.seprator', '结果数据分隔符，如：|、||、\\007、\\t、\\036', null, 8, null, null, 'default', '\\007');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MODEL_PROPS_SOLR', 'solr.collection.name', 'Solr collection名称', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_HBASE', 'hbase.fq.data.type', '结果数据类型，可选dsv、json，如果为dsv时hbase.fq.dsv.seprator生效', null, 7, null, null, 'default', 'dsv');
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_TYPE', '0', '内表', null, 1, null, null, 'default', null);
insert into T_GF_DICT (dict_type_id, dict_id, dict_name, status, sort_no, parent_id, seqno, appid, filter)
values ('IM_MD_PROPS_SOLR_HBASE', 'hbase.fq.dsv.seprator', '结果数据分隔符，如：|、||、\\007、\\t、\\036', null, 11, null, null, 'default', '\\007');
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
commit;
prompt 489 records loaded
prompt Loading T_GF_DICT_TYPE...
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
values ('OLQ_DS_PROPS_ORACLE', '联机查询oracle数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_MYSQL', '联机查询mysql数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_KYLIN', '联机查询kylin数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_DB2', '联机查询db2数据源参数', 'default');
insert into T_GF_DICT_TYPE (dict_type_id, dict_type_name, appid)
values ('OLQ_DS_PROPS_PGSQL', '联机查询pgsql数据源参数', 'default');
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
commit;
prompt 74 records loaded
prompt Loading T_GF_EMPLOYEE...
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7011', '2016010', 'rtsmap测试用户', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('17-08-2017 09:29:53', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7021', '2016010', 'rtsmap测试用户', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('17-08-2017 09:29:56', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('7031', '2016010', 'rtsmap测试用户', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('17-08-2017 09:30:07', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1031', '2017032102', '王吉', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('21-03-2017 09:20:44', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1`', '0002', 'core', 1, '1991-11-22', '1', null, '1', '2014-11-17', null, '15905696810', '15905696810', null, null, null, 'core@groupwith.com', to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, 'core@groupwith.com', null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('2011', '10705055', '王应利', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('23-05-2017 11:00:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4011', '1111', 'IFE', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('08-06-2017 16:12:18', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', '综合前端系统', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4021', '111', 'CRM', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('08-06-2017 16:15:28', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4031', '1111', 'MP', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('08-06-2017 16:16:32', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', '移动工作站', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('4041', '1111', 'SMS', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('08-06-2017 16:18:53', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', '短信系统', null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('5011', '00001', '核心系统', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('04-07-2017 09:34:51', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1', '00001', 'admin', 1, '2014-11-22', '1', null, '1', '2014-11-17', null, '111', '111111', null, null, null, 'admin@grouwith.com', to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, 'admin@grouwith.com', null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('1021', '2017032101', '王大明', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('21-03-2017 09:20:05', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('6011', 'S07', '村镇银行', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('11-07-2017 15:42:14', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('3011', '00000011', 'test', 1, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('31-05-2017 19:18:35', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
insert into T_GF_EMPLOYEE (emp_id, job_id, user_name, sex, birthday, status, card_no, card_type, indate, outdate, otel, mobile_no, htel, haddress, hzipcode, pemail, create_date, app_id, orgid, emp_comment, oemail, managerid, managername)
values ('3021', 'test1', 'test1', 3, null, '1', null, null, null, null, null, null, null, null, null, null, to_date('31-05-2017 19:20:51', 'dd-mm-yyyy hh24:mi:ss'), 'default', '1', null, null, null, null);
commit;
prompt 16 records loaded
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
commit;
prompt 68 records loaded
prompt Loading T_GF_LOGINUSER...
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('10011', '7011', 'rtsmap', 'rtsmap测试用户', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('17-08-2017 09:30:13', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5021', '4011', 'IFE', 'IFE', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:14:40', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, '2017-06-08', null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('2011', '2011', 'tomnic', '王应利', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('23-05-2017 11:00:20', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5031', '4021', 'CRM', 'CRM', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:15:28', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5041', '4031', 'MP', 'MP', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:16:32', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('5051', '4041', 'SMS', 'SMS', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('08-06-2017 16:18:53', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('1', '1', 'admin', 'admin', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('28-11-2015 23:19:20', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('9011', '6011', 'S07', '村镇银行', '670b14728ad9902aecba32e22fa4f6bd', '1', null, to_date('11-07-2017 15:50:14', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, '2017-07-11', '2019-11-07');
insert into T_GF_LOGINUSER (id, emp_id, user_id, user_name, password, status, menu_type, create_date, update_userid, app_id, user_comment, valid_startdate, valid_enddate)
values ('7011', '5011', 'S01', '核心系统', '96e79218965eb72c92a549dd5a330112', '1', null, to_date('04-07-2017 10:15:41', 'dd-mm-yyyy hh24:mi:ss'), null, 'default', null, null, null);
commit;
prompt 9 records loaded
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
commit;
prompt 57 records loaded
prompt Loading T_GF_NEXTID...
insert into T_GF_NEXTID (seq_type, next_id, last_time)
values ('LOG_ID', 501, to_date('28-09-2017 09:49:19', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 1 records loaded
prompt Loading T_GF_ORG...
insert into T_GF_ORG (orgid, orgname, orgcode, org_level, org_seq, org_type, org_address, zipcode, linkman, linktel, create_date, update_date, display_order, org_comment, app_id, parent_orgid)
values ('1', '台州银行', '000001', 1, '.1.', 'undefined', '台州', null, null, null, null, null, null, null, 'default', '0');
commit;
prompt 1 records loaded
prompt Loading T_GF_QUARTZ...
prompt Table is empty
prompt Loading T_GF_RES_AUTH...
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144411', 'ADMIN', 'role', '107001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144421', 'ADMIN', 'role', '100051', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144431', 'ADMIN', 'role', '100061', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144441', 'ADMIN', 'role', '100071', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144451', 'ADMIN', 'role', '100161', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144461', 'ADMIN', 'role', '113011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144471', 'ADMIN', 'role', '113021', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144481', 'ADMIN', 'role', '113031', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144491', 'ADMIN', 'role', '108001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144501', 'ADMIN', 'role', '112001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144511', 'ADMIN', 'role', '100091', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144521', 'ADMIN', 'role', '111011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144531', 'ADMIN', 'role', '100171', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144541', 'ADMIN', 'role', '110001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144551', 'ADMIN', 'role', '100111', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144561', 'ADMIN', 'role', '106001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144571', 'ADMIN', 'role', '100141', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144581', 'ADMIN', 'role', '100151', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144591', 'ADMIN', 'role', '100131', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144601', 'ADMIN', 'role', '100181', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144611', 'ADMIN', 'role', '100311', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144621', 'ADMIN', 'role', '100191', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144631', 'ADMIN', 'role', '100201', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144641', 'ADMIN', 'role', '100291', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144651', 'ADMIN', 'role', '100301', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144661', 'ADMIN', 'role', '100261', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144671', 'ADMIN', 'role', '100271', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144681', 'ADMIN', 'role', '115041', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144691', 'ADMIN', 'role', '115051', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144701', 'ADMIN', 'role', '115011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144711', 'ADMIN', 'role', '115021', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144721', 'ADMIN', 'role', '101011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144731', 'ADMIN', 'role', '1104', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144741', 'ADMIN', 'role', '1102', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144751', 'ADMIN', 'role', '1103', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144761', 'ADMIN', 'role', '1105', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144771', 'ADMIN', 'role', '1107', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144781', 'ADMIN', 'role', '101002', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144791', 'ADMIN', 'role', '109011', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125001', 'USER', 'role', '105001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125011', 'USER', 'role', '107001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('125021', 'USER', 'role', '108001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129431', 'USER', 'role', 'IQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129441', 'USER', 'role', 'IQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129451', 'USER', 'role', 'OLQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('129461', 'USER', 'role', 'OLQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('144401', 'ADMIN', 'role', '105001', 'default', 'menu');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143001', 'ADMIN', 'role', 'IM.cm.model.list.create', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143011', 'ADMIN', 'role', 'IM.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143021', 'ADMIN', 'role', 'IM.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143031', 'ADMIN', 'role', 'IM.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143041', 'ADMIN', 'role', 'IM.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143051', 'ADMIN', 'role', 'IM.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143061', 'ADMIN', 'role', 'IM.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143071', 'ADMIN', 'role', 'RC.service.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143081', 'ADMIN', 'role', 'RC.service.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143091', 'ADMIN', 'role', 'RC.service.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143101', 'ADMIN', 'role', 'MM.model.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143111', 'ADMIN', 'role', 'MM.model.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143121', 'ADMIN', 'role', 'MM.model.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143131', 'ADMIN', 'role', 'MM.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143141', 'ADMIN', 'role', 'MM.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143151', 'ADMIN', 'role', 'MM.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143161', 'ADMIN', 'role', 'RC.service.list.auth', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143171', 'ADMIN', 'role', 'OLQ.qm.testapp.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143181', 'ADMIN', 'role', 'OLQ.qm.testapp.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143191', 'ADMIN', 'role', 'RC.userService.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143201', 'ADMIN', 'role', 'RC.userService.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143211', 'ADMIN', 'role', 'RC.userService.listremove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143221', 'ADMIN', 'role', 'IQ.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143231', 'ADMIN', 'role', 'IQ.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143241', 'ADMIN', 'role', 'IQ.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143251', 'ADMIN', 'role', 'IQ.cm.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143261', 'ADMIN', 'role', 'IQ.cm.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143271', 'ADMIN', 'role', 'IQ.cm.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143281', 'ADMIN', 'role', 'MM.contractor.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143291', 'ADMIN', 'role', 'MM.contractor.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143301', 'ADMIN', 'role', 'MM.contractor.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143311', 'ADMIN', 'role', 'IQ.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143321', 'ADMIN', 'role', 'RTS.qm.producer.test', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143331', 'ADMIN', 'role', 'IQ.qm.test.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143341', 'ADMIN', 'role', 'IQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143351', 'ADMIN', 'role', 'RTS.qm.consumer.test', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143361', 'ADMIN', 'role', 'IQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143371', 'ADMIN', 'role', 'OLQ.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143381', 'ADMIN', 'role', 'OLQ.qm.test.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143391', 'ADMIN', 'role', 'OLQ.qm.app.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143401', 'ADMIN', 'role', 'OLQ.qm.app.download', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143411', 'ADMIN', 'role', 'RTS.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143421', 'ADMIN', 'role', 'RTS.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143431', 'ADMIN', 'role', 'RTS.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143441', 'ADMIN', 'role', 'RTS.cm.producer.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143451', 'ADMIN', 'role', 'RTS.cm.producer.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143461', 'ADMIN', 'role', 'RTS.cm.producer.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143471', 'ADMIN', 'role', 'RTS.cm.consumer.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143481', 'ADMIN', 'role', 'RTS.cm.consumer.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143491', 'ADMIN', 'role', 'RTS.cm.consumer.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143501', 'ADMIN', 'role', 'IQ.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143511', 'ADMIN', 'role', 'RTS.cm.md.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143521', 'ADMIN', 'role', 'IQ.cm.ds.list.add', 'default', 'func');
commit;
prompt 100 records committed...
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143531', 'ADMIN', 'role', 'IQ.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143541', 'ADMIN', 'role', 'RTS.cm.md.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143551', 'ADMIN', 'role', 'RTS.cm.md.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143561', 'ADMIN', 'role', 'OLQ.cm.ds.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143571', 'ADMIN', 'role', 'OLQ.cm.ds.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143581', 'ADMIN', 'role', 'OLQ.cm.ds.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143591', 'ADMIN', 'role', 'MM.qm.test.search', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143601', 'ADMIN', 'role', 'OLQ.cm.app.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143611', 'ADMIN', 'role', 'OLQ.cm.app.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143621', 'ADMIN', 'role', 'OLQ.cm.app.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143631', 'ADMIN', 'role', 'IM.cm.model.list.add', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143641', 'ADMIN', 'role', 'IM.cm.model.list.edit', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143651', 'ADMIN', 'role', 'IM.cm.model.list.remove', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143661', 'ADMIN', 'role', 'RC.service.list.start', 'default', 'func');
insert into T_GF_RES_AUTH (id, auth_id, auth_type, res_id, app_id, res_type)
values ('143671', 'ADMIN', 'role', 'RC.service.list.stop', 'default', 'func');
commit;
prompt 115 records loaded
prompt Loading T_GF_ROLE...
insert into T_GF_ROLE (roleid, app_id, rolename, role_desc)
values ('USER', 'default', '普通用户', null);
insert into T_GF_ROLE (roleid, app_id, rolename, role_desc)
values ('ADMIN', 'default', '系统管理员', null);
commit;
prompt 2 records loaded
prompt Loading T_GF_SCHD_JOB...
prompt Table is empty
prompt Loading T_GF_TASK_POOL...
prompt Table is empty
set feedback on
set define on
prompt Done.
