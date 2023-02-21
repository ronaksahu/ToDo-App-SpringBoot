package org.example;

import org.example.dto.CreateToDoRequestDto;
import org.example.model.ToDo;
import org.example.model.ToDoType;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.Set;

public class TestSupport {

    public User generateUser() {
        return new User("userId",
                "username",
                "password",
                Set.of());
    }

    public ToDo generateTask(String id, String header, String description, User user) {
        return new ToDo(id,
                "taskHeader",
                "taskDescription",
                LocalDateTime.now(),
                ToDoType.TODO,
                user);
    }

    public CreateToDoRequestDto generateRequestDto() {
        return new CreateToDoRequestDto("userId",
                "taskHeader",
                "taskDescription");
    }

}
