package com.example.CyrsachJava.service;

import com.example.CyrsachJava.dto.BudgetDTO;
import com.example.CyrsachJava.mapper.BudgetMapper;
import com.example.CyrsachJava.model.Budget;
import com.example.CyrsachJava.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;

    public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper) {
        this.budgetRepository = budgetRepository;
        this.budgetMapper = budgetMapper;
    }

    public List<BudgetDTO> getAllBudgets() {
        List<Budget> budgets = budgetRepository.findAll();
        return budgets.stream()
                .map(budgetMapper::toDto)
                .collect(Collectors.toList());
    }

    public BudgetDTO getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .map(budgetMapper::toDto)
                .orElse(null);
    }

    public BudgetDTO createBudget(BudgetDTO budgetDTO) {
        Budget budget = budgetMapper.toEntity(budgetDTO, null);
        Budget savedBudget = budgetRepository.save(budget);
        return budgetMapper.toDto(savedBudget);
    }

    public BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO) {
        Budget existingBudget = budgetRepository.findById(id)
                .orElse(null);
        if (existingBudget != null) {
            existingBudget.setBudget_name(budgetDTO.getBudgetName());
            existingBudget.setBudget_amount(budgetDTO.getBudgetAmount());
            existingBudget.setTimeframe(budgetDTO.getTimeframe());
            existingBudget.setNotification_threshold(budgetDTO.getNotificationThreshold());
            existingBudget.setNotification_method(budgetDTO.getNotificationMethod());
            Budget updatedBudget = budgetRepository.save(existingBudget);
            return budgetMapper.toDto(updatedBudget);
        }
        return null;
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}


