package com.javarush.repository;

import com.javarush.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {
   List<Task> findAll (Pageable pageable);

}
