package com.example.CyrsachJava.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "goal")
@EqualsAndHashCode(callSuper = true)
public class Goal extends BaseEntity {
    private String name;
    private double goal_amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date target_date;
    private int priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "goal", fetch = FetchType.LAZY)
    private Set<Budget> budgets;
}