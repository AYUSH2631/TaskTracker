package com.example.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.tasktracker.beans.TaskBean;
import com.example.tasktracker.dao.TaskDAOWrapperImpl;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	public TaskDAOWrapperImpl taskserviceimpl;
	
	
	public Collection<TaskBean>getAllTask(){
		
		return taskserviceimpl.getAllTask();
		
	}
	
	public TaskBean addTask(TaskBean bean) {
		
		TaskBean response=taskserviceimpl.addTask(bean);
		return response;
	}
	
	public Optional<TaskBean> deleteTask(Integer id) {
		return taskserviceimpl.deleteTask(id);
	}
	
	public List<TaskBean>getTaskByPriority(String priority){
		return taskserviceimpl.getTaskByPriority(priority);
	}
	


}
