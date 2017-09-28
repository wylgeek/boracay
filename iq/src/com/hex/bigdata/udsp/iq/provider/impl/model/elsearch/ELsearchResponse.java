package com.hex.bigdata.udsp.iq.provider.impl.model.elsearch;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class ELsearchResponse {
    /**
     * 请求耗时（单位毫秒）
     */
    private long took;

    /**
     * 请求是否超时，true为超时；false为没有超时
     */
    private boolean timed_out;

    /**
     * 分片计算信息
     */
    private JSONObject _shards;

    /**
     * 查询记录信息
     */
    private ELOuterHits hits;

    public long getTook() {
        return took;
    }

    public void setTook(long took) {
        this.took = took;
    }

    public boolean isTimed_out() {
        return timed_out;
    }

    public void setTimed_out(boolean timed_out) {
        this.timed_out = timed_out;
    }

    @JSONField(name="_shards")
    public JSONObject get_shards() {
        return _shards;
    }

    @JSONField(name="_shards")
    public void set_shards(JSONObject _shards) {
        this._shards = _shards;
    }

    public ELOuterHits getHits() {
        return hits;
    }

    public void setHits(ELOuterHits hits) {
        this.hits = hits;
    }

    public static void main(String[] args) {
        String jsonString = "{\"took\":6,\"timed_out\":false,\"_shards\":{\"total\":5,\"successful\":5,\"skipped\":0,\"failed\":0},\"hits\":{\"total\":2,\"max_score\":null,\"hits\":[{\"_index\":\"megacorp\",\"_type\":\"employee\",\"_id\":\"9\",\"_score\":null,\"_source\":{\"acct_no\":\"1000008\",\"acct_name\":\"刘邦\"},\"sort\":[\"1000008\",28]},{\"_index\":\"megacorp\",\"_type\":\"employee\",\"_id\":\"10\",\"_score\":null,\"_source\":{\"acct_no\":\"1000009\",\"acct_name\":\"李莉\"},\"sort\":[\"1000009\",24]}]}}";
        ELsearchResponse eLsearchResponse = JSONObject.parseObject(jsonString,ELsearchResponse.class);
        //ELsearchResponse eLsearchResponse2 = JSONUtil.parseJSON2Obj(jsonString,ELsearchResponse.class);
        //JSONObject hitsJsonObject =eLsearchResponse2.getHits();
        //JSONArray innerHits = (JSONArray)hitsJsonObject.get("hits");
        System.out.println(1);
    }

}
