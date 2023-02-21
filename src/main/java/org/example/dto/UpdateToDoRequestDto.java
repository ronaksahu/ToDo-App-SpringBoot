package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.ToDoType;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class UpdateToDoRequestDto {

    @NotBlank(message = "userId can not be blank!")
    private String userId;
    @NotBlank(message = "task header can not be blank!")
    private String taskHeader;
    private String taskDescription;
    private ToDoType taskType;
}
