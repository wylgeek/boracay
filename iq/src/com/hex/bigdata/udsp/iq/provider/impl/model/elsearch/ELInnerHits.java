package com.hex.bigdata.udsp.iq.provider.impl.model.elsearch;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

public class ELInnerHits {

    /**
     * 索引名称
     */
    private String _index;
    /**
     * 类型
     */
    private String _type;
    /**
     * 主键
     */
    private String _id;
    /**
     * 计算分数
     */
    private Double _score;
    /**
     * 查询记录
     */
    private Map<String, Object> _source;

    @JSONField(name="_index")
    public String get_index() {
        return _index;
    }

    @JSONField(name="_index")
    public void set_index(String _index) {
        this._index = _index;
    }

    @JSONField(name="_type")
    public String get_type() {
        return _type;
    }

    @JSONField(name="_type")
    public void set_type(String _type) {
        this._type = _type;
    }

    @JSONField(name="_id")
    public String get_id() {
        return _id;
    }

    @JSONField(name="_id")
    public void set_id(String _id) {
        this._id = _id;
    }

    @JSONField(name="_score")
    public Double get_score() {
        return _score;
    }

    @JSONField(name="_score")
    public void set_score(Double _score) {
        this._score = _score;
    }

    @JSONField(name="_source")
    public Map<String, Object> get_source() {
        return _source;
    }

    @JSONField(name="_source")
    public void set_source(Map<String, Object> _source) {
        this._source = _source;
    }
}
