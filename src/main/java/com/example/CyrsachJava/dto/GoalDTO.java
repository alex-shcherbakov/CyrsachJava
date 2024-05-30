package com.example.CyrsachJava.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GoalDTO {
    private Long id;
    private String name;
    private double goalAmount;
    private Date targetDate;
    private int priority;
    private Long userId;
}
