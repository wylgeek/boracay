package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.EdAppResponseParamMapper;
import com.hex.bigdata.udsp.ed.model.EdAppResponseParam;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.util.Util;
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
public class EdAppResponseParamService {
    @Autowired
    private EdAppResponseParamMapper edAppResponseParamMapper;

    /**
     * 删除一条接口输入参数信息
     *
     * @param pkId
     * @return
     */
    public MessageResult deleteByPrimaryKey(String pkId) {
        int result = edAppResponseParamMapper.deleteByPrimaryKey(pkId);
        if (result == 1) {
            return new MessageResult(true, "删除成功");
        }
        return new MessageResult(false, "删除失败");
    }

    /**
     * 修改一条接口输入参数
     *
     * @param edAppResponseParam
     * @return
     */
    public MessageResult updateByPrimaryKey(EdAppResponseParam edAppResponseParam) {
        int result = edAppResponseParamMapper.updateByPrimaryKey(edAppResponseParam);
        if (result == 1) {
            return new MessageResult(true, "修改成功");
        }
        return new MessageResult(false, "修改失败");
    }

    /**
     * 查询接口输入参数
     *
     * @param pkId
     * @return
     */
    public EdAppResponseParam selectByPrimaryKey(String pkId) {
        return edAppResponseParamMapper.selectByPrimaryKey(pkId);
    }

    public List<EdAppResponseParam> selectAll() {
        return edAppResponseParamMapper.selectAll();
    }


    public int addEdAppResponseParam(EdAppResponseParam edAppResponseParam) {
        edAppResponseParam.setPkId(Util.uuid());
        return edAppResponseParamMapper.addEdAppResponseParam(edAppResponseParam);
    }

    /**
     * 获取接口应用输出参数
     * @param appId
     * @return
     */
    public List<EdAppResponseParam> getEdAppResponseParamByAppId(String appId) {
        return edAppResponseParamMapper.getEdAppResponseParamByAppId(appId);
    }

    public int deleteEdAppResponseParamByAppId(String appId) {
        return edAppResponseParamMapper.deleteEdAppResponseParamByAppId(appId);
    }

    @Transactional(rollbackFor = Exception.class)
    public MessageResult addEdAppResponseParam(List<EdAppResponseParam> edAppResponseParams) throws Exception {
        for (EdAppResponseParam edAppResponseParam : edAppResponseParams) {
            int result = this.addEdAppResponseParam(edAppResponseParam);
            if (result != 1) {
                throw new Exception();
            }
        }
        return new MessageResult(true, "添加成功");
    }

    /**
     * 总入口
     * 添加接口应用输出参数
     * @param pkId
     * @param edAppResponseParams
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult addEdAppResponseParam(String pkId, List<EdAppResponseParam> edAppResponseParams) throws Exception {
        for (EdAppResponseParam edAppResponseParam : edAppResponseParams) {
            edAppResponseParam.setAppId(pkId);
            int result = this.addEdAppResponseParam(edAppResponseParam);
            if (result != 1) {
                throw new Exception();
            }
        }
        return new MessageResult(true, "添加成功");
    }
}
