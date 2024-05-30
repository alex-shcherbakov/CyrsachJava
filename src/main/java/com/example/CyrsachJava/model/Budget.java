package com.example.CyrsachJava.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "budget")
@EqualsAndHashCode(callSuper = true)
public class Budget extends BaseEntity {
    private String budget_name;
    private double budget_amount;
    private String timeframe;
    private int notification_threshold;
    private String notification_method;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY)
    private Set<Transaction> transactions;
}
