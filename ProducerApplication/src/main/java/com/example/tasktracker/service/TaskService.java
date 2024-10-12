package com.example.tasktracker.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.tasktracker.beans.TaskBean;

public interface TaskService {
	
	Collection<TaskBean>getAllTask();
	TaskBean addTask(TaskBean bean);
	Optional<TaskBean>deleteTask(Integer id);
	List<TaskBean>getTaskByPriority(String priority);

}
