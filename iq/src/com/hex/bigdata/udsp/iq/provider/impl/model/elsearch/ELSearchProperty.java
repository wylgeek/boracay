package com.hex.bigdata.udsp.iq.provider.impl.model.elsearch;

/**
 * Created by JunjieM on 2017-10-31.
 */
public class ELSearchProperty {
    private String type;
    private String index;
    private boolean store;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public boolean isStore() {
        return store;
    }

    public void setStore(boolean store) {
        this.store = store;
    }
}
