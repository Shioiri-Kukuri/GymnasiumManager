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

}
