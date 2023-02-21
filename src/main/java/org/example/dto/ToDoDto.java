package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.ToDoType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDoDto {

    private String id;
    private String header;
    private String description;
    private LocalDateTime transactionDate;
    private ToDoType taskType;
}
