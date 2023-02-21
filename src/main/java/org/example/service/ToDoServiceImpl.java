package org.example.service;

import org.example.dto.CreateToDoRequestDto;
import org.example.dto.ToDoDto;
import org.example.dto.UpdateToDoRequestDto;
import org.example.dto.UserDto;
import org.example.dto.converter.ToDoDtoConverter;
import org.example.exception.ToDoNotFoundException;
import org.example.model.ToDo;
import org.example.model.User;
import org.example.repository.ToDoRepository;

import java.time.LocalDateTime;
import java.util.Set;

public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserService userService;
    private final ToDoDtoConverter converter;

    public ToDoServiceImpl(ToDoRepository toDoRepository, UserService userService, ToDoDtoConverter converter) {
        this.toDoRepository = toDoRepository;
        this.userService = userService;
        this.converter = converter;
    }

    @Override
    public ToDo findToDoById(String id) {
        return toDoRepository.findById(id)
                .orElseThrow(() ->
                        new ToDoNotFoundException("Task could not find by id: " + id));
    }

    @Override
    public ToDoDto getToDoById(String id) {
        return converter.convert(toDoRepository.getById(id));
    }

    @Override
    public Set<ToDoDto> getToDosByUserId(String id) {
        UserDto userDto = userService.getUserById(id);
        return userDto.getTasks();
    }



    @Override
    public ToDoDto createToDo(CreateToDoRequestDto requestDto) {
        User user = userService.findUserById(requestDto.getUserId());
        ToDo task = new ToDo(requestDto.getTaskHeader(),
                requestDto.getTaskDescription(),
                LocalDateTime.now(),
                user);

        return converter.convert(toDoRepository.save(task));
    }

    public ToDoDto updateToDo(String id, UpdateToDoRequestDto requestDto) {
        User user = userService.findUserById(requestDto.getUserId());
        ToDo task = new ToDo(id,
                requestDto.getTaskHeader(),
                requestDto.getTaskDescription(),
                LocalDateTime.now(),
                requestDto.getTaskType(),
                user);
        return converter.convert(toDoRepository.save(task));
    }

    @Override
    public void deleteToDo(String id) {
        final ToDo task = findToDoById(id);
        toDoRepository.deleteById(task.getId());
    }
}
