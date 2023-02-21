package org.example.service;

import org.example.dto.CreateToDoRequestDto;
import org.example.dto.ToDoDto;
import org.example.dto.UpdateToDoRequestDto;
import org.example.model.ToDo;

import java.util.Set;

public interface ToDoService {

    public ToDo findToDoById(String id);

    public ToDoDto getToDoById(String id);

    public Set<ToDoDto> getToDosByUserId(String id);

    public ToDoDto createToDo(CreateToDoRequestDto requestDto);

    public ToDoDto updateToDo(String id, UpdateToDoRequestDto requestDto);

    public void deleteToDo(String id);

}
