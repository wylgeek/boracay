package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.EdAppRequestParamMapper;
import com.hex.bigdata.udsp.ed.model.EdAppRequestParam;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.util.Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:16:19
 */
@Service
public class EdAppRequestParamService {
    @Autowired
    private EdAppRequestParamMapper edAppRequestParamMapper;

    /**
     * 删除一条接口输入参数信息
     * @param pkId
     * @return
     */
    public MessageResult deleteByPrimaryKey(String pkId) {
        int result = edAppRequestParamMapper.deleteByPrimaryKey(pkId);
        if (result == 1) {
            return new MessageResult(true, "删除成功");
        }
        return new MessageResult(false, "删除失败");
    }

    /**
     * 修改一条接口输入参数
     * @param edAppRequestParam
     * @return
     */
    public MessageResult updateByPrimaryKey(EdAppRequestParam edAppRequestParam) {
        if(StringUtils.isBlank(edAppRequestParam.getIsNeed())) {
            edAppRequestParam.setIsNeed("0");
        }
        int result = edAppRequestParamMapper.updateByPrimaryKey(edAppRequestParam);
        if (result == 1) {
            return new MessageResult(true, "修改成功");
        }
        return new MessageResult(false, "修改失败");
    }

    /**
     * 查询接口输入参数
     * @param pkId
     * @return
     */
    public EdAppRequestParam selectByPrimaryKey(String pkId) {
        return edAppRequestParamMapper.selectByPrimaryKey(pkId);
    }

    public List<EdAppRequestParam> selectAll() {
        return edAppRequestParamMapper.selectAll();
    }


    public int addEdAppRequestParam(EdAppRequestParam edAppRequestParam) {
        edAppRequestParam.setPkId(Util.uuid());
        if(StringUtils.isBlank(edAppRequestParam.getIsNeed())) {
            edAppRequestParam.setIsNeed("1");
        }
        return edAppRequestParamMapper.addEdAppRequestParam(edAppRequestParam);
    }

    /**
     * 获取接口应用输入参数
     * @param appId
     * @return
     */
    public List<EdAppRequestParam> getEdAppRequestParamByAppId(String appId) {
        return edAppRequestParamMapper.getEdAppRequestParamByAppId(appId);
    }

    public int deleteEdAppRequestParamByAppId(String appId) {
        return edAppRequestParamMapper.deleteEdAppRequestParamByAppId(appId);
    }

    @Transactional(rollbackFor = Exception.class)
    public MessageResult addEdAppRequestParam(List<EdAppRequestParam> edAppRequestParams) throws Exception {
        for (EdAppRequestParam edAppRequestParam : edAppRequestParams) {
            int result = this.addEdAppRequestParam(edAppRequestParam);
            if (result != 1) {
                throw new Exception();
            }
        }
        return new MessageResult(true, "添加成功");
    }

    /**
     * 总入口
     * 添加接口应用输入参数
     * @param pkId
     * @param edAppRequestParams
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult addEdAppRequestParam(String pkId, List<EdAppRequestParam> edAppRequestParams) throws Exception {
        for (EdAppRequestParam edAppRequestParam : edAppRequestParams) {
            edAppRequestParam.setAppId(pkId);
            int result = this.addEdAppRequestParam(edAppRequestParam);
            if (result != 1) {
                throw new Exception();
            }
        }
        return new MessageResult(true, "添加成功");
    }

}
