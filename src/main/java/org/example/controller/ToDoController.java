package org.example.controller;

import org.example.dto.CreateToDoRequestDto;
import org.example.dto.ToDoDto;
import org.example.dto.UpdateToDoRequestDto;
import org.example.model.ToDo;
import org.example.service.ToDoService;
import org.example.service.ToDoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class ToDoController {

    private final ToDoService todoService;

    public ToDoController(ToDoServiceImpl taskManager) {
        this.todoService = taskManager;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Set<ToDoDto>> getTasksByUserId(
            @PathVariable String id)
    {
        return ResponseEntity.ok(todoService.getToDosByUserId(id));
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<ToDoDto> getTaskById(
            @PathVariable String id)
    {
        return ResponseEntity.ok(todoService.getToDoById(id));
    }

    @PostMapping("/task")
    public ResponseEntity<ToDoDto> createTask(
            @Valid @RequestBody CreateToDoRequestDto requestDto)
    {
        return ResponseEntity.ok(todoService.createToDo(requestDto));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<ToDoDto> updateTask(
            @PathVariable String id,
            @Valid @RequestBody UpdateToDoRequestDto requestDto)
    {
        return ResponseEntity.ok(todoService.updateToDo(id, requestDto));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
        todoService.deleteToDo(id);
        return ResponseEntity.ok("task deleted");
    }



}
