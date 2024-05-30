package com.example.CyrsachJava.mapper;

import com.example.CyrsachJava.dto.BudgetDTO;
import com.example.CyrsachJava.model.Budget;
import com.example.CyrsachJava.model.Goal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {
    private final ModelMapper modelMapper;

    public BudgetMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BudgetDTO toDto(Budget budget) {
        return modelMapper.map(budget, BudgetDTO.class);
    }

    public Budget toEntity(BudgetDTO budgetDTO, Goal goal) {
        Budget budget = modelMapper.map(budgetDTO, Budget.class);
        budget.setGoal(goal);
        return budget;
    }
}
