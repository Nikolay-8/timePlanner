package com.timePlanner.services;

import com.timePlanner.dao.TaskDao;
import com.timePlanner.models.Tasks;

import java.util.List;

public class TaskServices {
    public TaskServices() {}

    TaskDao taskDao = new TaskDao();

    public void save(Tasks task) {
        taskDao.save(task);
    }

    public void update(Tasks task) {
        taskDao.update(task);
    }

    public void delete(Tasks task) {
        taskDao.delete(task);
    }

    public Tasks findById(int id) {
        return taskDao.findById(id);
    }

    public List<Tasks> findAll() {
        return taskDao.findAll();
    }
}
