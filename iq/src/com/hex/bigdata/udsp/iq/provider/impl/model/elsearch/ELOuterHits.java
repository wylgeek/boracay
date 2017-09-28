package com.hex.bigdata.udsp.iq.provider.impl.model.elsearch;

import java.util.List;

public class ELOuterHits {

    private List<ELInnerHits> hits;
    private int total;
    private Double max_score;

    public List<ELInnerHits> getHits() {
        return hits;
    }

    public void setHits(List<ELInnerHits> hits) {
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Double getMax_score() {
        return max_score;
    }

    public void setMax_score(Double max_score) {
        this.max_score = max_score;
    }
}
