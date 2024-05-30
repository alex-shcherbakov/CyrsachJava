package com.example.CyrsachJava.dto;

import lombok.Data;

@Data
public class BudgetDTO {
    private Long id;
    private String budgetName;
    private double budgetAmount;
    private String timeframe;
    private int notificationThreshold;
    private String notificationMethod;
    private Long goalId;
}

