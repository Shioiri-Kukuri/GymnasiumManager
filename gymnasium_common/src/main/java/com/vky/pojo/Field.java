package com.vky.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Field implements Serializable {
    private Integer id;
    private Integer fieldNo;
    private String fieldName;
    private String fieldType;
    private Integer fieldCost;
    private Integer fieldStatus;

    /**
     * 获取
     * @return status
     */
    public Integer getStatus() {
        return fieldStatus;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(Integer status) {
        this.fieldStatus = status;
    }
}
