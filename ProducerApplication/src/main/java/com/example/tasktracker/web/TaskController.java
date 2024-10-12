package com.example.tasktracker.web;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.tasktracker.beans.TaskBean;
import com.example.tasktracker.exception.TaskNotFoundException;
import com.example.tasktracker.service.TaskService;

import jakarta.validation.Valid;

@RestController
public class TaskController {
	
	@Autowired
	public TaskService taskservice;
	
	@RequestMapping(value="/getAllTask",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<TaskBean>>getAllTask(){
		try {
		Collection<TaskBean>response=taskservice.getAllTask();
		return new ResponseEntity<Collection<TaskBean>>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/addTask",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>addTask(@Valid @RequestBody TaskBean bean,BindingResult result){
		 
		if(result.hasErrors())
			return ResponseEntity.badRequest().body(result.getAllErrors());
		try {
			TaskBean response=taskservice.addTask(bean);
			return new ResponseEntity<TaskBean>(response,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/deleteTask/{id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>deleteTask(@PathVariable("id")int id){
		
		Optional<TaskBean>response=taskservice.deleteTask(id);
		System.out.println(response);
		if(response.isPresent())
			return new ResponseEntity<String>("Task "+response.get().getTaskId()+" Deleted Sucessfully!!",HttpStatus.OK);
		else {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
		
	}
	@RequestMapping(value="/getTaskByPriority/{priority}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>getTaskByPriority(@PathVariable ("priority") String priority){
		
		List<TaskBean>list=taskservice.getTaskByPriority(priority);
		if(list.isEmpty()) {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
		else {
			return new ResponseEntity<List<TaskBean>>(list,HttpStatus.OK);
		}
		
	}
	@RequestMapping(value="/getTaskById/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>getTaskById(@PathVariable ("id")int id){
		TaskBean bean=taskservice.getTaskById(id);
		if(bean==null)
			return (ResponseEntity<?>) ResponseEntity.notFound();
		else
			return new ResponseEntity<TaskBean>(bean,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateTask",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>updatetask(@Valid @RequestBody TaskBean bean,BindingResult result){
		
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());

		}
		try {
			TaskBean response=taskservice.updateTask(bean);
			return new ResponseEntity<TaskBean>(response,HttpStatus.CREATED);
		}
		catch (TaskNotFoundException e) { 
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with the given ID.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }
	}

}
