package com.javarush.service;

import com.javarush.entity.Status;
import com.javarush.entity.Task;
import com.javarush.exception.TaskNotFoundException;
import com.javarush.repository.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll(int pageNumber, int pageSize) {
        Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize);
        return taskRepository.findAll(pageable);
    }

    public int getAllCount() {
        return (int) taskRepository.count();
    }

    public Task edit(int id, String description, Status status) {
        if (taskRepository.existsById(id)) {
            Task task = taskRepository.findById(id).get();
            task.setDescription(description);
            task.setStatus(status);
            return taskRepository.save(task);
        } else
            throw new TaskNotFoundException("Task with id" + id + "wasn't found");
    }

    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        return taskRepository.save(task);

    }

    public void delete(int id) {
        if (taskRepository.existsById(id))
            taskRepository.deleteById(id);
        else
            throw new TaskNotFoundException("Task with id" + id + "wasn't found");
    }
}
