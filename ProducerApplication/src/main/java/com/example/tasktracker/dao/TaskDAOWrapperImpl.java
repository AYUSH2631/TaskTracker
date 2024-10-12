package com.example.tasktracker.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tasktracker.beans.TaskBean;
import com.example.tasktracker.entity.TaskEntity;
@Repository
public class TaskDAOWrapperImpl {
	
	@Autowired
	private TaskDAO taskdao;
	
	public Collection<TaskBean>getAllTask(){
		Collection<TaskEntity>task=(Collection<TaskEntity>) taskdao.findAll();
		List<TaskBean>list=new ArrayList();
		for(TaskEntity entity:task) {
			TaskBean bean=new TaskBean();
			BeanUtils.copyProperties(entity, bean);
			list.add(bean);
		}
		return list;
		
		
	}
	
	public TaskBean addTask(TaskBean bean) {
		TaskEntity entity=new TaskEntity();
		BeanUtils.copyProperties(bean, entity);
		entity=taskdao.save(entity);
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}
	
	public Optional<TaskBean> deleteTask(int id) {
		Optional<TaskEntity> entity=taskdao.findById(id);
		
		if(entity.isPresent())
		{
			TaskEntity ent=entity.get();
			taskdao.delete(ent);
			TaskBean bean=new TaskBean();
			BeanUtils.copyProperties(ent, bean);
			
			System.out.println("...."+bean);
			return Optional.of(bean);
			
		}
		else
			return Optional.empty();
			
	}
	
	public List<TaskBean>getTaskByPriority(String priority){
		
		List<TaskEntity>tasks=taskdao.gettaskByPriority(priority);
		List<TaskBean>list=new ArrayList();
		
		if(!tasks.isEmpty()) {
			for(TaskEntity entity:tasks) {
				//TaskEntity ent=(TaskEntity) entity.get();
				TaskBean bean=new TaskBean();
				BeanUtils.copyProperties(entity, bean);
				list.add(bean);
			}
			return list;
		}
		else
			return list;
		
	}
	
	public TaskBean getTaskById(Integer id) {
		Optional<TaskEntity> entity=taskdao.findById(id);
		if(entity.isPresent()) {
		TaskEntity ent=entity.get();
		TaskBean bean=new TaskBean();
		BeanUtils.copyProperties(ent, bean);
		return bean;
		}
		else
			return null;
	}
	
	public TaskBean updateTask(TaskBean bean) {
		Optional<TaskEntity>entity=taskdao.findById(bean.getTaskId());
		if(entity.isPresent()) {
			TaskEntity ent=new TaskEntity();
			BeanUtils.copyProperties(bean, ent);;
			TaskEntity updated=taskdao.save(ent);
			BeanUtils.copyProperties(updated,bean);
			return bean;
		}
		else
			return null;
	}

}
