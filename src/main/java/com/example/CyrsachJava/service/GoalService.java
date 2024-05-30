package com.example.CyrsachJava.service;

import com.example.CyrsachJava.dto.GoalDTO;
import com.example.CyrsachJava.mapper.GoalMapper;
import com.example.CyrsachJava.model.Goal;
import com.example.CyrsachJava.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    public GoalService(GoalRepository goalRepository, GoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
    }

    public List<GoalDTO> getAllGoals() {
        List<Goal> goals = goalRepository.findAll();
        return goals.stream()
                .map(goalMapper::toDto)
                .collect(Collectors.toList());
    }

    public GoalDTO getGoalById(Long id) {
        return goalRepository.findById(id)
                .map(goalMapper::toDto)
                .orElse(null);
    }

    public GoalDTO createGoal(GoalDTO goalDTO) {
        Goal goal = goalMapper.toEntity(goalDTO);
        Goal savedGoal = goalRepository.save(goal);
        return goalMapper.toDto(savedGoal);
    }

    public GoalDTO updateGoal(Long id, GoalDTO goalDTO) {
        Goal existingGoal = goalRepository.findById(id)
                .orElse(null);
        if (existingGoal != null) {
            existingGoal.setName(goalDTO.getName());
            existingGoal.setGoal_amount(goalDTO.getGoalAmount());
            existingGoal.setTarget_date(goalDTO.getTargetDate());
            existingGoal.setPriority(goalDTO.getPriority());
            Goal updatedGoal = goalRepository.save(existingGoal);
            return goalMapper.toDto(updatedGoal);
        }
        return null;
    }

    public void deleteGoal(Long id) {
        goalRepository.deleteById(id);
    }
}


