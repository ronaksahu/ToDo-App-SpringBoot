package org.example.service;

import org.example.TestSupport;
import org.example.dto.CreateToDoRequestDto;
import org.example.dto.ToDoDto;
import org.example.dto.converter.ToDoDtoConverter;
import org.example.exception.ToDoNotFoundException;
import org.example.model.ToDo;
import org.example.model.ToDoType;
import org.example.model.User;
import org.example.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ToDoServiceTest extends TestSupport {

    private ToDoRepository toDoRepository;
    private UserService userService;
    private ToDoDtoConverter converter;

    private ToDoService toDoService;

    @BeforeEach
    public void setup() {
        toDoService = mock(ToDoService.class);
        userService = mock(UserService.class);
        converter = mock(ToDoDtoConverter.class);

        toDoService = new ToDoServiceImpl(toDoRepository, userService, converter);
    }

    @Test
    public void testFindByTaskId_whenTaskIdExists_shouldReturnTask() {
        ToDo task = generateTask("id", "header", "description", new User());

        Mockito.when(toDoRepository.findById(task.getId()))
                .thenReturn(Optional.of(task));

        ToDo result = toDoService.findToDoById(task.getId());
        assertEquals(result, task);

    }

    @Test
    public void testFindByTaskId_whenTaskIdDoesNotExists_shouldThrowTaskNotFoundException() {
        Mockito.when(toDoRepository.findById("id"))
                .thenReturn(Optional.empty());

        assertThrows(ToDoNotFoundException.class,
                () -> toDoService.findToDoById("id"));
    }

    @Test
    public void testCreateTask_whenUserIdExist_shouldCreateTask() {
        CreateToDoRequestDto requestDto = generateRequestDto();
        User user = generateUser();
        ToDo task = generateTask("","taskHeader", "taskDescription", user);
        ToDoDto expected = new ToDoDto("taskId",
                "taskHeader",
                "taskDescription",
                LocalDateTime.now(),
                ToDoType.TODO);

        Mockito.when(userService.findUserById(requestDto.getUserId())).thenReturn(user);
        Mockito.when(toDoRepository.save(task)).thenReturn(task);
        Mockito.when(converter.convert(task)).thenReturn(expected);

        ToDoDto result = toDoService.createToDo(requestDto);

    }

}
