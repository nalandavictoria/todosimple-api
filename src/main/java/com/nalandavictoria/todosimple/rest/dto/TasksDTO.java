package com.nalandavictoria.todosimple.rest.dto;

import com.nalandavictoria.todosimple.model.UserModel;
import lombok.Data;

@Data
public class TasksDTO {
    private Long id;
    private String description;
    private UserModel user;
}
