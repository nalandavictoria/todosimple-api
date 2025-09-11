package com.nalandavictoria.todosimple.rest.controller;

import com.nalandavictoria.todosimple.model.TaskModel;
import com.nalandavictoria.todosimple.rest.dto.TasksDTO;
import com.nalandavictoria.todosimple.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<TasksDTO> findById (@PathVariable Long id){
        TasksDTO tasksDTO = taskService.findById(id);
        return ResponseEntity.ok(tasksDTO);
    }

    @PostMapping()
    @Validated
    public ResponseEntity<TasksDTO> create (@Valid @RequestBody TaskModel taskModel){
        TasksDTO newTask = taskService.create(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<TasksDTO> update (@Valid @RequestBody TaskModel taskModel, @PathVariable Long id){
        TasksDTO updateTask = taskService.update(taskModel);
        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<TasksDTO>> findAllByUserID (@PathVariable Long userID){
        List<TasksDTO> findAllByUserID = taskService.findAllByUserID(userID);
        return ResponseEntity.ok(findAllByUserID);
    }
}
