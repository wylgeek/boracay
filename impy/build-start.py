#!/usr/local/python/bin/python
# coding=utf-8

# ---------------------------------------------- HEAD START ------------------------------------------------
# @descript：
# UDSP交互建模的模型批量构建（批量作业调用）接口
# @author：Junjie.M
# @date: 2017-04-01
# @param：
# serviceName       服务名
# udspUser          用户名
# token             密码
# bizDate           业务日期 20170527
# ---------------------------------------------- HEAD  END -------------------------------------------------

# ----------------------------------------------模板说明----------------------------------------------------
# 开发UDSP交互建模的模型批量构建Python脚本
# 采用过程方式开发，基础组件提供调用接口，如接口不够，基础组件增加提供
#
# 1.建议采用Python Ide 工具开发 推荐PyCharm，PyDev
# 2.程序指定Python 2.7版本，目前不支持3.0以上版本
# 3.程序必须符合Python开发规范
# 4.所有实现方法必须在本文件内实现，原则上不允许自定义外部Python程序,如确有需要联系大数据平台组
# ----------------------------------------------------------------------------------------------------------

# ---------------------------------------------- IMPORT START -----------------------------------------------
import os, sys
import job.base.ClientUtil as util
from json import *
from job.base.HttpUtil import *
from job.base.JobBase import ExitCode

#---------------------------------------------- IMPORT START -----------------------------------------------
default_encoding = 'utf-8'
if sys.getdefaultencoding() != default_encoding:
    reload(sys)
    sys.setdefaultencoding(default_encoding)
# 当前脚本目录
filename = sys.argv[0]
dirname = os.path.dirname(filename)
abspath = os.path.abspath(dirname)


def checkArgs(length):
    util.debug('参数检查!')
    util.checkArgsEx(length)

#-----------------------------------------以上脚本信息不可以修改-------------------------------------------

#---------------------------------------参数检查、参数赋值【开始】------------------------------------------
# 参数检查 参数，当存续存在参数请预先检查 ，checkArgs(1) 参数是检查该方法外部参数有几个
# 所有参数赋值，并提取各个参数值，如没有参数，请删除
checkArgs(4)
httpUrl = "http://10.1.97.1:8080/udsp/http/consume"
serviceName = sys.argv[1]
udspUser = sys.argv[2]
token = sys.argv[3]
bizDate = sys.argv[4]
#---------------------------------------参数检查、参数赋值【结束】------------------------------------------

#----------------------------------------常用日期函数列表【开始】--------------------------------------------
# 日期转换
bizDate10 = util.DateUtils.getLongDate('20170401')
bizDate8 = util.DateUtils.getShortDate('2017-04-01')
# 当前日期下一日
nextDate = util.DateUtils.getNextDate(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期上一日
preDate = util.DateUtils.getPreDate(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应周初（周一）
weekStart = util.DateUtils.getWeekStart(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应月初
monthStart = util.DateUtils.getMonthStart(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期上月初
prMonStart = util.DateUtils.getPrMonStart(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应旬初
tendStart = util.DateUtils.getTendStart(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应半年初
halfyearStart = util.DateUtils.getHalfyearStart(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应年初
yearStart = util.DateUtils.getYearStart(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应周末（周六）
weekEnd = util.DateUtils.getWeekEnd(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应月末
monthEnd = util.DateUtils.getMonthEnd(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期上月末
prMonEnd = util.DateUtils.getPrMonEnd(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应旬末
tendEnd = util.DateUtils.getTendEnd(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应季末
quarterEnd = util.DateUtils.getQuarterEnd(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期上季末
prQtrEnd = util.DateUtils.getPrQtrEnd(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期对应半年末
halfyearEnd = util.DateUtils.getHalfyearEnd(bizDate10, util.DATE_YYYY_MM_DD)
# 当前日期对应年末
yearEnd = util.DateUtils.getYearEnd(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期去年同期
priSDate = util.DateUtils.getPriSDate(bizDate8, util.DATE_YYYY_MM_DD)
# 当前日期前几天或后几天日期  当前日期为字符串格式为20150411
dateByCount = util.DateUtils.getDateByCount(bizDate8, -2, util.DATE_YYYY_MM_DD)
# 当前日期对应季初
quarterStart = util.DateUtils.getQuarterStart(bizDate8, util.DATE_YYYY_MM_DD)
# 上年末日期
preYearEndStart = util.DateUtils.getOppYearEnd(bizDate8, -1, util.DATE_YYYY_MM_DD)
# 当月第2天日期
prMon = util.DateUtils.getPrMon(bizDate8, 2, util.DATE_YYYY_MM_DD)

util.debug('bizDate10=' + bizDate10)
util.debug('bizDate8=' + bizDate8)
util.debug('dateByCount=' + dateByCount)
util.debug('halfyearEnd=' + halfyearEnd)
util.debug('halfyearStart=' + halfyearStart)
util.debug('monthEnd=' + monthEnd)
util.debug('monthStart=' + monthStart)
util.debug('yearStart=' + yearStart)
util.debug('weekStart=' + weekStart)
util.debug('yearEnd=' + yearEnd)
util.debug('weekEnd=' + weekEnd)
util.debug('nextDate=' + nextDate)
util.debug('preDate=' + preDate)
util.debug('prMonStart=' + prMonStart)
util.debug('prMonEnd=' + prMonEnd)
util.debug('quarterEnd=' + quarterEnd)
util.debug('prQtrEnd=' + prQtrEnd)
util.debug('priSDate=' + priSDate)
util.debug('tendStart=' + tendStart)
util.debug('tendEnd=' + tendEnd)
util.debug('quarterStart=' + quarterStart)
util.debug('preYearEndStart=' + preYearEndStart)
util.debug('prMon=' + prMon)
#----------------------------------------常用日期函数列表【结束】--------------------------------------------

#--------------------------------------UDSP模型应用程序调用【开始】------------------------------------------
try:
    if len(serviceName.strip()) == 0:
        raise DcpException(ExitCode.EXIT_ERROR, '传入的服务名地址为空')
    if len(udspUser.strip()) == 0:
        raise DcpException(ExitCode.EXIT_ERROR, '传入的用户名为空')
    if len(token.strip()) == 0:
        raise DcpException(ExitCode.EXIT_ERROR, '传入的密码为空')
    if len(bizDate.strip()) == 0:
        raise DcpException(ExitCode.EXIT_ERROR, '传入的业务日期为空')
    util.debug("serviceName:" + serviceName)
    util.debug("udspUser:" + udspUser)
    util.debug("token:" + token)
    util.debug("bizDate:" + bizDate)

    ## 开始构建 ##
    util.info('构建数据 ' + serviceName + " " + bizDate)
    request = {
        'serviceName': serviceName,
        'appUser': "schedule",
        'udspUser': udspUser,
        'token': token,
        'data': {'bizDate': bizDate}
        #'data': {'bizDate': util.DateUtils.getLongDate(bizDate)} # 8位转10位
    }
    util.debug("request" + str(request))
    response = HttpUtil.httpPost(httpUrl, request)
    if len(response) == 0:
        raise DcpException(ExitCode.EXIT_ERROR, '返回的结果数据为空')
    util.debug("response:" + response)
    responseJson = JSONDecoder().decode(response)
    status = responseJson['status'].__str__()
    message = responseJson['message'].__str__()
    errorCode = responseJson['errorCode'].__str__()
    consumeTime = responseJson['consumeTime'].__str__()
    if status == 'DEFEAT':
        raise DcpException(ExitCode.EXIT_ERROR, '请求构建数据' + serviceName + ' ' + bizDate + '失败\n错误信息如下：\n' + message)
    elif status == 'SUCCESS':
        util.exit(ExitCode.EXIT_SUCCESS, '请求构建数据执行成功!' + serviceName + ' ' + bizDate + ' 耗时：' + consumeTime)
except DcpException as e:
    errInfo = "错误编码：" + str(e.code) + "  错误信息:" + e.message
    util.exit(ExitCode.EXIT_ERROR, errInfo)
#--------------------------------------UDSP模型应用程序调用【结束】------------------------------------------

#----------------------------以下脚本不可以修改---------------------------------
util.exit(ExitCode.EXIT_ERROR, '执行失败!')
