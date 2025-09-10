package com.nalandavictoria.todosimple.rest.dto;

import com.nalandavictoria.todosimple.model.TaskModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private List<TaskModel> tasks = new ArrayList<TaskModel>();
}
