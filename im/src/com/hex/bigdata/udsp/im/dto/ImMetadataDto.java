package com.hex.bigdata.udsp.im.dto;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.im.model.ImMetadata;
import com.hex.bigdata.udsp.im.model.ImMetadataCol;

import java.util.List;

/**
 * Created by hj on 2017/9/5.
 */
public class ImMetadataDto {
    private ImMetadata imMetadata;
    private List<ImMetadataCol> imMetadataColList;
    private List<ComProperties> comPropertiesList;

    public ImMetadata getImMetadata() {
        return imMetadata;
    }

    public void setImMetadata(ImMetadata imMetadata) {
        this.imMetadata = imMetadata;
    }

    public List<ImMetadataCol> getImMetadataColList() {
        return imMetadataColList;
    }

    public void setImMetadataColList(List<ImMetadataCol> imMetadataColList) {
        this.imMetadataColList = imMetadataColList;
    }

    public List<ComProperties> getComPropertiesList() {
        return comPropertiesList;
    }

    public void setComPropertiesList(List<ComProperties> comPropertiesList) {
        this.comPropertiesList = comPropertiesList;
    }
}
