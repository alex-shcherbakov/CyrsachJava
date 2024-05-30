package com.example.CyrsachJava.mapper;

import com.example.CyrsachJava.dto.GoalDTO;
import com.example.CyrsachJava.model.Goal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GoalMapper {
    private final ModelMapper modelMapper;

    public GoalMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GoalDTO toDto(Goal goal) {
        return modelMapper.map(goal, GoalDTO.class);
    }

    public Goal toEntity(GoalDTO goalDTO) {
        return modelMapper.map(goalDTO, Goal.class);
    }
}

