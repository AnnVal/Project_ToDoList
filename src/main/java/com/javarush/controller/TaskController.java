package com.javarush.controller;

import com.javarush.entity.Task;
import com.javarush.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {

        this.taskService = taskService;
    }

    @GetMapping("/")
    public String tasks(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize){
        List<Task> tasks = taskService.getAll(page,pageSize);
        model.addAttribute("tasks",tasks);
        model.addAttribute("current_page",page);
        int totalPages=(int) Math.ceil(1.0*taskService.getAllCount()/pageSize);
        if(totalPages>1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("page_numbers",pageNumbers);
        }

        return "tasks";
    }

    @PostMapping("/{id}")
    public String edit(Model model,
                       @PathVariable Integer id,
                       @RequestBody TaskDTO dto){

        Task task = taskService.edit(id, dto.getDescription(),dto.getStatus());
        return "redirect:tasks";
    }

    @PostMapping("/")
    public String add(Model model,
                      @RequestBody TaskDTO dto){
        Task task = taskService.create(dto.getDescription(),dto.getStatus());
        return "redirect:tasks";
    }

    @DeleteMapping("/{id}")
    public String delete (Model model,
                          @PathVariable Integer id){

        taskService.delete(id);
        return "redirect:tasks";
    }



}
