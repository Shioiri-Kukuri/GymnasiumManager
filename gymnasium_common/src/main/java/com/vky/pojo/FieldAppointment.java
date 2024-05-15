package com.vky.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

@Data
public class FieldAppointment implements Serializable {

    private Integer id;
    private Integer fieldId;
    private Integer account;
    private Time timeStart;
    private Time timeEnd;
    private Integer appointmentStatus;
    private String appointmentComment;
}
