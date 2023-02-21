package org.example.dto.converter;

import org.example.dto.UserDto;
import org.example.dto.UserViewDto;
import org.example.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final ToDoDtoConverter taskDtoConverter;

    public UserDtoConverter(ToDoDtoConverter taskDtoConverter) {
        this.taskDtoConverter = taskDtoConverter;
    }

    public UserViewDto convertToViewDto(User from) {
        return new UserViewDto(from.getId(),
                from.getUsername(),
                from.getPassword()
        );
    }

    public UserDto convert(User from) {
        return new UserDto(from.getId(),
                from.getUsername(),
                from.getPassword(),
                from.getTasks()
                        .stream()
                        .map(taskDtoConverter::convert)
                        .collect(Collectors.toSet())
        );
    }
}
