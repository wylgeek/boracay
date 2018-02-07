package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper;
import com.hex.bigdata.udsp.ed.model.EdInterfaceParam;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/5
 * Time:8:52
 */
@Service
public class EdInterfaceParamService extends BaseService{
    @Autowired
    private EdInterfaceParamMapper edInterfaceParamMapper;

    /**
     * 删除接口参数
     * @param pkId
     * @return
     */
    public MessageResult deleteByPrimaryKey(String pkId) {
        int result = edInterfaceParamMapper.deleteByPrimaryKey(pkId);
        if(result == 1) {
            return new MessageResult(true,"删除成功！");
        }
        return new MessageResult(false,"删除失败！");
    }

    /**
     * 插入接口参数
     * @param record
     * @return
     */
    public MessageResult insert(EdInterfaceParam record) {
        record.setPkId(Util.uuid());
        int result = edInterfaceParamMapper.insert(record);
        if(result == 1) {
            return new MessageResult(true,"添加成功！");
        }
        return new MessageResult(false,"添加失败！");
    }

    /**
     * 修改接口参数
     * @param edInterfaceParams
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor=Exception.class)
    public MessageResult updateByPrimaryKey(List<EdInterfaceParam> edInterfaceParams) throws Exception {
        for(EdInterfaceParam edInterfaceParam:  edInterfaceParams){
            int result = edInterfaceParamMapper.updateByPrimaryKey(edInterfaceParam);
            if(result != 1) {
                throw new Exception();
            }
        }
        return new MessageResult(true,"更新成功！");
    }

    public EdInterfaceParam selectByPrimaryKey(String pkId) {
        return edInterfaceParamMapper.selectByPrimaryKey(pkId);
    }

    public List<EdInterfaceParam> selectAll() {
        return edInterfaceParamMapper.selectAll();
    }

    public List<EdInterfaceParam> selectList(EdInterfaceParam record,Page page) {
        return edInterfaceParamMapper.selectList(record,page);
    }

    public List<EdInterfaceParam> select(EdInterfaceParam record) {
        return edInterfaceParamMapper.select(record);
    }

    public List<EdInterfaceParam> selectByInterfaceId(String interfaceId) {
        return edInterfaceParamMapper.selectByInterfaceId(interfaceId);
    }

    public int deleteByInterfaceId(String interfaceId) {
        return edInterfaceParamMapper.deleteByInterfaceId(interfaceId);
    }

    /**
     * 总入口
     * 添加输入参数
     * @param interfaceId
     * @param edInterfaceParams
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor=Exception.class)
    public MessageResult insertRequestColList(String interfaceId, List<EdInterfaceParam> edInterfaceParams) throws Exception {
        for(EdInterfaceParam edInterfaceParam:edInterfaceParams) {
            edInterfaceParam.setPkId(Util.uuid());
            edInterfaceParam.setType("1");
            edInterfaceParam.setInterfaceId(interfaceId);
            int result = edInterfaceParamMapper.insert(edInterfaceParam);
            if(result != 1) {
                throw new Exception();
            }
        }
        return new MessageResult(true,"输入参数插入成功");
    }

    /**
     * 总入口
     * 添加输出参数
     * @param interfaceId
     * @param edInterfaceParams
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor=Exception.class)
    public MessageResult insertResponseColList(String interfaceId, List<EdInterfaceParam> edInterfaceParams) throws Exception {
        for(EdInterfaceParam edInterfaceParam:edInterfaceParams) {
            edInterfaceParam.setPkId(Util.uuid());
            edInterfaceParam.setType("2");
            edInterfaceParam.setInterfaceId(interfaceId);
            int result = edInterfaceParamMapper.insert(edInterfaceParam);
            if(result != 1) {
                throw new Exception();
            }
        }
        return new MessageResult(true,"输出参数插入成功");
    }

    /**
     * 获取接口参数列表
     * @param record
     * @return
     */
    public List<EdInterfaceParam> selectList(EdInterfaceParam record) {
        return edInterfaceParamMapper.selectList(record);
    }

    public List<EdInterfaceParam> selectByInterfaceIdAndType(String interfaceId,String type) {
        EdInterfaceParam edInterfaceParam = new EdInterfaceParam();
        edInterfaceParam.setInterfaceId(interfaceId);
        edInterfaceParam.setType(type);
        return this.edInterfaceParamMapper.selectByInterfaceIdAndType(edInterfaceParam);
    }
}
