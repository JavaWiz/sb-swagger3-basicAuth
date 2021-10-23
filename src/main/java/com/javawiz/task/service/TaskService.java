package com.javawiz.task.service;

import com.javawiz.task.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TaskService {

    private static final Map<Long, Task> tasks = new HashMap<>();

    public Task findById(long id) {
        return tasks.get(id);
    }

    public List<Task> findAll(){
        return new ArrayList<>(tasks.values());
    }
    
    public Task save(Task task) {
        if (!tasks.containsKey(task.getId())) { //save
            return tasks.put(task.getId(), Task.builder()
                    .id(task.getId())
                    .description(task.getDescription()).build());
        } else {//update operation
            return tasks.replace(task.getId(), task);
        }
    }

    public void delete(Task existingTask) {
        tasks.remove(existingTask.getId());
    }
}
