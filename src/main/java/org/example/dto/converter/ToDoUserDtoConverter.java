package org.example.dto.converter;

import org.example.dto.ToDoUserDto;
import org.example.model.User;
import org.springframework.stereotype.Component;

@Component
public class ToDoUserDtoConverter {

    public ToDoUserDto convertToTaskUserDto(User from) {
        return new ToDoUserDto(from.getId(),
                from.getUsername(),
                from.getPassword()
        );
    }
}
