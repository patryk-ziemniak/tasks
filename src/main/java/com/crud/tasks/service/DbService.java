package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    private final TaskRepository repository;

    public DbService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(long taskId) {
        return repository.findById(taskId).orElse(new Task(taskId, "No such taskId", "ERROR!"));
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTask(long taskId) {
        repository.deleteById(taskId);
    }
}
