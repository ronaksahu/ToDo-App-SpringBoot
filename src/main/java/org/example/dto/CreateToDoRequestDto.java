package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class CreateToDoRequestDto {

    @NotBlank(message = "userId can not be blank!")
    private String userId;
    @NotBlank(message = "task header can not be blank!")
    private String taskHeader;
    private String taskDescription;
}
