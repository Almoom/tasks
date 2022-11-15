package com.moc.tasks.controller;

import com.moc.tasks.model.TaskEntity;
import com.moc.tasks.model.UserEntity;
import com.moc.tasks.repository.TaskRepository;
import com.moc.tasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserEntity create(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @GetMapping("/users/me")
    public UserEntity getCurrentUser() {
        return userService.getCurrentUser();
    }

//    curl --header "Content-Type: application/json" -X POST  -d '{"date":"2022-11-14","description":"xyz"}' http://localhost:8080/tasks

    @PostMapping("/tasks")
    public TaskEntity create(@RequestBody TaskEntity task) {
        task.setUserId(userService.getCurrentUser().getId());
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<TaskEntity> getAll() {
        return taskRepository.findAllByUserId(userService.getCurrentUser().getId()).orElse(null);
    }

    @GetMapping("/tasks/{id}")
    public TaskEntity getById(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @PutMapping("/tasks/{id}")
    public TaskEntity update(@RequestBody TaskEntity task, @PathVariable long id) {
        task.setId(id);
        task.setDate(LocalDate.now());
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable Long id){
        taskRepository.markAsDone(id);
    }

}
