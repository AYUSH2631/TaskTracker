package com.example.tasktracker.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.tasktracker.entity.TaskEntity;

public interface TaskDAO extends CrudRepository<TaskEntity,Integer>{
	
	@Query("select s from TaskEntity s where s.priority=?1")
	List<TaskEntity> gettaskByPriority(String priority);

}
