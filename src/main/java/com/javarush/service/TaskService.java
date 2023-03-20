package com.javarush.service;

import com.javarush.entity.Status;
import com.javarush.entity.Task;
import com.javarush.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
 public List<Task> getAll(int pageNumber, int pageSize);
 public int getAllCount();
 public Task edit(int id, String descrition, Status status );
 public Task create(String descrition, Status status);
 public void delete(int id);


}
