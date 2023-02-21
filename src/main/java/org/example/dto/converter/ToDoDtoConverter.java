package org.example.dto.converter;

import org.example.dto.ToDoDto;
import org.example.model.ToDo;
import org.springframework.stereotype.Component;

@Component
public class ToDoDtoConverter {

    private final ToDoUserDtoConverter converter;

    public ToDoDtoConverter(ToDoUserDtoConverter converter) {
        this.converter = converter;
    }

    public ToDoDto convert(ToDo from) {
        return new ToDoDto(from.getId(),
                from.getHeader(),
                from.getDescription(),
                from.getTimestamp(),
                from.getTaskType()
//                from.getCategory()
        );
    }

}
