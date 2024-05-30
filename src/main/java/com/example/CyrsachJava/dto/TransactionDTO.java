package com.example.CyrsachJava.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {
    private Long id;
    private String type;
    private double amount;
    private Date date;
    private Long userId;
    private Long categoryId;
    private Long budgetId;
}
