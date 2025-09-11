package com.nalandavictoria.todosimple.service;

import com.nalandavictoria.todosimple.model.TaskModel;
import com.nalandavictoria.todosimple.model.UserModel;
import com.nalandavictoria.todosimple.repository.TaskRepository;
import com.nalandavictoria.todosimple.rest.dto.TasksDTO;
import com.nalandavictoria.todosimple.rest.dto.UserDTO;
import com.nalandavictoria.todosimple.service.exceptions.DataBindingViolationException;
import com.nalandavictoria.todosimple.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TasksDTO findById(Long id){
        TaskModel taskModel = taskRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Tarefa não encontrada."));
        return new TasksDTO(taskModel);
    }

    public List<TasksDTO> findAllByUserID (Long userID){
        userService.findById(userID);
        return taskRepository.findAllByUserID(userID);
    }

    @Transactional
    public TasksDTO create(TaskModel taskModel){
        UserModel user = userService.findEntityById(taskModel.getUser().getId());
        taskModel.setId(null);
        taskModel.setUser(user);
        TaskModel saved = taskRepository.save(taskModel);
        return new TasksDTO(saved);
    }

    @Transactional
    public TasksDTO update(TaskModel taskModel) {
        TaskModel task = taskRepository.findById(taskModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada."));
        task.setDescription(taskModel.getDescription());
        TaskModel newTask = taskRepository.save(task);
        return new TasksDTO(newTask);
    }

    @Transactional
    public void delete (Long id){
        findById(id);
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Não foi possível excluir a tarefa, , pois há entidades relacionadas.");
        }
    }
}