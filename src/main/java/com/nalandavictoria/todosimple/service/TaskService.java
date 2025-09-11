package com.nalandavictoria.todosimple.service;

import com.nalandavictoria.todosimple.model.TaskModel;
import com.nalandavictoria.todosimple.model.UserModel;
import com.nalandavictoria.todosimple.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskModel findById(Long id){
        return taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Tarefa não encontrada."));
    }

    @Transactional
    public TaskModel create(TaskModel taskModel){
        UserModel user = userService.findById(taskModel.getUser().getId());
        taskModel.setId(null);
        taskModel.setUser(user);
        return taskRepository.save(taskModel);
    }

    @Transactional
    public TaskModel update(TaskModel taskModel) {
        TaskModel newTask = findById(taskModel.getId());
        newTask.setDescription(taskModel.getDescription());
        return taskRepository.save(newTask);
    }

    @Transactional
    public void delete (Long id){
        findById(id);
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir a tarefa.");
        }
    }
}